package gui.dialogs;

import exceptions.InvalidInputException;
import gui.MainFrame;
import gui.Translator;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

public class ClearDialog extends JDialog {
    private final JLabel sureLabel;
    private final JButton yes;
    private final JButton no;

    public ClearDialog(MainFrame owner) {
        super(owner, "Clear", true);
        setSize(300, 100);
        setMinimumSize(new Dimension(300, 100));
        setLayout(new FlowLayout());
        sureLabel = new JLabel();
        add(sureLabel);
        yes = new JButton();
        no = new JButton();
        add(yes);
        add(no);
        no.addActionListener(e -> setVisible(false));
        yes.addActionListener(e -> {
            try {
                owner.getMessageLabel().setText(owner.getExecutor().executeCommand("clear").get(0));
                setVisible(false);
                owner.updateTable();
            } catch (InvalidInputException ex) {
                owner.getMessageLabel().setText(ex.getMessage());
            }
        });
    }

    public void setLocaleAndUpdateLabels(String localeName) {
        ResourceBundle bundle = ResourceBundle.getBundle("MainLabels", Translator.getLocale(localeName));
        sureLabel.setText(bundle.getString("sure"));
        yes.setText(bundle.getString("yes"));
        no.setText(bundle.getString("no"));
        setTitle(bundle.getString("clear"));
    }
}
