package dungeons;

import java.util.List;

/**
 * Interface for the Cave and the Tunnel class.
 * Each location can have at most 4 entrances that refer to other locations.
 * If the class is a tunnel, it can only two entrances which can be denoted as srcEntrance
 * and dstEntrance.
 * Only a cave can store list of treasures.
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

}
