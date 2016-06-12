package GraphGeneratorProject.MenuListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GraphGeneratorProject.Dialogs.DialogAbout;
import GraphGeneratorProject.Helpers.PositionOnScreen;

public class AboutListener implements ActionListener{

	private static DialogAbout dialogAbout;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		dialogAbout = new DialogAbout();
		dialogAbout.setSize(500, 280);
		dialogAbout.setResizable(false);
		
		PositionOnScreen pos = new PositionOnScreen();
		pos.centerScreen(dialogAbout);
		
		dialogAbout.setVisible(true);
	}

	public static DialogAbout getDialogAbout() {
		return dialogAbout;
	}

}
