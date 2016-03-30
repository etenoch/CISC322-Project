package editor;
import java.awt.*;
import java.io.Writer;
import javax.swing.*;

/**
 * Main panel for the editor's user inferface.
 *
 * Original code provided by David Alex Lamb 2010.
 * Modified by Enoch Tam, Vic Setlur, Eric Balboa
 */
public class MainPanel extends JPanel {

    /**
     * A writer whose output goes to the main panel.
     */
    private Writer log;

    /**
     * Gets a writer that sends information to the main panel. Thus
     * the initial main panel also serves as a log for messages.
     */
    public Writer getLog() { return log; }

    /**
     * Constructs the main panel.
     */
    public MainPanel() {
        super();

        // show "open csv" message
        JLabel openCSVMessage =  new JLabel("Open a CSV File");
        openCSVMessage.setVerticalTextPosition(JLabel.CENTER);
        openCSVMessage.setHorizontalTextPosition(JLabel.CENTER);

        setPreferredSize(new Dimension(500,500));
        add(openCSVMessage, BorderLayout.CENTER);

    } // end constructor MainPanel

} // end class MainPanel
