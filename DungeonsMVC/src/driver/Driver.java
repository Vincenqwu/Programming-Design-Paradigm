package driver;

import java.io.InputStreamReader;
import java.util.Scanner;

import dungeons.Dungeons;
import dungeons.DungeonsImpl;

/**
 * Driver class for running the dungeon model.
 */
public class Driver {

  /**
   * Main methods for Driver class.
   * Use command-line arguments to specify size of dungeons, interconnectivity, and wrapping.
   * Print all the caves the player has covered and descriptions.
   *
   * @param arg no use
   */
  public static void main(String[] arg) {
    int numRows;
    int numColumns;
    int wrapping;
    int interconnectivity;
    int treasureArrowPercentage;
    int monsterPercentage;

    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter the number of rows:\n");
    numRows = scanner.nextInt();

    System.out.println("Enter the number of columns:");
    numColumns = scanner.nextInt();

    System.out.println("Does the dungeon wrap or not? (1/0):");
    wrapping = scanner.nextInt();

    System.out.println("Enter the interconnectivity:");
    interconnectivity = scanner.nextInt();

    System.out.println("Enter the percentage for arrow and treasures (20-100):");
    treasureArrowPercentage = scanner.nextInt();

    System.out.println("Enter the percentage for monsters (0-100):");
    monsterPercentage = scanner.nextInt();

    //Random testRandom = new RealRandom(0);
    Dungeons dungeons = new DungeonsImpl(numRows, numColumns, wrapping, interconnectivity,
            treasureArrowPercentage, monsterPercentage);
    Readable input = new InputStreamReader(System.in);
    Appendable output = System.out;
    new PlayerConsoleController(input, output).playGame(dungeons);

  }


}
