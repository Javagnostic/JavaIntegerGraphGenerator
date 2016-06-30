package GraphGeneratorProject.Helpers;

import java.awt.Font;

public class Fonts {
	
	private static final Font
		FONT_REGULAR_10 = new Font("ArialMT", Font.PLAIN, 10),
		FONT_REGULAR_12 = new Font("ArialMT", Font.PLAIN, 12),
		FONT_BOLD_14 = new Font("ArialMT-Bold", Font.BOLD, 14),
		FONT_BOLD_20 = new Font("ArialMT-Bold", Font.BOLD, 20),
		FONT_BOLD_30 = new Font("ArialMT-Bold", Font.BOLD, 30);
	
	public static Font getFontRegular10() {
		return FONT_REGULAR_10;
	}

	public static Font getFontRegular12() {
		return FONT_REGULAR_12;
	}

	public static Font getFontBold14() {
		return FONT_BOLD_14;
	}

	public static Font getFontBold20() {
		return FONT_BOLD_20;
	}

	public static Font getFontBold30() {
		return FONT_BOLD_30;
	}

}