package GraphGeneratorProject;

import java.util.Date;

import javax.swing.JFrame;

import GraphGeneratorProject.Helpers.PositionOnScreen;

public class GraphGeneratorExec{
	
	private static MainFrame frame = new MainFrame();
	
	private static void execute(){		
		PositionOnScreen pos = new PositionOnScreen();
		
		frame.setTitle(frame.getFRAME_TITLE());
		frame.setSize((int) MainFrame.getFRAME_WIDTH(), (int) MainFrame.getFRAME_HEIGHT());
		int x = pos.xPositionOnScreen(frame);
		int y = pos.yPositionOnScreen(frame);
		frame.setLocation(x, y);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		double startTime = new Date().getTime();
		execute();
		double endTime = new Date().getTime();
		double difference = (endTime - startTime) / 1000;
		System.err.println("Elapsed seconds: " + difference);
	}

	public static MainFrame getFrame() {
		return frame;
	}

}