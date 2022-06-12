package gui.dialogs;

import collection.entity.Worker;
import commands.AddRequest;
import exceptions.InvalidInputException;
import gui.MainFrame;
import gui.Translator;

import java.util.ResourceBundle;

public class RemoveLowerDialog extends AddDialog {

    public RemoveLowerDialog(MainFrame owner, String title, boolean modal) {
        super(owner, title, modal);
        setAddButtonListener(e -> {
            try {
                String[] values = new String[]{Integer.toString(getCoordXSlider().getValue()),
                        Integer.toString(getCoordYSlider().getValue()), getLocationX().getText(), getLocationY().getText(),
                        getLocationZ().getText(), getLocationName().getText(), getPersonHeight().getText(),
                        getPassportId().getText(), getNameField().getText(), getSalary().getText(),
                        getPositionComboBox().getItemAt(getPositionComboBox().getSelectedIndex()).toString(),
                        getStatusComboBox().getItemAt(getStatusComboBox().getSelectedIndex()).toString()};
                Worker worker = new AddRequest().getRowWorker(values);
                owner.getMessageLabel().setText(owner.getExecutor().executeCommand("remove_lower", worker).get(0));
                setVisible(false);
                owner.updateTable();
            } catch (InvalidInputException | NumberFormatException ex) {
                getMessageLabel().setText(ex.getMessage());
                owner.getMessageLabel().setText(ex.getMessage());
            }
        });
    }

    public void setLocaleAndUpdateLabels(String localeName) {
        ResourceBundle bundle = ResourceBundle.getBundle("MainLabels", Translator.getLocale(localeName));
        setAddButtonName(bundle.getString("removeLower"));
        setCancelButtonName(bundle.getString("cancel"));
        setTitle(bundle.getString("removeLower"));
    }
}
