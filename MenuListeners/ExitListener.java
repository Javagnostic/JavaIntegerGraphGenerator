package GraphGeneratorProject.MenuListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import GraphGeneratorProject.GraphGeneratorExec;
import GraphGeneratorProject.MainPanel;
import GraphGeneratorProject.Helpers.LookAndFeelManager;
import GraphGeneratorProject.Helpers.Strings;

public class ExitListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		closeApp();
	}
	
	public static void closeApp() {
		LookAndFeelManager.systemLookAndFeel(true);
		if (!MainPanel.isGraphIsExported()) {
			int warningDialog = JOptionPane.showConfirmDialog(
					GraphGeneratorExec.getFrame(),
					Strings.getNotExportedGraphMessage(),
					Strings.getNotExportedGraphTitle(), JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE);
			if (warningDialog == JOptionPane.YES_OPTION) {
				GraphGeneratorExec.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				System.exit(0);
			}
			else {
				GraphGeneratorExec.getFrame().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				return;
			}
		}
		else {
			GraphGeneratorExec.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			System.exit(0);
		}
		LookAndFeelManager.systemLookAndFeel(false);
	}

}