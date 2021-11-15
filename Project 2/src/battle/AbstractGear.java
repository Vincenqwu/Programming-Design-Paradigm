package battle;

/**
 * Abstract class for gear class that has parameters name, type, BeltType,
 * strength effect, constitution effect, dexterity effect, charisma effect.
 */
public abstract class AbstractGear implements Gear {
  protected String name = null;
  protected gearType type = null;
  protected gearType.BELTS beltType = null;
  protected int strengthEffect;
  protected int constitutionEffect;
  protected int dexterityEffect;
  protected int charismaEffect;

  /**
   * Constructor for Abstract gear.
   * Initialize everything to 0.
   */
  public AbstractGear() {
    this.strengthEffect = 0;
    this.constitutionEffect = 0;
    this.dexterityEffect = 0;
    this.charismaEffect = 0;

  }

  /**
   * Get the name of gear.
   *
   * @return name
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Get the strength effect.
   *
   * @return strength effect
   */
  @Override
  public int getStrengthEffect() {
    return this.strengthEffect;
  }

  /**
   * Get the constitution effect.
   *
   * @return constitution effect.
   */
  @Override
  public int getConstitutionEffect() {
    return this.constitutionEffect;
  }

  /**
   * Get the dexterity effect.
   *
   * @return dexterity effect
   */
  @Override
  public int getDexterityEffect() {
    return this.dexterityEffect;
  }

  /**
   * Get the charisma effect.
   *
   * @return charisma effect
   */
  @Override
  public int getCharismaEffect() {
    return this.charismaEffect;
  }

  @Override
  public gearType getGearType() {
    return this.type;
  }

  @Override
  public int getUnits() {
    return 0;
  }

  ;

  @Override
  public gearType.BELTS getBeltType() {
    return this.beltType;
  }

  // ========compareTo() methods for double dispatch========
  @Override
  public int compareTo(Gear o) {
    if (this == o) {
      return 0;
    }
    Class c = o.getClass();
    if (getClass() != c) {
      if (c == HeadGear.class) {
        return this.compareToHeadGear((HeadGear) o);
      } else if (c == Potion.class) {
        return this.compareToPotion((Potion) o);
      } else if (c == Belt.class) {
        return this.compareToBelt((Belt) o);
      } else if (c == FootWear.class) {
        return this.compareToFootWear((FootWear) o);
      }
    }
    return getName().compareTo(o.getName());
  }

  public int compareToHeadGear(HeadGear o) {
    return 1;
  }

  public int compareToPotion(Potion o) {
    return 1;
  }

  public int compareToBelt(Belt o) {
    return 1;
  }

  public int compareToFootWear(FootWear o) {
    return 1;
  }

  // =====================================================
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("\nName: " + this.name);
    sb.append(", Strength Effect: " + this.getStrengthEffect());
    sb.append(", Constitution Effect: " + this.getConstitutionEffect());
    sb.append(", Dexterity Effect: " + this.getDexterityEffect());
    sb.append(", Charisma Effect: " + this.getCharismaEffect());
    return sb.toString();
  }

}
