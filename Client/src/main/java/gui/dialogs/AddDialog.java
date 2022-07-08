package gui.dialogs;

import collection.entity.Position;
import collection.entity.Status;
import collection.entity.Worker;
import commands.AddRequest;
import exceptions.InvalidInputException;
import gui.GBC;
import gui.MainFrame;
import gui.Translator;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

/**
 * Dialog window for adding new object.
 * Dialogs with similar functionality are also inherited from it.
 */
public class AddDialog extends JDialog {
    private final JLabel messageLabel;
    private final JSlider coordXSlider;
    private final JSlider coordYSlider;
    private final JTextField locationX;
    private final JTextField locationY;
    private final JTextField locationZ;
    private final JTextField locationName;
    private final JTextField personHeight;
    private final JTextField passportId;
    private final JTextField name;
    private final JTextField salary;
    private final JComboBox<Position> positionComboBox;
    private final JComboBox<Status> statusComboBox;
    private final JButton addButton;
    private final JButton cancelButton;
    private ActionListener addButtonListener;

    public AddDialog(MainFrame owner, String title, boolean modal) {
        super(owner, title, modal);
        setMinimumSize(new Dimension(500, 450));
        int USER_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
        int USER_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
        setSize(USER_WIDTH / 4, USER_HEIGHT / 4);
        setLayout(new GridBagLayout());
        coordXSlider = new JSlider(JSlider.HORIZONTAL, -100, 100, 0);
        coordXSlider.setPaintTicks(true);
        coordXSlider.setMajorTickSpacing(20);
        coordXSlider.setMinorTickSpacing(10);
        coordYSlider = new JSlider(JSlider.HORIZONTAL, -100, 100, 0);
        coordYSlider.setPaintTicks(true);
        coordYSlider.setMajorTickSpacing(20);
        coordYSlider.setMinorTickSpacing(10);
        locationX = new JTextField(15);
        locationY = new JTextField(15);
        locationZ = new JTextField(15);
        locationName = new JTextField(15);
        personHeight = new JTextField(15);
        passportId = new JTextField(15);
        name = new JTextField(15);
        salary = new JTextField(15);
        positionComboBox = new JComboBox<>(Position.values());
        statusComboBox = new JComboBox<>(Status.values());
        messageLabel = new JLabel();
        addButton = new JButton("Add");
        cancelButton = new JButton("Cancel");
        add(new JLabel("CoordinateX"), new GBC(0, 0).setAnchor(GBC.EAST));
        add(coordXSlider, new GBC(1, 0).setInsets(3));
        add(new JLabel("CoordinateY"), new GBC(0, 1).setAnchor(GBC.EAST));
        add(coordYSlider, new GBC(1, 1).setInsets(3));
        add(new JLabel("LocationX"), new GBC(0, 2).setAnchor(GBC.EAST));
        add(locationX, new GBC(1, 2));
        add(new JLabel("LocationY"), new GBC(0, 3).setAnchor(GBC.EAST));
        add(locationY, new GBC(1, 3));
        add(new JLabel("LocationZ"), new GBC(0, 4).setAnchor(GBC.EAST));
        add(locationZ, new GBC(1, 4));
        add(new JLabel("Location name"), new GBC(0, 5).setAnchor(GBC.EAST));
        add(locationName, new GBC(1, 5));
        add(new JLabel("Person height"), new GBC(0, 6).setAnchor(GBC.EAST));
        add(personHeight, new GBC(1, 6));
        add(new JLabel("PassportID"), new GBC(0, 7).setAnchor(GBC.EAST));
        add(passportId, new GBC(1, 7));
        add(new JLabel("Name"), new GBC(0, 8).setAnchor(GBC.EAST));
        add(name, new GBC(1, 8));
        add(new JLabel("Salary"), new GBC(0, 9).setAnchor(GBC.EAST));
        add(salary, new GBC(1, 9));
        add(new JLabel("Position"), new GBC(0, 10).setAnchor(GBC.EAST));
        add(positionComboBox, new GBC(1, 10));
        add(new JLabel("Status"), new GBC(0, 11).setAnchor(GBC.EAST));
        add(statusComboBox, new GBC(1, 11));
        add(messageLabel, new GBC(0, 12, 2, 1));
        add(addButton, new GBC(0, 13).setInsets(10));
        add(cancelButton, new GBC(1, 13).setInsets(10));

        //--------------

        ChangeListener listener = event -> {
            JSlider slider = (JSlider) event.getSource();
            messageLabel.setText(Integer.toString(slider.getValue()));
        };
        coordXSlider.addChangeListener(listener);
        coordYSlider.addChangeListener(listener);
        cancelButton.addActionListener(e -> setVisible(false));
        addButtonListener = e -> {
            try {
                String[] values = new String[]{Integer.toString(coordXSlider.getValue()),
                        Integer.toString(coordYSlider.getValue()), locationX.getText(), locationY.getText(),
                        locationZ.getText(), locationName.getText(), personHeight.getText(),
                        passportId.getText(), name.getText(), salary.getText(),
                        positionComboBox.getItemAt(positionComboBox.getSelectedIndex()).toString(),
                        statusComboBox.getItemAt(statusComboBox.getSelectedIndex()).toString()};
                Worker worker = new AddRequest().getRowWorker(values);
                worker.setOwner(owner.getExecutor().getTransfer().getToken().getUserName());
                owner.getMessageLabel().setText(owner.getExecutor().executeCommand("add", worker).get(0));
                setVisible(false);
                owner.updateTable();
            } catch (InvalidInputException | NumberFormatException ex) {
                messageLabel.setText(ex.getMessage());
                owner.getMessageLabel().setText(ex.getMessage());
            }
        };
        addButton.addActionListener(addButtonListener);
    }

