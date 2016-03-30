package editor;

/**
 * {@link javax.swing.Action} for implementing "Clear Cell" functionality.
 *
 * Original code provided by Copyright 2010 David Alex Lamb.
 * Modified by Enoch Tam, Vic Setlur, Eric Balboa
 */
public class DeleteAction extends CSVAction {
    /**
     * Clears text from cell
     */
    public DeleteAction() {
	    super("Clear Cell");
    } // end constructor DeleteAction

    /**
     * clear text from selected cell
     * @param doc CSV to change.
     * @param row Currently selected table row
     * @param col Currently selected table column
     */
    protected void changeCSV(CSVDocument doc, int row, int col) {
		doc.setValue("",row,col);
    } // end changeCSV
} // end class DeleteAction
