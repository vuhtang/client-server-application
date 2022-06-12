package gui.dialogs;

import collection.entity.Worker;
import exceptions.InvalidInputException;
import gui.GBC;
import gui.MainFrame;
import gui.Translator;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

public class RemoveDialog extends JDialog {
    private ResourceBundle bundle;
    private final JLabel enterIdLabel;
    private final JButton ok;
    private final JButton cancel;

    public RemoveDialog(MainFrame owner) {
        super(owner, "Remove", true);
        setSize(600, 120);
        setMinimumSize(new Dimension(600, 120));
        setLayout(new GridBagLayout());
        enterIdLabel = new JLabel();
        add(enterIdLabel, new GBC(0,0,1,1));
        JTextField id = new JTextField(15);
        ok = new JButton("Ok");
        cancel = new JButton("Cancel");
        JLabel messageLabel = new JLabel();
        add(id, new GBC(1,0,1,1));
        add(messageLabel, new GBC(0,1,2,1));
        add(ok, new GBC(0,2,1,1));
        add(cancel, new GBC(1,2,1,1));

        cancel.addActionListener(e -> setVisible(false));

        ok.addActionListener(e -> {
            try {
                if (owner.getColManager().checkId(Integer.parseInt(id.getText()))) {
                    Worker worker = new Worker();
                    worker.setId(Integer.parseInt(id.getText()));
                    owner.getMessageLabel().setText(owner.getExecutor().executeCommand("remove_by_id " + id.getText(), worker).get(0));
                    setVisible(false);
                    owner.updateTable();
                } else messageLabel.setText(bundle.getString("incorrectId"));
            } catch (NumberFormatException ex) {
                messageLabel.setText((bundle.getString("incorrectId")));
            } catch (InvalidInputException ex) {
                messageLabel.setText(ex.getMessage());
            }
        });
    }

    public void setLocaleAndUpdateLabels(String localeName) {
        bundle = ResourceBundle.getBundle("MainLabels", Translator.getLocale(localeName));
        enterIdLabel.setText(bundle.getString("enterIdRemove"));
        ok.setText(bundle.getString("ok"));
        cancel.setText(bundle.getString("cancel"));
        setTitle(bundle.getString("remove"));
    }
}
