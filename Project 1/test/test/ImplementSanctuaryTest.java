package test;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import sanctuary.Animal;
import sanctuary.ImplementSanctuary;
import sanctuary.Sanctuary;

import static org.junit.Assert.assertEquals;

/**
 * Testing the implementSanctuary class.
 */
public class ImplementSanctuaryTest {

  private Sanctuary sanctuary;

  @Before
  public void setUp() {
    int n = 5;
    int m = 2;
    int troopCapacity = 15;
    sanctuary = setSanctuary(n, m, troopCapacity);
  }

  protected Sanctuary setSanctuary(int n, int m, int troopCapacity) {
    return new ImplementSanctuary(n, m, troopCapacity);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInput1() {
    setSanctuary(0, 2, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInput2() {
    setSanctuary(10, 0, 10);
  }

  @Test
  public void testExpandCages() {
    sanctuary.expand(2, 0);
    assertEquals(7, sanctuary.getNumCages());
  }

  @Test
  public void testExpandTroops() {
    sanctuary.expand(0, 1);
    assertEquals(3, sanctuary.getNumTroops());
  }

  @Test
  public void testReportSpecies() {
    OutputStream os = new ByteArrayOutputStream();
    System.setOut(new PrintStream(os));
    Animal monkey1 = new Animal("Drill1", Animal.Species.drill, Animal.Sex.male,
            35, 50, 5, Animal.FavFoods.fruits, "Isolation");
    sanctuary.isolateFirst(monkey1);
    sanctuary.reportSpecies();
    String expOutput = "drill: Isolation\n" + "unknown: Enclosure\n";
    assertEquals(expOutput, os.toString());
  }

  @Test
  public void testLookUpSpecies() {
    OutputStream os = new ByteArrayOutputStream();
    System.setOut(new PrintStream(os));
    Animal monkey1 = new Animal("Drill1", Animal.Species.drill, Animal.Sex.male,
            35, 50, 5, Animal.FavFoods.fruits, "Isolation");
    sanctuary.isolateFirst(monkey1);
    sanctuary.lookUpSpecies(Animal.Species.drill.toString());
    String expOutput = "drill reside in isolation\n";
    assertEquals(expOutput, os.toString());
  }

  @Test
  public void testLookUpSpecies2() {
    OutputStream os = new ByteArrayOutputStream();
    System.setOut(new PrintStream(os));
    Animal monkey1 = new Animal("Drill1", Animal.Species.drill, Animal.Sex.male,
            35, 50, 5, Animal.FavFoods.fruits, "Isolation");
    sanctuary.isolateFirst(monkey1);
    sanctuary.lookUpSpecies(Animal.Species.saki.toString());
    String expOutput = "saki is not found in the sanctuary\n";
    assertEquals(expOutput, os.toString());
  }

  @Test
  public void testListMonkey() {
    OutputStream os = new ByteArrayOutputStream();
    System.setOut(new PrintStream(os));
    Animal monkey1 = new Animal("Drill1", Animal.Species.drill, Animal.Sex.male,
            35, 50, 5, Animal.FavFoods.fruits, "Isolation");
    Animal monkey2 = new Animal("Howler1", Animal.Species.howler, Animal.Sex.male,
            25, 30, 3, Animal.FavFoods.leaves, "Isolation");
    Animal monkey3 = new Animal("Mangabey1", Animal.Species.mangabey, Animal.Sex.male,
            22, 32, 6, Animal.FavFoods.seeds, "Isolation");
    sanctuary.isolateFirst(monkey1);
    sanctuary.isolateFirst(monkey2);
    sanctuary.isolateFirst(monkey3);
    sanctuary.listAllAnimals();
    String expOutput = "Name: Drill1, Species: drill, House: Isolation\n"
            + "Name: Howler1, Species: howler, House: Isolation\n"
            + "Name: Mangabey1, Species: mangabey, House: Isolation\n";
    assertEquals(expOutput, os.toString());
  }

  @Test
  public void testShoppingList() {
    OutputStream os = new ByteArrayOutputStream();
    System.setOut(new PrintStream(os));
    Animal monkey1 = new Animal("Drill1", Animal.Species.drill, Animal.Sex.male,
            35, 50, 5, Animal.FavFoods.fruits, "Isolation");
    Animal monkey2 = new Animal("Howler1", Animal.Species.howler, Animal.Sex.male,
            25, 30, 3, Animal.FavFoods.leaves, "Isolation");
    Animal monkey3 = new Animal("Mangabey1", Animal.Species.mangabey, Animal.Sex.male,
            22, 32, 6, Animal.FavFoods.seeds, "Isolation");
    sanctuary.isolateFirst(monkey1);
    sanctuary.isolateFirst(monkey2);
    sanctuary.isolateFirst(monkey3);
    sanctuary.listFavFoods();
    String expOutput = "fruits: 500\n"
            + "leaves: 500\n"
            + "seeds: 500\n";
    assertEquals(expOutput, os.toString());
  }
}