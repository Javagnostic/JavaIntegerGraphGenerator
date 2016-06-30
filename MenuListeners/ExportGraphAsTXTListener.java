package GraphGeneratorProject.MenuListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import GraphGeneratorProject.GraphGeneratorExec;
import GraphGeneratorProject.MainPanel;
import GraphGeneratorProject.Helpers.LookAndFeelManager;
import GraphGeneratorProject.Helpers.Strings;

public class ExportGraphAsTXTListener implements ActionListener {
	
	private static final String
		TITLE = Strings.getExportGraphTxtTitle(),
		FILE_TYPE = Strings.getFileTypeTxt(),
		NEW_LINE = Strings.getNewLine();
	private JFileChooser chooseFile;
	private Preferences prefs;
	private JTextArea textArea;
	
	public ExportGraphAsTXTListener() {
		this.prefs = Preferences.userRoot().node(getClass().getName());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.textArea = new JTextArea();
		getValuesToTextArea(textArea);
		
		LookAndFeelManager.systemLookAndFeel(true);
		this.chooseFile = new JFileChooser(prefs.get("DEFAULT_PATH", new File(".").getAbsolutePath()));
		this.chooseFile.setAcceptAllFileFilterUsed(false);
		this.chooseFile.setFileFilter(new FileNameExtensionFilter(FILE_TYPE, FILE_TYPE.substring(1)));
		exportTXT();
	}
	
	private void getValuesToTextArea(JTextArea textArea){
		NewGraphListener.getNewGraphDialog();
		textArea.setText(
			MainPanel.getChartType() + NEW_LINE +
			MainPanel.getTitleGraph() + NEW_LINE +
			MainPanel.getTitleX() + NEW_LINE +
			MainPanel.getTitleY() + NEW_LINE +
			MainPanel.getPeeksNumber() + NEW_LINE +
			MainPanel.getList() + NEW_LINE +
			MainPanel.getChartColor() + NEW_LINE +
			MainPanel.getSmallestX() + NEW_LINE +
			MainPanel.getBiggestX() + NEW_LINE +
			MainPanel.getSmallestY() + NEW_LINE +
			MainPanel.getBiggestY() + NEW_LINE +
			MainPanel.getAxisXStartsWith() + NEW_LINE +
			MainPanel.getAxisXEndsWith() + NEW_LINE +
			MainPanel.getAxisXDivision() + NEW_LINE +
			MainPanel.getAxisYStartsWith() + NEW_LINE +
			MainPanel.getAxisYEndsWith() + NEW_LINE +
			MainPanel.getAxisYDivision());
	}
	
	private void exportTXT() {
		chooseFile.setDialogTitle(TITLE);
		chooseFile.setSelectedFile(new File(""));
		int actionDialog = chooseFile.showSaveDialog(GraphGeneratorExec.getFrame());
		if (actionDialog == JFileChooser.APPROVE_OPTION) {
			checkIfFileExists(actionDialog);
		}
		else {
			LookAndFeelManager.systemLookAndFeel(false);
			return;
		}
	}
	
	private void checkIfFileExists(int actionDialog) {
		File file = chooseFile.getSelectedFile();
		String fileNameWithExtension = file.getAbsolutePath();
		if (!fileNameWithExtension.endsWith(FILE_TYPE)) {
			file = new File(fileNameWithExtension + FILE_TYPE);
		}
		prefs.put("DEFAULT_PATH", file.getParent());
		
		if (file.exists()) {
			actionDialog = JOptionPane.showConfirmDialog(chooseFile, "Replace existing file?", "Choose",
					JOptionPane.YES_NO_OPTION);
			if (actionDialog == JOptionPane.NO_OPTION) {
				exportTXT();
				return;
			}
			else {
				file.delete();
			}
		}
		LookAndFeelManager.systemLookAndFeel(false);
		
		FileWriter txtWriter = null;
		try {
			txtWriter = new FileWriter(file, false);
			textArea.write(txtWriter);
		}
		catch (IOException ioe){
			JOptionPane.showMessageDialog(null, ioe.getMessage());
			ioe.printStackTrace();
		}
		finally {
			try {
				txtWriter.close();
			} catch (IOException ioe) {
				JOptionPane.showMessageDialog(null, ioe.getMessage());
				ioe.printStackTrace();
			}
		}
		MainPanel.setGraphIsExported(true);
		LookAndFeelManager.systemLookAndFeel(true);
		JOptionPane.showMessageDialog(
				GraphGeneratorExec.getFrame(),
				Strings.getExportedGraphMessageTxt(),
				Strings.getExportedGraphTitle(),
				JOptionPane.INFORMATION_MESSAGE);
		LookAndFeelManager.systemLookAndFeel(false);
	}

}