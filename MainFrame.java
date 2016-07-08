package GraphGeneratorProject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import GraphGeneratorProject.Dialogs.DialogCreateGraph;
import GraphGeneratorProject.Helpers.Colors;
import GraphGeneratorProject.Helpers.Fonts;
import GraphGeneratorProject.Helpers.Integers;
import GraphGeneratorProject.Helpers.Strings;
import GraphGeneratorProject.MenuListeners.AboutListener;
import GraphGeneratorProject.MenuListeners.CloseAppWindowListener;
import GraphGeneratorProject.MenuListeners.EditGraphListeners;
import GraphGeneratorProject.MenuListeners.ExitListener;
import GraphGeneratorProject.MenuListeners.ExportGraphAsPDFListener;
import GraphGeneratorProject.MenuListeners.ExportGraphAsTXTListener;
import GraphGeneratorProject.MenuListeners.ImportGraphDataListener;
import GraphGeneratorProject.MenuListeners.NewGraphListener;
import GraphGeneratorProject.MenuListeners.PeeksValuesListener;

public class MainFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	
	private final static int
		FRAME_WIDTH = Integers.getFrameWidth(),
		FRAME_HEIGHT = Integers.getFrameHeight(),
		PADDING_SIZE = Integers.getPaddingSize();
	private final String FRAME_TITLE = Strings.getFrameTitle();
	private final static Font MENU_FONT = Fonts.getFontRegular12();
	
	private static JComponent panel;
	private JMenuBar menuBar;
	private JMenu graphMenu, viewMenu, aboutMenu;
	private static JMenu exportMenu;
	private JMenuItem createGraph, exitApp, about;
	private static JMenuItem editGraph, importGraphData, exportGraphAsPDF, exportGraphAsTXT;
	private static JCheckBoxMenuItem peeksValues;
	private DialogCreateGraph newGraphDialog;
	
	public MainFrame (){
		super();
		BorderLayout panelLayout = new BorderLayout();
		
		this.menuBar = new JMenuBar();
		panel = new JPanel();
		this.newGraphDialog = new DialogCreateGraph();
		
		this.setMenuBar(menuBar);
		UIManagerSetup();
		
		//New Graph Menu
		this.graphMenu = new JMenu(Strings.getMenuIgraph());
		this.createGraph = new JMenuItem(Strings.getMenuitemCreateIgraph());
		editGraph = new JMenuItem(Strings.getMenuitemEditIgraph());
		importGraphData = new JMenuItem(Strings.getMenuitemImportIgraph());
		exportMenu = new JMenu(Strings.getSubmenuExport());
			exportGraphAsPDF = new JMenuItem(Strings.getMenuitemExportAsPdf());
			exportGraphAsTXT = new JMenuItem(Strings.getMenuitemExportAsTxt());
		this.exitApp = new JMenuItem(Strings.getMenuitemExit());
		
		//View Menu
		this.viewMenu = new JMenu(Strings.getMenuView());
		peeksValues = new JCheckBoxMenuItem(Strings.getMenuitemPeeksValue(), true);
		
		//About Menu
		this.aboutMenu = new JMenu(Strings.getMenuAbout());
		about = new JMenuItem(Strings.getMenuitemAbout());
		
		//Add Menu Items
		addMenuComponent(menuBar, graphMenu, MENU_FONT);
		addMenuComponent(graphMenu, createGraph, MENU_FONT);
		addMenuComponent(graphMenu, editGraph, MENU_FONT);
		addMenuComponent(graphMenu, importGraphData, MENU_FONT);
		addMenuComponent(graphMenu, exportMenu, MENU_FONT);
			addMenuComponent(exportMenu, exportGraphAsPDF, MENU_FONT);
			addMenuComponent(exportMenu, exportGraphAsTXT, MENU_FONT);
		addMenuComponent(graphMenu, exitApp, MENU_FONT);
		
		addMenuComponent(menuBar, viewMenu, MENU_FONT);
		addMenuComponent(viewMenu, peeksValues, MENU_FONT);
		
		addMenuComponent(menuBar, aboutMenu, MENU_FONT);
		addMenuComponent(aboutMenu, about, MENU_FONT);
		
		//Listeners
		NewGraphListener ngl = new NewGraphListener();
		EditGraphListeners egls = new EditGraphListeners();
		EditGraphListeners.EditGraphListener egl = egls.new EditGraphListener();
		ImportGraphDataListener igdl = new ImportGraphDataListener();
		ExportGraphAsPDFListener egpdfl = new ExportGraphAsPDFListener();
		ExportGraphAsTXTListener egtxtl = new ExportGraphAsTXTListener();
		ExitListener el = new ExitListener();
		PeeksValuesListener pvl = new PeeksValuesListener();
		AboutListener al = new AboutListener();
		CloseAppWindowListener cawl = new CloseAppWindowListener();
		
		createGraph.addActionListener(ngl);
		editGraph.addActionListener(egl);
		importGraphData.addActionListener(igdl);
		exportGraphAsPDF.addActionListener(egpdfl);
		exportGraphAsTXT.addActionListener(egtxtl);
		exitApp.addActionListener(el);
		peeksValues.addActionListener(pvl);;
		about.addActionListener(al);
		this.addWindowListener(cawl);
		
		//Add Short keys
		this.shortKeys();
		
		//Change App Icon and add background image
		this.setIconImage(createImageIcon("Resources/Imgs/GG_icon.png").getImage());
		JLabel picLabel = new JLabel(createImageIcon("Resources/Imgs/GG_logo.jpg"));
		
		editGraph.setEnabled(false);
		exportMenu.setEnabled(false);
		
		MainPanel.setGraphIsExported(true);
		panel.setLayout(panelLayout);
		panel.add(picLabel, BorderLayout.CENTER);
		this.add(panel);
		panel.setVisible(true);
		panel.setBackground(Colors.getWhiteColor());
	}

	//UI Manager method
	private void UIManagerSetup(){
		UIManager.put("MenuBar.borderColor", Colors.getWhiteColor());
		UIManager.put("Menu.selectionBackground", Colors.getDarkGreyColor());
		UIManager.put("PopupMenu.border", BorderFactory.createLineBorder(Colors.getDarkGreyColor(), 1));
		UIManager.put("PopupMenu.background", Colors.getWhiteColor());
		UIManager.put("MenuItem.selectionBackground", Colors.getLightGreyColor());
		UIManager.put("MenuItem.acceleratorForeground", Colors.getBlackColor());
		UIManager.put("CheckBoxMenuItem.selectionBackground", Colors.getLightGreyColor());
	}
	
	//Short keys method
	private void shortKeys() {
		createGraph.setMnemonic(KeyEvent.VK_C);
		createGraph.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		
		editGraph.setMnemonic(KeyEvent.VK_E);
		editGraph.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		
		importGraphData.setMnemonic(KeyEvent.VK_I);
		importGraphData.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		
		exportGraphAsPDF.setMnemonic(KeyEvent.VK_D);
		exportGraphAsPDF.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		
		exportGraphAsTXT.setMnemonic(KeyEvent.VK_T);
		exportGraphAsTXT.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
		
		exitApp.setMnemonic(KeyEvent.VK_X);
		exitApp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		
		peeksValues.setMnemonic(KeyEvent.VK_V);
		peeksValues.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.ALT_MASK));
	}
	
	// Menu bar and items methods
	private void setMenuBar(JMenuBar menuBar){
		menuBar.setBackground(Colors.getWhiteColor());
		this.setJMenuBar(menuBar);
	}
	
	private void addMenuComponent(JComponent menu, JComponent menuItem, Font MENU_FONT){
		menuItem.setBackground(Colors.getWhiteColor());
		menuItem.setFont(MENU_FONT);
		if (menu instanceof JMenuBar && menuItem instanceof JMenu){
			menuItem.setBorder(new EmptyBorder(PADDING_SIZE, PADDING_SIZE, PADDING_SIZE, PADDING_SIZE));
			menu.add(menuItem);
		}
		if (menu instanceof JMenu && menuItem instanceof JMenuItem){
			menuItem.setBorder(new EmptyBorder(0, PADDING_SIZE, 0, PADDING_SIZE));
			menuItem.setPreferredSize(new Dimension(20 * PADDING_SIZE, 4 * PADDING_SIZE));
			menu.add(menuItem);
		}
	}
	
	//Image files directory method
	public ImageIcon createImageIcon(String path) {
        URL imgURL = this.getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
	
	//Getters and Setters
	public String getFRAME_TITLE() {
		return FRAME_TITLE;
	}

	public static int getFRAME_WIDTH() {
		return FRAME_WIDTH;
	}

	public static int getFRAME_HEIGHT() {
		return FRAME_HEIGHT;
	}

	public static JComponent getPanel() {
		return panel;
	}

	public static void setPanel(JComponent panel) {
		MainFrame.panel = panel;
	}

	public DialogCreateGraph getNewGraphDialog() {
		return newGraphDialog;
	}

	public void setNewGraphDialog(DialogCreateGraph newGraphDialog) {
		this.newGraphDialog = newGraphDialog;
	}

	public static JMenuItem getEditGraph() {
		return editGraph;
	}
	
	public static JMenuItem getImportGraphData() {
		return importGraphData;
	}

	public static JMenu getExportMenu() {
		return exportMenu;
	}

	public static JCheckBoxMenuItem getPeeksValues() {
		return peeksValues;
	}

	public static void setPeeksValues(JCheckBoxMenuItem values) {
		MainFrame.peeksValues = values;
	}

}