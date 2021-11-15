package battle;

/**
 * Interface for weapon class that can retrieve the information of the weapon type
 * and the sword type if it is a sword.
 */
public interface Weapon {
  /**
   * Get the type of weapon.
   *
   * @return weapon type
   */
  weaponType getWeaponType();

  /**
   * Get the type of sword if it is a sword, else it is null.
   *
   * @return sword type
   */
  weaponType.SWORDS getSwordType();
}
