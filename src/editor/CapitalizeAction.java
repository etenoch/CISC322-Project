package editor;

import java.util.regex.*;

/**
 * {@link javax.swing.Action} for implementing "Capitalize" functionality.
 *
 * Original code provided by Copyright 2010 David Alex Lamb.
 * Modified by Enoch Tam, Vic Setlur, Eric Balboa
 */
public class CapitalizeAction extends CSVAction {
    private static final String wordPatternText =
	// "\\{L}+";
	"\\w+";
    protected static Pattern wordPattern;

    /**
     * Constructs a text capitalization action
     */
    public CapitalizeAction() {
	super("Capitalize");
    } // end constructor CapitalizeAction

    /**
     * Capitalize the text in a given range of the document -- that is,
     * capitalize each "word" (string of letters or digits). Does nothing if
     * the start and end indices are equal.
     * @param doc CSV to change.
     * @param row Currently selected table row
     * @param col Currently selected table column
     */
    protected void changeCSV(CSVDocument doc, int row, int col) {
	if (wordPattern==null)
	    wordPattern = Pattern.compile(wordPatternText);
	try {

		String oldText = doc.getValue(row, col);
		StringBuilder newText = new StringBuilder(oldText.length());
		//System.err.println("old '"+oldText+"'");
		Matcher matcher = wordPattern.matcher(oldText);
		int lastEnd = 0;
		String tmp = "";
		while(matcher.find()) {
		    int oldStart = matcher.start();
		    int oldEnd = matcher.end();
		    tmp = oldText.substring(lastEnd,oldStart);
		    //System.err.print(oldStart+":"+oldEnd+" gap '"+tmp+"'");
		    newText.append(tmp);
		    tmp = oldText.substring(oldStart,oldStart+1).toUpperCase();
		    //System.err.print("cap '"+tmp+"'");
		    newText.append(tmp);
		    tmp = oldText.substring(oldStart+1,oldEnd).toLowerCase();
		    //System.err.println(" lower '"+tmp+"'");
		    newText.append(tmp);
		    lastEnd = oldEnd;
		} // while
		tmp = oldText.substring(lastEnd);

		newText.append(tmp);
        doc.setValue(newText.toString(),row,col);

	} catch(Exception e) {
	    e.printStackTrace();
	}
    } // end changeCSV
} // end class CapitalizeAction
