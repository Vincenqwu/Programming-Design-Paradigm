package dungeons;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract location class that generalize all methods for both cave and tunnel classes.
 */
public class AbstractLocation implements Location {
  protected Location northEntrance;
  protected Location southEntrance;
  protected Location westEntrance;
  protected Location eastEntrance;
  protected LocationType type = null;
  protected int lId;

  /**
   * Constructor for abstractLocation class. Connect each entrance with predefined location,
   * or set to null.
   *
   * @param id location identifier
   * @param ne north entrance
   * @param se south entrance
   * @param we west entrance
   * @param ee east entrance
   */
  public AbstractLocation(int id, Location ne, Location se, Location we, Location ee) {
    this.lId = id;
    this.northEntrance = ne;
    this.southEntrance = se;
    this.westEntrance = we;
    this.eastEntrance = ee;
  }

  @Override
  public int getRow() {
    return -1;
  }

  @Override
  public int getColumn() {
    return -1;
  }

  @Override
  public Location getNorthEntrance() {
    return this.northEntrance;
  }

  @Override
  public Location getSouthEntrance() {
    return this.southEntrance;
  }

  @Override
  public Location getWestEntrance() {
    return this.westEntrance;
  }

  @Override
  public Location getEastEntrance() {
    return this.eastEntrance;
  }

  @Override
  public String getType() {
    return this.type.toString();
  }

  @Override
  public void setNorthEntrance(Location north) {
    this.northEntrance = north;
  }

  @Override
  public void setSouthEntrance(Location south) {
    this.southEntrance = south;
  }

  @Override
  public void setWestEntrance(Location west) {
    this.westEntrance = west;
  }

  @Override
  public void setEastEntrance(Location east) {
    this.eastEntrance = east;
  }

  @Override
  public int getlId() {
    return this.lId;
  }

  @Override
  public Location getSrcEntrance() {
    return this;
  }

  @Override
  public Location getDstEntrance() {
    return this;
  }

  @Override
  public List<Treasures> getStoredTreasures() {
    return new ArrayList<>();
  }

  @Override
  public void addTreasures(Treasures newTreasure) {
    // Does nothing in abstract class;
    // Will be overridden in Cave class.
  }

  @Override
  public void clearTreasures() {
    // Does nothing in abstract class;
    // Will be overridden in Cave class.
  }

  @Override
  public void setNeighbors() {
    // Does nothing in abstract class;
    // Will be overridden in Cave class.
  }

  @Override
  public List<Location> getNeighbors() {
    return null;
  }

  protected Location findNeighborCave(Location parentLocation, Location currLocation) {
    if (currLocation.getSrcEntrance() != parentLocation) {
      if (currLocation.getSrcEntrance().getType().equals("CAVE")) {
        return currLocation.getSrcEntrance();
      }
      return findNeighborCave(currLocation, currLocation.getSrcEntrance());
    } else {
      if (currLocation.getDstEntrance().getType().equals("CAVE")) {
        return currLocation.getDstEntrance();
      }
      return findNeighborCave(currLocation, currLocation.getDstEntrance());
    }
  }

}
