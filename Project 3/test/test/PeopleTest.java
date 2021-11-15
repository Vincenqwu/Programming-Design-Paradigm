package test;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import dungeons.Cave;
import dungeons.Location;
import dungeons.People;
import dungeons.PeopleInterface;
import dungeons.TreasureType;
import dungeons.Treasures;
import dungeons.Tunnel;

import static org.junit.Assert.assertEquals;

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

  @Before
  public void setUp() {
    startCave = new Cave(1, 1, 1, null, null, null, null);
    startCave.addTreasures(new Treasures(TreasureType.SAPPHIRES));
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
  }

  @Test
  public void testStartCave() {
    assertEquals(player.getCurrLocation(), startCave);
  }

  @Test
  public void testPlayerMove() {
    while (player.getCurrLocation() != player.getEndLocation()) {
      player.move();
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
  public void testDescription() {
    String expected = "\nPlayer in cave 1, (1, 1), with the following entrances:\n"
            + "East \n"
            + "The play has collected the following treasures:\n"
            + "None\n"
            + "This cave has the following treasures:\n"
            + "Diamonds*0 Rubies*0 Sapphires*1";
    assertEquals(expected, player.toString());

  }


}
