package GraphGeneratorProject.MenuListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GraphGeneratorProject.MainFrame;

public class PeeksValuesListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame.getPanel().update(MainFrame.getPanel().getGraphics());
	}

}