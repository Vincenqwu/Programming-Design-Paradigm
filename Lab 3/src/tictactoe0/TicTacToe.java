package tictactoe;

/**
 * An interface representing one design for the game of TicTacToe.
 */
public interface TicTacToe {

  /**
   * An enumeration representing the two players for use in our implementation.
   */
  public enum Player {
    X, O
  }

  /**
   * Places an X or O mark in the specified cell. Whether it places an X or O
   * depends on which player's turn it is.
   *
   * @param column the column of the cell
   * @param row    the row of the cell
   * @throws IllegalStateException     if the game is over, or if there is already
   *                                   a mark in the cell.
   * @throws IndexOutOfBoundsException if the cell is out of bounds.
   */
  void move(int column, int row);

  /**
   * Returns the player whose turn is next.
   *
   * @return the next player
   * @throws IllegalStateException if the game is over
   */
  Player nextPlayer();

  /**
   * Returns the {@link Player} whose mark is in the cell at the given
   * coordinates, or {@code null} if that cell is empty.
   *
   * @param column the column of the cell
   * @param row    the row of the cell
   * @return a {@code Player} or {@code null}
   * @throws IndexOutOfBoundsException if the cell is out of bounds.
   */
  Player getMarkAt(int column, int row);

  /**
   * Determines whether the game is over.
   *
   * @return whether the game is over
   */
  boolean isGameOver();

  /**
   * Returns the winner of the game, or {@code null} if the game is a tie.
   *
   * @return the winner or {@code null}
   * @throws IllegalStateException if the game isn't over
   */
  Player getWinner();
}
