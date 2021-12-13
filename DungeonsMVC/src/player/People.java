package player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import locations.DirectionType;
import properties.Arrows;
import properties.TreasureType;
import properties.Treasures;
import locations.Location;
import locations.Tunnel;


/**
 * People class that defines a player in the dungeons with a start cave and end cave.
 * Player move from the current cave to the neighbor caves randomly. '
 * Player can pick up treasures stored in the current cave.
 */
public class People implements PeopleInterface {
  private Location currLocation;
  private Location prevLocation;
  private final String name;
  private final Location endLocation;
  private final List<Arrows> arrowsBag;
  private final List<Treasures> treasureBag;

  /**
   * Construct a player object with name, start cave and end cave.
   *
   * @param name  player's name
   * @param start start cave
   * @param end   end cave
   */
  public People(String name, Location start, Location end) {
    this.name = name;
    this.endLocation = end;
    this.currLocation = start;
    this.prevLocation = start;
    this.treasureBag = new ArrayList<>();
    this.arrowsBag = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      arrowsBag.add(new Arrows());
    }
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Location getCurrLocation() {
    return this.currLocation;
  }

  @Override
  public Location getEndLocation() {
    return this.endLocation;
  }

  @Override
  public List<Treasures> getCollectedTreasures() {
    return new ArrayList<>(this.treasureBag);
  }

  @Override
  public int getArrowCount() {
    return this.arrowsBag.size();
  }

  @Override
  public String collectTreasures() {
    if (currLocation.getType().equals("TUNNEL")) {
      return "You cannot find any treasures in a tunnel";
    }
    if (currLocation.getStoredTreasures().size() > 0) {
      String result = "You collected: " + getTreasureDescription(currLocation.getStoredTreasures());
      this.treasureBag.addAll(currLocation.getStoredTreasures());
      currLocation.clearTreasures();
      return result;
    }
    return "No treasures in this cave";
  }


  @Override
  public String collectArrows() {
    if (currLocation.getStoredArrows().size() > 0) {
      String result = "You picked up " + currLocation.getStoredArrows().size() + " arrows";
      this.arrowsBag.addAll(currLocation.getStoredArrows());
      currLocation.clearArrows();
      return result;
    }
    if (currLocation.getType().equals("TUNNEL")) {
      return "No arrows found in this tunnel";
    }
    return "No arrows found in this cave";
  }

  @Override
  public String shootArrow(DirectionType direction, int distance) {
    if (distance < 1) {
      throw new IllegalArgumentException("The arrow must travel through at least 1 cave");
    }
    if (distance > 5) {
      throw new IllegalArgumentException("The arrow can only travel for a maximum of 5 caves");
    }
    if (arrowsBag.size() == 0) {
      throw new IllegalStateException("The arrow bag is empty");
    }
    Arrows newArrow = arrowsBag.get(0);
    newArrow.setStartLocation(currLocation);
    newArrow.setDirection(direction);
    newArrow.setDistances(distance);
    String result = newArrow.travelThroughCaves();
    arrowsBag.remove(0);
    return result;
  }

