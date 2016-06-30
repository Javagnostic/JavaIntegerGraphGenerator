package GraphGeneratorProject.MenuListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import GraphGeneratorProject.Dialogs.DialogCreateGraph;
import GraphGeneratorProject.GraphGeneratorExec;
import GraphGeneratorProject.MainFrame;
import GraphGeneratorProject.MainPanel;
import GraphGeneratorProject.Helpers.LookAndFeelManager;
import GraphGeneratorProject.Helpers.PositionOnScreen;
import GraphGeneratorProject.Helpers.Strings;

public class NewGraphListener implements ActionListener{
	
	private static DialogCreateGraph newGraphDialog;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ImportGraphDataListener.setImported(false);
		if (!MainPanel.isGraphIsExported()){
			LookAndFeelManager.systemLookAndFeel(true);
			int warningDialog = JOptionPane.showConfirmDialog(
					GraphGeneratorExec.getFrame(),
					Strings.getNotExportedGraphMessage(),
					Strings.getNotExportedGraphTitle(), JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE);
			if (warningDialog == JOptionPane.YES_OPTION){
				createGraph();
			}
			else {
				LookAndFeelManager.systemLookAndFeel(false);
				return;
			}
		}
		else {
			createGraph();
		}
	}
	
	public static void createGraph() {
		LookAndFeelManager.systemLookAndFeel(false);
		newGraphDialog = new DialogCreateGraph();
		if (ImportGraphDataListener.isActive()){
			ImportGraphDataListener.setImported(true);
			newGraphDialog.setTitle(Strings.getDialogImportGraphTitle());
			ImportGraphDataListener.addValues();
			ImportGraphDataListener.setActive(false);
		}
		newGraphDialog.setSize((int)MainFrame.getFRAME_WIDTH() / 2, newGraphDialog.getHeight());
		newGraphDialog.setMinimumSize(newGraphDialog.getSize());
		
		PositionOnScreen pos = new PositionOnScreen();
		pos.centerScreen(newGraphDialog);
		
		newGraphDialog.setVisible(true);
	}

	public static DialogCreateGraph getNewGraphDialog() {
		return newGraphDialog;
	}

	public static void setNewGraphDialog(DialogCreateGraph newGraphDialog) {
		NewGraphListener.newGraphDialog = newGraphDialog;
	}

}