package locations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import player.Monster;
import properties.Arrows;
import properties.TreasureType;
import properties.Treasures;

/**
 * The Cave class that implements the location interface.
 * Fields include cave id, row, column number, and four entrances.
 */
public class Cave extends AbstractLocation {
  private final int row;
  private final int column;
  private Monster monster;
  private boolean hasMonster;
  private final List<Arrows> storedArrows;
  private final List<Treasures> storedTreasures;
  private final List<Location> neighbors;
  private int oriArrows;
  private int oriDiamonds;
  private int oriRubies;
  private int oriEmeralds;
  private boolean oriMonster;

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
    this.storedArrows = new ArrayList<>();
    this.neighbors = new ArrayList<>();
    this.monster = null;
    this.hasMonster = false;
    this.oriArrows = 0;
    this.oriDiamonds = 0;
    this.oriRubies = 0;
    this.oriEmeralds = 0;
    this.oriMonster = false;
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
    if (newTreasure == null) {
      throw new IllegalArgumentException("Invalid treasure input");
    }
    if (newTreasure.getType().equals(TreasureType.DIAMONDS)) {
      oriDiamonds++;
    } else if (newTreasure.getType().equals(TreasureType.RUBIES)) {
      oriRubies++;
    } else {
      oriEmeralds++;
    }
    this.storedTreasures.add(newTreasure);
  }

  @Override
  public void clearTreasures() {
    this.storedTreasures.clear();
  }

  @Override
  public List<Arrows> getStoredArrows() {
    return new ArrayList<>(this.storedArrows);
  }

  @Override
  public void addArrows(Arrows newArrow) {
    if (newArrow == null) {
      throw new IllegalArgumentException("Invalid arrow input");
    }
    oriArrows++;
    this.storedArrows.add(newArrow);
  }

  @Override
  public void clearArrows() {
    this.storedArrows.clear();
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

  @Override
  public void setMonster(Monster monster) {
    if (monster == null) {
      throw new IllegalArgumentException("Monster cannot be null");
    }
    this.monster = monster;
    this.hasMonster = true;
    this.oriMonster = true;
  }

  @Override
  public Monster getMonster() {
    return this.monster;
  }

  @Override
  public boolean isHasMonster() {
    return this.hasMonster;
  }

  @Override
  public String receiveArrowAttack() {
    if (this.hasMonster) {
      this.monster.getHit();
      if (this.monster.isDead()) {
        this.monster = null;
        this.hasMonster = false;
        return "\nYou hear a death howl in the distance";
      } else {
        return "\nYou hear a great howl in the distance";
      }
    } else {
      return "\nYou shoot an arrow into the darkness";
    }
  }

  @Override
  public int getSmellLevel() {
    if (hasMonster) {
      return 2;
    }
    for (Location cave : this.getNeighbors()) {
      if (cave.isHasMonster()) {
        return 2;
      }
    }
    Set<Location> twoDistCaves = new HashSet<>();
    for (Location oneCaveNeighbor : this.getNeighbors()) {
      for (Location twoCaveNeighbor : oneCaveNeighbor.getNeighbors()) {
        if (twoCaveNeighbor != this) {
          if (twoCaveNeighbor.isHasMonster()) {
            twoDistCaves.add(twoCaveNeighbor);
          }
        }
      }
    }
    if (twoDistCaves.size() >= 2) {
      return 2;
    }
    if (twoDistCaves.size() == 1) {
      return 1;
    }

    return 0;
  }

  @Override
  public void reset() {
    visited = false;
    clearArrows();
    clearTreasures();
    for (int i = 0; i < oriDiamonds; i++) {
      this.storedTreasures.add(new Treasures(TreasureType.DIAMONDS));
    }
    for (int i = 0; i < oriRubies; i++) {
      this.storedTreasures.add(new Treasures(TreasureType.RUBIES));
    }
    for (int i = 0; i < oriEmeralds; i++) {
      this.storedTreasures.add(new Treasures(TreasureType.SAPPHIRES));
    }
    for (int i = 0; i < oriArrows; i++) {
      this.storedArrows.add(new Arrows());
    }
    if (oriMonster) {
      this.monster = new Monster();
      this.hasMonster = true;
    }
  }
}
