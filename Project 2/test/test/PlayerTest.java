package test;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import battle.Attack;
import battle.Axes;
import battle.Battle;
import battle.Belt;
import battle.FootWear;
import battle.Gear;
import battle.HeadGear;
import battle.Player;
import battle.Potion;
import battle.RunBattle;
import battle.Weapon;
import battle.gearType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Class for testing the player's object, and the RunBattle object,
 * including checking valid parameters, set gears, set weapons, make and receive attacks,
 * reset to original skills, and correctly setting up players in the RunBattle.
 */
public class PlayerTest {

  Player player;
  Player player2;
  Player player3;
  Weapon axes;
  List<Gear> gearList;
  Battle battle;

  @Before
  public void setUp() {
    battle = new RunBattle();
    player = new Player("PlayerTest", 10, 10, 10, 10);
    player2 = new Player("PlayerTest2", 18, 18, 18, 18);
    player3 = new Player("PlayerTest", 6, 6, 6, 6);
    axes = new Axes();

    Gear hg1 = new HeadGear("hg+5", 5);
    Gear hg2 = new HeadGear("hg+2", 2);

    Gear fw1 = new FootWear("fw+5", 5);
    Gear fw2 = new FootWear("fw+2", 2);

    Gear pt1 = new Potion("pt1", 1, 0, 2, 3);
    Gear pt2 = new Potion("pt2", 2, 8, 0, 0);
    Gear pt3 = new Potion("pt3", 8, 0, 0, 0);

    Gear bt1 = new Belt("bt_large1", gearType.BELTS.LARGE, 5, 0, -1, 0);
    Gear bt2 = new Belt("bt_medium2", gearType.BELTS.MEDIUM, 0, 1, 1, 0);
    Gear bt3 = new Belt("bt_large3", gearType.BELTS.LARGE, -1, 0, 3, 0);
    Gear bt4 = new Belt("bt_small4", gearType.BELTS.SMALL, 0, 0, 0, 0);

    gearList = new ArrayList<>();
    gearList.add(hg1);
    gearList.add(hg2);
    gearList.add(fw1);
    gearList.add(fw2);
    gearList.add(pt1);
    gearList.add(pt2);
    gearList.add(pt3);
    gearList.add(bt1);
    gearList.add(bt2);
    gearList.add(bt3);
    gearList.add(bt4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayerAbilities() {
    new Player("PlayerTest", 10, 5, 10, 10);
  }

  @Test
  public void testGet() {
    assertEquals("PlayerTest", player.getName());
    assertEquals(10, player.getStrength());
    assertEquals(10, player.getConstitution());
    assertEquals(10, player.getCharisma());
    assertEquals(10, player.getDexterity());
  }

  @Test
  public void testSetGear() {
    player.setGear(gearList, 1);
    assertEquals(25, player.getStrength());
    assertEquals(24, player.getConstitution());
    assertEquals(20, player.getDexterity());
    assertEquals(13, player.getCharisma());
    assertEquals(21, player.getAvoidAbility());
  }

  @Test
  public void testSetHeadGear() {
    player.setGear(gearList, 1);
    assertEquals(1, player.getHeadGearCount());
  }

  @Test
  public void testSetFootWear() {
    player.setGear(gearList, 1);
    assertEquals(1, player.getFootWearCount());
  }

  @Test
  public void testSetBelt() {
    player.setGear(gearList, 1);
    assertEquals(10, player.getBearUnitCount());
  }

  @Test
  public void testSetWeapon() {
    player.setWeapon(axes);
    assertEquals(axes, player.getWeapon());
  }

  @Test
  public void testAttack() {
    Attack newAttack = new Attack(player, axes, 5, 4, 6, 10, 8, 10, 8);
    player.receiveAttack(newAttack);
    assertEquals(32, player.getHealth());
  }

  @Test
  public void testAttack2() {
    Attack newAttack = new Attack(player, axes, 5, 4, 6, 10, 8, 10, 8);
    String expected = "PlayerTest with striking power of 15 make attack with damage 18";
    assertEquals(expected, newAttack.toString());
  }

  @Test
  public void testAttack3() {
    Attack newAttack = new Attack(player2, axes, 5, 4, 6, 10, 8, 10, 8);
    String expected = "Success! The attack has successfully hit the opponent with damage of 16";
    assertEquals(expected, player.receiveAttack(newAttack));
  }

  @Test
  public void testAttack4() {
    Attack newAttack = new Attack(player3, axes, 5, 4, 6, 10, 8, 10, 8);
    String expected = "Failed. The attack is avoided by the opponent with avoidability of 18";
    assertEquals(expected, player2.receiveAttack(newAttack));
  }

  @Test
  public void testAttack5() {
    Player player5 = new Player("PlayerTest5", 6, 6, 6, 6);
    Player player6 = new Player("PlayerTest6", 6, 13, 6, 6);
    Attack newAttack = new Attack(player5, axes, 5, 4, 6, 10, 8, 10, 6);
    String expected = "Failed. The attack does not get through the constitution of opponent";
    assertEquals(expected, player6.receiveAttack(newAttack));
  }

  @Test
  public void testIsDead() {
    Attack newAttack = new Attack(player, axes, 5, 4, 6, 10, 8, 10, 8);
    player.receiveAttack(newAttack);
    player.receiveAttack(newAttack);
    player.receiveAttack(newAttack);
    player.receiveAttack(newAttack);
    player.receiveAttack(newAttack);
    assertEquals(0, player.getHealth());
    assertTrue(player.isDead());
  }

  @Test
  public void testReset() {
    player.setGear(gearList, 1);
    player.reset();
    assertEquals(10, player.getStrength());
    assertEquals(10, player.getCharisma());
    assertEquals(10, player.getDexterity());
    assertEquals(10, player.getConstitution());
  }

  @Test
  public void testSetPlayers() {
    String expected = "\nPlayer 1 comes in:"
            + "\nstrength = 10"
            + "\nconstitution = 10"
            + "\ndexterity = 10"
            + "\ncharisma = 10";
    assertEquals(expected, battle.setPlayer1(10, 10, 10, 10));
    battle.setPlayer1(10, 10, 10, 10);

  }
}