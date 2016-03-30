package editor;
// $Id: UpCaseAction.java,v 1.0 2012/10/04 13:57:18 dalamb Exp $
// For documentation purposes, import only edfmwk classes actually used.

/**
 * {@link javax.swing.Action} for implementing "Move Row Up" functionality.
 *
 * Original code provided by Copyright 2010 David Alex Lamb.
 * Modified by Enoch Tam, Vic Setlur, Eric Balboa
 */
public class UpCaseAction extends CSVAction {
    /**
     * Constructs an uppercase action -- convert text to upper case.
     */
    public UpCaseAction() {
		super("Upper Case");
    } // end constructor UpCaseAction

    /**
     * Convert the text in current cell to upper case.
	 * @param doc CSV to change.
	 * @param row Currently selected table row
	 * @param col Currently selected table column
	 */
	protected void changeCSV(CSVDocument doc, int row, int col) {

		String oldText = doc.getValue(row,col);
		doc.setValue(oldText.toUpperCase(),row,col);

	} // end changeText
} // end class UpCaseAction
