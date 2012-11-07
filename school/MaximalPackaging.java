/*
 * Author: Mauricio Zepeda, mzepedas@fit.edu
 * Course: CSE 2010, Section 01, Fall 2008
 * Project: lab09
 */

import java.util.ArrayList;
import java.util.Arrays;

public class MaximalPackaging extends Packaging {

    public class SingleItem {
	final Product product;
	final int productType;
	
	public SingleItem(int productType, Product product) {
	    this.productType = productType;
	    this.product = product;
	}

	public String toString() {
	    return productType + "";
	}
    }

    public class Result {
	public final int value;
	public final int index;

	public Result(int value, int index) {
	    this.value = value;
	    this.index = index;
	}
    }

    Result[][][] memo;

    private SingleItem[] allItems;
    
    public MaximalPackaging(Order[] sale) {
	super(sale);
	allItems = flatten(sale);
    }

    /*
     * In order to treat it as a 0-1 Knapsack 
     */
    @Complexity (
         basic_operation = "add", 
         N = "Total number of items", 
         number_of_steps = "N",
         big_O = "N"
    )
    public SingleItem[] flatten(Order[] sale) {
	ArrayList<SingleItem> allItems = new ArrayList<SingleItem>();
	
	for (int m = 0; m < sale.length; m++) {
	    for (int j = 0; j < sale[m].quantity; j++) {
		allItems.add(new SingleItem(m, sale[m].product));
	    }
	}
	
	return allItems.toArray(new SingleItem[0]);
    }

    public Trip[] getTrips (int truck_max_volume, int truck_max_weight) throws IllegalArgumentException {
	ArrayList<Trip> trips = new ArrayList<Trip>();
	memo = new Result[truck_max_volume + 1][truck_max_weight + 1][(1 << allItems.length)];

	SingleItem[] tempAllItems = allItems.clone();

	// Keep subtracting until AllItems is empty
	while (!arrayIsNull(tempAllItems)) { 
	    Result result = knapsack(tempAllItems.length - 1, truck_max_volume, 
				     truck_max_weight, tempAllItems);  
	    trips.add(buildTrip(tempAllItems, truck_max_volume, truck_max_weight)); // Assuming temp has the optimal packaging
	    subtract(tempAllItems, truck_max_volume, truck_max_weight);
	}

	return trips.toArray(new Trip[0]);
    }

    private boolean arrayIsNull(SingleItem[] array) {
	for (SingleItem item : array) {
	    if (item != null) return false;
	}
	
	return true;
    }

    /*
     * Builds a trip based on the itemsUsed, assumes the list
     * is flattened.
     */
    private Trip buildTrip(SingleItem[] items, int volume, int weight) {
	Trip result = new Trip();
	int[] howMany = new int[result.quantity.length];
	int mask = bitmask(items);

	while (volume > 0 && weight > 0  && memo[volume][weight][mask] != null && memo[volume][weight][mask].index != -1) {
	    Result res = memo[volume][weight][mask];
	    int item = res.index;
	   
	    howMany[items[item].productType]++;
	           
	    volume -= items[item].product.volume;
	    weight -= items[item].product.weight;
	    mask -= (1 << item);
	}

	for (int j = 0; j < howMany.length; j++) {
	    result.quantity[j] = howMany[j];
	}

	return result;
    }

    /*
     * Marks allItems[i] as null if itemsUsed[i] = true;
     */
    private void subtract(SingleItem[] items, int volume, int weight) {
	
	int mask = bitmask(items);

	while (volume > 0 && weight > 0 && memo[volume][weight][mask] != null && memo[volume][weight][mask].index != -1) {
	    Result res = memo[volume][weight][mask];
	    int item = res.index;
	    
	    volume -= items[item].product.volume;
	    weight -= items[item].product.weight;
	    mask -= (1 << item);
	    items[item] = null;
	}

    }
    
    
    /*
     * Finds the most optimal set to maximize value
     */
    @Complexity (
         basic_operation = "knapsack()", 
         N = "i", 
         number_of_steps = "n*weight*volume",
         big_O = "n*weight*volume"
    )
    private Result knapsack(int i,  int volume, int weight, SingleItem[] items) {
	
	// Base Case
	if ( i < 0 || volume <= 0 || weight <= 0) {
	    return new Result(0, -1);
	}

	// Return the value if we already have it.
	int mask = bitmask(items);	
	if (memo[volume][weight][mask] != null) {
	    return memo[volume][weight][mask];
	}
	
	// If there's no such item in the list, skip it.
	if (items[i] == null) {
	    return memo[volume][weight][mask] = knapsack(i - 1, volume, weight, items);
	}

	Product currentProduct = items[i].product;

	// Don't include it if it exceeds the bounds.
	if (currentProduct.volume > volume ||
	    currentProduct.weight > weight) {
	    return memo[volume][weight][mask] = knapsack(i - 1, volume, weight, items);
	}
	
	SingleItem[] clone = items.clone();
	clone[i] = null;

	Result includingCurrent = new Result(knapsack(i - 1, volume - currentProduct.volume,
						      weight - currentProduct.weight, 
						      clone).value + currentProduct.value, i);
	
	Result notIncludingCurrent = knapsack(i - 1, volume, weight, items);

	// Include it
	if (includingCurrent.value > notIncludingCurrent.value) {
	    return memo[volume][weight][mask] = includingCurrent;
   	}

	// Don't include it
	return memo[volume][weight][mask] = notIncludingCurrent;
    }
    
    @Complexity (
         basic_operation = "assignment", 
         N = "array.length", 
         number_of_steps = "N",
         big_O = "N"
    )
    private int bitmask(SingleItem[] array) {
	int result = 0;
	for (int j = 0; j < array.length; j++) {
	    if (array[j] != null) {
		result = result | (1 << j );
	    }
	}

	return result;
    }
}
