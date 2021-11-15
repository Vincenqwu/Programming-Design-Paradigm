package dungeons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Dungeons class that create a dungeon model according to the input parameters:
 * number of rows, number of columns, wrapping or not, interconnectivity.
 * Add treasures to 20% of the caves.
 * Define a player that starts in a start cave and finishes in a end cave with a minimum distance
 * of 5.
 * Also print player's description: What caves player is currently at, what direction the player
 * can move to, available treasures to be collected, list of collected treasures in the bag.
 */
public class DungeonsImpl implements Dungeons {
  private List<List<Location>> cavesMap;
  private List<Location> cavesList;
  private List<Location> tunnelsList;
  private List<Treasures> treasuresList;
  private List<Location> leftOverTunnels;
  private PeopleInterface player;
  private final int numRows;
  private final int numColumns;
  private final int wrapping;
  private final int interConnectivity;
  private Location startCave = null;
  private Location endCave = null;
  private dungeons.Random random;

  /**
   * Constructor for the dungeons class that takes 4 inputs from the user:
   * number of rows, number of columns, wrapping or not, interconnectivity.
   *
   * @param rows              number of rows
   * @param columns           number of columns
   * @param wrapping          wrapping or not
   * @param interConnectivity interconnectivity
   * @param random            random number generator
   * @throws IllegalArgumentException Check rows and columns validity
   * @throws IllegalArgumentException Check wrapping validity
   * @throws IllegalArgumentException Check interconnectivity validity
   */
  public DungeonsImpl(int rows, int columns, int wrapping, int interConnectivity,
                      dungeons.Random random) {
    if (rows < 2 || columns < 2) {
      throw new IllegalArgumentException("Dungeon's dimension must be at least 2*2");
    }
    if (wrapping < 0 || wrapping > 1) {
      throw new IllegalArgumentException("Wrapping should be either 0 or 1. "
              + "0 for non-wrapping, 1 for wrapping");
    }
    if (interConnectivity < 0) {
      throw new IllegalArgumentException("Interconnectivity cannot be a negative integer");
    }
    this.numRows = rows;
    this.numColumns = columns;
    this.wrapping = wrapping;
    this.interConnectivity = interConnectivity;
    this.treasuresList = new ArrayList<>();
    this.tunnelsList = new ArrayList<>();
    this.cavesMap = new ArrayList<>();
    this.leftOverTunnels = new ArrayList<>();
    this.cavesList = new ArrayList<>();
    this.random = random;
  }

  @Override
  public int getNumRows() {
    return this.numRows;
  }

  @Override
  public int getNumColumns() {
    return this.numColumns;
  }

  @Override
  public void generateDungeons() {
    int cid = 1;
    for (int i = 0; i < this.numRows; i++) {
      List<Location> rowDungeon = new ArrayList<>();
      for (int j = 0; j < this.numColumns; j++) {
        rowDungeon.add(new Cave(cid, i + 1, j + 1, null, null, null, null));
        cid += 1;
      }
      cavesMap.add(rowDungeon);
    }
    if (wrapping == 0) {
      this.generateNonWrappingDungeons();
    } else {
      this.generateWrappingDungeons();
    }

    // Shuffle the leftover tunnels and remove the interconnectivity
    leftOverTunnels = ApplyKruskal.applyKruskal(cavesMap, tunnelsList, random);
    if (interConnectivity >= leftOverTunnels.size()) {
      throw new IllegalArgumentException("The interconnectivity is too big for this dungeon, try "
              + "decreasing the interconnectivity or increasing the dimension of the dungeon.");
    }
    //Collections.shuffle(leftOverTunnels);
    leftOverTunnels = leftOverTunnels.subList(interConnectivity, leftOverTunnels.size());
    //System.out.println("Number of leftovers = :" + leftOverTunnels.size());
    this.correctCaves();
    // Put only caves to a new list, for each cave, set its cave only neighbors
    for (List<Location> locations : cavesMap) {
      for (Location location : locations) {
        if (location.getType().equals("CAVE")) {
          location.setNeighbors();
          this.cavesList.add(location);
        }
      }
    }
    // Put treasures in 20% of caves in cavelist
    this.dumpTreasures();
  }

