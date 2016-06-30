package GraphGeneratorProject.MenuListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GraphGeneratorProject.GraphGeneratorExec;
import GraphGeneratorProject.Helpers.Strings;

public class EditGraphListeners {

	public class EditGraphListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			NewGraphListener.getNewGraphDialog().setTitle(Strings.getDialogEditGraphTitle());
			if (NewGraphListener.getNewGraphDialog().equals(GraphGeneratorExec.getFrame().getNewGraphDialog())) {
				ImportGraphDataListener.setImported(false);
			}
			NewGraphListener.setNewGraphDialog(GraphGeneratorExec.getFrame().getNewGraphDialog());
			GraphGeneratorExec.getFrame().getNewGraphDialog().setVisible(true);
		}

	}

}
