package GraphGeneratorProject.Dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import GraphGeneratorProject.GraphGeneratorExec;
import GraphGeneratorProject.Helpers.Colors;
import GraphGeneratorProject.Helpers.ExtraListeners;
import GraphGeneratorProject.Helpers.Fonts;
import GraphGeneratorProject.Helpers.Strings;

public class DialogAbout extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1;

	private static final String
		DIALOG_TITLE = Strings.getAboutDialogTitle(),
		ABOUT_TITLE = Strings.getAboutTitle(),
		ABOUT_TEXT = Strings.getAboutText();
	private static final int MARGINS = 20;
	private static final Color
		DARK_GREY_COLOR = Colors.getDarkGreyColor(),
		LIGHT_GREY_COLOR = Colors.getLightGreyColor(),
		WHITE_COLOR = Colors.getWhiteColor();
	private static final Font
		FONT_TITLE = Fonts.getFontBold20();
	
	private JPanel panel, imagePanel, textPanel, textInnerPanel, buttonPanel;
	private JLabel aboutTitle;
	private JTextArea aboutText;
	private JButton okButton;
	private EmptyBorder eb;
	
	public DialogAbout() {
		super();
		
		this.setTitle(DIALOG_TITLE);
		this.setModal(true);
		this.eb = new EmptyBorder(MARGINS, MARGINS, MARGINS, MARGINS);
		
		//Panels
		this.panel = new JPanel();
		this.imagePanel = new JPanel();
		this.textPanel = new JPanel();
		this.textInnerPanel = new JPanel();
		this.buttonPanel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		panel.setBorder(eb);
		
		//Attributes
		this.aboutTitle = new JLabel(ABOUT_TITLE);
		this.aboutTitle.setFont(FONT_TITLE);
		this.aboutText = new JTextArea(ABOUT_TEXT);
		this.aboutText.setEditable(false);
		this.aboutText.setFocusable(false);
		this.okButton = new JButton("OK");
		
		//Image panel
		JLabel picLabel = new JLabel(GraphGeneratorExec.getFrame().createImageIcon("Resources/Imgs/GG_logo_About.jpg"));
		
		this.imagePanel.setLayout(new BorderLayout());
		this.imagePanel.add(picLabel, BorderLayout.CENTER);
		this.imagePanel.setBackground(WHITE_COLOR);
		
		//Text Info panel
		this.textInnerPanel.setLayout(new GridLayout(2, 1));
		this.textInnerPanel.add(aboutTitle);
		this.textInnerPanel.add(aboutText);
		this.textInnerPanel.setBackground(WHITE_COLOR);
		this.textInnerPanel.setBorder(eb);
		
		this.textPanel.setLayout(new BorderLayout());
		this.textPanel.add(textInnerPanel, BorderLayout.CENTER);
		this.textPanel.setBackground(WHITE_COLOR);
		
		//Button panel
		okButton.setPreferredSize(new Dimension(MARGINS * 5, MARGINS + 5));
		okButton.setBorder(BorderFactory.createLineBorder(DARK_GREY_COLOR, 1));
		okButton.setBackground(LIGHT_GREY_COLOR);
		okButton.setFocusPainted(false);
		this.buttonPanel.add(okButton);
		this.buttonPanel.setBackground(WHITE_COLOR);
		
		this.panel.add(imagePanel, BorderLayout.WEST);
		this.panel.add(textPanel, BorderLayout.EAST);
		this.panel.add(buttonPanel, BorderLayout.SOUTH);
		
		//Listeners
		ExtraListeners el = new ExtraListeners();
		ExtraListeners.CloseAboutListener cal = el.new CloseAboutListener();
		
		okButton.addActionListener(cal);
		okButton.registerKeyboardAction(cal, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		this.getRootPane().registerKeyboardAction(cal, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		this.panel.setBackground(WHITE_COLOR);
		this.add(panel);
		this.setSize(this.getWidth(), this.getHeight());
	}

}