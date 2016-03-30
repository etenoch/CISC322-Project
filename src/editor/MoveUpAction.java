package editor;

/**
 * {@link javax.swing.Action} for implementing "Move Row Up" functionality.
 *
 * Original code provided by Copyright 2010 David Alex Lamb.
 * Modified by Enoch Tam, Vic Setlur, Eric Balboa
 */
public class MoveUpAction extends CSVAction {
    /**
     * Constructs an move up action - Swaps current row up
     */
    public MoveUpAction() {
	super("▲ Move Row Up");
    } // end constructor DownCaseAction

    /**
     * Swaps current row up
     * @param doc CSV to change.
     * @param row Currently selected table row
     * @param col Currently selected table column
     */
    protected void changeCSV(CSVDocument doc, int row, int col) {
        doc.moveRowUp(row);
    } // end changeCSV
} // end class MoveUpActiopn
