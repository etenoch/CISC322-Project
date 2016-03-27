package editor;
// $Id: TextDocument.java,v 1.0 2012/10/04 13:57:18 dalamb Exp $

import ca.queensu.cs.dal.edfmwk.doc.AbstractDocument;
import ca.queensu.cs.dal.edfmwk.doc.DocumentException;
import ca.queensu.cs.dal.edfmwk.doc.DocumentType;

import javax.sound.midi.SysexMessage;
import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//import java.util.*;
// Import only those classes from edfmwk that are essential, for documentation purposes

/**
 * Implementation of a text document, which is (indirectly) defined in
 * terms of a Swing {@link javax.swing.Document}.
 * <p>
 * Copyright 2010 David Alex Lamb.
 * See the <a href="../doc-files/copyright.html">copyright notice</a> for details.
 */
public class CSVDocument
        extends AbstractDocument
        implements javax.swing.event.DocumentListener {
    private static int numRows = 20;
    private static int numColumns = 80;
    private CSVContents contents;

    /**
     * Constructs a document representation.
     *
     * @param type The type of the document.
     */
    public CSVDocument(DocumentType type) {
        super(type);
        contents = new CSVContents();
        contents.addDocumentListener(this);
//        JTextArea jta = new JTextArea(numRows, numColumns);
//        JTable jta = new JTable(contents.data,contents.header);
////        jta.setDocument(contents);
//        window = new JScrollPane(jta);
    } // end CSVDocument

    // Text document change listeners: all invoke the framework's own document
    // change listeners.

    /**
     * Gives notification that an attribute or set of attributes changed.
     */
    public void changedUpdate(javax.swing.event.DocumentEvent e) {
        setChanged();
    } // end changedUpdate

    /**
     * Gives notification that there was an insert into the document.
     */
    public void insertUpdate(javax.swing.event.DocumentEvent e) {
        setChanged();
    } // end insertUpdate

    /**
     * Gives notification that a portion of the document has been removed.
     */
    public void removeUpdate(javax.swing.event.DocumentEvent e) {
        setChanged();
    } // end removeUpdate

    /**
     * Saves the entire document.  After this operation completes
     * successfully, {@link #isChanged} returns <b>false</b>
     *
     * @param out Where to write the document.
     * @throws IOException if any I/O errors occur, in which case it will have
     *                     closed the stream; isChanged() is unchanged.
     */
    public void save(OutputStream out) throws IOException {
        contents.save(out);
        setChanged(false);
    } // save

    /**
     * Gets an input stream from which the document contents can be read as a
     * stream of bytes.  This is required when running in a sandbox, where
     * {@link javax.jnlp.FileSaveService#saveAsFileDialog} does not provide a
     * means of supplying an output stream to which to write the internal
     * representation. Document managers should avoid using this method
     * wherever possible, preferring {@link #save} instead.
     *
     * @throws IOException if such a stream cannot be created.
     */
    public InputStream getContentsStream() throws DocumentException {
        return contents.getContentsStream();
    } // getContentsStream

    /**
     * Reads the entire document, and closes the stream from which it is read.
     *
     * @param in Where to read the document from.
     * @throws IOException if any I/O errors occur, in which case it will have
     *                     closed the stream.
     */
    public void open(InputStream in) throws IOException {
        contents.open(in);

        CSVTableModel ctm = new CSVTableModel(contents.header,contents.data);
        JTable jta = new JTable(ctm);
        window = new JScrollPane(jta);
        setChanged(false);
    } // open

    /**
     * Gets the contents of the text document, for those few methods within
     * this package that need direct access (such as actions).
     */
    CSVContents getContents() {
        return contents;
    }
} // end class CSVDocument

