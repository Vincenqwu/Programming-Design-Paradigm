package view;

/**
 * Controller interface that connects the model and view.
 * Receive action from the view and control the model, and update the view.
 */
public interface DungeonsController {
  /**
   * Receive setting information from the view and generate new dungeons.
   *
   * @param row number of rows
   * @param column number of columns
   * @param wrapping wrapping or not
   * @param interConnectivity interconnectivity
   * @param treArrowPercentage percentage of treasure and arrow
   * @param monsterPercentage percentage of monster
   */
  void getSettingFromView(int row, int column, String wrapping, int interConnectivity,
                          int treArrowPercentage, int monsterPercentage);

  /**
   * Shoot the arrow with the information from view.
   *
   * @param direction direction of arrow
   * @param distance number of caves
   */
  void getShootArrowSetting(String direction, int distance);

  /**
   * Stop the game and close the window.
   */
  void quitGame();

  /**
   * Reset the current game to the start.
   */
  void restartGame();
}
