package GraphGeneratorProject.MenuListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import GraphGeneratorProject.GraphGeneratorExec;
import GraphGeneratorProject.MainFrame;

public class EditGraphListeners {

	public class EditGraphListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			NewGraphListener.getNewGraphDialog().setTitle("Edit Graph");
			NewGraphListener.setNewGraphDialog(GraphGeneratorExec.getFrame().getNewGraphDialog());
			GraphGeneratorExec.getFrame().getNewGraphDialog().setVisible(true);
		}

	}
	
	public class EditGraphPropertyChangeListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if (!NewGraphListener.isTurnedON()){
				MainFrame.getEditGraph().setEnabled(false);
			}
			else {
				MainFrame.getEditGraph().setEnabled(true);
			}
		}
		
	}

}
