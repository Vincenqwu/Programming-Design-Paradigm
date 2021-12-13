package properties;

/**
 * Class for Treasures with treasure type defined.
 */
public class Treasures {
  private final TreasureType type;

  /**
   * Constructor a treasure object with type.
   *
   * @param type treasure enum type
   */
  public Treasures(TreasureType type) {
    this.type = type;
  }

  /**
   * Get the type of treasure.
   *
   * @return treasure enum type
   */
  public TreasureType getType() {
    return this.type;
  }
}
