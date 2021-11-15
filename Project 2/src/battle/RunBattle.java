package battle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Implement the battle game.
 * Set up two players with four skills, equips gears and weapon,
 * and get attack order, and make attack and choice of rematch.
 * Also gives description of each player when they get in the arena and make attack.
 */
public class RunBattle implements Battle {
  private PlayerInterface player1;
  private PlayerInterface player2;
  private List<Gear> gearBag;
  private List<Weapon> weaponBag;
  private int playersTurn = 0;

  /**
   * Construct the battle object.
   * Set up the gear bag and weapon bag.
   */
  public RunBattle() {
    gearBag = new ArrayList<>();
    weaponBag = new ArrayList<>();
    setGearBag();
    setWeaponBag();
  }

  /**
   * Set player 1 with four abilities.
   *
   * @return Description
   */
  @Override
  public String setPlayer1() {
    int strength;
    int constitution;
    int dexterity;
    int charisma;
    strength = rollDice(4);
    constitution = rollDice(4);
    dexterity = rollDice(4);
    charisma = rollDice(4);
    player1 = new Player("Player 1", strength, constitution, dexterity, charisma);
    String output = "\nPlayer 1 comes in:"
            + "\nstrength = " + strength
            + "\nconstitution = " + constitution
            + "\ndexterity = " + dexterity
            + "\ncharisma = " + charisma;
    return output;
  }

  /**
   * Set player 1 with four abilities.
   * Only for testing.
   *
   * @param randomLst dice values
   * @return Description
   */
  @Override
  public String setPlayer1(int... randomLst) {
    int strength;
    int constitution;
    int dexterity;
    int charisma;
    strength = randomLst[0];
    constitution = randomLst[1];
    dexterity = randomLst[2];
    charisma = randomLst[3];
    player1 = new Player("Player 1", strength, constitution, dexterity, charisma);
    String output = "\nPlayer 1 comes in:"
            + "\nstrength = " + strength
            + "\nconstitution = " + constitution
            + "\ndexterity = " + dexterity
            + "\ncharisma = " + charisma;
    return output;
  }

  /**
   * Set player 2 with four abilities.
   *
   * @return Description
   */
  @Override
  public String setPlayer2() {
    int strength;
    int constitution;
    int dexterity;
    int charisma;
    strength = rollDice(4);
    constitution = rollDice(4);
    dexterity = rollDice(4);
    charisma = rollDice(4);
    player2 = new Player("Player 2", strength, constitution, dexterity, charisma);
    String output = "\nPlayer 2 comes in:"
            + "\nstrength = " + strength
            + "\nconstitution = " + constitution
            + "\ndexterity = " + dexterity
            + "\ncharisma = " + charisma;
    return output;
  }

  /**
   * Equip player 1 with gears and weapons.
   *
   * @return Description
   */
  @Override
  public String equipPlayer1() {
    Collections.shuffle(gearBag);
    Collections.shuffle(weaponBag);
    player1.setGear(gearBag.subList(0, 20));
    player1.setWeapon(weaponBag.get(0));
    Gear[] sortedGears = player1.getEquippedGears().toArray(new Gear[0]);
    Arrays.sort(sortedGears);
    StringBuilder sb = new StringBuilder();
    sb.append("Player 1 has equipped the following gears:");
    for (Gear sortedGear : sortedGears) {
      sb.append(sortedGear.toString());
    }
    sb.append("\n\nPlayer 1 has received the following weapon:");
    sb.append(player1.getWeapon().toString());
    return sb.toString();
  }

  /**
   * Equip player 2 with gears and weapons.
   *
   * @return Description
   */
  @Override
  public String equipPlayer2() {
    Collections.shuffle(gearBag);
    Collections.shuffle(weaponBag);
    player2.setGear(gearBag.subList(0, 20));
    player2.setWeapon(weaponBag.get(0));
    Gear[] sortedGears = player2.getEquippedGears().toArray(new Gear[0]);
    Arrays.sort(sortedGears);
    StringBuilder sb = new StringBuilder();
    sb.append("Player 2 has equipped the following gears:");
    for (Gear sortedGear : sortedGears) {
      sb.append(sortedGear.toString());
    }
    sb.append("\n\nPlayer 2 has received the following weapon:");
    sb.append(player2.getWeapon().toString());
    return sb.toString();
  }

