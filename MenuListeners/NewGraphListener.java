package GraphGeneratorProject.MenuListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GraphGeneratorProject.Dialogs.DialogCreateGraph;
import GraphGeneratorProject.MainFrame;
import GraphGeneratorProject.Helpers.PositionOnScreen;

public class NewGraphListener implements ActionListener{
	
	private static DialogCreateGraph newGraphDialog;
	private static boolean turnedON = false;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		newGraphDialog = new DialogCreateGraph();

		newGraphDialog.setSize((int)MainFrame.getFRAME_WIDTH() / 2, newGraphDialog.getHeight());
		newGraphDialog.setMinimumSize(newGraphDialog.getSize());
		
		PositionOnScreen pos = new PositionOnScreen();
		pos.centerScreen(newGraphDialog);
		
		newGraphDialog.setVisible(true);
		if (newGraphDialog.hasValues()){
			turnedON = true;
		}
	}

	public static boolean isTurnedON() {
		return turnedON;
	}

	public static DialogCreateGraph getNewGraphDialog() {
		return newGraphDialog;
	}

	public static void setNewGraphDialog(DialogCreateGraph newGraphDialog) {
		NewGraphListener.newGraphDialog = newGraphDialog;
	}

}