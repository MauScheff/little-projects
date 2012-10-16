#!/usr/bin/python
""" Finds perfect maximum matchings in a weigthed complete bipartite graph using the Kuhn-Munkres (Hungarian) algorithm.
    Calculates matrix costs according to Engineering Problem #2 specifications.
    External Resources:
    http://www.topcoder.com/tc?module=Static&d1=tutorials&d2=hungarianAlgorithm
    http://en.wikipedia.org/wiki/Hungarian_algorithm
"""

import sys

def improve_matching(product):
    customer = parent_tree[product]
    if customer in matchings_customers:
        improve_matching(matchings_customers[customer])
    matchings_customers[customer] = product
    matchings_products[product] = customer

def improve_labels(slacked_value):
    for vertex in covered_tree:
        labels_customers[vertex] -= slacked_value
    for product in products:
        if product in parent_tree:
            labels_products[product] += slacked_value
        else:
            minimum_slack[product][0] -= slacked_value
    
def augment():
    while True:
        ((slacked_value, customer), product) = min([(minimum_slack[product], product) for product in products if product not in parent_tree])
        if slacked_value > 0:
            improve_labels(slacked_value)
        parent_tree[product] = customer
        if product in matchings_products:
            matched_customer = matchings_products[product]
            covered_tree[matched_customer] = True
            for product in products:
                if not product in parent_tree and minimum_slack[product][0] > slack(matched_customer, product):
                    minimum_slack[product] = [slack(matched_customer, product), matched_customer]
        else:
            improve_matching(product)
            return

def slack(customer, product):
    return labels_customers[customer] + labels_products[product] - weights[customer][product]

def hungarian(costs):
    global customers, products, covered_tree, parent_tree, matchings_customers, matchings_products, labels_customers, labels_products, minimum_slack, weights
    weights = costs
    n = len(weights)
    customers = products = range(n)
    labels_customers = [max([weights[customer][product] for product in products]) for customer in customers]
    labels_products = [0 for product in products]
    matchings_customers = {}
    matchings_products = {}
    while len(matchings_customers) < n:
        free_vertices = [customer for customer in customers if customer not in matchings_customers]
        free_vertex = free_vertices[0]
        covered_tree = {free_vertex: True}
        parent_tree = {}
        minimum_slack = [[slack(free_vertex, product), free_vertex] for product in products]
        augment()
    total_SS = sum(labels_customers) + sum(labels_products)
    return (matchings_customers, total_SS)

""" If the cost matrix is of size m x n,
    this function pads it with 0's until it
    is of size n x n. Because the hungarian algorithm
    works on squared matrices.
"""
def fix_matrix(matrix):
    if len(matrix) > len(matrix[0]):
        for i in range(len(matrix)):
            matrix[i].append(0)
        return fix_matrix(matrix)
    elif len(matrix) < len(matrix[0]):
        matrix.append([0 for i in range(len(matrix[0]))])
        return fix_matrix(matrix)
    return matrix
                      
def vowels(customer):
    count = 0
    for letter in customer:
        if letter in "aeiou":
            count += 1
    return count

def consonants(customer):
    count = 0
    for letter in customer:
        if letter not in "aeiou ":
            count += 1
    return count

def cost(customer, product):
    cost = 0
    if len(product) % 2 == 0:
        cost = vowels(customer.lower()) * 1.5
    else:
        cost = consonants(customer.lower())
    if gcd (len(product), len(customer)) > 1:
        cost += cost * 0.5
    return cost

def gcd(a, b):
    if b == 0:
        return a
    return gcd(b, a % b)

def main():
    customers = []
    products  = []
    filename  = sys.stdin.readlines()
    for line in open(filename[0].rstrip("\n"), 'r'):
        product = line.rstrip("\n")
        products.append(product)
    for line in open(filename[1].rstrip("\n"), 'r'):
        customer = line.rstrip("\n")
        customers.append(customer)
    
    costs = fix_matrix([[cost(customer, product) for product in products] for customer in customers])
    (matchings_customers, total_SS) = hungarian(costs)
    print "\nTotal Suitability Score = " + str(total_SS) + "\n\nMatchings from Customer to Product:"
    for x, y in matchings_customers.iteritems():
        if x < len(customers) and y < len(products):
            print customers[x] + " -> " + products[y]

if __name__ == "__main__":
    main()
