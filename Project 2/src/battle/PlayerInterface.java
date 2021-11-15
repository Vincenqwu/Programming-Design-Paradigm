package battle;

import java.util.List;

/**
 * Interface for the player class that includes the player's name,
 * strength, constitution, dexterity, charisma.
 */
public interface PlayerInterface {
  /**
   * Get player's name.
   *
   * @return name
   */
  String getName();

  /**
   * Get player's strength.
   *
   * @return strength
   */
  int getStrength();

  /**
   * Get player's constitution.
   *
   * @return constitution
   */
  int getConstitution();

  /**
   * Get player's dexterity.
   *
   * @return dexterity
   */
  int getDexterity();

  /**
   * Get player's charisma.
   *
   * @return charisma
   */
  int getCharisma();

  /**
   * Get player's health.
   *
   * @return health
   */
  int getHealth();

  /**
   * Get player's avoid ability.
   *
   * @return avoid ability
   */
  int getAvoidAbility();

  /**
   * Set the player's current health to the original health.
   */
  void resetHealth();

  /**
   * Get the list of gears that player equipped.
   *
   * @return list of gears
   */
  List<Gear> getEquippedGears();

  /**
   * Take a list of gears and let the player equip them.
   *
   * @param gearList List of gears
   */
  void setGear(List<Gear> gearList);

  /**
   * Take a list of gears and let the player equip them.
   * Also takes in a list of random number.
   * This is for testing only.
   *
   * @param gearList  List of gears
   * @param randomInt list of random numbers for testing
   */
  void setGear(List<Gear> gearList, int... randomInt);

  /**
   * Equip weapon for player.
   *
   * @param weapon weapon object
   */
  void setWeapon(Weapon weapon);

  /**
   * Return the weapon used by the player.
   *
   * @return the weapon object
   */
  Weapon getWeapon();

  /**
   * Create a attack object.
   *
   * @return the attack object
   */
  Attack makeAttack();

  /**
   * Takes an attack, and retrieve the damage which
   * will be subtracted from the player's health.
   *
   * @param newAttack attack object.
   * @return String massage
   */
  String receiveAttack(Attack newAttack);

  /**
   * Check if the player's health is 0.
   *
   * @return boolean
   */
  boolean isDead();

  /**
   * Reset the four abilities to original value.
   */
  void reset();

}
