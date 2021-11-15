package test;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import battle.Attack;
import battle.Axes;
import battle.Flails;
import battle.Player;
import battle.Swords;
import battle.Weapon;
import battle.weaponType;

/**
 * Class for testing the Attack object, including checking weapon damage,
 * striking power and potential striking power.
 */
public class AttackTest {

  private Player player;
  private Player player2;
  private Weapon katanas;
  private Weapon twoHandSwords;
  private Weapon broadswords;
  private Weapon axes;
  private Weapon flails;

  @Before
  public void setUp() {
    player = new Player("PlayerTest", 10, 10, 10, 10);
    player2 = new Player("PlayerTest2", 18, 18, 18, 18);
    katanas = new Swords(weaponType.SWORDS.KATANAS);
    twoHandSwords = new Swords(weaponType.SWORDS.TWO_HAND_SWORDS);
    broadswords = new Swords(weaponType.SWORDS.BROADSWORDS);
    axes = new Axes();
    flails = new Flails();
  }

  @Test
  public void testStrikingPower() {
    Attack attack = new Attack(player, katanas, 5, 4, 6, 10, 8, 10, 8);
    assertEquals(15, attack.getStrikingPower());
  }

  @Test
  public void testKatanas() {
    Attack attack = new Attack(player, katanas, 5, 4, 6, 10, 8, 10, 8);
    assertEquals(20, attack.getPotenStrikingDamage());
  }

  @Test
  public void testTwoHandSwords() {
    Attack attack = new Attack(player, twoHandSwords, 5, 4, 6, 10, 8, 10, 8);
    assertEquals(15, attack.getPotenStrikingDamage());
  }

  @Test
  public void testTwoHandSwords2() {
    Attack attack = new Attack(player2, twoHandSwords, 5, 4, 6, 10, 8, 10, 8);
    assertEquals(28, attack.getPotenStrikingDamage());
  }

  @Test
  public void testBroadSwords() {
    Attack attack = new Attack(player, broadswords, 5, 4, 6, 10, 8, 10, 8);
    assertEquals(18, attack.getPotenStrikingDamage());
  }

  @Test
  public void testFlails() {
    Attack attack = new Attack(player, flails, 5, 4, 6, 10, 8, 10, 8);
    assertEquals(15, attack.getPotenStrikingDamage());
  }

  @Test
  public void testFlails2() {
    Attack attack = new Attack(player2, flails, 5, 4, 6, 10, 8, 10, 8);
    assertEquals(28, attack.getPotenStrikingDamage());
  }

  @Test
  public void testAxes() {
    Attack attack = new Attack(player, axes, 5, 4, 6, 10, 8, 10, 8);
    assertEquals(18, attack.getPotenStrikingDamage());
  }

}