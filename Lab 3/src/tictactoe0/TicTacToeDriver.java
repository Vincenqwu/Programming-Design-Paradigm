package tictactoe;

/**
 * An example driver for the Tic-Tac-Toe model.
 */
public class TicTacToeDriver {
  private static final int SIZE = 3;
    
  /**
   * Driver program for TicTacToe to show how it works.
   * 
   * @param args Not used.
   */
  public static void main(String[] args) {
    TicTacToe game = new TicTacToeImpl();
    System.out.println("Example of X winning:");
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(1, 1);
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(3, 3);
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    try {
      game.move(1, 1);
    } catch (IllegalStateException ex) {
      System.out.println("\tIllegal move");
    }
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(3, 1);
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(2, 1);
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(1, 3);
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(2, 2);
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(1, 2);
    if (game.isGameOver()) {
      System.out.println("Game Over: " + game.getWinner() + " wins");
    } else {
      System.out.println("Game should have been over but wasn't");
    }
    System.out.println("At the end to the game, the board was:");
    printBoard(game);
    

    System.out.println();
    System.out.println("Example of tied game:");
    game = new TicTacToeImpl();
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(1, 1);
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(2, 2);
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(2, 1);
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(3, 1);
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(1, 3);
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(1, 2);
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(3, 2);
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(3, 3);
    System.out.println("\tPlayer " + game.nextPlayer() + " turn.");
    game.move(2, 3);
    if (game.isGameOver()) {
      System.out.println("Game Over: " + game.getWinner() + " wins");
    } else {
      System.out.println("Game should have been over but wasn't");
    }
    System.out.println("At the end to the game, the board was:");
    printBoard(game);

  }

  /**
   * Helper method for printing the board.
   * @param game The board to print.
   */
  private static void printBoard(TicTacToe game) {
    for (int r = 1; r <= SIZE; r++) {
      for (int c = 1; c <= SIZE; c++) {
        if (c != 1) {
          System.out.print(" | ");
        }
        System.out.print(game.getMarkAt(c, r));        
      }
      if (r != SIZE) {
        System.out.println("---------");
      }
    }
  }


}