  private int rollDice(int times) {
    int min = 7;
    int total = 0;
    int currVal = 0;
    for (int i = 0; i < times; i++) {
      currVal = getRandomNumber(2, 6);
      total += currVal;
      min = Math.min(min, currVal);
    }
    return total - min;
  }

  private int getRandomNumber(int min, int max) {
    return (int) ((Math.random() * (max - min)) + min);
  }

  private void setGearBag() {
    Gear hg1 = new HeadGear("hg+5", 5);
    Gear hg2 = new HeadGear("hg+1", 1);
    Gear hg3 = new HeadGear("hg+3", 3);
    Gear hg4 = new HeadGear("hg-1", -1);
    Gear hg5 = new HeadGear("hg-2", -2);

    Gear fw1 = new FootWear("fw+5", 5);
    Gear fw2 = new FootWear("fw+3", 3);
    Gear fw3 = new FootWear("fw+1", 1);
    Gear fw4 = new FootWear("fw-1", -1);
    Gear fw5 = new FootWear("fw-2", -2);

    Gear pt1 = new Potion("pt1", 1, 0, 2, 0);
    Gear pt2 = new Potion("pt2", 2, 6, 0, 0);
    Gear pt3 = new Potion("pt3", 8, 0, 0, 0);
    Gear pt4 = new Potion("pt4", 0, 5, 0, 0);
    Gear pt5 = new Potion("pt5", 6, 2, 0, 0);
    Gear pt6 = new Potion("pt6", 0, 6, 0, 0);
    Gear pt7 = new Potion("pt7", 4, 5, 5, 0);
    Gear pt8 = new Potion("pt8", 0, 1, -3, 0);
    Gear pt9 = new Potion("pt9", 6, 0, 4, 0);
    Gear pt10 = new Potion("pt10", 0, 5, 0, 5);
    Gear pt11 = new Potion("pt11", 8, 0, 0, 2);
    Gear pt12 = new Potion("pt12", 8, 0, 0, 5);
    Gear pt13 = new Potion("pt13", -1, 3, -1, 2);
    Gear pt14 = new Potion("pt14", 4, -1, -2, 0);
    Gear pt15 = new Potion("pt15", -1, 0, 3, -2);

    Gear bt1 = new Belt("bt_small1", gearType.BELTS.SMALL, 5, 0, -1, 0);
    Gear bt2 = new Belt("bt_small2", gearType.BELTS.SMALL, 0, 1, 1, 0);
    Gear bt3 = new Belt("bt_small3", gearType.BELTS.SMALL, -1, 0, -2, 0);
    Gear bt4 = new Belt("bt_small4", gearType.BELTS.SMALL, 0, 3, 0, 2);
    Gear bt5 = new Belt("bt_small5", gearType.BELTS.SMALL, 0, 0, 2, -1);
    Gear bt6 = new Belt("bt_med1", gearType.BELTS.MEDIUM, 6, 0, 0, 1);
    Gear bt7 = new Belt("bt_med2", gearType.BELTS.MEDIUM, 0, 4, 0, 1);
    Gear bt8 = new Belt("bt_med3", gearType.BELTS.MEDIUM, 0, 0, 4, 0);
    Gear bt9 = new Belt("bt_med4", gearType.BELTS.MEDIUM, -1, 5, 0, 0);
    Gear bt10 = new Belt("bt_med5", gearType.BELTS.MEDIUM, 0, 0, -2, 5);
    Gear bt11 = new Belt("bt_large1", gearType.BELTS.LARGE, 8, 0, 5, 0);
    Gear bt12 = new Belt("bt_large2", gearType.BELTS.LARGE, 0, 6, 0, 5);
    Gear bt13 = new Belt("bt_large3", gearType.BELTS.LARGE, 0, -1, 4, 0);
    Gear bt14 = new Belt("bt_large4", gearType.BELTS.LARGE, 8, 0, 0, 1);
    Gear bt15 = new Belt("bt_large5", gearType.BELTS.LARGE, 0, 0, -5, 8);

    gearBag.add(hg1);
    gearBag.add(hg2);
    gearBag.add(hg3);
    gearBag.add(hg4);
    gearBag.add(hg5);
    gearBag.add(fw1);
    gearBag.add(fw2);
    gearBag.add(fw3);
    gearBag.add(fw4);
    gearBag.add(fw5);
    gearBag.add(pt1);
    gearBag.add(pt2);
    gearBag.add(pt3);
    gearBag.add(pt4);
    gearBag.add(pt5);
    gearBag.add(pt6);
    gearBag.add(pt7);
    gearBag.add(pt8);
    gearBag.add(pt9);
    gearBag.add(pt10);
    gearBag.add(pt11);
    gearBag.add(pt12);
    gearBag.add(pt13);
    gearBag.add(pt14);
    gearBag.add(pt15);
    gearBag.add(bt1);
    gearBag.add(bt2);
    gearBag.add(bt3);
    gearBag.add(bt4);
    gearBag.add(bt5);
    gearBag.add(bt6);
    gearBag.add(bt7);
    gearBag.add(bt8);
    gearBag.add(bt9);
    gearBag.add(bt10);
    gearBag.add(bt11);
    gearBag.add(bt12);
    gearBag.add(bt13);
    gearBag.add(bt14);
    gearBag.add(bt15);
  }

