package dungeons;

import java.util.List;

/**
 * Interface for people class that defines a player in the dungeons with a start cave and end cave.
 * Player move from the current cave to the neighbor caves randomly. '
 * Player can pick up treasures stored in the current cave.
 */
public interface PeopleInterface {
  /**
   * Return the people's name.
   *
   * @return name
   */
  String getName();

  /**
   * Get the current cave.
   *
   * @return Cave
   */
  Location getCurrLocation();

  /**
   * Get the destination cave.
   *
   * @return Cave
   */
  Location getEndLocation();

  /**
   * Return a list of treasures that the people has collected.
   *
   * @return List of treasures
   */
  List<Treasures> getCollectedTreasures();

  /**
   * Move the treasures in the cave to the player's treasure bag.
   */
  void collectTreasures();

  /**
   * Randomly move from the current cave to one of the neighbor caves.
   */
  void move();
}