  @Override
  public String setPlayer() {
    // Choose start cave for player
    Location startCave = cavesList.get(random.nextInt(cavesList.size()));

    // Choose end cave for player
    Map<Location, Integer> distanceMap = new HashMap<>();
    Queue<Location> caveQueue = new LinkedList<>();
    distanceMap.put(startCave, 0);
    int distanceToStart;
    caveQueue.add(startCave);
    while (caveQueue.size() != 0) {
      Location removedCave = caveQueue.poll();
      distanceToStart = distanceMap.get(removedCave);
      for (Location child : removedCave.getNeighbors()) {
        if (!distanceMap.containsKey(child)) {
          distanceMap.put(child, distanceToStart + 1);
          caveQueue.add(child);
        }
      }
    }
    List<Location> candidateEndCaves = new ArrayList<>();
    for (Location cave : distanceMap.keySet()) {
      if (distanceMap.get(cave) >= 5) {
        candidateEndCaves.add(cave);
      }
    }
    if (candidateEndCaves.size() == 0) {
      throw new IllegalArgumentException("The dungeon is too small to have two caves with a "
              + "minimum distance of 5, try increasing the dimension of the dungeon");
    }
    //Collections.shuffle(candidateEndCaves);
    Location endCave = candidateEndCaves.get(random.nextInt(candidateEndCaves.size()));
    this.startCave = startCave;
    this.endCave = endCave;
    player = new People("Player", startCave, endCave);
    StringBuilder sb = new StringBuilder();
    sb.append("\nPlayer's start Cave: ")
            .append(startCave.getlId())
            .append(", (")
            .append(startCave.getRow())
            .append(", ")
            .append(startCave.getColumn())
            .append(")");
    sb.append("\nPlayer's end Cave: ")
            .append(endCave.getlId())
            .append(", (")
            .append(endCave.getRow())
            .append(", ")
            .append(endCave.getColumn())
            .append(")");
    sb.append("\n").append(player.toString());
    return sb.toString();
  }

  @Override
  public Location getStartCave() {
    return this.startCave;
  }

  @Override
  public Location getEndCave() {
    return this.endCave;
  }

  @Override
  public String getPlayerDescription() {
    return player.toString();
  }

  @Override
  public boolean isOver() {
    return player.getCurrLocation() == player.getEndLocation();
  }

  @Override
  public String startMoving() {
    player.move();
    String res = player.toString();
    player.collectTreasures();
    return res;
  }

  @Override
  public List<List<Location>> getCavesMap() {
    return new ArrayList<>(this.cavesMap);
  }

  @Override
  public List<Location> getCavesList() {
    return new ArrayList<>(this.cavesList);
  }

  private void generateNonWrappingDungeons() {
    int tid = 10001;
    for (int i = 0; i < this.numRows; i++) {
      for (int j = 0; j < this.numColumns; j++) {
        Location pivot = cavesMap.get(i).get(j);
        if (i < this.numRows - 1 && j < this.numColumns - 1) {
          Location rightCave = cavesMap.get(i).get(j + 1);
          Location lowCave = cavesMap.get(i + 1).get(j);
          Location rightTunnel = new Tunnel(tid, null, null, pivot, rightCave);
          tid += 1;
          Location lowTunnel = new Tunnel(tid, pivot, lowCave, null, null);
          tid += 1;
          pivot.setSouthEntrance(lowTunnel);
          pivot.setEastEntrance(rightTunnel);
          rightCave.setWestEntrance(rightTunnel);
          lowCave.setNorthEntrance(lowTunnel);
          tunnelsList.add(rightTunnel);
          tunnelsList.add(lowTunnel);
        } else if (i < this.numRows - 1) {
          Location lowCave = cavesMap.get(i + 1).get(j);
          Location lowTunnel = new Tunnel(tid, pivot, lowCave, null, null);
          tid += 1;
          pivot.setSouthEntrance(lowTunnel);
          lowCave.setNorthEntrance(lowTunnel);
          tunnelsList.add(lowTunnel);
        } else if (j < this.numColumns - 1) {
          Location rightCave = cavesMap.get(i).get(j + 1);
          Location rightTunnel = new Tunnel(tid, null, null, pivot, rightCave);
          tid += 1;
          pivot.setEastEntrance(rightTunnel);
          rightCave.setWestEntrance(rightTunnel);
          tunnelsList.add(rightTunnel);
        }
        // Set null entrance as itself
        if (i == 0) {
          pivot.setNorthEntrance(pivot);
        }
        if (j == 0) {
          pivot.setWestEntrance(pivot);
        }
        if (i == this.numRows - 1) {
          pivot.setSouthEntrance(pivot);
        }
        if (j == this.numColumns - 1) {
          pivot.setEastEntrance(pivot);
        }
      }
    }
  }

