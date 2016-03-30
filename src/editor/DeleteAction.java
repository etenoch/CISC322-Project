package editor;
// $Id: DeleteAction.java,v 1.0 2012/10/04 13:57:18 dalamb Exp $
// For documentation purposes, import only those classes from edfmwk that are
// actually used.

/**
 * {@link javax.swing.Action} for implementing "Delete" functionality.
 *<p>
 * Copyright 2010 David Alex Lamb.
 * See the <a href="../doc-files/copyright.html">copyright notice</a> for details.
 */
public class DeleteAction extends CSVAction {
    /**
     * Constructs a text deletion action -- delete selected text.
     */
    public DeleteAction() {
	super("Clear Cell");
    } // end constructor DeleteAction

    /**
     * Delete the selected region of text.
     * If <code>start</code> and <code>end</code> are equal, delete the
     * character after the caret (if any).
     * @param con Text document to change.
     * @param start Index of the first character to change.
     * @param end Index one beyond the last character to change.
     */
    protected void changeCSV(CSVDocument doc, int row, int col) {
		doc.setValue("",row,col);
    } // end changeText
} // end class DeleteAction
