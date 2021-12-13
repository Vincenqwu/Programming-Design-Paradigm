package locations;

import java.util.ArrayList;
import java.util.List;

/**
 * Tunnel class that implements the location interface.
 * Fields include src entrance and dst entrance.
 */
public class Tunnel extends AbstractLocation {
  private Location srcEntrance;
  private Location dstEntrance;

  /**
   * Constructor for tunnel class that define the tunnel identifier,
   * src entrance and dst entrance.
   *
   * @param id tunnel identifier
   * @param ne north entrance
   * @param se south entrance
   * @param we west entrance
   * @param ee east entrance
   * @throws IllegalArgumentException if the number of entrances is not 2, throw an error.
   */
  public Tunnel(int id, Location ne, Location se, Location we, Location ee) {
    super(id, ne, se, we, ee);
    this.type = LocationType.TUNNEL;
    getSrcDst();
  }

  private void getSrcDst() {
    List<Location> lList = new ArrayList<>();
    lList.add(this.getNorthEntrance());
    lList.add(this.getSouthEntrance());
    lList.add(this.getWestEntrance());
    lList.add(this.getEastEntrance());
    boolean getSrc = false;
    int counter = 0;
    for (Location location : lList) {
      if (location != null) {
        counter += 1;
        if (!getSrc) {
          this.srcEntrance = location;
          getSrc = true;
        } else {
          this.dstEntrance = location;
        }
      }
    }
    if (counter != 2) {
      throw new IllegalArgumentException("The tunnel should have exactly two entrances");
    }
  }

  @Override
  public Location getSrcEntrance() {
    getSrcDst();
    return this.srcEntrance;
  }

  @Override
  public Location getDstEntrance() {
    getSrcDst();
    return this.dstEntrance;
  }

}
