package editor;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class CSVContents extends AbstractTableModel {

    String[] header;
    String[][] data;

    ArrayList<int[]> columnReorders = new ArrayList<>();

    public CSVContents(){

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
        data[row][col] = (String) value;
    }

    public boolean isCellEditable(int row, int col) {
        return true;
    }

    public void moveColumn(int columnFrom, int columnTo){
        columnReorders.add(new int[]{columnFrom,columnTo});
    }

    public void open(InputStream in) throws IOException {
        CSVReader reader = new CSVReader(new InputStreamReader(in));
        List<String[]> rawData = reader.readAll();

        if (rawData!=null){
            data = new String[rawData.size()][];
            int i = 0;
            for(String[] line : rawData){
                data[i]=line;
                i++;
            }

            header = new String[data[0].length];
            char alphabet = 'A';
            for (int j = 0; j < header.length; j++){
                header[j]=String.valueOf(alphabet);
                alphabet++;
            }
        }

    } // end method open

    public void save(OutputStream out) throws IOException {
        CSVWriter writer = new CSVWriter(new OutputStreamWriter(out),',', CSVWriter.NO_QUOTE_CHARACTER);

        String[] saveData;
        for(String[] line : data){
            saveData = new String[line.length];
            System.arraycopy(line,0,saveData,0,line.length);
            for(int[] change:columnReorders){
                swap(saveData,change[0],change[1]);
            }

            writer.writeNext(saveData);
        }
        writer.close();
    } // end save

    public InputStream getContentsStream() //throws DocumentException
    {
        return null;
    } // end getContentStream


    // helper function

    public static void swap(String[] array, int i, int j){
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


}
