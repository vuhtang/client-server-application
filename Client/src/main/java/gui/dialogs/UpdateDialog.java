package gui.dialogs;

import collection.WorkerColManager;
import collection.entity.Worker;
import commands.AddRequest;
import exceptions.InvalidInputException;
import gui.MainFrame;
import gui.Translator;

import java.util.ResourceBundle;

public class UpdateDialog extends AddDialog {
    private final MainFrame mainFrame;
    private int workerId;

    public UpdateDialog(MainFrame owner, String title, boolean modal) {
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
                owner.getMessageLabel().setText(owner.getExecutor().executeCommand("update " + workerId, worker).get(0));
                setVisible(false);
                owner.updateTable();
            } catch (InvalidInputException | NumberFormatException ex) {
                getMessageLabel().setText(ex.getMessage());
                owner.getMessageLabel().setText(ex.getMessage());
            }
        });
    }

    public void fillFields() {
        Worker worker = mainFrame.getColManager().getWorkerByID(workerId);
        setCoordinateX(Math.toIntExact(worker.getCoordinates().getX()));
        setCoordinateY((int) worker.getCoordinates().getY());
        setLocationXText(worker.getPerson().getLocation().getX().toString());
        setLocationYText(worker.getPerson().getLocation().getY().toString());
        setLocationZText(worker.getPerson().getLocation().getZ().toString());
        setLocationNameText(worker.getPerson().getLocation().getName());
        setPersonHeightText(worker.getPerson().getHeight().toString());
        setPassportIdText(worker.getPerson().getPassportID());
        setNameText(worker.getName());
        setSalaryText(Long.toString(worker.getSalary()));
        setPositionComboBox(worker.getPosition());
        setStatusComboBox(worker.getStatus());
    }

    public void setLocaleAndUpdateLabels(String localeName) {
        ResourceBundle bundle = ResourceBundle.getBundle("MainLabels", Translator.getLocale(localeName));
        setAddButtonName(bundle.getString("update"));
        setCancelButtonName(bundle.getString("cancel"));
        setTitle(bundle.getString("update"));
    }

    public WorkerColManager getColManager() {
        return mainFrame.getColManager();
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public int getWorkerId() {
        return workerId;
    }
}
