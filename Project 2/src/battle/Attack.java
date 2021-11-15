package battle;

/**
 * Create a attack class that takes in a player object and a weapon,
 * which will be used to calculate striking power and potential striking damage.
 */
public class Attack {
  private Player player;
  private Weapon weapon;
  private int strikingPower;
  private int potenStrikingDamage;

  /**
   * Constructor for attack that takes in a player and weapon object.
   * And also set striking power and potential striking damage.
   *
   * @param player Player object
   * @param weapon Weapon object
   */
  public Attack(Player player, Weapon weapon) {
    this.player = player;
    this.weapon = weapon;
    this.strikingPower = this.player.getStrength() + getRandomNumber(1, 10);
    this.potenStrikingDamage = this.player.getStrength()
            + getWeaponDamage(player, weapon);
  }

  /**
   * For testing only.
   * Constructor for attack that takes in a player and weapon object.
   * And also set striking power and potential striking damage.
   * Also takes in a random list for testing.
   *
   * @param player    Player object
   * @param weapon    Weapon object
   * @param randomLst list of predefined random numbers for testing.
   */
  public Attack(Player player, Weapon weapon, int... randomLst) {
    this.player = player;
    this.weapon = weapon;
    if (randomLst.length != 7) {
      throw new IllegalArgumentException("There must be exactly 7 arguments in randomLst");
    }
    this.strikingPower = this.player.getStrength() + randomLst[0];
    this.potenStrikingDamage = this.player.getStrength()
            + getWeaponDamage(player, weapon, randomLst);
  }

  private int getWeaponDamage(Player player, Weapon weapon) {
    int damage;
    if (weapon.getSwordType() != null
            && weapon.getSwordType().equals(weaponType.SWORDS.KATANAS)) {
      damage = getRandomNumber(4, 6) + getRandomNumber(4, 6);
    } else if (weapon.getSwordType() != null
            && weapon.getSwordType().equals(weaponType.SWORDS.TWO_HAND_SWORDS)) {
      damage = getRandomNumber(8, 12);
      if (player.getStrength() <= 14) {
        damage /= 2;
      }
    } else if (weapon.getSwordType() != null
            && weapon.getSwordType().equals(weaponType.SWORDS.BROADSWORDS)) {
      damage = getRandomNumber(6, 10);
    } else if (weapon.getWeaponType() != null
            && weapon.getWeaponType().equals(weaponType.FRAILS)) {
      damage = getRandomNumber(8, 12);
      if (player.getDexterity() <= 14) {
        damage /= 2;
      }
    } else {
      damage = getRandomNumber(6, 10);
    }
    return damage;
  }

  private int getWeaponDamage(Player player, Weapon weapon, int... randomLst) {
    int damage;
    if (weapon.getSwordType() != null
            && weapon.getSwordType().equals(weaponType.SWORDS.KATANAS)) {
      damage = randomLst[1] + randomLst[2];
    } else if (weapon.getSwordType() != null
            && weapon.getSwordType().equals(weaponType.SWORDS.TWO_HAND_SWORDS)) {
      damage = randomLst[3];
      if (player.getStrength() <= 14) {
        damage /= 2;
      }
    } else if (weapon.getSwordType() != null
            && weapon.getSwordType().equals(weaponType.SWORDS.BROADSWORDS)) {
      damage = randomLst[4];
    } else if (weapon.getWeaponType() != null
            && weapon.getWeaponType().equals(weaponType.FRAILS)) {
      damage = randomLst[5];
      if (player.getDexterity() <= 14) {
        damage /= 2;
      }
    } else {
      damage = randomLst[6];
    }
    return damage;
  }

  /**
   * Get the striking power of the attack.
   *
   * @return striking power integer
   */
  public int getStrikingPower() {
    return this.strikingPower;
  }

  /**
   * Get the potential striking damage of the attack.
   *
   * @return the potential striking damage integer
   */
  public int getPotenStrikingDamage() {
    return this.potenStrikingDamage;
  }

  private int getRandomNumber(int min, int max) {
    return (int) ((Math.random() * (max - min)) + min);
  }

  /**
   * Override the toString method to print out the attack.
   *
   * @return A string that describe who attacks, and how much damage is created
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.player.getName() + " with striking power of "
            + this.getStrikingPower() + " make attack with damage "
            + this.getPotenStrikingDamage());
    return sb.toString();
  }

}
