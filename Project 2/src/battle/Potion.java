package battle;

/**
 * Construct a potion class with name, any effects.
 */
public class Potion extends AbstractGear {

  /**
   * Construct a potion object with name, any of the effects.
   *
   * @param name               gear name
   * @param strengthEffect     Strangth effect
   * @param constitutionEffect Constitution effect
   * @param dexterityEffect    Dexterity effect
   * @param charismaEffect     Charisma effect
   * @throws IllegalArgumentException invalid effects
   */
  public Potion(String name, int strengthEffect,
                int constitutionEffect, int dexterityEffect, int charismaEffect) {
    super();
    this.name = name;
    this.type = gearType.POTIONS;
    if (strengthEffect > 10 || strengthEffect < -10 || constitutionEffect > 10
            || constitutionEffect < -10 || dexterityEffect > 10 || dexterityEffect < -10
            || charismaEffect > 10 || charismaEffect < -10) {
      throw new IllegalArgumentException("The effect must be between -10 to 10");
    }
    this.strengthEffect = strengthEffect;
    this.constitutionEffect = constitutionEffect;
    this.dexterityEffect = dexterityEffect;
    this.charismaEffect = charismaEffect;
  }

  @Override
  public int compareToBelt(Belt o) {
    return -1;
  }

  @Override
  public int compareToFootWear(FootWear o) {
    return -1;
  }

}
