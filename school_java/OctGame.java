/**
 * Author: Mauricio Zepeda, mzepedas@fit.edu
 * Course: CSE 2010, Section 01, Fall 2008
 * Project: lab02
 * Algorithm Implemented: Quick Union
 */

import java.util.*;

public class OctGame implements Connective {

    private int[] id;
    private int[] board;
    private int boardWidth;
    private byte player = -1;
    
    /**
     * Returns the name of the algorithm you will be using to solve this assignment.
     * @return The name of the algorithm used to solve the connective problem
     */
    public String algorithm(){
	return "Quick Union With Path Compression";
    }

    /**
     * Reset the game board to allow a new game to be played
     * @param height A positive integer specifying the height of the board
     * @param width  A positive integer specifying the width of the board
     * @throws IllegalArgumentException if the height or width is invalid
     */
    public void reset(int height, int width) throws IllegalArgumentException{
	
	if (height < 0 || width < 0) throw new IllegalArgumentException();
       
	this.boardWidth = width;

	/*
	 * Reset "id" array
	 */
	id = new int[height * width];
	for (int j = 0; j < id.length; j++) {
	    id[j] = j;
	}

	/*
	 * Reset "board" array
	 */
	board = new int[height * width];
	Arrays.fill(board, 0);
    }
 
    
    /**
     * Adds a move to the board. The move being added is determined by
     * the value of side.
     * @param x A non-negative integer between 0 and height determining the row of the next move
     * @param y A non-negative integer between 0 and width determining the column of the next move
     * @param side -1 for Red, 1 for Blue
     * @throws IllegalArgumentException if x or y is invalid.
     */
    public void addMove(int x, int y, int side) throws IllegalArgumentException{
	if (x < 0 || y < 0) throw new IllegalArgumentException();
	
	int index = to1Dimension(x, y);
	
	/*
	 * Add to "board" array
	 */
	if (board[index] != 0) throw new IllegalArgumentException();
	board[index] = side;

	/*
	 * Add to "id" array
	 */
	int p = root(index);
	Vector<Integer> adjacent = getAdjacentPositions(side, index);
	for (int i = 0; i < adjacent.size() ; i++){
	    int q = root(adjacent.get(i));
	    id[p] = q;
	}
    }
    
    /**
     * Adds a move to the board. The move being added alternates in side, with Red always making the first move.
     * @param x A non-negative integer between 0 and height determining the row of the next move
     * @param y A non-negative integer between 0 and width determining the column of the next move
     * @throws IllegalArgumentException if x or y is invalid.
     */
    public void addMove(int x, int y) throws IllegalArgumentException {
	addMove(x, y, player);

	/*
	 * Toggle Player's Turn
	 */
	if (player == -1) player = 1;
	else player = -1;
    }
    
    /**
     * Prints the board to standard output. The board should print each position's internal ID and each position's color
     */
    public void printBoard(){

	int counter = 1;
	System.out.println("");
	
	/*
	 * Print Id Board
	 */
	for (int j = 0; j < id.length; j++, ++counter) {
	    String string = Integer.toString(id[j]);
	    if (id[j] < 10) string = " " + string;

	    if (counter == boardWidth) {
		string += "\n";
		counter = 0;
	    } else {
		string += " ";
	    }
	    System.out.print(string);
	}	
	
	counter = 1;
	System.out.print("\n ");
	
	/*
	 * Print Color Board
	 */
	for (int j = 0; j < board.length; j++, ++counter) {
	    String string = getPositionColor(board[j]);
	    if (counter == boardWidth) {
		string += "\n ";
		counter = 0;
	    } else {
		string += "  ";
	    }
	    System.out.print(string);
	}
	System.out.println("");
    }
    
    /**
     * Converts numerical value to color letter.
     */
    private String getPositionColor(int value){
	if (value == -1) return "R";
	else if (value == 1) return "B";
	else return "_";
    }
        
    /**
     * Returns the value of the winner.
     * @return 0 if there is no winner, -1 if Red wins, 1 if Blue wins.
     */
    public int winner(){
	
	/*
	 * Check Red
	 */
	for (int j = 0; j < this.boardWidth; j++) {
	    if (board[j] == -1) {
		for (int k = board.length - 1; k > (board.length - 1) - this.boardWidth; k--) {
		    if (board[k] == -1) {
			if (root(j) == root(k)) return -1; 
		    }
		}
	    }
	}

	/*
	 * Check Blue
	 */
	for (int j = 0; j < board.length; j += this.boardWidth) {
	    if (board[j] == 1) {
		for (int k = board.length - 1; k > 0 ; k -= this.boardWidth) {
		    if (board[k] == 1) {
			if (root(j) == root (k)) return 1;
		    }
		}
	    }
	}

	return 0;
    }
    
    private int root(int index){
	while (index != id[index]){
	    
	    /*
	     * Path compression to keep tree flat, 
	     * makes every visited node point to 
	     * it's grandparent.
	     */
	    id[index] = id[id[index]];
	    
	    index = id[index];
	}
	return index;
    }

    /**
     * @param y The Row
     * @param x The Column
     */
    private int to1Dimension(int y, int x){
	return y * boardWidth + x;
    }

    private Vector<Integer> getAdjacentPositions(int player, int position){

	Vector<Integer> results =  new Vector<Integer>();
	
	/*
	 * Calculate 1d Positions
	 */
	int left1d = position - 1;
	int right1d = position + 1;
	int above1d = position - this.boardWidth;
	int below1d = position + this.boardWidth;

	int aboveLeft1d = above1d - 1;
	int aboveRight1d = above1d + 1;
	int belowLeft1d = below1d - 1;
	int belowRight1d = below1d + 1;

	/*
	 * Check boundaries
	 */
	boolean leftFits = position > 0 && position % this.boardWidth != 0;
	boolean rightFits = position > 0 && (position + 1) % this.boardWidth != 0; 
	boolean aboveFits = position > 0 && position > this.boardWidth;
	boolean belowFits = position > 0 && position < (board.length - this.boardWidth);
	
	/*
	 * Add touching positions to list
	 */
	if (aboveFits && leftFits && board[aboveLeft1d] == player) results.add(aboveLeft1d); // Above Left
	if (aboveFits && board[above1d] == player) results.add(above1d); // Above
	if (aboveFits && rightFits && board[aboveRight1d] == player) results.add(aboveRight1d); // Above Right
	if (leftFits && board[left1d] == player) results.add(left1d); // Left
	if (rightFits && board[right1d] == player) results.add(right1d); //Right
	if (belowFits && leftFits && board[belowLeft1d] == player) results.add(belowLeft1d); // Below Left
	if (belowFits && board[below1d] == player) results.add(below1d); // Below Middle
	if (belowFits && rightFits && board[belowRight1d] == player) results.add(belowRight1d); // Below Right

	return results;
    }
}