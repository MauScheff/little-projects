/*
 * Author: Mauricio Zepeda, mzepedas@fit.edu
 * Course: CSE 2010, Section 01, Fall 2008
 * Project: lab05
 */

public class Minesweep implements Lab05Minesweep {


    @Complexity (
        basic_operation = "Array Access", 
        N = "board.length", 
        number_of_steps = "N^2 + N^2",
        big_O = "N^2"
    )
    public char[][] buildAdjacencies(String[] lines) throws IllegalArgumentException {
	
	if (lines ==  null) throw new IllegalArgumentException();
	
	char[][] board = new char[lines.length][lines[0].length()];
	
	// Fill Array with '0' characters.
	for (int y = 0; y < board.length; y++) {
	    for (int x = 0; x < board[y].length; x++){
		board[y][x] = '0';
	    }
	}
	
	// For each row
	for (int y = 0; y < lines.length; ++y) {
	    char[] chars = lines[y].toCharArray();
	    
	    // For each Column
	    for (int x = 0; x < chars.length; ++x) {
		if (chars[x] == '*'){

		    // Check current cell boundaries.
		    boolean up = y > 0;
		    boolean left = x > 0;
		    boolean right = x < chars.length - 1;
		    boolean down = y < lines.length - 1;
		    

		    // Increment touching cells that are not mines
		    if (up && board[y-1][x] != '*') {
			board[y-1][x] = (char) (board[y-1][x] + 1);
		    }
		    if (left && board[y][x-1] != '*') {
			board[y][x-1] = (char) (board[y][x-1] + 1);
		    }
		    if (right && board[y][x+1] != '*') {
			board[y][x+1] = (char) (board[y][x+1] + 1);
		    }
		    if (down && board[y+1][x] != '*') {
			board[y+1][x] = (char) (board[y+1][x] + 1);
		    }
 		    
		    board[y][x] = '*'; // Center

		    if (up && left && board[y-1][x-1] != '*') {
			board[y-1][x-1] = (char) (board[y-1][x-1] + 1);
		    }
		    if (up && right && board[y-1][x+1] != '*') {
			board[y-1][x+1] = (char) (board[y-1][x+1] + 1);
		    }
		    if (down && left && board[y+1][x-1] != '*') {
			board[y+1][x-1] = (char) (board[y+1][x-1] + 1);
		    }
		    if (down && right && board[y+1][x+1] != '*') {
			board[y+1][x+1] = (char) (board[y+1][x+1] + 1);
		    }
		    
		} else if (chars[x] == '.') {
		    // Do Nothing  
		} else {
		    throw new IllegalArgumentException();
		}
	    }
	}
	
	return board;
    }


    @Complexity (
        basic_operation = "Access", 
        N = "adjs.length", 
        number_of_steps = "N^2",
        big_O = "N^2"
    )
    public void printAdjacencies(char[][] adjs) {

	for (int y = 0; y < adjs.length ; y++) {

	    for (int x = 0; x < adjs[y].length ; x++) {
		System.out.print(adjs[y][x]);
	    }

	    System.out.println("");
	}
    }
}