  private void setWeaponBag() {
    Weapon katanas = new Swords(weaponType.SWORDS.KATANAS);
    Weapon broadswords = new Swords(weaponType.SWORDS.BROADSWORDS);
    Weapon two_hand_swords = new Swords(weaponType.SWORDS.TWO_HAND_SWORDS);
    Weapon axes = new Axes();
    Weapon flails = new Flails();

    weaponBag.add(katanas);
    weaponBag.add(broadswords);
    weaponBag.add(two_hand_swords);
    weaponBag.add(axes);
    weaponBag.add(flails);
  }

  /**
   * Decide who go first based on higher charisma.
   */
  @Override
  public void whoFirst() {
    if (player1.getCharisma() > player2.getCharisma()) {
      playersTurn = 1;
    } else {
      playersTurn = 2;
    }
  }

  /**
   * Get the next player.
   *
   * @return Player order
   */
  @Override
  public int nextPlayer() {
    return this.playersTurn;
  }

  /**
   * Let player attack each other.
   *
   * @return Attack result
   */
  @Override
  public String doAttack() {
    StringBuilder sb = new StringBuilder();
    Attack newAttack;
    if (this.playersTurn == 1) {
      newAttack = player1.makeAttack();
      sb.append(newAttack.toString());
      sb.append("\n" + player2.receiveAttack(newAttack));
      playersTurn = 2;
    } else {
      newAttack = player2.makeAttack();
      sb.append(newAttack.toString());
      sb.append("\n" + player1.receiveAttack(newAttack));
      playersTurn = 1;
    }
    return sb.toString();
  }

  /**
   * Check if one player is dead.
   *
   * @return Boolean
   */
  @Override
  public boolean isOver() {
    return player1.isDead() || player2.isDead();
  }

  /**
   * Return the winner.
   *
   * @return String
   */
  @Override
  public String getWinner() {
    if (player1.isDead()) {
      return "Player 2";
    } else {
      return "Player 1";
    }
  }

  /**
   * Get the current result of the battle.
   *
   * @return Description
   */
  @Override
  public String getResult() {
    if (isOver()) {
      return "Battle is over, and the winner is " + this.getWinner();
    } else {
      return "Nobody wins yet, the battle is a draw";
    }
  }

  /**
   * Start over and reset both players.
   */
  @Override
  public String reMatch() {
    this.setPlayer1();
    this.setPlayer2();
    this.equipPlayer1();
    this.equipPlayer2();
    return "Players has been reset";
  }
}
