package editor;
// $Id: DownCaseAction.java,v 1.0 2012/10/04 13:57:18 dalamb Exp $
// For documentation purposes, import only edfmwk classes actually used.

/**
 * {@link javax.swing.Action} for implementing "Move Row Down" functionality.
 *
 * Original code provided by Copyright 2010 David Alex Lamb.
 * Modified by Enoch Tam, Vic Setlur, Eric Balboa
 */
public class MoveDownAction extends CSVAction {
    /**
     * Constructs an move down action - Swaps current row down
     */
    public MoveDownAction() {
	    super("▼ Move Row Down");
    } // end constructor DownCaseAction

    /**
     * Swaps current row down
     * @param doc CSV to change.
     * @param row Currently selected table row
     * @param col Currently selected table column
     */
    protected void changeCSV(CSVDocument doc, int row, int col) {
        doc.moveRowDown(row);
    } // end changeCSV
} // end class MoveUpActiopn