  @Override
  public void move(DirectionType direction) {
    Map<DirectionType, Location> entranceMap = new HashMap<>();
    entranceMap.put(DirectionType.NORTH, currLocation.getNorthEntrance());
    entranceMap.put(DirectionType.SOUTH, currLocation.getSouthEntrance());
    entranceMap.put(DirectionType.WEST, currLocation.getWestEntrance());
    entranceMap.put(DirectionType.EAST, currLocation.getEastEntrance());
    if (entranceMap.get(direction) != currLocation) {
      prevLocation = currLocation;
      currLocation = entranceMap.get(direction);
    } else {
      throw new IllegalArgumentException("Entrance " + direction.toString() + " is not available");
    }
    while (currLocation instanceof Tunnel) {
      if (currLocation.getSrcEntrance() != prevLocation) {
        prevLocation = currLocation;
        currLocation = currLocation.getSrcEntrance();
      } else {
        prevLocation = currLocation;
        currLocation = currLocation.getDstEntrance();
      }
    }
    // Set visited
    currLocation.setVisited();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (!this.detectMonsterSmell().equals("")) {
      sb.append(detectMonsterSmell());
    }
    if (currLocation.getType().equals("TUNNEL")) {
      sb.append("\nYou are in tunnel ")
              .append("(").append(currLocation.getRow())
              .append(", ").append(currLocation.getColumn())
              .append(")")
              .append(", that leads to:\n");
    } else {
      sb.append("\nYou are in cave ")
              .append("(").append(currLocation.getRow())
              .append(", ").append(currLocation.getColumn())
              .append(")")
              .append(", with entrances:\n");
    }
    if (currLocation.getNorthEntrance() != currLocation) {
      sb.append("North ");
    }
    if (currLocation.getSouthEntrance() != currLocation) {
      sb.append("South ");
    }
    if (currLocation.getWestEntrance() != currLocation) {
      sb.append("West ");
    }
    if (currLocation.getEastEntrance() != currLocation) {
      sb.append("East ");
    }
    return sb.toString();
  }

  @Override
  public String checkSituation() {
    StringBuilder sb = new StringBuilder();
    sb.append(this);
    sb.append("\nYou have ").append(arrowsBag.size()).append(" arrows");
    sb.append("\nYou have collected treasures:");
    sb.append("\n").append(getTreasureDescription(this.treasureBag));
    if (currLocation.getType().equals("CAVE")) {
      sb.append("\nThe cave has ").append(currLocation.getStoredArrows().size()).append(" arrows");
      sb.append("\nThe cave has treasures:");
      sb.append("\n").append(getTreasureDescription(currLocation.getStoredTreasures()));
    } else {
      sb.append("\nThe tunnel has ")
              .append(currLocation.getStoredArrows().size())
              .append(" arrows");
    }
    return sb.toString();
  }

  /**
   * Detect monsters either in the nearest neighbor or in the second layer
   * neighbor of the current location.
   *
   * @return description
   */
  private String detectMonsterSmell() {
    for (Location cave : currLocation.getNeighbors()) {
      if (cave.isHasMonster()) {
        return "\nYou smell something very pungent nearby";
      }
    }
    Set<Location> twoDistCaves = new HashSet<>();
    for (Location oneCaveNeighbor : currLocation.getNeighbors()) {
      for (Location twoCaveNeighbor : oneCaveNeighbor.getNeighbors()) {
        if (twoCaveNeighbor != currLocation) {
          if (twoCaveNeighbor.isHasMonster()) {
            twoDistCaves.add(twoCaveNeighbor);
          }
        }
      }
    }
    if (twoDistCaves.size() >= 2) {
      return "\nYou smell something very pungent nearby";
    }
    if (twoDistCaves.size() == 1) {
      return "\nYou smell something less pungent nearby";
    }

    return "";
  }

  /**
   * Return the description of the treasures in the given list.
   *
   * @param treasures list of treasures
   * @return description
   */
  private String getTreasureDescription(List<Treasures> treasures) {
    StringBuilder sb = new StringBuilder();
    if (treasures.size() > 0) {
      int diamondsCount = 0;
      int rubiesCount = 0;
      int sapphiresCount = 0;
      for (Treasures treasure : treasures) {
        if (treasure.getType().equals(TreasureType.DIAMONDS)) {
          diamondsCount += 1;
        } else if (treasure.getType().equals(TreasureType.RUBIES)) {
          rubiesCount += 1;
        } else {
          sapphiresCount += 1;
        }
      }
      sb.append("Diamonds*").append(diamondsCount);
      sb.append(" Rubies*").append(rubiesCount);
      sb.append(" Sapphires*").append(sapphiresCount);
      return sb.toString();
    } else {
      return "None";
    }
  }

}
