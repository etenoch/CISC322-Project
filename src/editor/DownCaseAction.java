package editor;

/**
 * {@link javax.swing.Action} for implementing "Move Row Up" functionality.
 *
 * Original code provided by Copyright 2010 David Alex Lamb.
 * Modified by Enoch Tam, Vic Setlur, Eric Balboa
 */
public class DownCaseAction extends CSVAction {
    /**
     * Constructs an lowercase action -- convert text to lower case.
     */
    public DownCaseAction() {
	    super("Lower Case");
    } // end constructor DownCaseAction

    /**
     * Convert text in cell to lower case.
     * @param doc CSV to change.
     * @param row Currently selected table row
     * @param col Currently selected table column
     */
    protected void changeCSV(CSVDocument doc, int row, int col) {

		String oldText = doc.getValue(row,col);
		doc.setValue(oldText.toLowerCase(),row,col);

    } // end changeCSV
} // end class DownCaseAction
