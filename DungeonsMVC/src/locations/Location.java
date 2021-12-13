package locations;

import java.util.List;

import player.Monster;
import properties.Arrows;
import properties.Treasures;

/**
 * Interface for the Cave and the Tunnel class.
 * Each location can have at most 4 entrances that refer to other locations.
 * If the class is a tunnel, it will have two special entrances which can be denoted as srcEntrance
 * and dstEntrance.
 * Only a cave can store list of treasures.
 * A cave can change to a tunnel by changing its LocationType if only has two entrances.
 */
public interface Location {

  /**
   * Return the row index of the location.
   *
   * @return row
   */
  int getRow();

  /**
   * Return the column index of the location.
   *
   * @return column
   */
  int getColumn();

  /**
   * Return the location reference on the north side.
   *
   * @return north location
   */
  Location getNorthEntrance();

  /**
   * Return the location reference on the south side.
   *
   * @return south location
   */
  Location getSouthEntrance();

  /**
   * Return the location reference on the west side.
   *
   * @return west location
   */
  Location getWestEntrance();

  /**
   * Return the location reference on the east side.
   *
   * @return east location
   */
  Location getEastEntrance();

  /**
   * Return the type of the location.
   *
   * @return cave or tunnel
   */
  String getType();

  /**
   * Set the location reference on the north side.
   *
   * @param north location
   */
  void setNorthEntrance(Location north);

  /**
   * Set the location reference on the south side.
   *
   * @param south location
   */
  void setSouthEntrance(Location south);

  /**
   * Set the location reference on the west side.
   *
   * @param west location
   */
  void setWestEntrance(Location west);

  /**
   * Set the location reference on the east side.
   *
   * @param east location
   */
  void setEastEntrance(Location east);

  /**
   * Return the location identifier.
   *
   * @return id
   */
  int getlId();

  /**
   * Return one of the two entrances of tunnel.
   *
   * @return location
   */
  Location getSrcEntrance();

  /**
   * Return one of the two entrances of tunnel.
   *
   * @return location
   */
  Location getDstEntrance();

  /**
   * Return a list of treasures stored in this cave.
   *
   * @return List of treasures
   */
  List<Treasures> getStoredTreasures();

  /**
   * Add new treasure to the cave.
   *
   * @param newTreasure treasure object
   */
  void addTreasures(Treasures newTreasure);

  /**
   * Remove all treasures in this cave.
   */
  void clearTreasures();

  /**
   * Set all cave only neighbors of this cave.
   */
  void setNeighbors();

  /**
   * Get a list of cave only neighbors of this cave.
   *
   * @return List of caves
   */
  List<Location> getNeighbors();

  /**
   * Add a monster to the cave with two bloods.
   *
   * @param monster monster object
   * @throws IllegalArgumentException check whether the input monster is valid
   */
  void setMonster(Monster monster);

  /**
   * When a new arrow comes in, if the cave has a monster, the monster will drop 1 blood.
   *
   * @return description of the arrow hit
   */
  String receiveArrowAttack();

  /**
   * Check whether a monster resides in this cave.
   *
   * @return boolean
   */
  boolean isHasMonster();

  /**
   * Add new arrow to this cave.
   *
   * @param newArrow arrow object
   */
  void addArrows(Arrows newArrow);

  /**
   * Remove all arrows in this cave.
   */
  void clearArrows();

  /**
   * Return a list of arrows in this cave.
   *
   * @return list of arrow objects
   */
  List<Arrows> getStoredArrows();

  /**
   * Return the monster object in this cave.
   *
   * @return monster object
   */
  Monster getMonster();

  /**
   * Change the type from CAVE to TUNNEL.
   */
  void changeToTunnel();

  /**
   * Check if this location is visited by the player.
   *
   * @return boolean
   */
  boolean getVisited();

  /**
   * Set as visited if the player get to this location.
   */
  void setVisited();

  /**
   * Return the smell level 0, 1, 2 based on the monsters location.
   *
   * @return integer
   */
  int getSmellLevel();

  /**
   * Reset the treasures, arrows and monsters in this location.
   */
  void reset();

}
