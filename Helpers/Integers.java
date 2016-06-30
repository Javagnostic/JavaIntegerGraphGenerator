package GraphGeneratorProject.Helpers;

public class Integers {
	
	private final static double SCREEN_PERCENTAGE = 1;
	private final static int
		FRAME_WIDTH = (int) (PositionOnScreen.getScreensize().getWidth() * SCREEN_PERCENTAGE),
		FRAME_HEIGHT = (int) (PositionOnScreen.getScreensize().getHeight() * SCREEN_PERCENTAGE),
		PADDING_SIZE = 10,
		
		CIRCLE_D = 10,
		
		CELLS_DISTANCE = 5,
		DIALOG_PADDING_SIZE = 20,
		VALUES_HEIGHT = CELLS_DISTANCE + DIALOG_PADDING_SIZE,
		VALUES_NUMBER = 5,
		MIN_DIV = 1,
		MAX_DIV = 10,
		PEEKS_NUMBER = 100,
		VALUES_ROW_ELEMENTS = 3;
	
	public static double getScreenPercentage() {
		return SCREEN_PERCENTAGE;
	}
	
	public static int getFrameWidth() {
		return FRAME_WIDTH;
	}
	
	public static int getFrameHeight() {
		return FRAME_HEIGHT;
	}
	
	public static int getPaddingSize() {
		return PADDING_SIZE;
	}

	public static int getCircleD() {
		return CIRCLE_D;
	}

	public static int getCellsDistance() {
		return CELLS_DISTANCE;
	}

	public static int getDialogPaddingSize() {
		return DIALOG_PADDING_SIZE;
	}

	public static int getValuesHeight() {
		return VALUES_HEIGHT;
	}

	public static int getValuesNumber() {
		return VALUES_NUMBER;
	}

	public static int getMinDiv() {
		return MIN_DIV;
	}

	public static int getMaxDiv() {
		return MAX_DIV;
	}

	public static int getPeeksNumber() {
		return PEEKS_NUMBER;
	}

	public static int getValuesRowElements() {
		return VALUES_ROW_ELEMENTS;
	}

}