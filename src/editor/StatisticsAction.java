package editor;
// $Id: UpCaseAction.java,v 1.0 2012/10/04 13:57:18 dalamb Exp $
// For documentation purposes, import only edfmwk classes actually used.

import javax.swing.*;

/**
 * {@link javax.swing.Action} for implementing "Upper Case" functionality.
 *<p>
 * Copyright 2010 David Alex Lamb.
 * See the <a href="../doc-files/copyright.html">copyright notice</a> for details.
 */
public class StatisticsAction extends CSVAction {
    /**
     * Constructs an uppercase action -- convert text to upper case.
     */
    public StatisticsAction() {
	super("∑ Show Column Statistics");
    } // end constructor UpCaseAction

    /**
     * Convert the text in a given range of the document to upper case.
     * Does nothing if the start and end indices are equal.
     * @param con Text to change.
     * @param start Index of the first character to change (the one to be
     *  capitalized).
     * @param end Index one beyond the last character to change.
     */
	protected void changeCSV(CSVDocument doc, int row, int col) {


        int sum=0, mean=0, nonBlank=0, max=0, min=0;
        boolean first = true;
        for (int i = 0; i<doc.getJTable().getRowCount(); i++){
            String str = doc.getValue(i,col);
            int value;
            try {
                value = Integer.valueOf(str);
                if(first) {
                    min = value;
                    first = false;
                }
                nonBlank +=1;
                sum += value;
                if (value > max) max = value;
                if (value < min) min = value;

            } catch (NumberFormatException e) {
            }
        }
        JOptionPane optionPane;
        if(nonBlank>0){
            mean = sum/nonBlank;
            optionPane = new JOptionPane("SUM:"+sum+"\nMEAN:"+mean+"\nNum Non-blank:"+nonBlank+"\nMAX:"+max+"\nMIN:"+min+"\n",JOptionPane.INFORMATION_MESSAGE);
        }else{
            optionPane = new JOptionPane("Not enough non-blank number data to compute statistics",JOptionPane.INFORMATION_MESSAGE);
        }

		JDialog dialog = optionPane.createDialog("Statistics About Column "+(col+1));
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);

	} // end changeText
} // end class UpCaseAction
