package test;


import org.junit.Before;
import org.junit.Test;

import battle.Belt;
import battle.FootWear;
import battle.Gear;
import battle.HeadGear;
import battle.Potion;
import battle.gearType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Class for testing the gear object, including checking valid parameters,
 * valid return value of methods.
 */
public class GearTest {
  private Gear hg1;
  private Gear hg2;
  private Gear bt1;
  private Gear pt1;
  private Gear fw1;
  private Gear fw2;

  @Before
  public void setUp() {
    hg1 = new HeadGear("head1", 5);
    hg2 = new HeadGear("headgear", 2);
    bt1 = new Belt("SmallGear", gearType.BELTS.SMALL, 2, 1, 0, 0);
    pt1 = new Potion("Potion1", 1, 2, 3, 4);
    fw1 = new FootWear("FootWear1", 2);
    fw2 = new FootWear("FootWear2", -1);
  }

  public int getRandomNumber(int min, int max) {
    return (int) ((Math.random() * (max - min)) + min);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHeadGear1() {
    new HeadGear("TestHeadGear", -11);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHeadGear2() {
    new HeadGear("TestHeadGear", 11);
  }

  @Test
  public void testHeadGear3() {
    assertEquals(5, hg1.getConstitutionEffect());
    assertEquals(0, hg1.getCharismaEffect());
    assertEquals(gearType.HANDGEAR, hg2.getGearType());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPotion1() {
    new Potion("TestPotion", 1, 2, 3, 20);
  }

  @Test
  public void testPotion2() {
    assertEquals(4, pt1.getCharismaEffect());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBelt1() {
    new Belt("TestBelt", gearType.BELTS.SMALL, 1, 2, 3, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBelt2() {
    new Belt("TestBelt", gearType.BELTS.SMALL, -11, 2, 3, 0);
  }

  @Test
  public void testBelt3() {
    assertEquals(0, hg2.getCharismaEffect());
    assertNotEquals(gearType.BELTS.LARGE, bt1.getBeltType());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFootWear1() {
    new HeadGear("TestFootWear", 21);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHFootWear2() {
    new HeadGear("TestFootWear", -14);
  }

  @Test
  public void testFootWear3() {
    assertEquals(2, fw1.getDexterityEffect());
    assertEquals(0, fw1.getConstitutionEffect());
    assertEquals(gearType.FOOTWEAR, fw2.getGearType());
  }

}