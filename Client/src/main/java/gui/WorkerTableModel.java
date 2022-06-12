package gui;

import collection.WorkerColManager;

import javax.swing.table.AbstractTableModel;

public class WorkerTableModel extends AbstractTableModel {
    private final WorkerColManager colManager;
    private final Object[][] rowData;
    private final String[] fieldNames;

    public WorkerTableModel(WorkerColManager colManager) {
        this.colManager = colManager;
        this.rowData = colManager.getRowData();
        this.fieldNames = colManager.getRowFieldNames();
    }

    @Override
    public String getColumnName(int column) {
        return fieldNames[column];
    }

    @Override
    public int getRowCount() {
        return colManager.getSize();
    }

    @Override
    public int getColumnCount() {
        return 14;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rowData[rowIndex][columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0,columnIndex).getClass();
    }
}
