package battle;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement a player class that includes the player's name,
 * strength, constitution, dexterity, charisma.
 */
public class Player implements PlayerInterface {
  final private String name;
  final private int oriStrength;
  final private int oriConstitution;
  final private int oriDexterity;
  final private int oriCharisma;
  final private int originalHealth;
  private int strength;
  private int constitution;
  private int dexterity;
  private int charisma;
  private int health;
  private Weapon weapon = null;
  private List<Gear> equippedGears;
  private int avoidAbility;
  private int countHeadGears = 0;
  private int countFootWears = 0;
  private int countBeltUnits = 0;

  /**
   * Construct a player instance with parameters name, strength,
   * constitution, dexterity, charisma. Throw exception if the
   * integer input are not not between 6 and 18.
   *
   * @param name         Player's name
   * @param strength     Player's strength
   * @param constitution Player's constitution
   * @param dexterity    Player's dexterity
   * @param charisma     Player's charisma
   * @throws IllegalArgumentException Check if the skill values are between 6 and 18.
   */
  public Player(String name, int strength, int constitution, int dexterity, int charisma) {
    this.name = name;
    if (strength < 6 || strength > 18 || constitution < 6
            || constitution > 18 || dexterity < 6 || dexterity > 18
            || charisma < 6 || charisma > 18) {
      throw new IllegalArgumentException("Player's ability must be between 6 and 18");
    }
    this.strength = strength;
    this.constitution = constitution;
    this.dexterity = dexterity;
    this.charisma = charisma;
    this.health = strength + constitution + dexterity + charisma;
    this.originalHealth = this.health;
    this.equippedGears = new ArrayList<>();
    this.avoidAbility = dexterity;
    this.oriStrength = this.strength;
    this.oriConstitution = this.constitution;
    this.oriDexterity = this.dexterity;
    this.oriCharisma = this.charisma;
  }

  /**
   * Get player's name.
   *
   * @return name
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Get player's strength.
   *
   * @return strength
   */
  @Override
  public int getStrength() {
    return this.strength;
  }

  /**
   * Get player's constitution.
   *
   * @return constitution
   */
  @Override
  public int getConstitution() {
    return this.constitution;
  }

  /**
   * Get player's dexterity.
   *
   * @return dexterity
   */
  @Override
  public int getDexterity() {
    return this.dexterity;
  }

  /**
   * Get player's charisma.
   *
   * @return charisma
   */
  @Override
  public int getCharisma() {
    return this.charisma;
  }

  /**
   * Get player's health.
   *
   * @return health
   */
  @Override
  public int getHealth() {
    return this.health;
  }

  /**
   * Get player's avoid ability.
   *
   * @return avoid ability
   */
  @Override
  public int getAvoidAbility() {
    return this.avoidAbility;
  }

  /**
   * Set the player's current health to the original health.
   */
  @Override
  public void resetHealth() {
    this.health = this.originalHealth;
  }

  /**
   * Get the list of gears that player equipped.
   *
   * @return list of gears
   */
  @Override
  public List<Gear> getEquippedGears() {
    return new ArrayList<>(this.equippedGears);
  }

  /**
   * Take a list of gears and let the player equip them.
   *
   * @param gearList List of gears
   */
  @Override
  public void setGear(List<Gear> gearList) {
    int nHeadGear = 1;
    int nFootWears = 1;
    int nBearUnits = 10;
    for (Gear g : gearList) {
      if (g.getGearType() != null && g.getGearType().equals(gearType.HANDGEAR) && nHeadGear > 0) {
        this.constitution += g.getConstitutionEffect();
        nHeadGear -= 1;
        this.equippedGears.add(g);
        this.countHeadGears += 1;
      } else if (g.getBeltType() != null && (g.getBeltType().equals(gearType.BELTS.LARGE)
              || g.getBeltType().equals(gearType.BELTS.MEDIUM)
              || g.getBeltType().equals(gearType.BELTS.SMALL))
              && nBearUnits >= g.getUnits()) {
        this.constitution += g.getConstitutionEffect();
        this.strength += g.getStrengthEffect();
        this.dexterity += g.getDexterityEffect();
        this.charisma += g.getCharismaEffect();
        nBearUnits -= g.getUnits();
        this.equippedGears.add(g);
        this.countBeltUnits += g.getUnits();
      } else if (g.getGearType() != null && g.getGearType().equals(gearType.FOOTWEAR)
              && nFootWears > 0) {
        this.dexterity += g.getDexterityEffect();
        nFootWears -= 1;
        this.equippedGears.add(g);
        this.countFootWears += 1;
      } else if (g.getGearType() != null && g.getGearType().equals(gearType.POTIONS)) {
        this.constitution += g.getConstitutionEffect();
        this.strength += g.getStrengthEffect();
        this.dexterity += g.getDexterityEffect();
        this.charisma += g.getCharismaEffect();
        this.equippedGears.add(g);
      }
    }
    this.avoidAbility = this.dexterity + getRandomNumber(1, 6);
  }

