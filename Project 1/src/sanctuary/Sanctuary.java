package sanctuary;

/**
 * Sanctuary represents as an isolation of n number of cages
 * and m number of troops, and troop capacity.
 */
public interface Sanctuary {

  /**
   * Add the animal object to the isolation list.
   *
   * @param member animal object
   */
  void isolateFirst(Animal member);

  /**
   * Get number of cages.
   *
   * @return num of cages
   */
  int getNumCages();

  /**
   * Get number of troops.
   * @return num of troops
   */
  int getNumTroops();

  /**
   * Increase the number of cages and number of troops.
   *
   * @param nNewCages  num of cages
   * @param nNewTroops num of troops
   */
  void expand(int nNewCages, int nNewTroops);

  /**
   * Extract all the monkeys currently in isolations
   * and put them into troops.
   */
  void startMoving();

  /**
   * Report the species that are currently being housed in alphabetical
   * order. The list should include where in the sanctuary each species
   * is (both in enclosures and in isolation).
   */
  void reportSpecies();

  /**
   * look up where a particular species is currently housed.
   * If none of a particular species is currently being housed,
   * it should report this fact.
   *
   * @param species species of a monkey.
   */
  void lookUpSpecies(String species);

  /**
   * Get the sign of the enclosure based on index.
   *
   * @param index index of the enclosure.
   */
  void getEnclosureSign(int index);

  /**
   * Produce an alphabetical list (by name) of all of the monkeys housed in the
   * Sanctuary. This information should include where each monkey can be found.
   */
  void listAllAnimals();

  /**
   * Produce a shopping list of the favorite foods of the inhabitants of the Sanctuary.
   * For each food listed, the quantity needed should also be listed.
   * Large monkeys should receive 500 gr of their favorite food while medium
   * and small monkeys require 250 gr and 100 gr respectively.
   */
  void listFavFoods();


}
