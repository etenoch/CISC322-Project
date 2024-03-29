package editor;
// $Id: TextEditor.java,v 1.2 2012/12/14 20:23:49 dalamb Exp dalamb $
// Import only those classes from edfmwk that are essential, for documentation purposes

import ca.queensu.cs.dal.edfmwk.Application;
import ca.queensu.cs.dal.edfmwk.act.AboutAction;
import ca.queensu.cs.dal.edfmwk.act.CreditAction;
import ca.queensu.cs.dal.edfmwk.act.HelpAction;
import ca.queensu.cs.dal.edfmwk.act.NewAction;
import ca.queensu.cs.dal.edfmwk.doc.DocumentType;
import ca.queensu.cs.dal.edfmwk.doc.DocumentManager;
import ca.queensu.cs.dal.edfmwk.menu.MenuDescriptor;
import ca.queensu.cs.dal.edfmwk.menu.MenuElement;
import ca.queensu.cs.dal.edfmwk.Menus;
import ca.queensu.cs.dal.flex.Register;

import javax.sound.midi.SysexMessage;

/**
 * A simple csv editor using the document framework.
 *
 * Original code provided by David Alex Lamb 2010.
 * Modified by Enoch Tam, Vic Setlur, Eric Balboa
 */
public class CSVEditor extends Application {
    private MenuDescriptor menu;
    /* package */ MainPanel mainPanel;
    private static String title = "Simple CSV File Editor";
    private static String copyright = "2010 David Alex Lamb & Students: Enoch Tam, Vic Setlur, Eric Balboa ";
    private static String aboutMsg = title + "\nCopyright " + copyright;
    private static String packageName = "ca.queensu.cs.dal.txt";
    private static String version = "0.1";
    private static Register register;
    private final String helpURI = "http://cs.queensu.ca/home/dalamb/java/txt/help.html";

    /**
     * Gets the menu descriptor for the main window, which contains only menu
     * items independent of the type of document to be displayed.
     */
    public MenuDescriptor getMainMenu() {
        if (menu == null) {
            menu = new MenuDescriptor(Menus.getStandardMenu());
            //System.err.println("Got standard menu.");
            try {
                //menu.addElement(new MenuElement("Edit")); // position empty menu
                //menu.addElement(new MenuElement("View")); // position empty menu
                menu.addPath(Menus.getLanguageMenu());
                //menu.addElement(new MenuElement("Tools")); // position empty menu
                menu.addElement(new MenuElement("Help/Help", new HelpAction(helpURI), "Help contents"));
                menu.addElement(new MenuElement("Help/About", new AboutAction(aboutMsg)));
                menu.addElement(new MenuElement("Help/Credits", new CreditAction()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return menu;
    } // end getMainMenu

    /*
     * Constructs and initializes the editor.
     */
    public CSVEditor() {
        super(title);
        mainPanel = new MainPanel();
        DocumentType fac = new CSVType();
        DocumentManager manager = getDocumentManager();
        //System.err.println("Got to return from getDocumentManager");
        if (manager != null) {
            manager.addExtension(fac);
        }
        // Localizers.Menu.getLocalizer().addBaseName(packageName+".Text");
        // Localizers.Message.getLocalizer().addBaseName(packageName+".Text");
        addLocalizerBaseName(packageName + ".Text");
        //System.err.println("About to get main menu");
        MenuDescriptor mainMenu = getMainMenu();
        //System.err.println("Returned from get main menu");
        MenuElement newAction = mainMenu.getElement("New");
        if (newAction != null)
            newAction.setAction(new NewAction(fac));
        register = new Register(packageName, title, copyright, version);
        Register.setPrimaryRegister(register);
        //System.err.println("About set up main menu");
        setup(mainPanel, mainMenu);
        finishSetup();
        //System.err.println("Got to end of Text Editor constructor");
    } // end constructor CSVEditor


    /**
     * Editor main program.  Constructs an instance of CSVEditor and
     * waits for user interface events.
     */
    public static void main(String args[]) {
        new CSVEditor();
    } // end main

    /**
     * Returns the main application, so that other classes can access some
     * global information.
     */
    public static CSVEditor getApplication() {
        return (CSVEditor) Application.getApplication();
    }

} // end class CSVEditor