  private void generateWrappingDungeons() {
    int tid = 10001;
    for (int i = 0; i < this.numRows; i++) {
      for (int j = 0; j < this.numColumns; j++) {
        Location pivot = cavesMap.get(i).get(j);
        if (i < this.numRows - 1 && j < this.numColumns - 1) {
          Location rightCave = cavesMap.get(i).get(j + 1);
          Location lowCave = cavesMap.get(i + 1).get(j);
          Location rightTunnel = new Tunnel(tid, null, null, pivot, rightCave);
          tid += 1;
          Location lowTunnel = new Tunnel(tid, pivot, lowCave, null, null);
          tid += 1;
          pivot.setSouthEntrance(lowTunnel);
          pivot.setEastEntrance(rightTunnel);
          rightCave.setWestEntrance(rightTunnel);
          lowCave.setNorthEntrance(lowTunnel);
          tunnelsList.add(rightTunnel);
          tunnelsList.add(lowTunnel);
        } else if (i < this.numRows - 1) {
          Location lowCave = cavesMap.get(i + 1).get(j);
          Location lowTunnel = new Tunnel(tid, pivot, lowCave, null, null);
          pivot.setSouthEntrance(lowTunnel);
          lowCave.setNorthEntrance(lowTunnel);
          tunnelsList.add(lowTunnel);
          tid += 1;
          Location rightCave = cavesMap.get(i).get(0);
          Location rightTunnel = new Tunnel(tid, null, null, pivot, rightCave);
          pivot.setEastEntrance(rightTunnel);
          rightCave.setWestEntrance(rightTunnel);
          tunnelsList.add(rightTunnel);
          tid += 1;
        } else if (j < this.numColumns - 1) {
          Location rightCave = cavesMap.get(i).get(j + 1);
          Location rightTunnel = new Tunnel(tid, null, null, pivot, rightCave);
          pivot.setEastEntrance(rightTunnel);
          rightCave.setWestEntrance(rightTunnel);
          tunnelsList.add(rightTunnel);
          tid += 1;
          Location lowCave = cavesMap.get(0).get(j);
          Location lowTunnel = new Tunnel(tid, pivot, lowCave, null, null);
          pivot.setSouthEntrance(lowTunnel);
          lowCave.setNorthEntrance(lowTunnel);
          tunnelsList.add(lowTunnel);
          tid += 1;
        } else {
          Location rightCave = cavesMap.get(i).get(0);
          Location rightTunnel = new Tunnel(tid, null, null, pivot, rightCave);
          pivot.setEastEntrance(rightTunnel);
          rightCave.setWestEntrance(rightTunnel);
          tunnelsList.add(rightTunnel);
          tid += 1;
          Location lowCave = cavesMap.get(0).get(j);
          Location lowTunnel = new Tunnel(tid, pivot, lowCave, null, null);
          pivot.setSouthEntrance(lowTunnel);
          lowCave.setNorthEntrance(lowTunnel);
          tunnelsList.add(lowTunnel);
          tid += 1;
        }
      }
    }
  }

