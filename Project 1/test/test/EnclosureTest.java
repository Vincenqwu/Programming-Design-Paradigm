package test;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import sanctuary.Animal;
import sanctuary.Enclosure;

import static org.junit.Assert.assertEquals;

/**
 * Testing the enclosure class.
 */
public class EnclosureTest {
  private Enclosure troop1;
  private Enclosure troop2;
  private Animal monkey1;
  private Animal monkey2;
  private Animal monkey5;
  private Animal monkey9;
  private Animal monkey10;

  @Before
  public void setUp() {

    monkey1 = setAnimal("Drill1", Animal.Species.drill, Animal.Sex.male,
            35, 50, 5, Animal.FavFoods.fruits, "Isolation");
    monkey2 = setAnimal("Drill2", Animal.Species.drill, Animal.Sex.female,
            30, 40, 4, Animal.FavFoods.insects, "Isolation");
    monkey5 = setAnimal("Mangabey1", Animal.Species.mangabey, Animal.Sex.male,
            22, 32, 6, Animal.FavFoods.seeds, "Isolation");
    monkey9 = setAnimal("Mangabey2", Animal.Species.mangabey, Animal.Sex.female,
            19, 25, 3, Animal.FavFoods.treeSap, "Isolation");
    monkey10 = setAnimal("Mangabey3", Animal.Species.mangabey, Animal.Sex.male,
            15, 21, 2, Animal.FavFoods.eggs, "Isolation");

    troop1 = setTroop(50, Animal.Species.unknown);
    troop2 = setTroop(40, Animal.Species.unknown);
  }


  protected Animal setAnimal(String name, Animal.Species species,
                             Animal.Sex sex, int size, int weight, int age,
                             Animal.FavFoods favFood, String house) {
    return new Animal(name, species, sex, size, weight, age, favFood, house);
  }

  protected Enclosure setTroop(int capacity, Animal.Species species) {
    return new Enclosure(capacity, species);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidCapacity1() {
    setTroop(0, Animal.Species.unknown);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidCapacity2() {
    setTroop(-10, Animal.Species.unknown);
  }

  @Test
  public void testGetCapacity() {
    assertEquals(50, troop1.getCapacity());
    assertEquals(40, troop2.getCapacity());
  }

  @Test
  public void testGetAvailableSpace() {
    troop1.addResident(monkey1);
    troop1.addResident(monkey2);
    assertEquals(30, troop1.getAvailableSpace());
    assertEquals(40, troop2.getAvailableSpace());
  }

  @Test(expected = IllegalStateException.class)
  public void testAddResident1() {
    troop1.addResident(monkey1);
    troop1.addResident(monkey5);
  }

  @Test(expected = IllegalStateException.class)
  public void testAddResident2() {
    Enclosure troop3 = setTroop(10, Animal.Species.unknown);
    troop3.addResident(monkey10);
    troop3.addResident(monkey5);
    troop3.addResident(monkey9);
  }

  @Test
  public void testAddResident3() {
    troop1.addResident(monkey5);
    troop1.addResident(monkey9);
    troop1.addResident(monkey10);
    assertEquals(Animal.Species.mangabey, troop1.getSpecies());
    assertEquals(30, troop1.getAvailableSpace());
  }

  @Test
  public void testIsEmpty() {
    troop2.addResident(monkey5);
    assertEquals(true, troop1.isEmpty());
    assertEquals(false, troop2.isEmpty());
  }

  @Test
  public void testSign() {
    OutputStream os = new ByteArrayOutputStream();
    System.setOut(new PrintStream(os));
    troop1.addResident(monkey5);
    troop1.sign();
    String expOutput = "Name: Mangabey1, Sex: male, Favorite food: seeds\n";
    assertEquals(expOutput, os.toString());
  }

}