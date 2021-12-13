package test;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import locations.Cave;
import locations.DirectionType;
import locations.Location;
import player.Monster;
import player.People;
import player.PeopleInterface;
import properties.Arrows;
import properties.TreasureType;
import properties.Treasures;
import locations.Tunnel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Class for testing the people class:
 * Start and end cave;
 * Move method;
 * Player pick up treasures,
 * Player's description toString().
 */
public class PeopleTest {
  Location startCave;
  Location endCave;
  Location tunnel;

  PeopleInterface player;

  Monster otyugh;

  @Before
  public void setUp() {
    startCave = new Cave(1, 1, 1, null, null, null, null);
    startCave.addTreasures(new Treasures(TreasureType.SAPPHIRES));
    startCave.addArrows(new Arrows());
    endCave = new Cave(2, 1, 2, null, null, null, null);
    tunnel = new Tunnel(100, null, null, startCave, endCave);
    startCave.setWestEntrance(startCave);
    startCave.setNorthEntrance(startCave);
    startCave.setSouthEntrance(startCave);
    startCave.setEastEntrance(tunnel);
    endCave.setNorthEntrance(endCave);
    endCave.setSouthEntrance(endCave);
    endCave.setEastEntrance(endCave);
    endCave.setWestEntrance(tunnel);

    player = new People("PlayerTest", startCave, endCave);
    otyugh = new Monster();
  }

  @Test
  public void testStartCave() {
    assertEquals(player.getCurrLocation(), startCave);
  }


  @Test
  public void testPlayerMove() {
    while (player.getCurrLocation() != player.getEndLocation()) {
      player.move(DirectionType.EAST);
    }
    assertEquals(player.getCurrLocation(), player.getEndLocation());
  }

  @Test
  public void testPickUpTreasures() {
    List<Treasures> storedTreasures = startCave.getStoredTreasures();
    player.collectTreasures();
    assertEquals(storedTreasures, player.getCollectedTreasures());
  }

  @Test
  public void testInitialArrowCount() {
    assertEquals(3, player.getArrowCount());
  }

  @Test
  public void testPickUpArrows() {
    player.collectArrows();
    assertEquals(4, player.getArrowCount());
  }

  @Test
  public void testDescription() {
    String expected = "\nYou are in cave (1, 1), with entrances:\nEast ";
    assertEquals(expected, player.toString());

  }

  @Test
  public void testGetAttacked() {
    otyugh.getHit();
    assertEquals(1, otyugh.getLife());
  }

  @Test
  public void testIsDead() {
    otyugh.getHit();
    assertFalse(otyugh.isDead());
    otyugh.getHit();
    assertTrue(otyugh.isDead());
  }

}
