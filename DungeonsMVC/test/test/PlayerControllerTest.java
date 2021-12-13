package test;

import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import driver.PlayerConsoleController;
import driver.PlayerController;
import dungeons.Dungeons;
import dungeons.DungeonsImpl;
import random.Random;
import random.RealRandom;

import static org.junit.Assert.assertTrue;


/**
 * Testing text based controller.
 */
public class PlayerControllerTest {

  Dungeons testDungeon;

  @Before
  public void setUp() {
    Random testRandom = new RealRandom(0);
    testDungeon = new DungeonsImpl(6, 6, 0, 10, 50, 50, testRandom);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidModel() {
    StringReader input = new StringReader("");
    StringBuffer output = new StringBuffer();
    PlayerController c = new PlayerConsoleController(input, output);
    c.playGame(null);
  }

  @Test
  public void testStartCave() {
    StringReader input = new StringReader("Q");
    StringBuffer output = new StringBuffer();
    new PlayerConsoleController(input, output).playGame(testDungeon);
    assertTrue(output.toString().contains("Player's start Cave: 17, (3, 5)"));
  }

  @Test
  public void testInvalidAction() {
    StringReader input = new StringReader("sfe Q");
    StringBuffer output = new StringBuffer();
    new PlayerConsoleController(input, output).playGame(testDungeon);
    assertTrue(output.toString().contains("Unknown action sfe"));
  }

  @Test
  public void testInvalidMove() {
    StringReader input = new StringReader("M W Q");
    StringBuffer output = new StringBuffer();
    new PlayerConsoleController(input, output).playGame(testDungeon);
    assertTrue(output.toString().contains("try again"));
  }

  @Test
  public void testCheckTreasures() {
    StringReader input = new StringReader("C Q");
    StringBuffer output = new StringBuffer();
    new PlayerConsoleController(input, output).playGame(testDungeon);
    assertTrue(output.toString().contains("The cave has treasures:\n"
            + "Diamonds*2 Rubies*2 Sapphires*0"));
  }

  @Test
  public void testCheckArrows() {
    StringReader input = new StringReader("C Q");
    StringBuffer output = new StringBuffer();
    new PlayerConsoleController(input, output).playGame(testDungeon);
    assertTrue(output.toString().contains("The cave has 0 arrows"));
  }

  @Test
  public void testSmellMonster() {
    StringReader input = new StringReader("Q");
    StringBuffer output = new StringBuffer();
    new PlayerConsoleController(input, output).playGame(testDungeon);
    assertTrue(output.toString().contains("You smell something very pungent nearby"));
  }

  @Test
  public void testShootDarkness() {
    StringReader input = new StringReader("S N 1 Q");
    StringBuffer output = new StringBuffer();
    new PlayerConsoleController(input, output).playGame(testDungeon);
    assertTrue(output.toString().contains("You shoot an arrow into the darkness"));
  }

  @Test
  public void testShootMonster() {
    StringReader input = new StringReader("S N 2 Q");
    StringBuffer output = new StringBuffer();
    new PlayerConsoleController(input, output).playGame(testDungeon);
    assertTrue(output.toString().contains("You hear a great howl in the distance"));
  }

  @Test
  public void testKillMonster() {
    StringReader input = new StringReader("S E 1 S E 1 Q");
    StringBuffer output = new StringBuffer();
    new PlayerConsoleController(input, output).playGame(testDungeon);
    assertTrue(output.toString().contains("You hear a death howl in the distance"));
  }

  @Test
  public void testPickUpTreasures() {
    StringReader input = new StringReader("P T Q");
    StringBuffer output = new StringBuffer();
    new PlayerConsoleController(input, output).playGame(testDungeon);
    assertTrue(output.toString().contains("You collected: Diamonds*2 Rubies*2 Sapphires*0"));
  }

  @Test
  public void testEatenByMonster() {
    StringReader input = new StringReader("M E");
    StringBuffer output = new StringBuffer();
    new PlayerConsoleController(input, output).playGame(testDungeon);
    assertTrue(output.toString().contains("eaten"));
  }


}