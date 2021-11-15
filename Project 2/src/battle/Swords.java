package battle;

/**
 * Construct a sword class with sword type.
 */
public class Swords extends AbstractWeapon {
  /**
   * Construct a sword class with sword type.
   *
   * @param type sword type
   */
  public Swords(weaponType.SWORDS type) {
    super();
    this.swordType = type;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("\nWeapon: " + this.swordType.toString());
    return sb.toString();
  }
}
