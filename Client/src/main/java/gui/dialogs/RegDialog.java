package gui.dialogs;

import commands.CommandExecutor;
import exceptions.InvalidInputException;
import gui.LoginFrame;
import gui.Translator;
import transferring.Token;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * Window for registration. Lets you pass on, even if something went wrong.
 */
public class RegDialog extends JDialog {
    private final JLabel enterLoginLabel;
    private final JLabel enterPasswordLabel;
    private final JButton register;
    private final JButton cancel;

    public RegDialog(LoginFrame owner) {
        super(owner, "Registration", true);
        setLayout(new FlowLayout());
        setSize(300,200);
        setMinimumSize(new Dimension(300,200));
        enterLoginLabel = new JLabel();
        add(enterLoginLabel);
        JTextField login = new JTextField(20);
        add(login);
        enterPasswordLabel = new JLabel();
        add(enterPasswordLabel);
        JPasswordField password = new JPasswordField(20);
        add(password);
        register = new JButton();
        add(register);
        cancel = new JButton();
        add(cancel);

        cancel.addActionListener(e -> setVisible(false));

        register.addActionListener(e -> {
            String newLogin = login.getText();
            String newPassword = password.getText();
            String args = newLogin + "," + newPassword;
            CommandExecutor executor = owner.getExecutor();
            executor.getTransfer().setToken(new Token(newLogin, newPassword));
            try {
                executor.executeCommand("register " + args);
            } catch (InvalidInputException ex) {
                ex.printStackTrace();
            }
            setVisible(false);
        });
    }

    /**
     * Updates all labels in window according to locale from the argument.
     * Calls when the window is opened.
     *
     * @param localeName locale to display labels
     */
    public void setLocaleAndUpdateLabels(String localeName) {
        ResourceBundle bundle = ResourceBundle.getBundle("RegDialogLabels", Translator.getLocale(localeName));
        enterLoginLabel.setText(bundle.getString("enterLogin"));
        enterPasswordLabel.setText(bundle.getString("enterPassword"));
        register.setText(bundle.getString("register"));
        cancel.setText(bundle.getString("cancel"));
    }
}