    /**
     * Updates all labels in window according to locale from the argument.
     * Called when the window is opened.
     *
     * @param localeName locale to display labels
     */
    public void setLocaleAndUpdateLabels(String localeName) {
        ResourceBundle bundle = ResourceBundle.getBundle("MainLabels", Translator.getLocale(localeName));
        addButton.setText(bundle.getString("add"));
        cancelButton.setText(bundle.getString("cancel"));
        setTitle(bundle.getString("add"));
    }

    public JSlider getCoordXSlider() {
        return coordXSlider;
    }

    public JSlider getCoordYSlider() {
        return coordYSlider;
    }

    public JTextField getLocationX() {
        return locationX;
    }

    public JTextField getLocationY() {
        return locationY;
    }

    public JTextField getLocationZ() {
        return locationZ;
    }

    public JTextField getLocationName() {
        return locationName;
    }

    public JTextField getPersonHeight() {
        return personHeight;
    }

    public JTextField getPassportId() {
        return passportId;
    }

    public JTextField getNameField() {
        return name;
    }

    public JTextField getSalary() {
        return salary;
    }

    public JLabel getMessageLabel() {
        return messageLabel;
    }

    public JComboBox<Position> getPositionComboBox() {
        return positionComboBox;
    }

    public JComboBox<Status> getStatusComboBox() {
        return statusComboBox;
    }

    public void setAddButtonName(String name) {
        addButton.setText(name);
    }

    public void setCancelButtonName(String name) {
        cancelButton.setText(name);
    }

    public void setAddButtonListener(ActionListener listener) {
        addButton.removeActionListener(addButtonListener);
        addButton.addActionListener(listener);
        addButtonListener = listener;
    }

    public void setLocationXText(String text) {
        locationX.setText(text);
    }

    public void setLocationYText(String text) {
        locationY.setText(text);
    }

    public void setLocationZText(String text) {
        locationZ.setText(text);
    }

    public void setLocationNameText(String text) {
        locationName.setText(text);
    }

    public void setPersonHeightText(String text) {
        personHeight.setText(text);
    }

    public void setPassportIdText(String text) {
        passportId.setText(text);
    }

    public void setNameText(String text) {
        name.setText(text);
    }

    public void setSalaryText(String text) {
        salary.setText(text);
    }

    public void setCoordinateX(int value) {
        coordXSlider.setValue(value);
    }

    public void setCoordinateY(int value) {
        coordYSlider.setValue(value);
    }

    public void setPositionComboBox(Position position) {
        positionComboBox.setSelectedItem(position);
    }

    public void setStatusComboBox(Status status) {
        statusComboBox.setSelectedItem(status);
    }
}
