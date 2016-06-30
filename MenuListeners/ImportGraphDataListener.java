package GraphGeneratorProject.MenuListeners;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import GraphGeneratorProject.GraphGeneratorExec;
import GraphGeneratorProject.MainPanel;
import GraphGeneratorProject.Dialogs.DialogCreateGraph;
import GraphGeneratorProject.Helpers.LookAndFeelManager;
import GraphGeneratorProject.Helpers.Strings;

public class ImportGraphDataListener implements ActionListener {
	
	private static DialogCreateGraph newGraphDialog;

	private static final String
		TITLE = Strings.getImportGraphTitle(),
		FILE_TYPE = Strings.getFileTypeTxt();
	private JFileChooser chooseFile;
	private Preferences prefs;
	private JTextArea textArea;
	private static String chartType, graphTitle, axisXTitle, axisYTitle, axisXMin, axisXMax, axisYMin, axisYMax;
	private static int peeksNumber, chartColor, smallestX, biggestX, smallestY, biggestY, axisXDiv, axisYDiv;
	private static LinkedList<Integer> valuesList;
	private static boolean isActive, isImported;
	
	public ImportGraphDataListener() {
		this.prefs = Preferences.userRoot().node(getClass().getName());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.textArea = new JTextArea();
		valuesList = new LinkedList<>();
		
		LookAndFeelManager.systemLookAndFeel(true);
		this.chooseFile = new JFileChooser(prefs.get("DEFAULT_PATH", new File(".").getAbsolutePath()));
		this.chooseFile.setAcceptAllFileFilterUsed(false);
		this.chooseFile.setFileFilter(new FileNameExtensionFilter(FILE_TYPE, FILE_TYPE.substring(1)));
		importTXT();
	}
	
	private void importTXT() {
		
		this.chooseFile.setDialogTitle(TITLE);
		this.chooseFile.setSelectedFile(new File(""));
		
		if (!MainPanel.isGraphIsExported()) {
			int warningDialog = JOptionPane.showConfirmDialog(
					GraphGeneratorExec.getFrame(),
					Strings.getNotExportedGraphMessage(),
					Strings.getNotExportedGraphTitle(), JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE);
			if (warningDialog == JOptionPane.YES_OPTION) {
				checkIfGraphIsExported();
			}
			else {
				LookAndFeelManager.systemLookAndFeel(false);
				return;
			}
		}
		else {
			checkIfGraphIsExported();
		}
	}
	
	private void checkIfGraphIsExported(){
		int actionDialog = chooseFile.showSaveDialog(GraphGeneratorExec.getFrame());
		if (actionDialog == JFileChooser.APPROVE_OPTION) {
			File file = chooseFile.getSelectedFile();
			prefs.put("DEFAULT_PATH", file.getParent());
			try {
				readFromFile(file);
			}
			catch (Exception e){
				JOptionPane.showMessageDialog(null, Strings.getExceptionMessage(), Strings.getExceptionMessageTitle(), JOptionPane.ERROR_MESSAGE);
//				e.printStackTrace();
				return;
			}
			createGraph();
		}
		else {
			LookAndFeelManager.systemLookAndFeel(false);
			return;
		}
	}
	
	private void readFromFile(File file) {
		FileReader reader = null;
		BufferedReader br = null;
		try {
			reader = new FileReader(file);
			br = new BufferedReader(reader);
			textArea.read(br, null);
			
			String[] lines = textArea.getText().split("\n");
			chartType = lines[0];
			graphTitle = lines[1];
			axisXTitle = lines[2];
			axisYTitle = lines[3];
			peeksNumber = Integer.parseInt(lines[4]);
			String values = lines[5].substring(1, lines[5].length() - 1);
			for (String s : values.split(", ")){
				valuesList.add(Integer.parseInt(s));
			}
			chartColor = Integer.parseInt(lines[6]);
			smallestX = Integer.parseInt(lines[7]);
			biggestX = Integer.parseInt(lines[8]);
			smallestY = Integer.parseInt(lines[9]);
			biggestY = Integer.parseInt(lines[10]);
			axisXMin = lines[11];
			axisXMax = lines[12];
			axisXDiv = Integer.parseInt(lines[13]);
			axisYMin = lines[14];
			axisYMax = lines[15];
			axisYDiv = Integer.parseInt(lines[16]);
		}
		catch (IOException ioe){
			LookAndFeelManager.systemLookAndFeel(true);
			JOptionPane.showMessageDialog(null, ioe.getMessage());
			LookAndFeelManager.systemLookAndFeel(false);
			ioe.printStackTrace();
			return;
		}
		finally {
			try {
				br.close();
				reader.close();
			} catch (IOException ioe) {
				JOptionPane.showMessageDialog(null, ioe.getMessage());
				ioe.printStackTrace();
			}
			
		}
	}
	
	private void createGraph() {
		LookAndFeelManager.systemLookAndFeel(false);
		isActive = true;
		NewGraphListener.createGraph();	
	}
	
	public static void addValues(){
		newGraphDialog = NewGraphListener.getNewGraphDialog();
		
		newGraphDialog.getChartType().setSelectedItem(chartType);
		newGraphDialog.getAttrTitle().setText(graphTitle);
		newGraphDialog.getAttrX().setText(axisXTitle);
		newGraphDialog.getAttrY().setText(axisYTitle);
		newGraphDialog.getAttrNodes().setValue(peeksNumber);
		
		LinkedList<JTextField> valuesFiledsList = new LinkedList<>();
		for (Component c : newGraphDialog.getValuesField().getComponents()){
			if (c instanceof JTextField){
				valuesFiledsList.add((JTextField) c);
			}
		}
		
		for (int i = 0; i < valuesFiledsList.size(); i++){
			valuesFiledsList.get(i).setText(valuesList.get(i)+"");
		}
		
		newGraphDialog.getColorButton().setBackground(new Color(chartColor));
		
		newGraphDialog.setSmallestPeekX(smallestX);
		newGraphDialog.setBiggestPeekX(biggestX);
		newGraphDialog.setSmallestPeekY(smallestY);
		newGraphDialog.setBiggestPeekY(biggestY);
		
		newGraphDialog.getAxisXMinValue().setText(axisXMin);
		newGraphDialog.getAxisXMaxValue().setText(axisXMax);
		newGraphDialog.getAxisXDiv().setValue(axisXDiv);
		
		newGraphDialog.getAxisYMinValue().setText(axisYMin);
		newGraphDialog.getAxisYMaxValue().setText(axisYMax);
		newGraphDialog.getAxisYDiv().setValue(axisYDiv);
	}

	public static DialogCreateGraph getNewGraphDialog() {
		return newGraphDialog;
	}

	public static void setNewGraphDialog(DialogCreateGraph newGraphDialog) {
		ImportGraphDataListener.newGraphDialog = newGraphDialog;
	}

	public static boolean isActive() {
		return isActive;
	}

	public static void setActive(boolean isActive) {
		ImportGraphDataListener.isActive = isActive;
	}

	public static boolean isImported() {
		return isImported;
	}

	public static void setImported(boolean isImported) {
		ImportGraphDataListener.isImported = isImported;
	}

}