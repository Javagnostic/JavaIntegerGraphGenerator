package GraphGeneratorProject.Helpers;

import GraphGeneratorProject.MainFrame;
import GraphGeneratorProject.Dialogs.DialogCreateGraph;
import GraphGeneratorProject.MenuListeners.NewGraphListener;

public class PositionRulers {
	
	private final DialogCreateGraph dcg = NewGraphListener.getNewGraphDialog();
	
	private final double FRAME_WIDTH = MainFrame.getFRAME_WIDTH(),
			FRAME_HEIGHT = MainFrame.getFRAME_HEIGHT(),
			FRAME_HEADER = 78.5,
			MARGINS = (FRAME_HEIGHT * 0.05),
			AXISES_MARGINS = (FRAME_HEIGHT * 0.1),
			GRAPH_START_X = 3 * MARGINS + AXISES_MARGINS,
			GRAPH_END_X = (FRAME_WIDTH - (MARGINS + AXISES_MARGINS)),
			GRAPH_START_Y = (FRAME_HEIGHT - FRAME_HEADER - MARGINS - AXISES_MARGINS),
			GRAPH_END_Y = (FRAME_HEIGHT - (FRAME_HEIGHT - (MARGINS + AXISES_MARGINS))),
			MIN_VALUE_X = Integer.parseInt(dcg.getAttrXMinValue().getText()),
			MAX_VALUE_X = Integer.parseInt(dcg.getAttrXMaxValue().getText()),
			MIN_VALUE_Y = Integer.parseInt(dcg.getAttrYMinValue().getText()),
			MAX_VALUE_Y = Integer.parseInt(dcg.getAttrYMaxValue().getText()),
			SCREEN_PERCENTAGE_X = ((GRAPH_END_X - GRAPH_START_X) / (MAX_VALUE_X - MIN_VALUE_X)),
			SCREEN_PERCENTAGE_Y = ((GRAPH_START_Y - GRAPH_END_Y) / (MAX_VALUE_Y - MIN_VALUE_Y));
	private final int MAX_NUMBER_SEPARATOR = 10;
	
	public int roundUpValue(int biggestPeekValue){
		if (0 <= biggestPeekValue && biggestPeekValue <= 20){
			while (biggestPeekValue % (MAX_NUMBER_SEPARATOR / 2) != 0){
				biggestPeekValue++;
			}
		}
		else if (20 < biggestPeekValue && biggestPeekValue <= 100){
			while (biggestPeekValue % MAX_NUMBER_SEPARATOR != 0){
				biggestPeekValue++;
			}
		}
		else {
			int unit = 1;
			for (int i = 2; i < (biggestPeekValue + "").length(); i++){
				unit *= MAX_NUMBER_SEPARATOR;
			}
			while (biggestPeekValue % (unit) != 0){
				biggestPeekValue++;
			}
		}
		return biggestPeekValue;
	}
	
	public int roundDownValue(int biggestPeekValue){
		if (0 <= biggestPeekValue && biggestPeekValue <= 20){
			while (biggestPeekValue % (MAX_NUMBER_SEPARATOR / 2) != 0){
				biggestPeekValue--;
			}
		}
		else if (20 < biggestPeekValue && biggestPeekValue <= 100){
			while (biggestPeekValue % MAX_NUMBER_SEPARATOR != 0){
				biggestPeekValue--;
			}
		}
		else {
			int unit = 1;
			for (int i = 2; i < (biggestPeekValue + "").length(); i++){
				unit *= MAX_NUMBER_SEPARATOR;
			}
			while (biggestPeekValue % (unit) != 0){
				biggestPeekValue--;
			}
		}
		return biggestPeekValue;
	}

	public int positionAxisX (int valueX){
		return (int) (GRAPH_START_X + valueX);
	}
	
	public int positionAxisY (int valueY){
		return (int) (GRAPH_START_Y - valueY);
	}
	
	public int positionX (int valueX, int minX){
		int number = valueX - minX;
		return (int) (GRAPH_START_X + number * SCREEN_PERCENTAGE_X);
	}
	
	public int positionY (int valueY, int minY){
		int number = valueY - minY;
		return (int) (GRAPH_START_Y - number * SCREEN_PERCENTAGE_Y);
	}
	
	public double getFRAME_WIDTH() {
		return FRAME_WIDTH;
	}

	public double getFRAME_HEIGHT() {
		return FRAME_HEIGHT;
	}

	public double getFRAME_HEADER() {
		return FRAME_HEADER;
	}

	public double getMARGINS() {
		return MARGINS;
	}

	public double getAXISES_MARGINS() {
		return AXISES_MARGINS;
	}

	public double getGRAPH_START_X() {
		return GRAPH_START_X;
	}

	public double getGRAPH_END_X() {
		return GRAPH_END_X;
	}

	public double getGRAPH_START_Y() {
		return GRAPH_START_Y;
	}

	public double getGRAPH_END_Y() {
		return GRAPH_END_Y;
	}

	public int getMAX_NUMBER_SEPARATOR() {
		return MAX_NUMBER_SEPARATOR;
	}
	
}