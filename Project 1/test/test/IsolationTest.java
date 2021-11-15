package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import sanctuary.Animal;
import sanctuary.Isolation;

import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing the isolation object.
 */
public class IsolationTest {

  private Isolation isolation;
  private Animal monkey1;
  private Animal monkey2;
  private Animal monkey3;
  private Animal monkey4;


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

    int n = 20;
    isolation = setIsolation(n);
  }


  protected Animal setAnimal(String name, Animal.Species species,
                             Animal.Sex sex, int size, int weight, int age,
                             Animal.FavFoods favFood, String house) {
    return new Animal(name, species, sex, size, weight, age, favFood, house);
  }

  protected Isolation setIsolation(int n) {
    return new Isolation(n);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInput1() {
    setIsolation(-10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInput2() {
    setIsolation(0);
  }

  @Test
  public void testAddMember() {
    boolean success = isolation.addMember(monkey1);
    assertTrue(success);
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidAddMember() {
    Isolation isolation2 = setIsolation(2);
    isolation2.addMember(monkey1);
    isolation2.addMember(monkey2);
    isolation2.addMember(monkey3);
    isolation2.addMember(monkey4);
  }

  @Test
  public void testAddCages() {
    isolation.addCages(10);
    assertEquals(30, isolation.getNumCages());
  }

  @Test
  public void testGetNumCages() {
    assertEquals(20, isolation.getNumCages());
  }

  @Test
  public void testFreeSpace() {
    Isolation isolation2 = setIsolation(10);
    isolation2.addMember(monkey1);
    isolation2.addMember(monkey2);
    isolation2.addMember(monkey3);
    isolation2.addMember(monkey4);
    assertEquals(6, isolation2.getFreeSpace());

    isolation2.removeMember();
    isolation2.removeMember();
    assertEquals(8, isolation2.getFreeSpace());
  }

}