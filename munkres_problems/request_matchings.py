#! /usr/bin/env python

from sys import maxint
from sys import stdin

from munkres import Munkres

class RequestMatching():

    def __init__(self):
        self.days = 20
        self.cost_matrix = []
        self.categories = {} # maps each category to a list of the names that can provide it.
        self.name_row = {} # maps each name to the indices of the rows it corresponds to.
        self.count = 0
        self.munkres = Munkres()

    def main(self):
        print('--- snip ---')
        for line in stdin:
            self.process(line.rstrip('\n'))
        self.flush() # don't forget to flush :)
        print('--- snip ---')

    def process(self, line):
        words = line.split(' ')

        if words[0] == 'service':
            for word in words[1:]:
                # remember who does what
                self.categories.setdefault(word, []).append(words[1])
            # allocate rows
            self.cost_matrix += [[] for indices in range(self.days)]
            # remember which matrix rows correspond to who
            self.name_row.setdefault(words[1], range(self.count, self.count + self.days))
            self.count = self.count + self.days

        elif words[0] == 'request':
            # fill column
            for row in self.cost_matrix:
                row.append(maxint)

            category, days = words[2], words[3].split("-")
            # fill range
            if len(days) == 1:
                days += days

            for name in self.categories.get(category, []):
                for day in range(int(days[0]), int(days[1]) + 1):
                    # replace last element
                    row = self.cost_matrix[self.name_row[name][day-1]]
                    self.cost_matrix[self.name_row[name][day-1]][len(row)-1] = 1

        elif words[0] == '':
            self.flush()

        else:
            pass

    def flush(self):
        # compute and print total valid assignments using the hungarian algorithm
        self.cost_matrix = self.munkres.pad_matrix(self.cost_matrix, pad_value=maxint)
        matchings = self.munkres.compute(self.cost_matrix)
        print sum(filter(lambda x: x == 1, \
                         [self.cost_matrix[row][col] for row, col in matchings]))
        # reset
        self.categories = {}
        self.cost_matrix = []
        self.name_row = {}
        self.count = 0

if __name__ == "__main__":
    RequestMatching().main()




















