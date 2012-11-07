public interface Connective {
  
  /**
   * Returns the name of the algorithm you will be using to solve this assignment.
   * @return The name of the algorithm used to solve the connective problem
   */
  String algorithm();
  /**
   * Reset the game board to allow a new game to be played
   * @param height A positive integer specifying the height of the board
   * @param width  A positive integer specifying the width of the board
   * @throws IllegalArgumentException if the height or width is invalid
   */
  void reset(int height, int width) throws IllegalArgumentException;
  
  /**
   * Adds a move to the board. The move being added is determined by
   * the value of side.
   * @param x A non-negative integer between 0 and height determining the row of the next move
   * @param y A non-negative integer between 0 and width determining the column of the next move
   * @param side -1 for Red, 1 for Blue
   * @throws IllegalArgumentException if x or y is invalid.
   */
  void addMove(int x, int y, int side) throws IllegalArgumentException;
  
  /**
   * Adds a move to the board. The move being added alternates in side, with Red always making the first move.
   * @param x A non-negative integer between 0 and height determining the row of the next move
   * @param y A non-negative integer between 0 and width determining the column of the next move
   * @throws IllegalArgumentException if x or y is invalid.
   */
  void addMove(int x, int y) throws IllegalArgumentException;
  
  /**
   * Prints the board to standard output. The board should print each position's internal ID and each position's color
   */
  void printBoard();
  
  /**
   * Returns the value of the winner.
   * @return 0 if there is no winner, -1 if Red wins, 1 if Blue wins.
   */
  int winner();
}