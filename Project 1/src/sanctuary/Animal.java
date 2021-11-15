package sanctuary;

/**
 * Animal represents as monkeys;
 * Each monkey has name, species, sex, size,
 * weight, age, favorite food, and house.
 */
public class Animal {

  /**
   * Enumerate all species of monkeys in the sanctuary.
   */
  public enum Species {
    drill, howler, mangabey, guereza,
    saki, spider, squirrel, tamarin, unknown
  }

  /**
   * Enumerate all possible favorite foods of monkeys.
   */
  public enum FavFoods {
    eggs, fruits, insects, leaves,
    nuts, seeds, treeSap, unknown
  }

  /**
   * Enumerate the sex of monkeys.
   */
  public enum Sex {
    male, female
  }

  private final String name;
  private final Species species;
  private final Sex sex;
  private final int size;
  private final int weight;
  private final int age;
  private final FavFoods favFoods;
  private String house;

  /**
   * Construct an animal class with parameters
   * for different attributes of monkeys.
   *
   * @param name    name of individual monkey
   * @param species species of individual monkey
   * @param sex     sex of individual monkey
   * @param size    size of monkey in cm
   * @param weight  weight of monkey in pound
   * @param age     age of monkey
   * @param favFood favorite food of monkey
   * @param house   specify where the monkey currently reside.
   */
  public Animal(String name, Species species, Sex sex,
                int size, int weight, int age, FavFoods favFood, String house) {
    this.name = name;
    this.species = species;
    this.sex = sex;
    this.size = size;
    if (!"Isolation".equals(house)) {
      throw new IllegalArgumentException("House parameter must be Isolation");
    }
    if (size <= 0 || weight <= 0 || age <= 0) {
      throw new IllegalArgumentException("Size, age and weight must be positive integer");
    }
    boolean isFavFood = false;
    if (favFood == FavFoods.unknown) {
      throw new IllegalArgumentException("Invalid favorite food");
    }

    this.weight = weight;
    this.age = age;
    this.favFoods = favFood;
    this.house = house;
  }

  /**
   * get the name of monkey.
   *
   * @return name in string
   */
  public String getName() {
    return this.name;
  }

  /**
   * get the species of monkey.
   *
   * @return species type
   */
  public Species getSpecies() {
    return this.species;
  }

  /**
   * Return the sex of monkey.
   *
   * @return sex type
   */
  public Sex getSex() {
    return this.sex;
  }

  /**
   * Return the size of monkey.
   *
   * @return Size
   */
  public int getSize() {
    return this.size;
  }

  /**
   * Return the weight of monkey.
   *
   * @return Weight
   */
  public int getWeight() {
    return this.weight;
  }

  /**
   * Return the age of monkey.
   *
   * @return Age
   */
  public int getAge() {
    return this.age;
  }

  /**
   * Return the favorite food of moneky.
   *
   * @return Favorite food tyoe
   */
  public FavFoods getFavFoods() {
    return this.favFoods;
  }

  /**
   * change the house the monkey currently resides.
   *
   * @param house the new place to live
   */
  public void changeHouse(String house) {
    if (!house.equals("Enclosure")) {
      throw new IllegalArgumentException("House parameter "
              + "must be 'Enclosure'");
    }
    this.house = house;
  }

  /**
   * Return where the monkey resides.
   *
   * @return House in string
   */
  public String getHouse() {
    return this.house;
  }

  /**
   * Summarize monkey information.
   *
   * @return monkey's name, species and house in string.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Name: ");
    sb.append(this.name);
    sb.append(", Species: ");
    sb.append(this.species);
    sb.append(", House: ");
    sb.append(this.house);

    return sb.toString();
  }
}
