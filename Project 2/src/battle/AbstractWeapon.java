package battle;

/**
 * Abstract class for weapon class that can retrieve the information of the weapon type
 * and the sword type if it is a sword.
 */
public abstract class AbstractWeapon implements Weapon {
  protected weaponType type;
  protected weaponType.SWORDS swordType;

  /**
   * Initialize weapon with default value null.
   */
  public AbstractWeapon() {
    this.type = null;
    this.swordType = null;
  }

  @Override
  public weaponType getWeaponType() {
    return this.type;
  }

  @Override
  public weaponType.SWORDS getSwordType() {
    return this.swordType;
  }
}
