package GraphGeneratorProject.Helpers;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class ChartTypeDropDownMenu extends BasicComboBoxUI{

	private static final Color
			DARK_GREY_COLOR = Colors.getDarkGreyColor(),
			WHITE_COLOR = Colors.getWhiteColor();
	private static final Font
			REGULAR_12 = Fonts.getFontRegular12();

	public static ComponentUI createUI(JComponent c) {
		UIManager.put("ComboBox.selectionBackground", DARK_GREY_COLOR);
		c.setFocusable(false);
		c.setBackground(WHITE_COLOR);
		c.setFont(REGULAR_12);
		return new ChartTypeDropDownMenu();
	}

	protected JButton createArrowButton() {
		JButton button = new BasicArrowButton(BasicArrowButton.SOUTH);
		button.setBackground(WHITE_COLOR);
		button.setBorder(BorderFactory.createLineBorder(WHITE_COLOR, 1));
		return button;
	}
	
}
