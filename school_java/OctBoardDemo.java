public class OctBoardDemo
{
    public static void main(String[] args)
    {
        Connective game = new OctGame();
        game.reset(6,6);
        game.addMove(2,3);
        game.addMove(3,3);
        game.addMove(1,2);
        game.addMove(4,4);
        game.addMove(3,4);
        game.addMove(4,3);
        game.addMove(3,5);
        game.addMove(4,5);
        game.addMove(0,1);
        game.addMove(3,2);
        game.addMove(2,1);
        game.addMove(3,1);
        game.addMove(4,1);
        game.addMove(5,1);
        game.addMove(3,0);
        game.addMove(2,0);
        game.printBoard();
    }
}