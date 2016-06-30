package GraphGeneratorProject.Helpers;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;

import GraphGeneratorProject.MainFrame;

public class PositionOnScreen{
	
	private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public int xPositionOnScreen(MainFrame frame){
		return (int) (screenSize.getWidth() - frame.getWidth()) / 2;
	}
	
	public int yPositionOnScreen(MainFrame frame){
		return (int) (screenSize.getHeight() - frame.getHeight()) / 2;
	}
	
	public int xPositionOnScreen(JDialog dialog){
		return (int) (screenSize.getWidth() - dialog.getWidth()) / 2;
	}
	
	public int yPositionOnScreen(JDialog dialog){
		return (int) (screenSize.getHeight() - dialog.getHeight()) / 2;
	}

	public void centerScreen(JDialog dialog){
		int x = xPositionOnScreen(dialog);
		int y = yPositionOnScreen(dialog);
		dialog.setLocation(x, y);
	}
	
	public static Dimension getScreensize() {
		return screenSize;
	}
	
	
}