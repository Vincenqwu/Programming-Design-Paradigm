package battle;

/**
 * Construct a belt class with name, gearType, type, any two effects.
 */
public class Belt extends AbstractGear {
  private int units;

  /**
   * Construct a belt object with name, gearType, type, any two effects.
   *
   * @param name               Belt name
   * @param size               Belt size
   * @param strengthEffect     Strength effect
   * @param constitutionEffect Constitution effect
   * @param dexterityEffect    Dexterity effect
   * @param charismaEffect     Charisma Effect
   * @throws IllegalArgumentException invalid number of effects
   * @throws IllegalArgumentException invalid effect value
   */
  public Belt(String name, gearType.BELTS size, int strengthEffect,
              int constitutionEffect, int dexterityEffect, int charismaEffect) {
    super();
    this.name = name;
    this.beltType = size;
    int counter = 0;
    if (strengthEffect != 0) {
      counter += 1;
    }
    if (constitutionEffect != 0) {
      counter += 1;
    }
    if (dexterityEffect != 0) {
      counter += 1;
    }
    if (charismaEffect != 0) {
      counter += 1;
    }
    if (counter > 2) {
      throw new IllegalArgumentException("Belt can affect no more than two abilities.");
    }
    if (strengthEffect > 10 || strengthEffect < -10 || constitutionEffect > 10
            || constitutionEffect < -10 || dexterityEffect > 10 || dexterityEffect < -10
            || charismaEffect > 10 || charismaEffect < -10) {
      throw new IllegalArgumentException("The effect must be between -10 to 10");
    }
    this.strengthEffect = strengthEffect;
    this.constitutionEffect = constitutionEffect;
    this.dexterityEffect = dexterityEffect;
    this.charismaEffect = charismaEffect;

    int units;
    if (size.equals(gearType.BELTS.SMALL)) {
      units = 1;
    } else if (size.equals(gearType.BELTS.LARGE)) {
      units = 4;
    } else {
      units = 2;
    }
    this.units = units;
  }

  @Override
  public int getUnits() {
    return units;
  }

  @Override
  public int compareToFootWear(FootWear o) {
    return -1;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("\nName: " + this.name);
    sb.append(", Size: " + this.beltType.toString());
    sb.append(", Strength Effect: " + this.getStrengthEffect());
    sb.append(", Constitution Effect: " + this.getConstitutionEffect());
    sb.append(", Dexterity Effect: " + this.getDexterityEffect());
    sb.append(", Charisma Effect: " + this.getCharismaEffect());
    return sb.toString();
  }
}