  private void correctCaves() {
    for (List<Location> locations : cavesMap) {
      for (Location location : locations) {
        if (leftOverTunnels.contains(location.getNorthEntrance())) {
          location.setNorthEntrance(location);
        }
        if (leftOverTunnels.contains(location.getSouthEntrance())) {
          location.setSouthEntrance(location);
        }
        if (leftOverTunnels.contains(location.getWestEntrance())) {
          location.setWestEntrance(location);
        }
        if (leftOverTunnels.contains(location.getEastEntrance())) {
          location.setEastEntrance(location);
        }
      }
    }

    for (List<Location> locations : cavesMap) {
      for (int j = 0; j < locations.size(); j++) {
        List<String> entrancesDirections = new ArrayList<>();
        if (locations.get(j) instanceof Cave) {
          if (locations.get(j).getNorthEntrance() != locations.get(j)) {
            entrancesDirections.add("North");
          }
          if (locations.get(j).getSouthEntrance() != locations.get(j)) {
            entrancesDirections.add("South");
          }
          if (locations.get(j).getWestEntrance() != locations.get(j)) {
            entrancesDirections.add("West");
          }
          if (locations.get(j).getEastEntrance() != locations.get(j)) {
            entrancesDirections.add("East");
          }
          // If a cave only has two entrances, change it into a tunnel

          Location northEntrance;
          Location southEntrance;
          Location westEntrance;
          Location eastEntrance;
          if (entrancesDirections.size() == 2) {
            if (entrancesDirections.contains("North")) {
              northEntrance = locations.get(j).getNorthEntrance();
            } else {
              northEntrance = null;
            }
            if (entrancesDirections.contains("South")) {
              southEntrance = locations.get(j).getSouthEntrance();
            } else {
              southEntrance = null;
            }
            if (entrancesDirections.contains("West")) {
              westEntrance = locations.get(j).getWestEntrance();
            } else {
              westEntrance = null;
            }
            if (entrancesDirections.contains("East")) {
              eastEntrance = locations.get(j).getEastEntrance();
            } else {
              eastEntrance = null;
            }
            Location newTunnel = new Tunnel(locations.get(j).getlId(),
                    northEntrance, southEntrance, westEntrance, eastEntrance);

            if (northEntrance != null) {
              northEntrance.setSouthEntrance(newTunnel);
            }
            if (southEntrance != null) {
              southEntrance.setNorthEntrance(newTunnel);
            }
            if (westEntrance != null) {
              westEntrance.setEastEntrance(newTunnel);
            }
            if (eastEntrance != null) {
              eastEntrance.setWestEntrance(newTunnel);
            }
            locations.set(j, new Tunnel(locations.get(j).getlId(),
                    northEntrance, southEntrance, westEntrance, eastEntrance));
          }
        }
      }
    }
  }

  private void dumpTreasures() {
    treasuresList.add(new Treasures(TreasureType.DIAMONDS));
    treasuresList.add(new Treasures(TreasureType.RUBIES));
    treasuresList.add(new Treasures(TreasureType.SAPPHIRES));

    int numTreasuredCaves = (int) Math.round(cavesList.size() * 0.2);
    //Collections.shuffle(this.cavesList);
    //Random random = new Random();

    List<Location> treasuredCaves = new ArrayList<>();
    for (int i = 0; i < numTreasuredCaves; i++) {
      Location selectedCave = cavesList.get(random.nextInt(cavesList.size()));
      while (treasuredCaves.contains(selectedCave)) {
        selectedCave = cavesList.get(random.nextInt(cavesList.size()));
      }
      treasuredCaves.add(selectedCave);
    }

    for (Location cave : treasuredCaves) {
      // for each selected caves, add 2 to 5 random treasures
      for (int count = 0; count < random.nextInt(4) + 2; count++) {
        cave.addTreasures(treasuresList.get(random.nextInt(3)));
      }
    }
  }


}
