package tictactoe;

import java.io.IOException;
import java.util.Scanner;

/**
 * This starter files is for students to implement a console controller for the
 * TicTacToe MVC assignment.
 */
public class TicTacToeConsoleController implements TicTacToeController {

  private final Appendable out;
  private final Scanner scan;

  /**
   * Constructor for the controller.
   *
   * @param in  the source to read from
   * @param out the target to print to
   */
  public TicTacToeConsoleController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    scan = new Scanner(in);
  }

  @Override
  public void playGame(TicTacToe m) {
    if (m == null) {
      throw new IllegalArgumentException();
    }
    int[] move = new int[]{0, 0};
    String quit;
    String prompt;
    boolean printBoard = true;
    boolean printPrompt = true;

    try {
      while (!m.isGameOver()) {
        if (printBoard) {
          out.append(m.toString()).append("\n");
        }
        if (printPrompt) {
          prompt = "Enter a move for " + m.getTurn().toString() + ":\n";
          out.append(prompt);
        }
        for (int i = 0; i < 2; i++) {
          while (scan.hasNext()) {
            if (scan.hasNextInt()) {
              move[i] = scan.nextInt();
              break;
            } else {
              quit = scan.next();
              if (quit.equals("q") || quit.equals("Q")) {
                String quitGame = "Game quit! Ending game state:" + "\n" + m + "\n";
                out.append(quitGame);
                return;
              }
              //printBoard = false;
              out.append("Not a valid number: ")
                      .append(quit)
                      .append("\n");

            }
          }
        }
        try {
          m.move(move[0] - 1, move[1] - 1);
          printBoard = true;
          printPrompt = true;
        } catch (IllegalArgumentException e) {
          out.append("Not a valid move: ")
                  .append(String.valueOf(move[0]))
                  .append(", ")
                  .append(String.valueOf(move[1]))
                  .append("\n");
          printPrompt = false;
          printBoard = false;
        } catch (IllegalStateException e) {
          out.append(e.toString());
        }
      }
      StringBuilder gameResult = new StringBuilder();
      gameResult.append(m).append("\n");
      gameResult.append("Game is over! ");
      if (m.getWinner() == null) {
        gameResult.append("Tie game.");
      } else if (m.getWinner().toString().equals("X")) {
        gameResult.append("X wins.");
      } else {
        gameResult.append("O wins.");
      }
      out.append(gameResult.toString());
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
  }

}
