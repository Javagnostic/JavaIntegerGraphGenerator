package GraphGeneratorProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
import GraphGeneratorProject.Helpers.PositionOnScreen;
import GraphGeneratorProject.MenuListeners.AboutListener;
import GraphGeneratorProject.MenuListeners.EditGraphListeners;
import GraphGeneratorProject.MenuListeners.ExitListener;
import GraphGeneratorProject.MenuListeners.NewGraphListener;

public class MainFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	
	private final static double SCREEN_PERCENTAGE = 1;
	private final static int FRAME_WIDTH = (int) (PositionOnScreen.getScreensize().getWidth() * SCREEN_PERCENTAGE),
						FRAME_HEIGHT = (int) (PositionOnScreen.getScreensize().getHeight() * SCREEN_PERCENTAGE);
	private final String FRAME_TITLE = "Graph Generator";
	private final static int PADDING_SIZE = 10;
	private final static Font menuFont = new Font("San-Serif", Font.PLAIN, 12);
	
	private JComponent panel;
	private JMenuBar menuBar;
	private JMenu newGraphMenu, aboutMenu;
	private JMenuItem createGraph;

	private static JMenuItem editGraph;

	private JMenuItem exitApp;

	private JMenuItem about;
	private DialogCreateGraph newGraphDialog;
	
	public MainFrame (){
		super();
		BorderLayout panelLayout = new BorderLayout();
		
		this.menuBar = new JMenuBar();
		this.panel = new JPanel();
		this.newGraphDialog = new DialogCreateGraph();
		
		this.setMenuBar(menuBar);
		UIManagerSetup();
		
		//New Graph Menu
		this.newGraphMenu = new JMenu("New");
		createGraph = new JMenuItem("Create Graph");
		editGraph = new JMenuItem("Edit Graph");
		exitApp = new JMenuItem("Exit");
		
		//About Menu
		this.aboutMenu = new JMenu("About");
		about = new JMenuItem("About Graph Generator");
		
		//Add Menu Items
		addMenu(menuBar, newGraphMenu, menuFont);
		addMenuItem(newGraphMenu, createGraph, menuFont);
		addMenuItem(newGraphMenu, editGraph, menuFont);
		addMenuItem(newGraphMenu, exitApp, menuFont);
		
		addMenu(menuBar, aboutMenu, menuFont);
		addMenuItem(aboutMenu, about, menuFont);
		
		//Listeners
		NewGraphListener ngl = new NewGraphListener();
		EditGraphListeners egls = new EditGraphListeners();
		EditGraphListeners.EditGraphListener egl = egls.new EditGraphListener();
		EditGraphListeners.EditGraphPropertyChangeListener egpcl = egls.new EditGraphPropertyChangeListener();
		ExitListener el = new ExitListener();
		AboutListener al = new AboutListener();
		
		createGraph.addActionListener(ngl);
		editGraph.addActionListener(egl);
		editGraph.addPropertyChangeListener(egpcl);
		exitApp.addActionListener(el);
		about.addActionListener(al);
		
		//Add Short keys
		this.shortKeys();
		
		//Change App Icon and add background image
		this.setIconImage(createImageIcon("Imgs/GG_icon.jpg").getImage());
		JLabel picLabel = new JLabel(createImageIcon("Imgs/GG_logo.jpg"));
		
		this.panel.setLayout(panelLayout);
		this.panel.add(picLabel, BorderLayout.CENTER);
		this.add(panel);
		this.panel.setVisible(true);
		this.panel.setBackground(Color.WHITE);
	}

	private void UIManagerSetup(){
		UIManager.put("Menu.selectionBackground", Color.decode("#d8dadb"));
		UIManager.put("MenuBar.borderColor", Color.WHITE);
		UIManager.put("MenuItem.selectionBackground", Color.decode("#ecedee"));
		UIManager.put("PopupMenu.border", BorderFactory.createLineBorder(Color.decode("#d8dadb"), 1));
		UIManager.put("MenuItem.acceleratorForeground", Color.BLACK);
	}
	
	private void shortKeys() {
		createGraph.setMnemonic(KeyEvent.VK_C);
		createGraph.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		
		editGraph.setMnemonic(KeyEvent.VK_E);
		editGraph.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		
		exitApp.setMnemonic(KeyEvent.VK_X);
		exitApp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
	}
	
	public ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = this.getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
	
	private void setMenuBar(JMenuBar menuBar){
		menuBar.setBackground(Color.WHITE);
		this.setJMenuBar(menuBar);
	}
	
	private void addMenu(JMenuBar menuBar, JMenu menu, Font menuFont){
		menu.setBackground(Color.WHITE);
		menu.setFont(menuFont);
		menu.setBorder(new EmptyBorder(PADDING_SIZE, PADDING_SIZE, PADDING_SIZE, PADDING_SIZE));
		menuBar.add(menu);
	}
	
	private void addMenuItem(JMenu menu, JMenuItem menuItem, Font menuFont){
		menuItem.setBackground(Color.WHITE);
		menuItem.setFont(menuFont);
		menuItem.setBorder(new EmptyBorder(0, PADDING_SIZE, 0, PADDING_SIZE));
		menuItem.setPreferredSize(new Dimension(20 * PADDING_SIZE, 4 * PADDING_SIZE));
		menu.add(menuItem);
	}
	
	public String getFRAME_TITLE() {
		return FRAME_TITLE;
	}

	public static int getFRAME_WIDTH() {
		return FRAME_WIDTH;
	}

	public static int getFRAME_HEIGHT() {
		return FRAME_HEIGHT;
	}

	public JComponent getPanel() {
		return panel;
	}

	public void setPanel(JComponent panel) {
		this.panel = panel;
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

}