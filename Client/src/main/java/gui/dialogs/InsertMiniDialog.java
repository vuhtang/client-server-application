package gui.dialogs;

import gui.GBC;
import gui.MainFrame;
import gui.Translator;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * Mini-dialog index entry window. Lets further only if input is correct.
 * Tells this index to the next dialog.
 */
public class InsertMiniDialog extends JDialog {
    private ResourceBundle bundle;
    private final JTextField position;
    private final JButton ok;
    private final JButton cancel;
    private final JLabel messageLabel;
    private final JLabel enterPositionLabel;

    public InsertMiniDialog(MainFrame owner, InsertDialog insertDialog) {
        super(owner, "Insert At", true);
        setSize(600, 120);
        setMinimumSize(new Dimension(450,120));
        setLayout(new GridBagLayout());
        enterPositionLabel = new JLabel();
        add(enterPositionLabel, new GBC(0,0,1,1));
        position = new JTextField(15);
        ok = new JButton();
        cancel = new JButton();
        messageLabel = new JLabel();
        add(position, new GBC(1,0,1,1));
        add(messageLabel, new GBC(0,1,2,1));
        add(ok, new GBC(0,2,1,1));
        add(cancel, new GBC(1,2,1,1));


        cancel.addActionListener(e -> setVisible(false));

        ok.addActionListener(e -> {
            try {
                int positionNumber = Integer.parseInt(position.getText());
                if (0 < positionNumber && positionNumber < insertDialog.getColManager().getSize()) {
                    insertDialog.setPositionNumber(positionNumber);
                    setVisible(false);
                    insertDialog.setVisible(true);
                } else messageLabel.setText(bundle.getString("incorrectPosition"));
            } catch (NumberFormatException ex) {
                messageLabel.setText(bundle.getString("incorrectPosition"));
            }
        });
    }

    /**
     * Updates all labels in window according to locale from the argument.
     * Called when the window is opened.
     *
     * @param localeName locale to display labels
     */
    public void setLocaleAndUpdateLabels(String localeName) {
        bundle = ResourceBundle.getBundle("MainLabels", Translator.getLocale(localeName));
        enterPositionLabel.setText(bundle.getString("enterPosition"));
        ok.setText(bundle.getString("ok"));
        cancel.setText((bundle.getString("cancel")));
        setTitle(bundle.getString("insert"));
    }
}
