package dungeons;

import java.util.Scanner;

/**
 * Driver class for running the dungeon model.
 */
public class Driver {

  /**
   * Main methods for Driver class.
   * Use command-line arguments to specify size of dungeons, interconnectivity, and wrapping.
   * Print all the caves the player has covered and descriptions.
   * @param arg no use
   */
  public static void main(String[] arg) {
    int numRows;
    int numColumns;
    int wrapping;
    int interconnectivity = 0;
    Random realRandom = new RealRandom();

    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter the number of rows: \n");
    numRows = scanner.nextInt();

    System.out.println("Enter the number of columns: ");
    numColumns = scanner.nextInt();

    System.out.println("Does the dungeon wrap or not? (1/0) :");
    wrapping = scanner.nextInt();

    System.out.println("Enter the interconnectivity: ");
    interconnectivity = scanner.nextInt();

    DungeonsImpl dungeons = new DungeonsImpl(numRows, numColumns, wrapping, interconnectivity,
            realRandom);
    dungeons.generateDungeons();
    System.out.println(dungeons.setPlayer());

    while (!dungeons.isOver()) {
      System.out.println(dungeons.startMoving());
    }
    System.out.println("\nPlayer has reached the end");
  }




}
