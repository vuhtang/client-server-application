package gui;

import collection.WorkerColManager;
import collection.WorkerCollection;
import collection.entity.Worker;
import commands.CommandExecutor;
import exceptions.InvalidInputException;
import gui.dialogs.*;
import parser.WorkerReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class MainFrame extends JFrame {
    private final int USER_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private final int USER_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private final CommandExecutor executor;
    private WorkerColManager colManager;
    private RemoveDialog removeDialog;
    private AddDialog addDialog;
    private UpdateDialog updateDialog;
    private ClearDialog clearDialog;
    private UpdateMiniDialog updateMiniDialog;
    private InsertDialog insertDialog;
    private InsertMiniDialog insertMiniDialog;
    private RemoveLowerDialog removeLowerDialog;
    private AverageDialog averageDialog;
    private final JTabbedPane tabbedPane;
    private JScrollPane scrollPane;
    private JTable table;
    private final JLabel userLabel;
    private final JLabel messageLabel;
    private final JLabel infoLabel;
    private List<String> infoList;
    private final JButton logOutButton;
    private final JButton addButton;
    private final JButton updateButton;
    private final JButton removeButton;
    private final JButton clearButton;
    private final JButton insertButton;
    private final JButton removeLowerButton;
    private final JButton averageOfSalaryButton;
    private final JComboBox<String> langBox;
    private VisualizationPanel visPanel;

    public MainFrame(LoginFrame parent) {
        this.executor = parent.getExecutor();
        setTitle("ye");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(USER_WIDTH / 4 * 3, USER_HEIGHT / 4 * 3);
        setLocation(new Point(USER_WIDTH / 8, USER_HEIGHT / 8));
        setLayout(new BorderLayout(5, 5));

        //-------------------

        WorkerCollection collection = new WorkerCollection();
        try {
            List<String> list = executor.executeCommand("show");
            for (String s : list) {
                collection.add(WorkerReader.readWorker(s.split(",")));
            }
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
        colManager = new WorkerColManager(collection.sortedById());
        WorkerTableModel tableModel = new WorkerTableModel(colManager);
        table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true);
        scrollPane = new JScrollPane(table);

        //--------------------

        visPanel = new VisualizationPanel();
        for (Worker worker: colManager.getWorkers()) {
            visPanel.drawWorker(worker, this);
        }

        //--------------------

        tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        tabbedPane.addTab("Table", scrollPane);
        tabbedPane.addTab("Visualization", visPanel);
        add(tabbedPane, BorderLayout.CENTER);

        //--------------------

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridBagLayout());
        logOutButton = new JButton("Log out");
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        removeButton = new JButton("Remove");
        clearButton = new JButton("Clear");
        insertButton = new JButton("Insert at");
        removeLowerButton = new JButton("Remove lower");
        averageOfSalaryButton = new JButton("Average of salary");
        messageLabel = new JLabel("ye");

        southPanel.add(messageLabel, new GBC(1, 0, 6, 1).setInsets(5));
        southPanel.add(addButton, new GBC(0, 1));
        southPanel.add(updateButton, new GBC(1, 1));
        southPanel.add(removeButton, new GBC(2, 1));
        southPanel.add(clearButton, new GBC(3, 1));
        southPanel.add(insertButton, new GBC(4, 1));
        southPanel.add(removeLowerButton, new GBC(5, 1));
        southPanel.add(averageOfSalaryButton, new GBC(6, 1));

        //-----------------------

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());
        langBox = new JComboBox<>();
        langBox.addItem("English");
        langBox.addItem("Русский");
        langBox.addItem("Română");
        langBox.addItem("Ελληνικά");
        infoLabel = new JLabel();
        try {
            infoList = executor.executeCommand("info");
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }

        userLabel = new JLabel();
        northPanel.add(infoLabel, BorderLayout.WEST);
        northPanel.add(userLabel, BorderLayout.SOUTH);
        JPanel tmpPanel = new JPanel();
        tmpPanel.setLayout(new BorderLayout());
        tmpPanel.add(langBox, BorderLayout.NORTH);
        tmpPanel.add(logOutButton, BorderLayout.SOUTH);
        northPanel.add(tmpPanel, BorderLayout.EAST);

        add(northPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);

        //------------------

        ActionListener logOut = e -> {
            setVisible(false);
            parent.setLang(langBox.getItemAt(langBox.getSelectedIndex()));
            parent.setLocaleAndUpdateLabels(langBox.getItemAt(langBox.getSelectedIndex()));
            parent.setVisible(true);
        };
        logOutButton.addActionListener(logOut);

        ActionListener add = e -> {
            if (addDialog == null) addDialog = new AddDialog(this, "Add", true);
            addDialog.setLocation(USER_WIDTH / 2 - addDialog.getWidth() / 2,
                    USER_HEIGHT / 2 - addDialog.getHeight() / 2);
            addDialog.setLocaleAndUpdateLabels(langBox.getItemAt(langBox.getSelectedIndex()));
            addDialog.setVisible(true);
        };
        addButton.addActionListener(add);

        ActionListener update = e -> {
            if (updateDialog == null) {
                updateDialog = new UpdateDialog(this, "Update", true);
                updateDialog.setLocation(USER_WIDTH / 2 - updateDialog.getWidth() / 2,
                        USER_HEIGHT / 2 - updateDialog.getHeight() / 2);
            }
            if (updateMiniDialog == null) updateMiniDialog = new UpdateMiniDialog(this, updateDialog);
            updateMiniDialog.setLocation(USER_WIDTH / 2 - updateMiniDialog.getWidth() / 2,
                    USER_HEIGHT / 2 - updateMiniDialog.getHeight() / 2);
            updateDialog.setLocaleAndUpdateLabels(langBox.getItemAt(langBox.getSelectedIndex()));
            updateMiniDialog.setLocaleAndUpdateLabels(langBox.getItemAt(langBox.getSelectedIndex()));
            updateMiniDialog.setVisible(true);
            if (updateDialog.getWorkerId() != 0) updateDialog.fillFields();
        };
        updateButton.addActionListener(update);

        ActionListener remove = e -> {
            if (removeDialog == null) {
                removeDialog = new RemoveDialog(this);
                removeDialog.setLocation(USER_WIDTH / 2 - removeDialog.getWidth() / 2,
                        USER_HEIGHT / 2 - removeDialog.getHeight() / 2);
            }
            removeDialog.setLocaleAndUpdateLabels(langBox.getItemAt(langBox.getSelectedIndex()));
            removeDialog.setVisible(true);
        };
        removeButton.addActionListener(remove);

        ActionListener clear = e -> {
            if (clearDialog == null) {
                clearDialog = new ClearDialog(this);
                clearDialog.setLocation(USER_WIDTH / 2 - clearDialog.getWidth() / 2,
                        USER_HEIGHT / 2 - clearDialog.getHeight() / 2);
            }
            clearDialog.setLocaleAndUpdateLabels(langBox.getItemAt(langBox.getSelectedIndex()));
            clearDialog.setVisible(true);
        };
        clearButton.addActionListener(clear);

        ActionListener insert = e -> {
            if (insertDialog == null) {
                insertDialog = new InsertDialog(this, "Insert", true);
                insertDialog.setLocation(USER_WIDTH / 2 - insertDialog.getWidth() / 2,
                        USER_HEIGHT / 2 - insertDialog.getHeight() / 2);
            }
            if (insertMiniDialog == null) {
                insertMiniDialog = new InsertMiniDialog(this, insertDialog);
                insertMiniDialog.setLocation(USER_WIDTH / 2 - insertMiniDialog.getWidth() / 2,
                        USER_HEIGHT / 2 - insertMiniDialog.getHeight() / 2);
            }
            insertMiniDialog.setLocaleAndUpdateLabels(langBox.getItemAt(langBox.getSelectedIndex()));
            insertDialog.setLocaleAndUpdateLabels(langBox.getItemAt(langBox.getSelectedIndex()));
            insertMiniDialog.setVisible(true);
        };
        insertButton.addActionListener(insert);

        ActionListener removeLower = e -> {
            if (removeLowerDialog == null) {
                removeLowerDialog = new RemoveLowerDialog(this, "Remove lower", true);
                removeLowerDialog.setLocation(USER_WIDTH / 2 - removeLowerDialog.getWidth() / 2,
                        USER_HEIGHT / 2 - removeLowerDialog.getHeight() / 2);
            }
            removeLowerDialog.setLocaleAndUpdateLabels(langBox.getItemAt(langBox.getSelectedIndex()));
            removeLowerDialog.setVisible(true);
        };
        removeLowerButton.addActionListener(removeLower);

        ActionListener averageOfSalary = e -> {
            if (averageDialog == null) {
                averageDialog = new AverageDialog(this);
                averageDialog.setLocation(USER_WIDTH / 2 - averageDialog.getWidth() / 2,
                        USER_HEIGHT / 2 - averageDialog.getHeight() / 2);
            }
            averageDialog.setLocaleAndUpdateLabels(langBox.getItemAt(langBox.getSelectedIndex()));
            averageDialog.setVisible(true);
        };
        averageOfSalaryButton.addActionListener(averageOfSalary);

        ActionListener langListener = e -> setLocaleAndUpdateLabels(langBox.getItemAt(langBox.getSelectedIndex()));
        langBox.addActionListener(langListener);

        setLocaleAndUpdateLabels(langBox.getItemAt(langBox.getSelectedIndex()));

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateTable();
            }
        }, 1000, 4000);
    }

    public JLabel getMessageLabel() {
        return messageLabel;
    }

    public CommandExecutor getExecutor() {
        return executor;
    }

    public WorkerColManager getColManager() {
        return colManager;
    }

    public synchronized void updateTable() {
        int selectedTab = tabbedPane.getSelectedIndex();
        WorkerCollection collection = new WorkerCollection();
        try {
            List<String> list = executor.executeCommand("show");
            for (String s : list) {
                collection.add(WorkerReader.readWorker(s.split(",")));
            }
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
        colManager = new WorkerColManager(collection.sortedById());
        WorkerTableModel tableModel = new WorkerTableModel(colManager);
        table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true);

        scrollPane = new JScrollPane(table);

        visPanel = new VisualizationPanel();
        for (Worker worker: colManager.getWorkers()) {
            visPanel.drawWorker(worker, this);
        }

        tabbedPane.removeAll();
        tabbedPane.addTab("Table", scrollPane);
        tabbedPane.addTab("Visualization", visPanel);
        tabbedPane.setSelectedIndex(selectedTab);
        setLocaleAndUpdateLabels(langBox.getItemAt(langBox.getSelectedIndex()));
        revalidate();
    }

    public void setLang(String lang) {
        langBox.setSelectedItem(lang);
    }

    public void setLocaleAndUpdateLabels(String localeName) {
        Translator.setLocale(localeName);
        ResourceBundle bundle = ResourceBundle.getBundle("MainLabels", Translator.currentLocale());
        tabbedPane.setTitleAt(0, bundle.getString("table"));
        tabbedPane.setTitleAt(1, bundle.getString("visual"));
        infoLabel.setText("<html><b>" + bundle.getString("colType") + "</b> " + infoList.get(0) + "<br><b>" +
                bundle.getString("initDate") + "</b> " + infoList.get(1));
        userLabel.setText("<html><b>" + bundle.getString("user") + "</b> " + executor.getTransfer().getToken().getUserName() + "</html>");
        logOutButton.setText(bundle.getString("logOut"));
        addButton.setText(bundle.getString("add"));
        updateButton.setText(bundle.getString("update"));
        removeButton.setText(bundle.getString("remove"));
        clearButton.setText(bundle.getString("clear"));
        insertButton.setText(bundle.getString("insert"));
        removeLowerButton.setText(bundle.getString("removeLower"));
        averageOfSalaryButton.setText(bundle.getString("average"));
    }
}
