import static org.junit.Assert.assertEquals;

import tictactoe.TicTacToe;
import tictactoe.TicTacToeConsoleController;
import tictactoe.TicTacToeController;
import tictactoe.TicTacToeModel;

import java.io.StringReader;

import org.junit.Test;

/**
 * Test cases for the tic tac toe controller, using mocks for readable and
 * appendable.
 */
public class TicTacToeControllerTest {

  // Providing this shell for you to write your
  // own test cases for the TicTacToe controller
  // You DO NOT NEED to implement tests for the provided model

  // TODO: Implement your own tests cases for the controller

  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    // Testing when something goes wrong with the Appendable
    // Here we are passing it a mock of an Appendable that always fails
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("0 0 0 1 0 2 1 0 1 1 1 2 2 0 2 1 2 2");
    Appendable gameLog = new FailingAppendable();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidModel() {
    StringReader input = new StringReader("1 1 1 2 2 1 2 2 3 1");
    StringBuffer output = new StringBuffer();
    TicTacToeController c = new TicTacToeConsoleController(input, output);
    c.playGame(null);
  }

  @Test
  public void testXWins() {
    StringReader input = new StringReader("1 1 1 2 2 1 2 2 3 1");
    StringBuffer output = new StringBuffer();
    new TicTacToeConsoleController(input, output).playGame(new TicTacToeModel());
    assertEquals("\nGame is over! X wins.",
            output.substring(output.lastIndexOf("\n")));
  }

  @Test
  public void testOWins() {
    StringReader input = new StringReader("1 2 1 3 1 1 2 3 2 2 3 3");
    StringBuffer output = new StringBuffer();
    new TicTacToeConsoleController(input, output).playGame(new TicTacToeModel());
    assertEquals("\nGame is over! O wins.",
            output.substring(output.lastIndexOf("\n")));
  }

  @Test
  public void testTie() {
    StringReader input = new StringReader("1 1 1 2 2 3 3 3 3 2 2 2 1 3 2 1 3 1");
    StringBuffer output = new StringBuffer();
    new TicTacToeConsoleController(input, output).playGame(new TicTacToeModel());
    assertEquals("\nGame is over! Tie game.",
            output.substring(output.lastIndexOf("\n")));
  }

  @Test
  public void testInvalidRowMove() {
    StringReader input = new StringReader("1 2 1 3 a 1 1 2 3 2 2 3 3");
    StringBuffer output = new StringBuffer();
    new TicTacToeConsoleController(input, output).playGame(new TicTacToeModel());
    assertEquals(43, output.toString().lines().count());
  }

  @Test
  public void testInvalidColumnMove() {
    StringReader input = new StringReader("1 2 1 3 1 a 1 2 3 2 2 3 3");
    StringBuffer output = new StringBuffer();
    new TicTacToeConsoleController(input, output).playGame(new TicTacToeModel());
    assertEquals(43, output.toString().lines().count());
  }

  @Test
  public void testOutBoundRowMove() {
    StringReader input = new StringReader("1 2 1 3 4 1 1 1 2 3 2 2 3 3");
    StringBuffer output = new StringBuffer();
    new TicTacToeConsoleController(input, output).playGame(new TicTacToeModel());
    assertEquals(43, output.toString().lines().count());
  }

  @Test
  public void testOutBoundColumnMove() {
    StringReader input = new StringReader("1 2 1 3 2 4 1 1 2 3 2 2 3 3");
    StringBuffer output = new StringBuffer();
    new TicTacToeConsoleController(input, output).playGame(new TicTacToeModel());
    assertEquals(43, output.toString().lines().count());
  }

  @Test
  public void testQuitRow() {
    StringReader input = new StringReader("q 1");
    StringBuffer output = new StringBuffer();
    new TicTacToeConsoleController(input, output).playGame(new TicTacToeModel());
    String expOutput = "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + "Game quit! Ending game state:\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n";
    assertEquals(expOutput, output.toString());
  }

  @Test
  public void testQuitColumn() {
    StringReader input = new StringReader("1 q");
    StringBuffer output = new StringBuffer();
    new TicTacToeConsoleController(input, output).playGame(new TicTacToeModel());
    String expOutput = "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + "Game quit! Ending game state:\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n";
    assertEquals(expOutput, output.toString());
  }

  @Test
  public void testValidMove() {
    StringReader input = new StringReader("1 1 1 2 2 1 2 2 3 1");
    StringBuffer output = new StringBuffer();
    new TicTacToeConsoleController(input, output).playGame(new TicTacToeModel());
    String expOutput = "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n" +
            "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + " X |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for O:\n"
            + " X | O |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + " X | O |  \n"
            + "-----------\n"
            + " X |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for O:\n"
            + " X | O |  \n"
            + "-----------\n"
            + " X | O |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + " X | O |  \n"
            + "-----------\n"
            + " X | O |  \n"
            + "-----------\n"
            + " X |   |  \n" +
            "Game is over! X wins.";
    assertEquals(expOutput, output.toString());
  }

  @Test
  public void testOccupiedMove() {
    StringReader input = new StringReader("1 1 1 1 1 2 2 1 2 2 3 1");
    StringBuffer output = new StringBuffer();
    new TicTacToeConsoleController(input, output).playGame(new TicTacToeModel());
    String expOutput = "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n" +
            "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + " X |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for O:\n"
            + "Not a valid move: 1, 1\n"
            + " X | O |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + " X | O |  \n"
            + "-----------\n"
            + " X |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for O:\n"
            + " X | O |  \n"
            + "-----------\n"
            + " X | O |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + " X | O |  \n"
            + "-----------\n"
            + " X | O |  \n"
            + "-----------\n"
            + " X |   |  \n" +
            "Game is over! X wins.";
    assertEquals(expOutput, output.toString());
  }

  @Test
  public void testInvalidMove() {
    StringReader input = new StringReader("4 4 1 1 1 2 2 1 2 2 3 1");
    StringBuffer output = new StringBuffer();
    new TicTacToeConsoleController(input, output).playGame(new TicTacToeModel());
    String expOutput = "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n" +
            "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + "Not a valid move: 4, 4\n"
            + " X |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for O:\n"
            + " X | O |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + " X | O |  \n"
            + "-----------\n"
            + " X |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for O:\n"
            + " X | O |  \n"
            + "-----------\n"
            + " X | O |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + " X | O |  \n"
            + "-----------\n"
            + " X | O |  \n"
            + "-----------\n"
            + " X |   |  \n" +
            "Game is over! X wins.";
    assertEquals(expOutput, output.toString());
  }


}
