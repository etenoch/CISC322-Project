package editor;

import javax.swing.*;

/**
 * {@link javax.swing.Action} for implementing "Statistics" functionality.
 *
 * Original code provided by Copyright 2010 David Alex Lamb.
 * Modified by Enoch Tam, Vic Setlur, Eric Balboa
 */
public class StatisticsAction extends CSVAction {

    /**
     * StatisticsAction - shows a popup with statistics about the current column
     */
    public StatisticsAction() {
	    super("∑ Show Column Statistics");
    } // end constructor UpCaseAction

    /**
     * Goes through the data in the current column and computes simple stats
     * @param doc CSV to change.
     * @param row Currently selected table row
     * @param col Currently selected table column
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

	} // end changeCSV
} // end class StatisticsAction
