package view;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * Setting window that will pop up when the user hit the "New Game" button.
 */
public class SettingDialog extends JDialog {

  /**
   * Constructor for the settingDialog.
   *
   * @param frame      JFrame
   * @param controller controller
   */
  public SettingDialog(JFrame frame, DungeonsController controller) {
    super(frame, "Game Setting");
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(7, 2));
    JLabel row = new JLabel("Number of rows: ");
    JTextField getRow = new JTextField(3);
    JLabel col = new JLabel("Number of columns: ");
    JTextField getCol = new JTextField(3);
    JLabel wrapping = new JLabel("Wrapping or not: ");
    String[] wrappingChoices = new String[]{"Yes", "No"};
    JComboBox<String> getWrapping = new JComboBox<>(wrappingChoices);
    JLabel interconnectivity = new JLabel("Interconnectivity of the dungeon: ");
    JTextField getInterconnectivity = new JTextField(3);
    JLabel treArrowPercentage = new JLabel("Percentage of treasures and arrows (20 - 100): ");
    JTextField getTreArrowPercentage = new JTextField(3);
    JLabel monsterPercentage = new JLabel("Percentage of monsters (0 - 100): ");
    JTextField getMonsterPercentage = new JTextField(3);

    JButton cancelButton = new JButton("Cancel");
    cancelButton.setActionCommand("Cancel");
    cancelButton.addActionListener(e -> dispose());
    JButton confirmButton = new JButton("Start Playing");
    confirmButton.setActionCommand("Confirm");
    confirmButton.addActionListener(e -> {
      try {
        int rVal = Integer.parseInt(getRow.getText());
        int cVal = Integer.parseInt(getCol.getText());
        String wVal = (String) getWrapping.getSelectedItem();
        int interC = Integer.parseInt(getInterconnectivity.getText());
        int treArrowP = Integer.parseInt(getTreArrowPercentage.getText());
        int monsterP = Integer.parseInt(getMonsterPercentage.getText());

        controller.getSettingFromView(rVal, cVal, wVal, interC, treArrowP, monsterP);
        dispose();
      } catch (NumberFormatException nfe) {
        // Skip
      }

    });

    panel.add(row);
    panel.add(getRow);
    panel.add(col);
    panel.add(getCol);
    panel.add(wrapping);
    panel.add(getWrapping);
    panel.add(interconnectivity);
    panel.add(getInterconnectivity);
    panel.add(treArrowPercentage);
    panel.add(getTreArrowPercentage);
    panel.add(monsterPercentage);
    panel.add(getMonsterPercentage);
    panel.add(cancelButton);
    panel.add(confirmButton);
    add(panel);
    pack();
    setVisible(true);
  }

}
