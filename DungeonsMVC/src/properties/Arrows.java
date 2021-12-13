package properties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import locations.DirectionType;
import locations.Location;
import locations.Tunnel;

/**
 * Arrow can kill monster and travel through multiple caves;
 * Arrow come with a start location, direction, and number of caves to travel.
 */
public class Arrows {

  private DirectionType direction;
  private int distances;
  private Location currLocation;
  private Location prevLocation;

  /**
   * Constructor for the arrow that initialize the everything field to default values.
   */
  public Arrows() {
    direction = null;
    distances = -1;
    currLocation = null;
    prevLocation = null;
  }

  /**
   * Define the start location of the arrow.
   *
   * @param startLocation the cave that the arrow starts at
   * @throws IllegalArgumentException check whether the start location is not null
   * @throws IllegalArgumentException check whether the start location is a cave
   */
  public void setStartLocation(Location startLocation) {
    if (startLocation == null) {
      throw new IllegalArgumentException("Start Location cannot be null");
    }
    if (startLocation instanceof Tunnel) {
      throw new IllegalArgumentException("Start Location must be a cave");
    }
    currLocation = startLocation;
    prevLocation = startLocation;
  }

  /**
   * Set the direction of the arrow: North/South/West/East.
   *
   * @param newDirection Direction type of the arrow
   */
  public void setDirection(DirectionType newDirection) {
    this.direction = newDirection;
  }

  /**
   * Set the number of caves the arrow will travel.
   *
   * @param newDistance distance in number of caves
   */
  public void setDistances(int newDistance) {
    this.distances = newDistance;
  }

  /**
   * After the distance and direction is set up, the arrow will be shoot from the current cave and
   * will make damage to the monster if successfully hit the target.
   *
   * @return the description of the hit result
   */
  public String travelThroughCaves() {
    while (distances > 0) {
      Map<DirectionType, Location> entranceMap = new HashMap<>();
      entranceMap.put(DirectionType.NORTH, currLocation.getNorthEntrance());
      entranceMap.put(DirectionType.SOUTH, currLocation.getSouthEntrance());
      entranceMap.put(DirectionType.WEST, currLocation.getWestEntrance());
      entranceMap.put(DirectionType.EAST, currLocation.getEastEntrance());

      if (entranceMap.get(direction) != currLocation) {
        prevLocation = currLocation;
        currLocation = entranceMap.get(direction);
      } else {
        break;
      }
      while (currLocation.getType().equals("TUNNEL")) {
        if (currLocation instanceof Tunnel) {
          if (currLocation.getSrcEntrance() != prevLocation) {
            prevLocation = currLocation;
            currLocation = currLocation.getSrcEntrance();
          } else {
            prevLocation = currLocation;
            currLocation = currLocation.getDstEntrance();
          }
        } else {
          List<Location> lList = new ArrayList<>();
          if (currLocation.getNorthEntrance() != currLocation) {
            lList.add(currLocation.getNorthEntrance());
          }
          if (currLocation.getSouthEntrance() != currLocation) {
            lList.add(currLocation.getSouthEntrance());
          }
          if (currLocation.getWestEntrance() != currLocation) {
            lList.add(currLocation.getWestEntrance());
          }
          if (currLocation.getEastEntrance() != currLocation) {
            lList.add(currLocation.getEastEntrance());
          }
          Location src = lList.get(0);
          Location dst = lList.get(1);
          if (src != prevLocation) {
            prevLocation = currLocation;
            currLocation = src;
          } else {
            prevLocation = currLocation;
            currLocation = dst;
          }
        }
      }
      distances -= 1;
    }
    if (distances == 0) {
      return currLocation.receiveArrowAttack();
    }
    return "\nThe arrow hits the wall";
  }
}
