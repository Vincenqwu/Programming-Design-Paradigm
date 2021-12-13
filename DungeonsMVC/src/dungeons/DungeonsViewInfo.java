package dungeons;

/**
 * Pack all information about each cave, such as type of cave, visited, has monster or not.
 */
public class DungeonsViewInfo {
  private final String graphType;
  private final String playerCondition;
  private final boolean currLocation;
  private final boolean visited;
  private final boolean hasMonster;
  private final int smellLevel;

  /**
   * Constructor for the DungeonsViewInfo that takes in 6 parameters about the condition
   * the cave.
   *
   * @param graphType       type of cave for image
   * @param playerCondition condition of the player
   * @param currLocation    current location of the player
   * @param visited         whether this location is visited
   * @param hasMonster      whether this cave has monster
   * @param smellLevel      smell level
   */
  public DungeonsViewInfo(String graphType, String playerCondition, boolean currLocation,
                          boolean visited, boolean hasMonster, int smellLevel) {
    this.graphType = graphType;
    this.playerCondition = playerCondition;
    this.currLocation = currLocation;
    this.visited = visited;
    this.hasMonster = hasMonster;
    this.smellLevel = smellLevel;
  }

  public String getGraphType() {
    return this.graphType;
  }

  public String getPlayerCondition() {
    return this.playerCondition;
  }

  public boolean getCurrLocation() {
    return this.currLocation;
  }

  public boolean getVisited() {
    return this.visited;
  }

  public boolean getHasMonster() {
    return this.hasMonster;
  }

  public int getSmellLevel() {
    return this.smellLevel;
  }


}
