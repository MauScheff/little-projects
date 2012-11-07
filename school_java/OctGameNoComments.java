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
    
    public String algorithm(){
	return "Quick Union With Path Compression";
    }

    public void reset(int height, int width) throws IllegalArgumentException{
	
	if (height < 0 || width < 0) throw new IllegalArgumentException();     
	this.boardWidth = width;
	id = new int[height * width];
	for (int j = 0; j < id.length; j++) {
	    id[j] = j;
	}
	board = new int[height * width];
	Arrays.fill(board, 0);
    }
 
    public void addMove(int x, int y, int side) throws IllegalArgumentException{
	if (x < 0 || y < 0) throw new IllegalArgumentException();
	
	int index = to1Dimension(x, y);

	if (board[index] != 0) throw new IllegalArgumentException();
	board[index] = side;

	int p = root(index);
	Vector<Integer> adjacent = getAdjacentPositions(side, index);
	for (int i = 0; i < adjacent.size() ; i++){
	    int q = root(adjacent.get(i));
	    id[p] = q;
	}
    }
    
    public void addMove(int x, int y) throws IllegalArgumentException {
	addMove(x, y, player);

	if (player == -1) player = 1;
	else player = -1;
    }

    public void printBoard(){

	int counter = 1;
	System.out.println("");

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

    private String getPositionColor(int value){
	if (value == -1) return "R";
	else if (value == 1) return "B";
	else return "_";
    }
        
    public int winner(){
	
	for (int j = 0; j < this.boardWidth; j++) {
	    if (board[j] == -1) {
		for (int k = board.length - 1; k > (board.length - 1) - this.boardWidth; k--) {
		    if (board[k] == -1) {
			if (root(j) == root(k)) return -1; 
		    }
		}
	    }
	}

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

	    id[index] = id[id[index]];
	    
	    index = id[index];
	}
	return index;
    }

    private int to1Dimension(int y, int x){
	return y * boardWidth + x;
    }

    private Vector<Integer> getAdjacentPositions(int player, int position){

	Vector<Integer> results =  new Vector<Integer>();

	int left1d = position - 1;
	int right1d = position + 1;
	int above1d = position - this.boardWidth;
	int below1d = position + this.boardWidth;

	int aboveLeft1d = above1d - 1;
	int aboveRight1d = above1d + 1;
	int belowLeft1d = below1d - 1;
	int belowRight1d = below1d + 1;

	boolean leftFits = position > 0 && position % this.boardWidth != 0;
	boolean rightFits = position > 0 && (position + 1) % this.boardWidth != 0; 
	boolean aboveFits = position > 0 && position > this.boardWidth;
	boolean belowFits = position > 0 && position < (board.length - this.boardWidth);

	if (aboveFits && leftFits && board[aboveLeft1d] == player) results.add(aboveLeft1d);
	if (aboveFits && board[above1d] == player) results.add(above1d);
	if (aboveFits && rightFits && board[aboveRight1d] == player) results.add(aboveRight1d);
	if (leftFits && board[left1d] == player) results.add(left1d);
	if (rightFits && board[right1d] == player) results.add(right1d);
	if (belowFits && leftFits && board[belowLeft1d] == player) results.add(belowLeft1d);
	if (belowFits && board[below1d] == player) results.add(below1d);
	if (belowFits && rightFits && board[belowRight1d] == player) results.add(belowRight1d);

	return results;
    }
}
