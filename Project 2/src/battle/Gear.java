package battle;

/**
 * Interface for gear class that can retrieve the effects for the 4 basic abilities.
 * Also include the gear name, type. If it is a belt, also get the units, and belt type.
 */
public interface Gear extends Comparable<Gear> {

  /**
   * Get the name of the gear.
   *
   * @return String of name
   */
  String getName();

  /**
   * Get the strength effect.
   *
   * @return Integer of strength effect
   */
  int getStrengthEffect();

  /**
   * Get the constitution effect.
   *
   * @return Integer of constitution effect.
   */
  int getConstitutionEffect();

  /**
   * Get the dexterity effect.
   *
   * @return Integer of dexterity effect.
   */
  int getDexterityEffect();

  /**
   * Get the charisma effect.
   *
   * @return Integer of charisma effect.
   */
  int getCharismaEffect();

  /**
   * Return the type of gear.
   *
   * @return gear type
   */
  gearType getGearType();

  /**
   * Return the number of units of the belt, default 0.
   *
   * @return number of units
   */
  int getUnits();

  /**
   * Return the size of belt, default null.
   *
   * @return belt size
   */
  gearType.BELTS getBeltType();

}
