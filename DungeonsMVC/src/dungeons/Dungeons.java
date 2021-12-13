package dungeons;

import java.util.List;
import java.util.Map;

import locations.Location;

/**
 * Dungeons interface that create a dungeon model according to the input parameters:
 * number of rows, number of columns, wrapping or not, interconnectivity.
 * Add treasures to 20% of the caves.
 * Define a player that starts in a start cave and finishes in a end cave with a minimum distance
 * of 5.
 * Also print player's description: What caves player is currently at, what direction the player
 * can move to, available treasures to be collected, list of collected treasures in the bag.
 */
public interface Dungeons {

  /**
   * Return the number of rows of the dungeons.
   *
   * @return number of rows
   */
  int getNumRows();

  /**
   * Return the number of columns of the dungeons.
   *
   * @return number of columns
   */
  int getNumColumns();

  /**
   * Create a row*column dungeons and apply Kruskal to remove useless tunnels.
   *
   * @throws IllegalArgumentException If the input interconnectivity is greater than
   *                                  the number leftover tunnels, throw an error.
   */
  void generateDungeons();

  /**
   * Create a player object and define the start and end location.
   *
   * @return Player's description at the start cave
   * @throws IllegalArgumentException If the dungeons is too small to have two caves
   *                                  with a minimum distance of 5, throw an error.
   */
  String setPlayer();

  /**
   * Player move to the next cave given the direction.
   *
   * @param direction direction of the next move
   * @return description after player moves
   * @throws IllegalArgumentException check if the direction is valid
   */
  String playerMove(String direction);

  /**
   * Player collect the treasures in the current cave.
   *
   * @return pick up result
   */
  String playerPickUpTreasures();

  /**
   * Player collect the arrows in the current caves.
   *
   * @return pick up result
   */
  String playerPickUpArrows();

  /**
   * Player shoot an arrow with direction and number of caves.
   *
   * @param direction direction of the arrow
   * @param distance  number of caves the arrow travels
   * @return description of the hit result of the arrow
   * @throws IllegalArgumentException check whether direction is valid
   * @throws IllegalArgumentException check whether distance is valid
   */
  String playerShootAnArrow(String direction, int distance);

  /**
   * Get the map information for the view.
   *
   * @return 2d array of DungeonsViewInfo
   */
  DungeonsViewInfo[][] getMapViewInformation();

  /**
   * Get the property information for the view.
   *
   * @return Hashmap
   */
  Map<String, int[]> getPropertyInfo();

  /**
   * Get the player's description at the current cave.
   *
   * @return Player's description
   */
  String getPlayerDescription();

  /**
   * Check whether the player has reached the end cave.
   *
   * @return True or false
   */
  boolean isOver();

  /**
   * Get the 2d list of caves and tunnels.
   *
   * @return 2d list of locations
   */
  List<List<Location>> getCavesMap();

  /**
   * Get the list cave only locations.
   *
   * @return list of caves
   */
  List<Location> getCavesList();

  /**
   * Get the start cave for the player.
   *
   * @return cave
   */
  Location getStartCave();

  /**
   * Get the end cave for the player.
   *
   * @return cave
   */
  Location getEndCave();

  /**
   * Return the result of the game:
   * Whether the player has reached the end.
   *
   * @return game result
   */
  String getResult();

  /**
   * Check if the player has reached the end without being eatten.
   *
   * @return boolean
   */
  boolean playerWin();

  /**
   * Reset the game to the original state, includes all caves and player.
   */
  void resetGame();
}
