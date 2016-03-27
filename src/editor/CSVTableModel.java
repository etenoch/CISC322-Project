package editor;

import javax.swing.table.AbstractTableModel;


public class CSVTableModel extends AbstractTableModel {

    String[] header;
    String[][] data;

    public CSVTableModel(String[] header,String[][] data){
        this.header = header;
        this.data = data;
    }

    public int getColumnCount() {
        return header.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return header[col];
    }

    public String getValueAt(int row, int col) {
        return data[row][col];
    }

    public void setValueAt(Object value, int row, int col) {
        System.out.println((String) value);
        data[row][col] = (String)value;
    }

    public boolean isCellEditable(int row, int col) {
        return true;
    }

}
