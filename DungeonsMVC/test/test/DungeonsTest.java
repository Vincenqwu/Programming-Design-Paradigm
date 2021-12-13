package test;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import dungeons.Dungeons;
import dungeons.DungeonsImpl;
import locations.Location;
import locations.LocationType;
import random.PseudoRandom;
import random.Random;
import random.RealRandom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Class for testing Dungeons models:
 * Input validity;
 * Wrapping and nonWrapping dungeons;
 * Entrances and neighbors of caves;
 * Treasures percentage in caves;
 * Path length between start and end cave;
 * Each cave can go to every other caves.
 */
public class DungeonsTest {
  Dungeons nonWrapDungeons;
  Dungeons wrapDungeons;
  Dungeons bigNonWrapDungeons;
  Dungeons bigWrappingDungeons;
  Random testRandom1;
  Random testRandom2;
  Random testRandom3;

  @Before
  public void setUp() {
    //testRandom1 = new PseudoRandom(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    testRandom1 = new PseudoRandom(new int[12]);
    testRandom2 = new PseudoRandom(new int[18]);
    testRandom3 = new RealRandom();
    nonWrapDungeons = new DungeonsImpl(3, 3, 0, 0, 20, 20, testRandom1);
    wrapDungeons = new DungeonsImpl(3, 3, 1, 5, 20, 20, testRandom2);
    bigNonWrapDungeons = new DungeonsImpl(10, 10, 0, 10, 20, 20, testRandom3);
    bigWrappingDungeons = new DungeonsImpl(7, 7, 1, 5, 20, 20, testRandom3);

  }

  @Test
  public void testDungeons() {
    wrapDungeons.generateDungeons();
    List<List<Location>> cavesMap = wrapDungeons.getCavesMap();
    for (int i = 0; i < wrapDungeons.getNumRows(); i++) {
      for (int j = 0; j < wrapDungeons.getNumColumns(); j++) {
        Location thisCave = cavesMap.get(i).get(j);
        if (thisCave.getType().equals(LocationType.CAVE.toString())) {
          System.out.println("\n******Cave #: " + thisCave.getlId() + "******");
          System.out.println("North: " + thisCave.getNorthEntrance().getNorthEntrance().getlId()
                  + thisCave.getNorthEntrance().getNorthEntrance().getType());
          System.out.println("\nSouth: " + thisCave.getSouthEntrance().getSouthEntrance().getlId()
                  + thisCave.getSouthEntrance().getSouthEntrance().getType());
          System.out.println("\nWest: " + thisCave.getWestEntrance().getWestEntrance().getlId()
                  + thisCave.getWestEntrance().getWestEntrance().getType());
          System.out.println("\nEast: " + thisCave.getEastEntrance().getEastEntrance().getlId()
                  + thisCave.getEastEntrance().getEastEntrance().getType());
        } else {
          System.out.println("\n******Tunnel #: " + thisCave.getlId() + "******");
          if (thisCave.getNorthEntrance() != null) {
            System.out.println("North: " + thisCave.getNorthEntrance().getNorthEntrance().getlId());
          }
          if (thisCave.getSouthEntrance() != null) {
            System.out.println("\nSouth: " + thisCave.getSouthEntrance()
                    .getSouthEntrance().getlId());
          }
          if (thisCave.getWestEntrance() != null) {
            System.out.println("\nWest: " + thisCave.getWestEntrance().getWestEntrance().getlId());
          }
          if (thisCave.getEastEntrance() != null) {
            System.out.println("\nEast: " + thisCave.getEastEntrance().getEastEntrance().getlId());
          }
        }
      }
    }
    assertEquals(3, wrapDungeons.getNumRows());
    assertEquals(3, wrapDungeons.getNumColumns());
  }

