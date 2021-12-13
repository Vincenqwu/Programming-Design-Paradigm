package player;

import java.util.List;

import locations.DirectionType;
import properties.Treasures;
import locations.Location;

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
  String collectTreasures();


  /**
   * Player move from the current cave to one of the neighbor caves based on the input.
   *
   * @param direction direction of the new move
   */
  void move(DirectionType direction);

  /**
   * Player shoot an arrow given the number of caves and the direction.
   *
   * @param direction direction of the arrow
   * @param distance  number of caves the arrow will travel
   * @return the result string
   */
  String shootArrow(DirectionType direction, int distance);

  /**
   * Collect the arrows in the current cave.
   *
   * @return description of the arrow collection
   */
  String collectArrows();

  /**
   * Return the description of the player's current location, arrows, treasures.
   *
   * @return description
   */
  String checkSituation();

  /**
   * Return the number of arrows the player equipped.
   *
   * @return number of arrows
   */
  int getArrowCount();

}
