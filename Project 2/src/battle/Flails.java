package battle;

/**
 * Construct a flails class with type.
 */
public class Flails extends AbstractWeapon {
  /**
   * Construct a flail object with type.
   */
  public Flails() {
    super();
    this.type = weaponType.FRAILS;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("\nWeapon: " + this.type.toString());
    return sb.toString();
  }
}
