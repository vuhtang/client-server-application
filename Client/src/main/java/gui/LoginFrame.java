package gui;

import commands.CommandExecutor;
import exceptions.InvalidInputException;
import gui.dialogs.RegDialog;
import transferring.Token;
import transferring.Transfer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;


public class LoginFrame extends JFrame {
    private final int USER_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private final int USER_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private final CommandExecutor executor;
    private final JComboBox<String> langBox;
    private final JLabel workersLabel;
    private final JLabel signInLabel;
    private final JLabel messageLabel;
    private final JLabel loginLabel;
    private final JLabel passwordLabel;
    private final JLabel notRegLabel;
    private final JButton loginButton;
    private final JLabel registerButton;
    private RegDialog regDialog;
    private MainFrame mainFrame;

    public LoginFrame(Transfer transfer) {
        this.executor = new CommandExecutor(transfer);
        setSize(USER_WIDTH / 2 - 50, USER_HEIGHT / 2 - 50);
        setMinimumSize(new Dimension(USER_WIDTH / 2 - 50, USER_HEIGHT / 2 - 50));
        setLocation(new Point(USER_WIDTH / 4 + 25, USER_HEIGHT / 4 + 25));
        setLayout(new GridBagLayout());

        ImageIcon imageWorkers = new ImageIcon("Client/src/main/resources/workers.jpg");
        JLabel imageLabel = new JLabel(imageWorkers);
        Font workersFont = new Font("Dialog", Font.BOLD, 17);
        workersLabel = new JLabel();
        workersLabel.setFont(workersFont);
        Font signInFont = new Font("Dialog", Font.BOLD, 14);
        signInLabel = new JLabel();
        signInLabel.setFont(signInFont);
        messageLabel = new JLabel();
        messageLabel.setForeground(Color.RED);
        loginLabel = new JLabel();
        passwordLabel = new JLabel();
        JTextField loginField = new JTextField(20);
        JPasswordField pwdField = new JPasswordField(20);
        notRegLabel = new JLabel();
        loginButton = new JButton();
        registerButton = new JLabel();
        registerButton.setForeground(Color.BLUE);

        langBox = new JComboBox<>();
        langBox.addItem("English");
        langBox.addItem("Русский");
        langBox.addItem("Română");
        langBox.addItem("Ελληνικά");

        setLocaleAndUpdateLabels("English");
        ActionListener listenerLogIn = e -> {
            String login = loginField.getText();
            String password = pwdField.getText();
            Token token = new Token(login, password);
            transfer.setToken(token);
            List<String> result = null;
            try {
                result = executor.executeCommand("logIn");
            } catch (InvalidInputException ex) {
                messageLabel.setText(ex.getMessage());
            }
            if (result.get(0).equals("ye")) {
                if (mainFrame == null) mainFrame = new MainFrame(this);
                mainFrame.setLang(langBox.getItemAt(langBox.getSelectedIndex()));
                mainFrame.setLocaleAndUpdateLabels(langBox.getItemAt(langBox.getSelectedIndex()));
                setVisible(false);
                mainFrame.setVisible(true);
            } else messageLabel.setText(result.get(0));
        };

        MouseAdapter adapterReg = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (regDialog == null) {
                    regDialog = new RegDialog(LoginFrame.this);
                    regDialog.setLocation(USER_WIDTH / 2 - 150, USER_HEIGHT / 2 - 100);
                }
                regDialog.setLocaleAndUpdateLabels(langBox.getItemAt(langBox.getSelectedIndex()));
                regDialog.setVisible(true);
            }
        };

        ActionListener langListener = e -> {
            setLocaleAndUpdateLabels(langBox.getItemAt(langBox.getSelectedIndex()));
            mainFrame.setLang(langBox.getItemAt(langBox.getSelectedIndex()));
        };

        loginButton.addActionListener(listenerLogIn);
        registerButton.addMouseListener(adapterReg);
        langBox.addActionListener(langListener);

        add(imageLabel, new GBC(0, 0, 2, 3).setAnchor(GBC.NORTHWEST).setInsets(10));
        add(langBox, new GBC(3, 0).setAnchor(GBC.EAST));
        add(workersLabel, new GBC(2, 2, 2, 1).setAnchor(GBC.CENTER));
        add(signInLabel, new GBC(0, 4, 4, 1).setAnchor(GBC.CENTER).setInsets(5));
        add(messageLabel, new GBC(0, 5, 4, 1).setAnchor(GBC.CENTER).setInsets(5));
        add(loginLabel, new GBC(0, 6).setAnchor(GBC.EAST).setInsets(5));
        add(loginField, new GBC(1, 6, 2, 1).setAnchor(GBC.WEST));
        add(passwordLabel, new GBC(0, 7).setAnchor(GBC.EAST).setInsets(5));
        add(pwdField, new GBC(1, 7, 2, 1).setAnchor(GBC.WEST));
        add(notRegLabel, new GBC(0, 8, 3, 1).setAnchor(GBC.CENTER).setInsets(10));
        add(loginButton, new GBC(3, 6, 1, 2).setAnchor(GBC.CENTER));
        add(registerButton, new GBC(3, 8));
    }

    public CommandExecutor getExecutor() {
        return executor;
    }

    public void setLang(String lang) {
        langBox.setSelectedItem(lang);
    }

    public void setLocaleAndUpdateLabels(String localeName) {
        Translator.setLocale(localeName);
        ResourceBundle bundle = ResourceBundle.getBundle("LoginLabels", Translator.currentLocale());
        workersLabel.setText("<html>" + bundle.getString("welcomeTo")
                + "<br>" + bundle.getString("app") + "<html>");
        signInLabel.setText(bundle.getString("signIn"));
        loginLabel.setText(bundle.getString("login"));
        passwordLabel.setText(bundle.getString("password"));
        notRegLabel.setText(bundle.getString("notRegistered"));
        loginButton.setText(bundle.getString("logIn"));
        registerButton.setText(bundle.getString("register"));
    }
}
