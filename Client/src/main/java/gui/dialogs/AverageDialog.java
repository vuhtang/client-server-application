package gui.dialogs;

import commands.CommandExecutor;
import exceptions.InvalidInputException;
import gui.GBC;
import gui.MainFrame;
import gui.Translator;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

public class AverageDialog extends JDialog {
    private final CommandExecutor executor;
    private final JLabel messageLabel;
    private final JButton ok;

    public AverageDialog(MainFrame owner) {
        super(owner, "Average", true);
        setSize(400, 100);
        setMinimumSize(new Dimension(300,100));
        setLayout(new GridBagLayout());
        executor = owner.getExecutor();
        messageLabel = new JLabel();
        add(messageLabel, new GBC(0,0,2,1));
        ok = new JButton();
        add(ok, new GBC(0,1,2,1));

        ok.addActionListener(e -> setVisible(false));
    }

    public void setLocaleAndUpdateLabels(String localeName) {
        ResourceBundle bundle = ResourceBundle.getBundle("MainLabels", Translator.getLocale(localeName));
        try {
            messageLabel.setText(bundle.getString("average") + ": " +  executor.executeCommand("average_of_salary"));
        } catch (InvalidInputException ex) {
            messageLabel.setText(":-(");
        }
        ok.setText(bundle.getString("ok"));
        setTitle(bundle.getString("average"));
    }
}
