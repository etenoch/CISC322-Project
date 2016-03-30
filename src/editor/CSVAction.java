package editor;
// $Id: TextAction.java,v 1.1 2012/10/24 17:06:40 dalamb Exp $
import java.awt.event.ActionEvent;
// import javax.swing.Action;
import javax.swing.*;
// import javax.swing.text.Keymap;
// import javax.swing.text.DefaultEditorKit;
// import javax.swing.KeyStroke;
import ca.queensu.cs.dal.edfmwk.Application;
import ca.queensu.cs.dal.edfmwk.act.DefaultAction;
import ca.queensu.cs.dal.edfmwk.win.CommonWindow;
import ca.queensu.cs.dal.flex.log.Log;
/**
 * Parent for {@link javax.swing.Action Actions} for implementing changes to
 * the current text selection. Subclasses need only implement the
 * {@link #changeCSV} method.
 *
 * Original code provided by David Alex Lamb 2010.
 * Modified by Enoch Tam, Vic Setlur, Eric Balboa
 */
public abstract class CSVAction extends DefaultAction {
    /**
     * Constructs a CSV manipulation action
     */
    private CSVAction() {
        super("CSV");
    } // end constructor CSVAction

    /**
     * Constructs a text manipulation action
     * @param name Name of the action.
     */
    protected CSVAction(String name) {
        super(name);
    } // end constructor CSVAction

    /**
     * Perform some appropriate change on a selected region of text;
     *    subclasses must implement this method. If
     * <code>start</code> and <code>end</code> are equal, the operation might
     * do nothing (as in capitalization) or might affect the character before
     * the start or after the end (as in using a delete or backspace key).
     * @param doc CSV to change.
     * @param row Currently selected table row
     * @param col Currently selected table column
     */
    protected abstract void changeCSV(CSVDocument doc, int row, int col);

    /**
     * Perform the appropriate action (defined by {@link #changeCSV}) on the
     *    currently-selected cell (row, col) of the document.
     */
    public void actionPerformed(ActionEvent ae) {
        try {
            Application app = Application.getApplication();
            CommonWindow win = app.getActiveWindow();
            JTable area = (JTable) ((JScrollPane) win.getContentPane()).getViewport().getView();

            CSVDocument doc = (CSVDocument) app.getActiveDocument();
            CSVContents con = (CSVContents) doc.getContents();

            int selRow = area.getSelectedRowCount();
            int selCol = area.getSelectedColumnCount();

            if (selRow > 0 && selCol > 0){
                int selRows[] = area.getSelectedRows();
                int selCols[] = area.getSelectedColumns();

                for (int row : selRows){
                    for (int col : selCols){
                        changeCSV(doc,row,col);
                    }
                }
            }else{
                JOptionPane optionPane = new JOptionPane("Please select a cell first",JOptionPane.WARNING_MESSAGE);
                JDialog dialog = optionPane.createDialog("Warning!");
                dialog.setAlwaysOnTop(true);
                dialog.setVisible(true);
            }


        } catch (Exception ex) {
            Log.error("Text action error: "+ex.getLocalizedMessage());
        }
    }

    // debugging
    /*
    private static JTextArea firstArea = null;
    private static void setArea(JTextArea area) {
	CSVType.setActions(area);
	Keymap km = area.getKeymap();
	if (km==null) {System.out.println("No keymap"); return; }
	String actionName=DefaultEditorKit.pasteAction;
	Action ac = CSVType.getNamedAction(actionName);
	CSVType.debugAction(actionName, km, ac);
	debugStroke("ctrl pressed V",km);
	debugStroke("ctrl X",km);
	debugStroke("ctrl pressed C",km);
	firstArea = area;
    }
    private static void debugStroke(String stroke, Keymap km) {
	KeyStroke testChar = KeyStroke.getKeyStroke(stroke);
	if (testChar!=null) {
	    System.out.print("test='"+stroke+"' '"+testChar+"'");
	    while (km != null) {
		if (km.isLocallyDefined(testChar)) {
		    Action ac = km.getAction(testChar);
		    if (ac==null) System.out.print(" no action");
		    else System.out.print(" action "+ac);
		    break;
		} else {
		    System.out.println(" not in "+km);
		    km = km.getResolveParent();
		}
	    } // while 
	    System.out.println();
	} else System.out.println("No ctl-C keystroke");
    } // debugStroke
    */
} // end class CSVAction