  @Test
  public void testCaveNeighbors() {
    nonWrapDungeons.generateDungeons();
    List<List<Location>> cavesMap = nonWrapDungeons.getCavesMap();
    for (int i = 0; i < nonWrapDungeons.getNumRows(); i++) {
      for (int j = 0; j < nonWrapDungeons.getNumColumns(); j++) {
        Location thisCave = cavesMap.get(i).get(j);
        if (thisCave.getType().equals("CAVE")) {
          System.out.println("\n******Cave #: " + thisCave.getlId() + " ******");
          System.out.println("\nTreasures: " + thisCave.getStoredTreasures().size());
          for (Location location : thisCave.getNeighbors()) {
            System.out.println(location.getlId());
          }
        }
      }
    }
    assertEquals("TUNNEL", cavesMap.get(0).get(0).getType());
    assertEquals("CAVE", cavesMap.get(0).get(1).getType());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInputs() {
    new DungeonsImpl(3, 3, 2, 0, 20, 0, new RealRandom());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInputs2() {
    new DungeonsImpl(0, 3, 1, 0, 20, 20, new RealRandom());
  }

  @Test
  public void testNonWrappingDungeons() {
    nonWrapDungeons.generateDungeons();
    List<List<Location>> cavesMap = nonWrapDungeons.getCavesMap();
    Location currCave = cavesMap.get(0).get(1);
    assertEquals(5, currCave.getSouthEntrance().getSouthEntrance().getlId());
    assertEquals(2, currCave.getNorthEntrance().getNorthEntrance().getlId());
    assertEquals(1, currCave.getWestEntrance().getWestEntrance().getlId());
    assertEquals(3, currCave.getEastEntrance().getEastEntrance().getlId());
  }

  @Test
  public void testWrappingDungeons() {
    wrapDungeons.generateDungeons();
    List<List<Location>> cavesMap = wrapDungeons.getCavesMap();
    Location currCave = cavesMap.get(0).get(0);
    assertEquals(4, currCave.getSouthEntrance().getSouthEntrance().getlId());
    assertEquals(7, currCave.getNorthEntrance().getNorthEntrance().getlId());
    assertEquals(3, currCave.getWestEntrance().getWestEntrance().getlId());
    assertEquals(2, currCave.getEastEntrance().getEastEntrance().getlId());
  }

  @Test
  public void testTreasuresPercentage() {
    wrapDungeons.generateDungeons();
    List<Location> cavesList = wrapDungeons.getCavesList();
    int count = 0;
    for (Location location : cavesList) {
      if (location.getStoredTreasures().size() > 0) {
        count += 1;
      }
    }
    assertEquals(2, count);
  }

  @Test
  public void testPathLength() {
    bigNonWrapDungeons.generateDungeons();
    bigNonWrapDungeons.setPlayer();
    Location startCave = bigNonWrapDungeons.getStartCave();
    Location endCave = bigNonWrapDungeons.getEndCave();

    int pathLength = 0;
    Map<Location, Integer> distanceMap = new HashMap<>();
    Queue<Location> caveQueue = new LinkedList<>();
    distanceMap.put(startCave, 0);
    int distanceToStart;
    caveQueue.add(startCave);
    while (caveQueue.size() != 0) {
      Location removedCave = caveQueue.poll();
      distanceToStart = distanceMap.get(removedCave);
      for (Location child : removedCave.getNeighbors()) {
        if (!distanceMap.containsKey(child)) {
          distanceMap.put(child, distanceToStart + 1);
          caveQueue.add(child);
        }
      }
    }
    for (Location cave : distanceMap.keySet()) {
      if (cave == endCave) {
        pathLength = distanceMap.get(cave);
        break;
      }
    }
    assertTrue(pathLength >= 5);
  }


  @Test
  public void testPathLength2() {
    bigWrappingDungeons.generateDungeons();
    bigWrappingDungeons.setPlayer();
    Location startCave = bigWrappingDungeons.getStartCave();
    Location endCave = bigWrappingDungeons.getEndCave();
    int pathLength = 0;
    Map<Location, Integer> distanceMap = new HashMap<>();
    Queue<Location> caveQueue = new LinkedList<>();
    distanceMap.put(startCave, 0);
    int distanceToStart;
    caveQueue.add(startCave);
    while (caveQueue.size() != 0) {
      Location removedCave = caveQueue.poll();
      distanceToStart = distanceMap.get(removedCave);
      for (Location child : removedCave.getNeighbors()) {
        if (!distanceMap.containsKey(child)) {
          distanceMap.put(child, distanceToStart + 1);
          caveQueue.add(child);
        }
      }
    }
    for (Location cave : distanceMap.keySet()) {
      if (cave == endCave) {
        pathLength = distanceMap.get(cave);
        break;
      }
    }
    assertTrue(pathLength >= 5);
  }

  @Test
  public void testCanGoAnywhere() {
    bigNonWrapDungeons.generateDungeons();
    bigNonWrapDungeons.setPlayer();
    Location startCave = bigNonWrapDungeons.getStartCave();
    List<Location> result = new ArrayList<>();
    Queue<Location> caveQueue = new LinkedList<>();
    caveQueue.add(startCave);
    while (caveQueue.size() != 0) {
      Location removedCave = caveQueue.poll();
      for (Location child : removedCave.getNeighbors()) {
        if (!result.contains(child)) {
          caveQueue.add(child);
          result.add(child);
        }
      }
    }
    boolean allCaves = true;
    for (Location cave : bigNonWrapDungeons.getCavesList()) {
      if (!result.contains(cave)) {
        allCaves = false;
        break;
      }
    }
    assertTrue(allCaves);
  }


}