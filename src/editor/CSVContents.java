package editor;

import ca.queensu.cs.dal.edfmwk.doc.StringSequence;

// $Id: TextContents.java,v 1.0 2012/10/04 13:57:18 dalamb Exp $
import java.awt.*;
import java.io.*;
import java.util.List;
//import java.util.*;
import ca.queensu.cs.dal.edfmwk.doc.DocumentException;
import ca.queensu.cs.dal.edfmwk.doc.StringSequence;
import ca.queensu.cs.dal.edfmwk.doc.StringSequenceInputStream;
import com.opencsv.CSVReader;

import javax.swing.*;


public class CSVContents extends javax.swing.text.PlainDocument implements StringSequence {
    public String[] header;
    public String[][] data;

    public CSVContents() {
        super();

    } // end constructor


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

    } // end save

    public InputStream getContentsStream() //throws DocumentException
    {
        return null;
    } // end getContentStream


    public String safelyGetText(int start, int length) {
        return null;
    } // end safelyGetText

} // end CSVContents
