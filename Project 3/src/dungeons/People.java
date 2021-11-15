package dungeons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * People class that defines a player in the dungeons with a start cave and end cave.
 * Player move from the current cave to the neighbor caves randomly. '
 * Player can pick up treasures stored in the current cave.
 */
public class People implements PeopleInterface {
  private List<Treasures> treasureBag;
  private Location currLocation;
  private Location prevLocation;
  private final String name;
  private final Location endLocation;

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
  public void collectTreasures() {
    if (currLocation.getStoredTreasures().size() > 0) {
      this.treasureBag.addAll(currLocation.getStoredTreasures());
      currLocation.clearTreasures();
    }

  }

  @Override
  public void move() {
    List<Location> entranceChoices = new ArrayList<>();
    if (currLocation.getNorthEntrance() != currLocation) {
      entranceChoices.add(currLocation.getNorthEntrance());
    }
    if (currLocation.getSouthEntrance() != currLocation) {
      entranceChoices.add(currLocation.getSouthEntrance());
    }
    if (currLocation.getWestEntrance() != currLocation) {
      entranceChoices.add(currLocation.getWestEntrance());
    }
    if (currLocation.getEastEntrance() != currLocation) {
      entranceChoices.add(currLocation.getEastEntrance());
    }
    Collections.shuffle(entranceChoices);
    if (entranceChoices.size() == 1) {
      prevLocation = currLocation;
      currLocation = entranceChoices.get(0);
    } else {
      for (Location entrance : entranceChoices) {
        if (entrance != prevLocation) {
          prevLocation = currLocation;
          currLocation = entrance;
          break;
        }
      }
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

  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (currLocation instanceof Tunnel) {
      sb.append("\nPlayer in a tunnel with two entrances");
      sb.append("\nLeft Right");
    } else {
      sb.append("\nPlayer in cave ").
              append(currLocation.getlId()).
              append(", (").append(currLocation.getRow()).
              append(", ").append(currLocation.getColumn()).
              append(")").
              append(", with the following entrances:\n");
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
      sb.append("\nThe play has collected the following treasures:");
      sb.append("\n").append(getTreasureDescription(this.treasureBag));
      sb.append("\nThis cave has the following treasures:");
      sb.append("\n").append(getTreasureDescription(currLocation.getStoredTreasures()));
    }
    return sb.toString();
  }

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
