package test;

import static org.junit.Assert.assertEquals;

import sanctuary.Animal;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Class for testing the Animal object.
 */
public class AnimalTest {
  private Animal monkey1;
  private Animal monkey2;
  private Animal monkey3;
  private Animal monkey4;
  private Animal monkey5;
  private Animal monkey6;
  private Animal monkey7;
  private Animal monkey8;
  private Animal monkey9;
  private Animal monkey10;

  @Before
  public void setUp() {
    monkey1 = setAnimal("Drill1", Animal.Species.drill, Animal.Sex.male,
            35, 50, 5, Animal.FavFoods.fruits, "Isolation");
    monkey2 = setAnimal("Drill2", Animal.Species.drill, Animal.Sex.female,
            30, 40, 4, Animal.FavFoods.insects, "Isolation");
    monkey3 = setAnimal("Howler1", Animal.Species.howler, Animal.Sex.male,
            25, 30, 3, Animal.FavFoods.leaves, "Isolation");
    monkey4 = setAnimal("Howler2", Animal.Species.howler, Animal.Sex.female,
            20, 35, 2, Animal.FavFoods.nuts, "Isolation");
    monkey5 = setAnimal("Mangabey1", Animal.Species.mangabey, Animal.Sex.male,
            22, 32, 6, Animal.FavFoods.seeds, "Isolation");
    monkey6 = setAnimal("Guereza1", Animal.Species.guereza, Animal.Sex.female,
            35, 43, 3, Animal.FavFoods.fruits, "Isolation");
    monkey7 = setAnimal("Tamarin1", Animal.Species.tamarin, Animal.Sex.male,
            6, 10, 2, Animal.FavFoods.seeds, "Isolation");
    monkey8 = setAnimal("Tamarin2", Animal.Species.tamarin, Animal.Sex.female,
            5, 9, 3, Animal.FavFoods.fruits, "Isolation");
    monkey9 = setAnimal("Mangabey2", Animal.Species.mangabey, Animal.Sex.female,
            19, 25, 3, Animal.FavFoods.treeSap, "Isolation");
    monkey10 = setAnimal("Mangabey3", Animal.Species.mangabey, Animal.Sex.male,
            15, 21, 2, Animal.FavFoods.eggs, "Isolation");
  }


  protected Animal setAnimal(String name, Animal.Species species,
                             Animal.Sex sex, int size, int weight, int age,
                             Animal.FavFoods favFood, String house) {
    return new Animal(name, species, sex, size, weight, age, favFood, house);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidSize() {
    setAnimal("Drill3", Animal.Species.drill, Animal.Sex.male,
            -35, 50, 5, Animal.FavFoods.fruits, "Isolation");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidWeight() {
    setAnimal("Drill3", Animal.Species.drill, Animal.Sex.male,
            10, -50, 5, Animal.FavFoods.fruits, "Isolation");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidAge() {
    setAnimal("Drill3", Animal.Species.drill, Animal.Sex.male,
            35, 50, -5, Animal.FavFoods.fruits, "Isolation");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidFFood() {
    setAnimal("Drill3", Animal.Species.drill, Animal.Sex.male,
            35, 50, -5, Animal.FavFoods.unknown, "Isolation");
  }

  @Test
  public void testGetName() {
    assertEquals("Drill1", monkey1.getName());
    assertEquals("Drill2", monkey2.getName());
  }

  @Test
  public void testGetSpecies() {
    assertEquals(Animal.Species.drill, monkey1.getSpecies());
    assertEquals(Animal.Species.mangabey, monkey9.getSpecies());
  }

  @Test
  public void testGetSex() {
    assertEquals(Animal.Sex.female, monkey4.getSex());
    assertEquals(Animal.Sex.male.toString(), monkey5.getSex().toString());
  }

  @Test
  public void testGetAge() {
    assertEquals(3, monkey6.getAge());
    assertEquals(2, monkey7.getAge());
  }

  @Test
  public void testGetWeight() {
    assertEquals(25, monkey9.getWeight());
    assertEquals(9, monkey8.getWeight());
    assertEquals(21, monkey10.getWeight());
  }

  @Test
  public void testFavFood() {
    assertEquals(Animal.FavFoods.nuts, monkey4.getFavFoods());
    assertEquals(Animal.FavFoods.seeds, monkey5.getFavFoods());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeHouse1() {
    monkey2.changeHouse("Enclosre");
  }

  @Test
  public void testChangehouse2() {
    monkey3.changeHouse("Enclosure");
    assertEquals("Enclosure", monkey3.getHouse());
  }

  @Test
  public void testToString() {
    OutputStream os = new ByteArrayOutputStream();
    System.setOut(new PrintStream(os));
    System.out.print(monkey1.toString());
    String expOutput = "Name: Drill1, Species: drill, House: Isolation";
    assertEquals(expOutput, os.toString());
    System.out.print(monkey10.toString());
  }

  @Test
  public void testToString1() {
    OutputStream os = new ByteArrayOutputStream();
    System.setOut(new PrintStream(os));
    System.out.print(monkey10.toString());
    String expOutput = "Name: Mangabey3, Species: mangabey, House: Isolation";
    assertEquals(expOutput, os.toString());
  }

}