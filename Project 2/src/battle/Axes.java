package battle;

/**
 * Construct a axes class with type.
 */
public class Axes extends AbstractWeapon {
  /**
   * Construct a axes class with type.
   */
  public Axes() {
    super();
    this.type = weaponType.AXES;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("\nWeapon: " + this.type.toString());
    return sb.toString();
  }
}
