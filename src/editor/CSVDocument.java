package editor;
// $Id: TextDocument.java,v 1.0 2012/10/04 13:57:18 dalamb Exp $

import ca.queensu.cs.dal.edfmwk.doc.AbstractDocument;
import ca.queensu.cs.dal.edfmwk.doc.DocumentException;
import ca.queensu.cs.dal.edfmwk.doc.DocumentType;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Implementation of a csv document
 *
 * Original code provided by David Alex Lamb 2010.
 * Modified by Enoch Tam, Vic Setlur, Eric Balboa
 */
public class CSVDocument extends AbstractDocument{
    private static int numRows = 20;
    private static int numColumns = 80;
    private CSVContents contents;
    private JTable jtable;

    boolean columnDragging = false;
    boolean columnIndexChanged = false;


    /**
     * Constructs a document representation.
     *
     * @param type The type of the document.
     */
    public CSVDocument(DocumentType type) {
        super(type);
        jtable = new JTable(contents);
        window = new JScrollPane(jtable);

    } // end CSVDocument


    /**
     * Saves the entire document.  After this operation completes
     * successfully, {@link #isChanged} returns <b>false</b>
     *
     * @param out Where to write the document.
     * @throws IOException if any I/O errors occur, in which case it will have
     *                     closed the stream; isChanged() is unchanged.
     */
    public void save(OutputStream out) throws IOException {

        // take current data from jtable view
        // we found this was the easiest way to save the current data
        // instead of maintaining an updated model
        String[][] dataWithHeader = new String[jtable.getRowCount()+1][];

        dataWithHeader[0] = new String[jtable.getColumnCount()];
        for (int col = 0; col < jtable.getColumnCount(); col++) {
            TableColumnModel tcm = jtable.getColumnModel();
            TableColumn tc = tcm.getColumn(col);
            String val = tc.getHeaderValue().toString();
            dataWithHeader[0][col] = val;
        }

        for (int row = 1; row <= jtable.getRowCount(); row++) {
            dataWithHeader[row] = new String[jtable.getColumnCount()];
            for (int col = 0; col < jtable.getColumnCount(); col++) {
                dataWithHeader[row][col] = jtable.getValueAt(row-1, col).toString();
            }
        }

        contents.save(out,dataWithHeader);
        setChanged(false);
    } // save


    /**
     * Reads the entire document, and closes the stream from which it is read.
     *
     * @param in Where to read the document from.
     * @throws IOException if any I/O errors occur, in which case it will have
     *                     closed the stream.
     */
    public void open(InputStream in) throws IOException {
        contents = new CSVContents();
        contents.open(in);
        in.close();

        // populate jtable
        jtable.setModel(contents);
        jtable.setAutoCreateRowSorter(true);
        jtable.setCellSelectionEnabled(true);

        setChanged(false);
    } // open

    /**
     * Gets the contents of the csv document, for those few methods within
     * this package that need direct access (such as actions).
     */
    CSVContents getContents() {
        return contents;
    }

    /**
     * Gets the reference to the jtable, for those few methods within
     * this package that need direct access (such as actions).
     */
    JTable getJTable() {
        return jtable;
    }

    /**
     * Moves row up (reorder)
     * Manipulates the table data using the model
     * These methods used by Actions
     */
    public void moveRowUp(int rowIndex){
        if(rowIndex>0){
            for(int col=0; col<contents.getColumnCount(); col++) {
                Object o1 = contents.getValueAt(rowIndex, col);
                Object o2 = contents.getValueAt(rowIndex-1, col);
                contents.setValueAt(o1, rowIndex-1, col);
                contents.setValueAt(o2, rowIndex, col);
            }
            contents.fireTableDataChanged();
        }
    }

    /**
     * Moves row down (reorder)
     * Manipulates the table data using the model
     * These methods used by Actions
     */
    public void moveRowDown(int rowIndex){
        if(rowIndex<contents.getRowCount()-1){
            for(int col=0; col<contents.getColumnCount(); col++) {
                Object o1 = contents.getValueAt(rowIndex, col);
                Object o2 = contents.getValueAt(rowIndex+1, col);
                contents.setValueAt(o1, rowIndex+1, col);
                contents.setValueAt(o2, rowIndex, col);
            }
            contents.fireTableDataChanged();
        }
    }


    public void setValue(String value, int row, int col){
        jtable.setValueAt(value, row, col);
//        contents.fireTableDataChanged();
    }


    public String getValue(int row, int col){
        return jtable.getValueAt(row, col).toString();
    }


} // end class CSVDocument