  /**
   * Take a list of gears and let the player equip them.
   * Also takes in a list of random number.
   * This is for testing only.
   *
   * @param gearList  List of gears
   * @param randomInt list of random numbers for testing
   */
  @Override
  public void setGear(List<Gear> gearList, int... randomInt) {
    int nHeadGear = 1;
    int nFootWears = 1;
    int nBearUnits = 10;
    for (Gear g : gearList) {
      if (g.getGearType() != null && g.getGearType().equals(gearType.HANDGEAR) && nHeadGear > 0) {
        this.constitution += g.getConstitutionEffect();
        nHeadGear -= 1;
        this.equippedGears.add(g);
        this.countHeadGears += 1;
      } else if (g.getBeltType() != null && (g.getBeltType().equals(gearType.BELTS.LARGE)
              || g.getBeltType().equals(gearType.BELTS.MEDIUM)
              || g.getBeltType().equals(gearType.BELTS.SMALL))
              && nBearUnits >= g.getUnits()) {
        this.constitution += g.getConstitutionEffect();
        this.strength += g.getStrengthEffect();
        this.dexterity += g.getDexterityEffect();
        this.charisma += g.getCharismaEffect();
        nBearUnits -= g.getUnits();
        this.equippedGears.add(g);
        this.countBeltUnits += g.getUnits();
      } else if (g.getGearType() != null && g.getGearType().equals(gearType.FOOTWEAR)
              && nFootWears > 0) {
        this.dexterity += g.getDexterityEffect();
        nFootWears -= 1;
        this.equippedGears.add(g);
        this.countFootWears += 1;
      } else if (g.getGearType() != null && g.getGearType().equals(gearType.POTIONS)) {
        this.constitution += g.getConstitutionEffect();
        this.strength += g.getStrengthEffect();
        this.dexterity += g.getDexterityEffect();
        this.charisma += g.getCharismaEffect();
        this.equippedGears.add(g);
      }
    }
    this.avoidAbility = this.dexterity + randomInt[0];
  }

  public int getHeadGearCount() {
    return this.countHeadGears;
  }

  public int getBearUnitCount() {
    return this.countBeltUnits;
  }

  public int getFootWearCount() {
    return this.countFootWears;
  }

  /**
   * Equip weapon for player.
   *
   * @param weapon weapon object
   */
  @Override
  public void setWeapon(Weapon weapon) {
    this.weapon = weapon;
  }

  /**
   * Return the weapon used by the player.
   *
   * @return the weapon object
   */
  @Override
  public Weapon getWeapon() {
    return this.weapon;
  }

  /**
   * Create a attack object.
   *
   * @return the attack object
   */
  @Override
  public Attack makeAttack() {
    return new Attack(this, this.weapon);
  }

  /**
   * Takes an attack, and retrieve the damage which
   * will be subtracted from the player's health.
   *
   * @param newAttack attack object.
   * @return String massage
   */
  @Override
  public String receiveAttack(Attack newAttack) {
    StringBuilder sb = new StringBuilder();
    if (newAttack.getStrikingPower() > this.avoidAbility) {
      int actualDemage = newAttack.getPotenStrikingDamage() - this.getConstitution();
      if (actualDemage > 0) {
        this.health -= actualDemage;
        sb.append("Success! The attack has successfully hit the opponent with damage of "
                + actualDemage);
      } else {
        sb.append("Failed. The attack does not get through the constitution of opponent");
      }
    } else {
      sb.append("Failed. The attack is avoided by the opponent with avoidability of "
              + this.getAvoidAbility());
    }
    return sb.toString();
  }

  /**
   * Check if the player's health is 0.
   *
   * @return boolean
   */
  @Override
  public boolean isDead() {
    return this.health <= 0;
  }

  private int getRandomNumber(int min, int max) {
    return (int) ((Math.random() * (max - min)) + min);
  }

  @Override
  public void reset() {
    this.strength = this.oriStrength;
    this.constitution = this.oriConstitution;
    this.dexterity = this.oriDexterity;
    this.charisma = this.oriCharisma;
  }
}
