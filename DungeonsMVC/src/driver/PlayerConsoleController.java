package driver;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import dungeons.Dungeons;

/**
 * This starter files is for user to implement a console controller for the
 * Dungeons MVC.
 */
public class PlayerConsoleController implements PlayerController {
  private final Appendable out;
  private final Scanner scan;

  /**
   * Constructor for the controller.
   *
   * @param in  the source to read from
   * @param out the target to print to
   */
  public PlayerConsoleController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    scan = new Scanner(in);
  }


  @Override
  public void playGame(Dungeons m) {
    if (m == null) {
      throw new IllegalArgumentException();
    }
    try {
      String prompt;
      String action;
      String direction;
      String situation;
      String pickUps;
      int distance;
      m.generateDungeons();
      out.append("\n-----------------Game Started!-----------------\n");
      out.append(m.setPlayer());
      while (!m.isOver()) {
        prompt = "\n------------------------------------------------\n"
                + "Move, Pickup, Shoot, Check or Quit (M/P/S/C/Q)? ";
        out.append(prompt);
        action = scan.next();

        try {
          switch (action) {
            case "C":
              out.append(m.getPlayerDescription());
              break;
            case "Q":
              out.append("Game quit.\n");
              return;
            case "M":
              out.append("Which direction (N/S/W/E)? ");
              direction = scan.next();
              switch (direction) {
                case "N":
                  direction = "NORTH";
                  break;
                case "S":
                  direction = "SOUTH";
                  break;
                case "W":
                  direction = "WEST";
                  break;
                case "E":
                  direction = "EAST";
                  break;
                default:
                  out.append(String.format("Unknown direction %s", direction));
                  break;
              }
              try {
                situation = m.playerMove(direction);
                out.append(situation);
              } catch (IllegalArgumentException iae) {
                out.append("Direction not available, try again");
              }
              break;
            case "P":
              out.append("What to pick up (T/A)? ");
              pickUps = scan.next();
              switch (pickUps) {
                case "T":
                  out.append(m.playerPickUpTreasures());
                  break;
                case "A":
                  out.append(m.playerPickUpArrows());
                  break;
                default:
                  out.append(String.format("Unknown pickups %s", pickUps));
                  break;
              }
              break;
            case "S":
              out.append("Where to (N/S/W/E)? ");
              direction = scan.next();
              out.append("Number of caves (1-5)? ");
              distance = scan.nextInt();
              switch (direction) {
                case "N":
                  direction = "NORTH";
                  break;
                case "S":
                  direction = "SOUTH";
                  break;
                case "W":
                  direction = "WEST";
                  break;
                case "E":
                  direction = "EAST";
                  break;
                default:
                  out.append(String.format("Unknown direction %s", direction));
                  break;
              }
              try {
                situation = m.playerShootAnArrow(direction, distance);
                out.append(situation);
              } catch (IllegalArgumentException iae) {
                out.append("Direction not available, try again");
              } catch (IllegalStateException ise) {
                out.append("The arrow bag is empty");
              }
              break;
            default:
              out.append(String.format("Unknown action %s", action));
              break;
          }
        } catch (InputMismatchException ime) {
          out.append("Invalid action ").append(action);
        }
      }
      out.append("\n").append(m.getResult());
      out.append("\n-------------------Game over!-------------------\n");
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }

  }
}
