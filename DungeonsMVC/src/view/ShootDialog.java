package view;

import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;


/**
 * Dialog window that will pop up when the user press the shoot-arrow key.
 */
public class ShootDialog extends JDialog {
  /**
   * Constructor for the ShootDialog.
   *
   * @param frame      jFrame
   * @param controller controller
   * @param direction  number of caves
   */
  public ShootDialog(JFrame frame, DungeonsController controller, String direction) {
    super(frame, "Arrow Setting");
    setLayout(new FlowLayout());
    JLabel directionDisplay = new JLabel("Shoot " + direction + ",  Choose distance:");

    Integer[] distanceChoices = new Integer[]{1, 2, 3, 4, 5};
    JComboBox<Integer> getDistance = new JComboBox<>(distanceChoices);
    getDistance.setSelectedItem(distanceChoices[0]);

    JButton confirmButton = new JButton("Shoot");
    confirmButton.addActionListener(c -> {
      int sDistance = (Integer) getDistance.getSelectedItem();
      controller.getShootArrowSetting(direction, sDistance);
      dispose();
    });

    add(directionDisplay);
    add(getDistance);
    add(confirmButton);
    pack();
    setVisible(true);
  }
}
