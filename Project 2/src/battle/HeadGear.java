package battle;

/**
 * Construct a headgear object with name, type and effect.charismaEffe
 */
public class HeadGear extends AbstractGear {
  /**
   * Construct a headgear object with name, type and effect.
   *
   * @param name               Gear name
   * @param constitutionEffect constitution effect
   * @throws IllegalArgumentException For invalid effect
   */
  public HeadGear(String name, int constitutionEffect) {
    super();
    this.name = name;
    this.type = gearType.HANDGEAR;
    if (constitutionEffect > 10 || constitutionEffect < -10) {
      throw new IllegalArgumentException("The effect must be between -10 to 10");
    }
    this.constitutionEffect = constitutionEffect;
  }

  @Override
  public int compareToPotion(Potion o) {
    return -1;
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
