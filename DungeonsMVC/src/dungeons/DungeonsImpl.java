package dungeons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import locations.Cave;
import locations.DirectionType;
import locations.Location;
import locations.Tunnel;
import player.Monster;
import player.People;
import player.PeopleInterface;
import properties.Arrows;
import random.Random;
import properties.TreasureType;
import properties.Treasures;
import random.RealRandom;

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
  private final List<List<Location>> cavesMap;
  private final List<Location> cavesList;
  private final List<Location> tunnelsList;
  private final List<Treasures> treasuresList;
  private List<Location> leftOverTunnels;
  private PeopleInterface player;
  private final int numRows;
  private final int numColumns;
  private final int wrapping;
  private final int interConnectivity;
  private final Random random;
  private Location startCave;
  private Location endCave;
  private boolean playerDead;
  private final int treArrowPercent;
  private final int monsterPercent;

  /**
   * Constructor for the dungeons with default setting for MVC.
   */
  public DungeonsImpl() {
    this.numRows = 6;
    this.numColumns = 6;
    this.wrapping = 0;
    this.interConnectivity = 0;
    this.treasuresList = new ArrayList<>();
    this.tunnelsList = new ArrayList<>();
    this.cavesMap = new ArrayList<>();
    this.leftOverTunnels = new ArrayList<>();
    this.cavesList = new ArrayList<>();
    this.random = new RealRandom();
    this.startCave = null;
    this.endCave = null;
    this.playerDead = false;
    this.treArrowPercent = 0;
    this.monsterPercent = 0;
  }


  /**
   * Constructor for the dungeons class that takes 6 inputs from the user:
   * number of rows, number of columns, wrapping or not, interconnectivity.
   *
   * @param rows              number of rows
   * @param columns           number of columns
   * @param wrapping          wrapping or not
   * @param interConnectivity interconnectivity
   * @throws IllegalArgumentException Check rows and columns validity
   * @throws IllegalArgumentException Check wrapping validity
   * @throws IllegalArgumentException Check interconnectivity validity
   */
  public DungeonsImpl(int rows, int columns, int wrapping, int interConnectivity,
                      int treArrowPercent, int monsterPercent) {
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
    if (treArrowPercent < 20 || treArrowPercent > 100) {
      throw new IllegalArgumentException("Percentage of treasure and arrow "
              + "must be between 20% and 100%");
    }
    if (monsterPercent < 0 || monsterPercent > 100) {
      throw new IllegalArgumentException("Percentage of monster in the dungeon "
              + "must be between 0% and 100%");
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
    this.random = new RealRandom();
    this.startCave = null;
    this.endCave = null;
    this.playerDead = false;
    this.treArrowPercent = treArrowPercent;
    this.monsterPercent = monsterPercent;
  }

  /**
   * Constructor for the dungeons class that takes 5 inputs from the user:
   * number of rows, number of columns, wrapping or not, interconnectivity, random generator.
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
                      int treArrowPercent, int monsterPercent, Random random) {
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
    if (treArrowPercent < 20 || treArrowPercent > 100) {
      throw new IllegalArgumentException("Percentage of treasure and arrow "
              + "must be between 20% and 100%");
    }
    if (monsterPercent < 0 || monsterPercent > 100) {
      throw new IllegalArgumentException("Percentage of monster in the dungeon "
              + "must be between 0% and 100%");
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
    this.startCave = null;
    this.endCave = null;
    this.playerDead = false;
    this.treArrowPercent = treArrowPercent;
    this.monsterPercent = monsterPercent;
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
    this.generateDungeon();

    // Shuffle the leftover tunnels and remove the interconnectivity
    leftOverTunnels = ApplyKruskal.applyKruskal(cavesMap, tunnelsList, random);
    if (interConnectivity >= leftOverTunnels.size()) {
      throw new IllegalArgumentException("The interconnectivity is too big for this dungeon, try "
              + "decreasing the interconnectivity or increasing the dimension of the dungeon.");
    }
    leftOverTunnels = leftOverTunnels.subList(interConnectivity, leftOverTunnels.size());
    this.correctCaves();
    // Put only caves to a new list, for each cave, set its cave only neighbors
    for (List<Location> locations : cavesMap) {
      for (Location location : locations) {
        if (location.getType().equals("CAVE")) {
          this.cavesList.add(location);
        }
        location.setNeighbors();
      }
    }
    this.dumpTreasures();
    this.dumpArrows();
    this.dumpMonsters();
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
    Location endCave = candidateEndCaves.get(random.nextInt(candidateEndCaves.size()));
    this.startCave = startCave;
    this.startCave.setVisited();
    this.endCave = endCave;
    // If the start cave has monster, kill it with two arrows
    if (this.startCave.isHasMonster()) {
      this.startCave.receiveArrowAttack();
      this.startCave.receiveArrowAttack();
    }
    // If the end cave has no monster, add one
    if (!this.endCave.isHasMonster()) {
      this.endCave.setMonster(new Monster());
    }
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
  public DungeonsViewInfo[][] getMapViewInformation() {
    DungeonsViewInfo[][] dungeonsViewMap = new DungeonsViewInfo[numRows][numColumns];
    String playerCondition;
    if (playerWin()) {
      playerCondition = "Win";
    } else if (playerDead) {
      playerCondition = "Dead";
    } else {
      playerCondition = "Alive";
    }
    for (int i = 0; i < this.numRows; i++) {
      for (int j = 0; j < this.numColumns; j++) {
        Location currLocation = cavesMap.get(i).get(j);
        String graphType = getGraphType(currLocation);
        boolean isCurrLocation = (player.getCurrLocation() == currLocation);
        boolean visited = currLocation.getVisited();
        boolean hasMonsters = currLocation.isHasMonster();
        int smellLevel = currLocation.getSmellLevel();
        dungeonsViewMap[i][j] = new DungeonsViewInfo(graphType, playerCondition, isCurrLocation,
                visited, hasMonsters, smellLevel);
      }
    }
    return dungeonsViewMap;
  }

  private String getGraphType(Location location) {
    boolean canNorth = location.getNorthEntrance() != location;
    boolean canSouth = location.getSouthEntrance() != location;
    boolean canWest = location.getWestEntrance() != location;
    boolean canEast = location.getEastEntrance() != location;
    String graphType;
    if (location.getType().equals("CAVE")) {
      if (canNorth && canSouth && canWest && canEast) {
        graphType = "NESW";
      } else if (canNorth && canSouth && canWest) {
        graphType = "SWN";
      } else if (canNorth && canWest && canEast) {
        graphType = "NEW";
      } else if (canNorth && canSouth && canEast) {
        graphType = "NES";
      } else if (canSouth && canWest && canEast) {
        graphType = "ESW";
      } else if (canNorth) {
        graphType = "N";
      } else if (canSouth) {
        graphType = "S";
      } else if (canWest) {
        graphType = "W";
      } else {
        graphType = "E";
      }
    } else {
      if (canNorth && canSouth) {
        graphType = "NS";
      } else if (canNorth && canWest) {
        graphType = "WN";
      } else if (canNorth && canEast) {
        graphType = "NE";
      } else if (canSouth && canWest) {
        graphType = "SW";
      } else if (canSouth && canEast) {
        graphType = "ES";
      } else {
        graphType = "EW";
      }
    }
    return graphType;
  }

  @Override
  public Map<String, int[]> getPropertyInfo() {
    int countCaveDiamond;
    int countBagDiamond;
    int countCaveRuby;
    int countBagRuby;
    int countCaveEmerald;
    int countBagEmerald;
    int countCaveArrow;
    int countBagArrow;
    Map<String, int[]> propertyMap = new HashMap<>();

    List<Treasures> caveTreasuresList = player.getCurrLocation().getStoredTreasures();
    List<Treasures> bagTreasuresList = player.getCollectedTreasures();
    int[] countCaveTreasures = countTreasures(caveTreasuresList);
    int[] countBagTreasures = countTreasures(bagTreasuresList);
    countCaveDiamond = countCaveTreasures[0];
    countBagDiamond = countBagTreasures[0];
    countCaveRuby = countCaveTreasures[1];
    countBagRuby = countBagTreasures[1];
    countCaveEmerald = countCaveTreasures[2];
    countBagEmerald = countBagTreasures[2];
    countCaveArrow = player.getCurrLocation().getStoredArrows().size();
    countBagArrow = player.getArrowCount();

    propertyMap.put("Diamond", new int[]{countCaveDiamond, countBagDiamond});
    propertyMap.put("Ruby", new int[]{countCaveRuby, countBagRuby});
    propertyMap.put("Emerald", new int[]{countCaveEmerald, countBagEmerald});
    propertyMap.put("Arrow", new int[]{countCaveArrow, countBagArrow});

    return propertyMap;

  }

  private int[] countTreasures(List<Treasures> treasuresList) {
    int[] res = new int[]{0, 0, 0};
    for (Treasures treasure : treasuresList) {
      if (treasure.getType().equals(TreasureType.DIAMONDS)) {
        res[0] += 1;
      } else if (treasure.getType().equals(TreasureType.RUBIES)) {
        res[1] += 1;
      } else {
        res[2] += 1;
      }
    }
    return res;
  }

  @Override
  public String getPlayerDescription() {
    String[][] graph = new String[numRows * 2 + 1][numColumns * 2 + 1];
    for (int i = 0; i < this.numRows; i++) {
      for (int j = 0; j < this.numColumns; j++) {
        Location currLocation = cavesMap.get(i).get(j);
        // Cave
        if (player.getCurrLocation() == currLocation) {
          graph[i * 2 + 1][j * 2 + 1] = " Y ";
        } else if (currLocation == startCave) {
          graph[i * 2 + 1][j * 2 + 1] = " S ";
        } else if (currLocation == endCave) {
          graph[i * 2 + 1][j * 2 + 1] = " E ";
        } else {
          if (currLocation.getType().equals("CAVE")) {
            if (currLocation.isHasMonster()) {
              graph[i * 2 + 1][j * 2 + 1] = " M ";
            } else {
              graph[i * 2 + 1][j * 2 + 1] = " C ";
            }
          }
          // Tunnel
          else {
            graph[i * 2 + 1][j * 2 + 1] = " T ";
          }

        }
        if (currLocation.getSouthEntrance() != currLocation) {
          graph[i * 2 + 2][j * 2 + 1] = " | ";
          if (i == numRows - 1) {
            graph[0][j * 2 + 1] = " | ";
          }
        }
        if (currLocation.getEastEntrance() != currLocation) {
          graph[i * 2 + 1][j * 2 + 2] = "---";
          if (j == numColumns - 1) {
            graph[i * 2 + 1][0] = "---";
          }
        }

      }
    }

    StringBuilder result = new StringBuilder();
    for (String[] strings : graph) {
      for (int j = 0; j < graph[0].length; j++) {
        if (strings[j] == null) {
          result.append("   ");
        } else {
          result.append(strings[j]);
        }
      }
      result.append("\n");
    }
    return "\n" + result + player.checkSituation();
  }


  @Override
  public boolean isOver() {
    return playerDead || player.getCurrLocation() == player.getEndLocation();
  }

  @Override
  public String getResult() {
    if (this.player.getCurrLocation() == endCave) {
      if (playerDead) {
        return "\nYou get to the destination, but you are eaten by the monster\n";
      }
      return "\nCongratulation! You successfully get to the destination\n";
    }
    return "\nYou do not get to the destination\n";
  }

  @Override
  public void resetGame() {
    for (int i = 0; i < this.numRows; i++) {
      for (int j = 0; j < this.numColumns; j++) {
        Location currLocation = cavesMap.get(i).get(j);
        currLocation.reset();
      }
    }
    // If the start cave has monster, kill it with two arrows
    startCave.setVisited();
    if (this.startCave.isHasMonster()) {
      this.startCave.receiveArrowAttack();
      this.startCave.receiveArrowAttack();
    }
    // If the end cave has no monster, add one
    if (!this.endCave.isHasMonster()) {
      this.endCave.setMonster(new Monster());
    }
    player = new People("New Player", startCave, endCave);
    playerDead = false;
  }

  @Override
  public boolean playerWin() {
    return (this.player.getCurrLocation() == endCave) && (!playerDead);
  }

  @Override
  public String playerMove(String direction) {
    if (direction == null) {
      throw new IllegalArgumentException("Direction cannot be null");
    }
    try {
      player.move(DirectionType.valueOf(direction));
      if (player.getCurrLocation().isHasMonster()) {
        if (player.getCurrLocation().getMonster().getLife() == 1) {
          Random probEscape = new RealRandom();
          // Fail to escape
          if (probEscape.nextInt(2) == 0) {
            playerDead = true;
            return player.toString() + "\n\nChomp, chomp, chomp, you are eaten by an Otyugh!"
                    + "\nBetter luck next time";
          }
          return player.toString() + "\n\nYou just encountered an Otyugh,"
                  + " but you successfully escaped!";
        }
        playerDead = true;
        return player.toString() + "\n\nChomp, chomp, chomp, you are eaten by an Otyugh!"
                + "\nBetter luck next time";
      }
      return player.toString();
    } catch (IllegalArgumentException iae) {
      throw new IllegalArgumentException("Direction not available");
    }
  }

  @Override
  public String playerPickUpTreasures() {
    return player.collectTreasures();
  }

  @Override
  public String playerPickUpArrows() {
    return player.collectArrows();
  }

  @Override
  public String playerShootAnArrow(String direction, int distance) {
    if (direction == null) {
      throw new IllegalArgumentException("Direction cannot be null");
    }
    if (distance < 1) {
      throw new IllegalArgumentException("Distance should be at least 1");
    }
    try {
      return player.shootArrow(DirectionType.valueOf(direction), distance);
    } catch (IllegalArgumentException iae) {
      throw new IllegalArgumentException("Direction not available");
    }
  }

  @Override
  public List<List<Location>> getCavesMap() {
    return new ArrayList<>(this.cavesMap);
  }

  @Override
  public List<Location> getCavesList() {
    return new ArrayList<>(this.cavesList);
  }

  private void generateDungeon() {
    int tid = 10001;
    for (int i = 0; i < this.numRows; i++) {
      for (int j = 0; j < this.numColumns; j++) {
        Location pivot = cavesMap.get(i).get(j);
        if (i < this.numRows - 1) {
          Location lowCave = cavesMap.get(i + 1).get(j);
          Location lowTunnel = new Tunnel(tid, pivot, lowCave, null, null);
          pivot.setSouthEntrance(lowTunnel);
          lowCave.setNorthEntrance(lowTunnel);
          tunnelsList.add(lowTunnel);
          tid += 1;
        } else {
          if (wrapping == 0) {
            pivot.setSouthEntrance(pivot);
          } else {
            Location lowCave = cavesMap.get(0).get(j);
            Location lowTunnel = new Tunnel(tid, pivot, lowCave, null, null);
            pivot.setSouthEntrance(lowTunnel);
            lowCave.setNorthEntrance(lowTunnel);
            tunnelsList.add(lowTunnel);
            tid += 1;
          }
        }
        if (j < this.numColumns - 1) {
          Location rightCave = cavesMap.get(i).get(j + 1);
          Location rightTunnel = new Tunnel(tid, null, null, pivot, rightCave);
          pivot.setEastEntrance(rightTunnel);
          rightCave.setWestEntrance(rightTunnel);
          tunnelsList.add(rightTunnel);
          tid += 1;
        } else {
          if (wrapping == 0) {
            pivot.setEastEntrance(pivot);
          } else {
            Location rightCave = cavesMap.get(i).get(0);
            Location rightTunnel = new Tunnel(tid, null, null, pivot, rightCave);
            pivot.setEastEntrance(rightTunnel);
            rightCave.setWestEntrance(rightTunnel);
            tunnelsList.add(rightTunnel);
            tid += 1;

          }
        }
        // Set null entrance as itself
        if (i == 0 && wrapping == 0) {
          pivot.setNorthEntrance(pivot);
        }
        if (j == 0 && wrapping == 0) {
          pivot.setWestEntrance(pivot);
        }
      }
    }
  }


  /**
   * Remove tunnels after applying Kruskal algorithm;
   * Convert cave to tunnel if the cave only has two entrances.
   */
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
      for (Location currCave : locations) {
        if (currCave instanceof Cave) {
          int countEntrances = 0;
          if (currCave.getNorthEntrance() != currCave) {
            countEntrances += 1;
          }
          if (currCave.getSouthEntrance() != currCave) {
            countEntrances += 1;
          }
          if (currCave.getWestEntrance() != currCave) {
            countEntrances += 1;
          }
          if (currCave.getEastEntrance() != currCave) {
            countEntrances += 1;
          }

          // If the cave has only two entrances, change its type to tunnel
          if (countEntrances == 2) {
            currCave.changeToTunnel();
          }
        }
      }
    }
  }

  /**
   * Add treasures to specified percentage of caves;
   * For each selected cave, add 2-5 random treasures.
   */
  private void dumpTreasures() {
    treasuresList.add(new Treasures(TreasureType.DIAMONDS));
    treasuresList.add(new Treasures(TreasureType.RUBIES));
    treasuresList.add(new Treasures(TreasureType.SAPPHIRES));

    int numTreasuredCaves = (int) Math.round(cavesList.size() * ((double) treArrowPercent / 100.0));
    List<Location> treasuredCaves = new ArrayList<>();
    for (int i = 0; i < numTreasuredCaves; i++) {
      Location selectedCave = cavesList.get(random.nextInt(cavesList.size()));
      while (treasuredCaves.contains(selectedCave)) {
        selectedCave = cavesList.get(random.nextInt(cavesList.size()));
      }
      treasuredCaves.add(selectedCave);
    }

    for (Location cave : treasuredCaves) {
      // for each selected cave, add 2 to 5 random treasures
      for (int count = 0; count < random.nextInt(4) + 2; count++) {
        cave.addTreasures(treasuresList.get(random.nextInt(3)));
      }
    }
  }

  /**
   * Add 1-2 arrows to specified percentage of caves or tunnels.
   */
  private void dumpArrows() {
    List<Location> caveTunnelList = new ArrayList<>();
    for (List<Location> locations : cavesMap) {
      caveTunnelList.addAll(locations);
    }
    int numArrowedLocations = (int) Math.round(caveTunnelList.size()
            * ((double) treArrowPercent / 100.0));
    List<Location> arrowedLocations = new ArrayList<>();
    for (int i = 0; i < numArrowedLocations; i++) {
      Location selectedLoc = caveTunnelList.get(random.nextInt(caveTunnelList.size()));
      while (arrowedLocations.contains(selectedLoc)) {
        selectedLoc = caveTunnelList.get(random.nextInt(caveTunnelList.size()));
      }
      arrowedLocations.add(selectedLoc);
    }
    for (Location loc : arrowedLocations) {
      // for each selected cave or tunnel, add 1 to 3 arrows
      for (int count = 0; count < random.nextInt(2) + 1; count++) {
        loc.addArrows(new Arrows());
      }
    }
  }

  /**
   * Add specified monsters to specified percentage of caves.
   */
  private void dumpMonsters() {
    int numMonsterCaves = (int) Math.round(cavesList.size() * ((double) monsterPercent / 100.0));
    List<Location> monsterCaves = new ArrayList<>();
    for (int i = 0; i < numMonsterCaves; i++) {
      Location selectedCave = cavesList.get(random.nextInt(cavesList.size()));
      while (monsterCaves.contains(selectedCave)) {
        selectedCave = cavesList.get(random.nextInt(cavesList.size()));
      }
      monsterCaves.add(selectedCave);
    }
    for (Location cave : monsterCaves) {
      // for each selected cave, set the monster
      cave.setMonster(new Monster());
    }
  }

}
