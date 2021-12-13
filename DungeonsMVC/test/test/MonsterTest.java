package test;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import dungeons.Dungeons;
import dungeons.DungeonsImpl;
import locations.DirectionType;
import locations.Location;
import player.Monster;
import player.People;
import player.PeopleInterface;
import properties.Arrows;
import random.PseudoRandom;
import random.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Class for testing arrow and monsters:
 * Player can move from one cave to another;
 * Player shoot an arrow in the darkness;
 * Arrow hit the monster the first time and the second time;
 * Player shoot an arrow in caves and tunnels;
 * Percentage of arrows in the dungeons.
 */
public class MonsterTest {
  Dungeons monsterDungeons;
  Random testRandom;
  PeopleInterface player;

  @Before
  public void setUp() {
    testRandom = new PseudoRandom(new int[24]);
    monsterDungeons = new DungeonsImpl(4, 4, 0, 3, 100, 0, testRandom);
    monsterDungeons.generateDungeons();
    List<List<Location>> cavesMap = monsterDungeons.getCavesMap();
    cavesMap.get(0).get(2).setMonster(new Monster());
    cavesMap.get(1).get(3).setMonster(new Monster());

    Location start = cavesMap.get(1).get(1);
    Location end = cavesMap.get(3).get(3);
    for (int i = 0; i < 6; i++) {
      start.addArrows(new Arrows());
    }
    player = new People("Player", start, end);

  }

  @Test
  public void testPlayerMove() {
    player.move(DirectionType.WEST);
    assertEquals(5, player.getCurrLocation().getlId());
    player.move(DirectionType.NORTH);
    assertEquals(1, player.getCurrLocation().getlId());
  }

  @Test
  public void testShootInDarkness() {
    String result = player.shootArrow(DirectionType.SOUTH, 1);
    assertEquals("\nYou shoot an arrow into the darkness", result);
    result = player.shootArrow(DirectionType.WEST, 2);
    assertEquals("\nThe arrow hits the wall", result);
  }

  @Test
  public void testHitMonster() {
    String result = player.shootArrow(DirectionType.EAST, 2);
    assertEquals("\nYou hear a great howl in the distance", result);
  }

  @Test
  public void testKillMonster() {
    player.shootArrow(DirectionType.EAST, 2);
    player.shootArrow(DirectionType.EAST, 2);
    player.move(DirectionType.EAST);
    player.move(DirectionType.EAST);
    assertFalse(player.getCurrLocation().isHasMonster());
  }

  @Test
  public void testNotKillMonster() {
    player.shootArrow(DirectionType.EAST, 2);
    player.move(DirectionType.EAST);
    player.move(DirectionType.EAST);
    assertTrue(player.getCurrLocation().isHasMonster());
    assertEquals(1, player.getCurrLocation().getMonster().getLife());
  }

  @Test
  public void TestArrowInTunnel() {
    player.move(DirectionType.SOUTH);
    String result = player.collectArrows();
    assertEquals('Y', result.charAt(0));
    assertEquals("TUNNEL", player.getCurrLocation().getType());
  }

  @Test
  public void TestShootViaTunnel() {
    player.move(DirectionType.EAST);
    player.move(DirectionType.SOUTH);
    String result = player.shootArrow(DirectionType.NORTH, 2);
    assertEquals("\nYou hear a great howl in the distance", result);
    player.shootArrow(DirectionType.NORTH, 2);
    player.move(DirectionType.NORTH);
    player.move(DirectionType.NORTH);
    result = player.shootArrow(DirectionType.EAST, 1);
    assertEquals("\nYou hear a great howl in the distance", result);
  }

  @Test
  public void testShootLongDistance() {
    player.move(DirectionType.WEST);
    player.move(DirectionType.NORTH);
    String result = player.shootArrow(DirectionType.EAST, 3);
    assertEquals("\nYou hear a great howl in the distance", result);
    result = player.shootArrow(DirectionType.EAST, 4);
    assertEquals("\nThe arrow hits the wall", result);
    result = player.shootArrow(DirectionType.SOUTH, 2);
    assertEquals("\nYou shoot an arrow into the darkness", result);
  }

  @Test
  public void testArrowPercentage() {
    int count = 0;
    List<List<Location>> cavesMap = monsterDungeons.getCavesMap();
    for (List<Location> locations : cavesMap) {
      for (Location location : locations) {
        if (location.getStoredArrows().size() > 0) {
          count += 1;
        }
      }
    }
    assertEquals(16, count);
  }

}