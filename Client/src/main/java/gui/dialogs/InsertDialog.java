package gui.dialogs;

import collection.WorkerColManager;
import collection.entity.Worker;
import commands.AddRequest;
import exceptions.InvalidInputException;
import gui.MainFrame;
import gui.Translator;

import java.util.ResourceBundle;

/**
 * Dialog window for inserting new worker at the index as a collection index.
 */
public class InsertDialog extends AddDialog {
    /**
     * Position number to insert at. Sets after mini-dialog with index entry.
     */
    private int positionNumber;
    private final MainFrame mainFrame;

    public InsertDialog(MainFrame owner, String title, boolean modal) {
        super(owner, title, modal);
        mainFrame = owner;
        setAddButtonListener(e -> {
            try {
                String[] values = new String[]{Integer.toString(getCoordXSlider().getValue()),
                        Integer.toString(getCoordYSlider().getValue()), getLocationX().getText(), getLocationY().getText(),
                        getLocationZ().getText(), getLocationName().getText(), getPersonHeight().getText(),
                        getPassportId().getText(), getNameField().getText(), getSalary().getText(),
                        getPositionComboBox().getItemAt(getPositionComboBox().getSelectedIndex()).toString(),
                        getStatusComboBox().getItemAt(getStatusComboBox().getSelectedIndex()).toString()};
                Worker worker = new AddRequest().getRowWorker(values);
                worker.setOwner(owner.getExecutor().getTransfer().getToken().getUserName());
                owner.getMessageLabel()
                        .setText(owner.getExecutor().executeCommand("insert_at " + positionNumber, worker).get(0));
                setVisible(false);
                owner.updateTable();
            } catch (InvalidInputException | NumberFormatException ex) {
                getMessageLabel().setText(ex.getMessage());
                owner.getMessageLabel().setText(ex.getMessage());
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
        ResourceBundle bundle = ResourceBundle.getBundle("MainLabels", Translator.getLocale(localeName));
        setAddButtonName(bundle.getString("insert"));
        setCancelButtonName(bundle.getString("cancel"));
        setTitle(bundle.getString("insert"));
    }

    public void setPositionNumber(int positionNumber) {
        this.positionNumber = positionNumber;
    }

    public WorkerColManager getColManager() {
        return mainFrame.getColManager();
    }
}
