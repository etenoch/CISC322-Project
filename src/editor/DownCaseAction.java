package editor;
// $Id: DownCaseAction.java,v 1.0 2012/10/04 13:57:18 dalamb Exp $
// For documentation purposes, import only edfmwk classes actually used.

/**
 * {@link javax.swing.Action} for implementing "Lower Case" functionality.
 *<p>
 * Copyright 2010 David Alex Lamb.
 * See the <a href="../doc-files/copyright.html">copyright notice</a> for details.
 */
public class DownCaseAction extends CSVAction {
    /**
     * Constructs an uppercase action -- convert text to upper case.
     */
    public DownCaseAction() {
	super("Lower Case");
    } // end constructor DownCaseAction

    /**
     * Convert the text in a given range of the document to lower case.
     * Does nothing if the start and end indices are equal.
     * @param con Text to change.
     * @param start Index of the first character to change (the one to be
     *  capitalized).
     * @param end Index one beyond the last character to change.
     */
    protected void changeCSV(CSVDocument doc, int row, int col) {

		String oldText = doc.getValue(row,col);
		doc.setValue(oldText.toLowerCase(),row,col);

    } // end changeText
} // end class DownCaseAction
