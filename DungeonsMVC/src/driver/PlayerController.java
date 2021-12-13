package driver;


import dungeons.Dungeons;

/**
 * Represents a Controller for Dungeons Model: handle user moves by executing them using the model;
 * convey move outcomes to the user in some form.
 */
public interface PlayerController {
  /**
   * Execute a single game of tic tac toe given a tic tac toe Model. When the game is over,
   * the playGame method ends.
   *
   * @param m a non-null dungeons model
   */

  void playGame(Dungeons m);
}
