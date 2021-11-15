package battle;

/**
 * Construct a foot wear class with name, dexterity effect.
 */
public class FootWear extends AbstractGear {

  /**
   * Construct a footwear object with name, dexterity effect.
   *
   * @param name            name
   * @param dexterityEffect dexterity effect
   * @throws IllegalArgumentException invalid effects
   */
  public FootWear(String name, int dexterityEffect) {
    super();
    this.name = name;
    this.type = gearType.FOOTWEAR;
    if (dexterityEffect > 10 || dexterityEffect < -10) {
      throw new IllegalArgumentException("The effect must be between -10 to 10");
    }
    this.dexterityEffect = dexterityEffect;
  }
}
