package dungeons;

import java.util.ArrayList;
import java.util.List;

/**
 * The Cave class that implements the location interface.
 * Fields include cave id, row, column number, and four entrances.
 */
public class Cave extends AbstractLocation {
  private final int row;
  private final int column;
  private List<Treasures> storedTreasures;
  private List<Location> neighbors = new ArrayList<>();

  /**
   * Constructor for the cave class that includes cave id, row, column number, and four entrances.
   *
   * @param id  cave identifier
   * @param row row index of the cave
   * @param col column index of the cave
   * @param ne  north entrance
   * @param se  south entrance
   * @param we  west entrance
   * @param ee  east entrance
   * @throws IllegalArgumentException Check invalid row and column index.
   */
  public Cave(int id, int row, int col, Location ne, Location se, Location we, Location ee) {
    super(id, ne, se, we, ee);
    if (row < 1 || col < 1) {
      throw new IllegalArgumentException("The row and column index of cave must be positive");
    }
    this.lId = id;
    this.row = row;
    this.column = col;
    this.type = LocationType.CAVE;
    this.storedTreasures = new ArrayList<>();
  }

  @Override
  public int getRow() {
    return this.row;
  }

  @Override
  public int getColumn() {
    return this.column;
  }

  @Override
  public List<Treasures> getStoredTreasures() {
    return new ArrayList<>(this.storedTreasures);
  }

  @Override
  public void addTreasures(Treasures newTreasure) {
    this.storedTreasures.add(newTreasure);
  }

  @Override
  public void clearTreasures() {
    this.storedTreasures.clear();
  }

  @Override
  public void setNeighbors() {
    Location neighborCave;
    if (this.getNorthEntrance() != this) {
      neighborCave = findNeighborCave(this, this.getNorthEntrance());
      if (neighborCave != this) {
        neighbors.add(neighborCave);
      }
    }
    if (this.getSouthEntrance() != this) {
      neighborCave = findNeighborCave(this, this.getSouthEntrance());
      if (neighborCave != this) {
        neighbors.add(neighborCave);
      }
    }
    if (this.getWestEntrance() != this) {
      neighborCave = findNeighborCave(this, this.getWestEntrance());
      if (neighborCave != this) {
        neighbors.add(neighborCave);
      }
    }
    if (this.getEastEntrance() != this) {
      neighborCave = findNeighborCave(this, this.getEastEntrance());
      if (neighborCave != this) {
        neighbors.add(neighborCave);
      }
    }
  }

  @Override
  public List<Location> getNeighbors() {
    return new ArrayList<>(this.neighbors);
  }

}
