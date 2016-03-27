package editor;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.util.List;


public class CSVContents extends AbstractTableModel {

    String[] header;
    String[][] data;

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
        System.out.println((String) value);
        data[row][col] = (String) value;
    }

    public boolean isCellEditable(int row, int col) {
        return true;
    }


    public void open(InputStream in) throws IOException {
        CSVReader reader = new CSVReader(new InputStreamReader(in));
        List<String[]> rawData = reader.readAll();

        if (rawData!=null){

            data = new String[rawData.size()-1][];
            int i = 0;
            for(String[] line : rawData){
                if (i==0) header = line;
                else data[i-1]=line;
                i++;
            }

        }

    } // end method open

    public void write(Writer out) // throws IOException
    {

    } // end method write


    public void save(OutputStream out) throws IOException {
        CSVWriter writer = new CSVWriter(new OutputStreamWriter(out),',', CSVWriter.NO_QUOTE_CHARACTER);

        writer.writeNext(header);
        for(String[] line : data){
            writer.writeNext(line);
        }
        writer.close();
    } // end save

    public InputStream getContentsStream() //throws DocumentException
    {
        return null;
    } // end getContentStream


}
