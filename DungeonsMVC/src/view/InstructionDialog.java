package view;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JTextArea;

/**
 * This dialog provides the instruction for playing the game.
 */
public class InstructionDialog extends JDialog {

  /**
   * Constructor for InstructionDialog.
   *
   * @param frame JFrame
   */
  public InstructionDialog(JFrame frame) {
    super(frame, "Game Instructions");
    JTextArea text = new JTextArea();
    text.setOpaque(true);
    text.setEditable(false);
    text.setFont(new Font("Arial", Font.PLAIN, 14));
    String line1 = "MOVE: Press (NSWE) buttons or press arrow key\n";
    String line2 = "SHOOT: Press S on keyboard followed by a arrow key."
            + " Then select the distance \n";
    String line3 = "PICKUP: Press A on keyboard for arrows; Press T on keyboard for treasures\n";
    text.setText(line1 + line2 + line3);
    add(text);
    pack();
    setVisible(true);
  }
}
