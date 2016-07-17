package GraphGeneratorProject.Dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;

import GraphGeneratorProject.GraphGeneratorExec;
import GraphGeneratorProject.MainFrame;
import GraphGeneratorProject.MainPanel;
import GraphGeneratorProject.Helpers.Colors;
import GraphGeneratorProject.Helpers.ChartTypeDropDownMenu;
import GraphGeneratorProject.Helpers.ExtraListeners;
import GraphGeneratorProject.Helpers.ExtraListeners.AttrFocusListener;
import GraphGeneratorProject.Helpers.ExtraListeners.AttrMouseListener;
import GraphGeneratorProject.Helpers.Fonts;
import GraphGeneratorProject.Helpers.Integers;
import GraphGeneratorProject.Helpers.PositionRulers;
import GraphGeneratorProject.Helpers.Strings;
import GraphGeneratorProject.MenuListeners.ImportGraphDataListener;
import GraphGeneratorProject.MenuListeners.NewGraphListener;

public class DialogCreateGraph extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	private static final String
			DIALOG_TITLE = Strings.getDialogTitle(),
			ATTR_GRAPH_TITLE = Strings.getAttrGraphTitle(),
			ATTR_X_TEXT = Strings.getAttrXText(),
			ATTR_Y_TEXT = Strings.getAttrYText(),
			NUMBER_NODES_TEXT = Strings.getNumberNodesText(),
			CHART_TYPE = Strings.getChartType(),
			COLOR_CHOOSER_TITLE = Strings.getColorChooserTitle(),
			VALUE_X_TOOLTIP_TEXT = Strings.getValueXTooltipText(),
			VALUE_Y_TOOLTIP_TEXT = Strings.getValueYTooltipText(),
			AXIS_X_MIN_TITLE = Strings.getAxisXMinTitle(),
			AXIS_X_MAX_TITLE = Strings.getAxisXMaxTitle(),
			AXIS_X_DIV_TITLE = Strings.getAxisXDivTitle(),
			AXIS_Y_MIN_TITLE = Strings.getAxisYMinTitle(),
			AXIS_Y_MAX_TITLE = Strings.getAxisYMaxTitle(),
			AXIS_Y_DIV_TITLE = Strings.getAxisYDivTitle(),
			VALUE_FIELD_TEXT = Strings.getValueFieldText(),
			AXIS_STEP = Strings.getAxisStep(),
			CSS = Strings.getCss(),
			ERROR_MESSAGE_ATTR = Strings.getErrorMessageAttr(),
			ERROR_MESSAGE_VALUES = Strings.getErrorMessageValues(),
			ERROR_MESSAGE_EXTRA_ATTR = Strings.getErrorMessageExtraAttr(),
			ERROR_MESSAGE_X_MIN_ATTR = Strings.getErrorMessageXMinAttr(),
			ERROR_MESSAGE_X_MAX_ATTR = Strings.getErrorMessageXMaxAttr(),
			ERROR_MESSAGE_Y_MIN_ATTR = Strings.getErrorMessageYMinAttr(),
			ERROR_MESSAGE_Y_MAX_ATTR = Strings.getErrorMessageYMaxAttr(),
			ERROR_MESSAGE_X_MIN_ATTR_COL = Strings.getErrorMessageXMinAttrCol(),
			ERROR_MESSAGE_X_MAX_ATTR_COL = Strings.getErrorMessageXMaxAttrCol(),
			ERROR_MESSAGE_Y_MIN_ATTR_COL = Strings.getErrorMessageYMinAttrCol(),
			ERROR_MESSAGE_Y_MAX_ATTR_COL = Strings.getErrorMessageYMaxAttrCol(),
			ERROR_MESSAGE_DIV_X = Strings.getErrorMessageDivX(),
			ERROR_MESSAGE_DIV_Y = Strings.getErrorMessageDivY();
	private static final String[]
			CHART_STRINGS = {"Polyline", "Vertical Columns", "Horizontal Columns"};
	private static final Color
			DARK_GREY_COLOR = Colors.getDarkGreyColor(),
			LIGHT_GREY_COLOR = Colors.getLightGreyColor(),
			RED_COLOR = Colors.getRedColor(),
			WHITE_COLOR = Colors.getWhiteColor(),
			BLACK_COLOR = Colors.getBlackColor();
	private static final Font
			FONT_REGULAR_10 = Fonts.getFontRegular10();
	private static final int
			CELLS_DISTANCE = Integers.getCellsDistance(),
			PADDING_SIZE = Integers.getDialogPaddingSize(),
			VALUES_HEIGHT = Integers.getValuesHeight(),
			VALUES_NUMBER = Integers.getValuesNumber(),
			MIN_DIV = Integers.getMinDiv(),
			MAX_DIV = Integers.getMaxDiv(),
			PEEKS_NUMBER = Integers.getPeeksNumber(),
			VALUES_ROW_ELEMENTS = Integers.getValuesRowElements();
	private JPanel panelAttributes, panelValues, valuesField, buttonPanel, axisMinMaxValuesPanel, buttonField;
	private JLabel
			attrGraphTitle, attrXTitle, attrYTitle, attrNodesTitle, attrChartTitle, colorChooserTitle, axisXMinTitle, axisXMaxTitle, axisXDivTitle, axisYMinTitle,
			axisYMaxTitle, axisYDivTitle, errorMessage, nodeNumber, axisXStep, axisYStep;
	private JTextField
 			attrTitle, attrX, attrY, axisXMinValue, axisXMaxValue, axisYMinValue, axisYMaxValue, nodeValueX, nodeValueY;
	private JSpinner attrNodes, axisXDiv, axisYDiv;
	private JComboBox<String> chartType;
	private JColorChooser colorChooser;
	private int
			rows, dialogHeight, columns = 2,
			smallestPeekX, biggestPeekX, smallestPeekY, biggestPeekY;
	private GridLayout panelValuesGrid;
	private JScrollPane valuesScroll;
	private Dimension d;
	private JButton colorButton, okButton, roundDownMinXValue, roundUpMaxXValue, roundDownMinYValue, roundUpMaxYValue;
	private LinkedList<Integer> list;
	private LinkedList<int[]> valuesList, positionList;
	private static MainPanel mainPanel;

	public DialogCreateGraph() {
		super();
		
		// Common stuff
		this.setTitle(DIALOG_TITLE);
		this.setModal(true);
		this.list = new LinkedList<>();
		this.valuesList = new LinkedList<>();
		this.positionList = new LinkedList<>();

		BorderLayout mainLayout = new BorderLayout();
		this.setLayout(mainLayout);

		// Panels
		this.panelAttributes = new JPanel();
		this.panelValues = new JPanel();
		this.valuesField = new JPanel();
		this.buttonPanel = new JPanel();
		this.axisMinMaxValuesPanel = new JPanel();
		this.buttonField = new JPanel();

		this.d = new Dimension(getWidth(), PADDING_SIZE);
		GridLayout panelAttrGrid = new GridLayout(6, columns, CELLS_DISTANCE, CELLS_DISTANCE);
		EmptyBorder eb = new EmptyBorder(PADDING_SIZE, PADDING_SIZE, PADDING_SIZE, PADDING_SIZE);
		SpinnerModel sm = new SpinnerNumberModel(columns, columns, PEEKS_NUMBER, 1);
		
		//Listeners
		OkButtonListener okbl = new OkButtonListener();
		RoundButtonListener rbl = new RoundButtonListener();
		ExtraListeners el = new ExtraListeners();
		ExtraListeners.AttrFocusListener afl = el.new AttrFocusListener();
		ExtraListeners.AttrMouseListener aml = el.new AttrMouseListener();
		ExtraListeners.CloseDialogListener cdl = el.new CloseDialogListener();

		// Add Attributes Panel + JSpinner Listener
		this.panelAttributesLayout(panelAttrGrid, d, eb, sm, afl, aml);
		this.attrNodes.addChangeListener(new SpinnerNumber());

		// Add Values Panel
		BoxLayout valuesFieldBox = new BoxLayout(panelValues, BoxLayout.X_AXIS);
		this.rows = (int) attrNodes.getValue();
		this.panelValuesGrid = new GridLayout(rows, VALUES_ROW_ELEMENTS, CELLS_DISTANCE, CELLS_DISTANCE);
		this.panelValuesLayout(valuesFieldBox, panelValuesGrid, eb, d);

		// Add extra Attributes + OK Button + Error Message
		BorderLayout buttonFieldGrid = new BorderLayout(CELLS_DISTANCE, CELLS_DISTANCE);
		GridLayout axisMinMaxValuesPanelGrid = new GridLayout(6, VALUES_ROW_ELEMENTS, CELLS_DISTANCE, CELLS_DISTANCE);
		SpinnerModel smX = new SpinnerNumberModel(1, null, null, 1);
		SpinnerModel smY = new SpinnerNumberModel(1, null, null, 1);
		this.buttonPanel.setLayout(buttonFieldGrid);
		this.axisMinMaxValuesPanel(axisMinMaxValuesPanelGrid, eb, smX, smY, afl, aml, rbl);
		this.addButton(new FlowLayout(FlowLayout.LEFT, PADDING_SIZE, PADDING_SIZE));
		this.okButton.addActionListener(okbl);
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		//Enter and Escape short keys
		this.okButton.registerKeyboardAction(okButton.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
		this.okButton.registerKeyboardAction(okButton.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
		this.getRootPane().registerKeyboardAction(cdl, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		int componentsRowsCount = (panelAttributes.getComponentCount() / 2) + (valuesField.getComponentCount() / 3)
							+ (axisMinMaxValuesPanel.getComponentCount() / 3) + (buttonField.getComponentCount() / 2);
		this.dialogHeight = (VALUES_HEIGHT * componentsRowsCount + (8 * PADDING_SIZE + CELLS_DISTANCE));
		this.setSize(getWidth(), dialogHeight);
		this.setResizable(false);
	}

	//Main attributes methods
	private void panelAttributesLayout(GridLayout gl, Dimension d, EmptyBorder eb, SpinnerModel sm,
										AttrFocusListener afl, AttrMouseListener aml) {
		
		this.attrChartTitle = new JLabel(CHART_TYPE);
		this.chartType = new JComboBox<>(CHART_STRINGS);
		this.attrGraphTitle = new JLabel(ATTR_GRAPH_TITLE);
		this.attrTitle = new JTextField();
		this.attrXTitle = new JLabel(ATTR_X_TEXT);
		this.attrX = new JTextField();
		this.attrYTitle = new JLabel(ATTR_Y_TEXT);
		this.attrY = new JTextField();
		this.attrNodesTitle = new JLabel(NUMBER_NODES_TEXT);
		this.attrNodes = new JSpinner(sm);
		this.colorChooserTitle = new JLabel(COLOR_CHOOSER_TITLE.substring(7));
		this.colorChooser = new JColorChooser(RED_COLOR);
		this.colorButton = new JButton();

		this.addAttr(attrChartTitle, chartType, d, afl, aml);
		this.addAttr(attrGraphTitle, attrTitle, d, afl, aml);
		this.addAttr(attrXTitle, attrX, d, afl, aml);
		this.addAttr(attrYTitle, attrY, d, afl, aml);
		this.addAttr(attrNodesTitle, attrNodes, d, afl, aml);
		this.addAttr(colorChooserTitle, colorButton, d, afl, aml);

		panelAttributes.setLayout(gl);
		panelAttributes.setBorder(eb);
		panelAttributes.setBackground(LIGHT_GREY_COLOR);
		this.add(panelAttributes, BorderLayout.NORTH);
	}
	
	private void addAttr(JLabel attrTitle, JComponent attr, Dimension d, AttrFocusListener afl, AttrMouseListener aml) {
		attr.setPreferredSize(d);
		attr.setBorder(BorderFactory.createLineBorder(DARK_GREY_COLOR, 1));
		if (attr instanceof JTextField) {
			attr.addMouseListener(aml);
			attr.addFocusListener(afl);
		}
		if (attr instanceof JSpinner){
			JFormattedTextField ftf = ((JSpinner.DefaultEditor) ((JSpinner) attr).getEditor()).getTextField();
			ftf.addFocusListener(afl);
			ftf.addMouseListener(aml);
		}
		if (attr instanceof JComboBox<?>){
			((JComboBox<?>) attr).addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					chartTypeMinMaxAttr();
				}
			});
			chartType.setUI((ComboBoxUI) ChartTypeDropDownMenu.createUI(chartType));
			BasicComboPopup popup = (BasicComboPopup) attr.getAccessibleContext().getAccessibleChild(0);
			popup.setBorder(BorderFactory.createLineBorder(DARK_GREY_COLOR, 1));
		}
		if (attr instanceof JButton){
			attr.setBackground(colorChooser.getColor());
			attr.setFocusable(false);
			ExtraListeners.chooseColorListener((JButton) attr, colorChooser);
		}
		panelAttributes.add(attrTitle);
		panelAttributes.add(attr);
	}
	
	//Peeks Values methods
	private void panelValuesLayout(BoxLayout bl, GridLayout gl, EmptyBorder eb, Dimension d) {
		for (int i = 1; i <= rows; i++) {
			createPeekValueField(i, d);
		}
		valuesField.setBackground(null);
		valuesField.setLayout(gl);

		panelValues.add(valuesField);
		panelValues.setLayout(bl);
		panelValues.setBorder(eb);
		panelValues.setBackground(WHITE_COLOR);

		valuesScroll = new JScrollPane(panelValues);
		valuesScroll.setBorder(null);
		valuesScroll.getVerticalScrollBar().setUnitIncrement(PADDING_SIZE);
		this.add(valuesScroll, BorderLayout.CENTER);
	}

	private void createPeekValueField(int i, Dimension d) {
		this.nodeNumber = new JLabel(VALUE_FIELD_TEXT + i);
		this.nodeValueX = new JTextField();
		this.nodeValueY = new JTextField();

		valuesField.add(nodeNumber);
		this.addPeekValueField(nodeValueX, nodeValueY, i, d);
	}
	
	private void addPeekValueField(JTextField valueFieldX, JTextField valueFieldY, int i, Dimension d) {

		valueFieldX.setText(VALUE_X_TOOLTIP_TEXT);
		valueFieldX.setPreferredSize(d);
		valueFieldX.setBorder(BorderFactory.createLineBorder(DARK_GREY_COLOR, 1));
		valueFieldX.setForeground(DARK_GREY_COLOR);
		valueFieldX.setHorizontalAlignment(JTextField.CENTER);
		
		ExtraListeners.valueFieldAddMouseListener(valueFieldX, i, "X");
		ExtraListeners.valueFieldAddFocusListener(valueFieldX, i, "X");
		ExtraListeners.valueFieldAddDocumentListener(valueFieldX, BLACK_COLOR);
		
		valueFieldY.setText(VALUE_Y_TOOLTIP_TEXT);
		valueFieldY.setPreferredSize(d);
		valueFieldY.setBorder(BorderFactory.createLineBorder(DARK_GREY_COLOR, 1));
		valueFieldY.setForeground(DARK_GREY_COLOR);
		valueFieldY.setHorizontalAlignment(JTextField.CENTER);
		
		ExtraListeners.valueFieldAddMouseListener(valueFieldY, i, "Y");
		ExtraListeners.valueFieldAddFocusListener(valueFieldY, i, "Y");
		ExtraListeners.valueFieldAddDocumentListener(valueFieldY, BLACK_COLOR);
		
		valuesField.add(valueFieldX);
		valuesField.add(valueFieldY);
	}
	
	//MinMax attributes methods
	private void axisMinMaxValuesPanel(GridLayout gl, EmptyBorder eb, SpinnerModel smX, SpinnerModel smY,
										AttrFocusListener afl, AttrMouseListener aml, ActionListener rbl){
		this.axisXMinTitle = new JLabel(AXIS_X_MIN_TITLE);
		this.axisXMinValue = new JTextField();
		this.roundDownMinXValue = new JButton("Round Value");
		this.axisXMaxTitle = new JLabel(AXIS_X_MAX_TITLE);
		this.axisXMaxValue = new JTextField();
		this.roundUpMaxXValue = new JButton("Round Value");
		this.axisXDivTitle = new JLabel(AXIS_X_DIV_TITLE);
		this.axisXDiv = new JSpinner(smX);
		this.axisXStep = new JLabel("");

		this.axisYMinTitle = new JLabel(AXIS_Y_MIN_TITLE);
		this.axisYMinValue = new JTextField();
		this.roundDownMinYValue = new JButton("Round Value");
		this.axisYMaxTitle = new JLabel(AXIS_Y_MAX_TITLE);
		this.axisYMaxValue = new JTextField();
		this.roundUpMaxYValue = new JButton("Round Value");
		this.axisYDivTitle = new JLabel(AXIS_Y_DIV_TITLE);
		this.axisYDiv = new JSpinner(smY);
		this.axisYStep = new JLabel("");
		
		this.addMinMaxValueField(axisXMinTitle, axisXMinValue, roundDownMinXValue, axisXStep, afl, aml, rbl);
		this.addMinMaxValueField(axisXMaxTitle, axisXMaxValue, roundUpMaxXValue, axisXStep, afl, aml, rbl);
		this.addMinMaxValueField(axisXDivTitle, axisXDiv, roundUpMaxXValue, axisXStep, afl, aml, rbl);

		this.addMinMaxValueField(axisYMinTitle, axisYMinValue, roundDownMinYValue, axisYStep, afl, aml, rbl);
		this.addMinMaxValueField(axisYMaxTitle, axisYMaxValue, roundUpMaxYValue, axisYStep, afl, aml, rbl);
		this.addMinMaxValueField(axisYDivTitle, axisYDiv, roundUpMaxYValue, axisYStep, afl, aml, rbl);
		
		ExtraListeners.axisMinMaxSpinnerAddChangeListener(axisXDiv, MIN_DIV, MAX_DIV);
		ExtraListeners.axisMinMaxSpinnerAddChangeListener(axisYDiv, MIN_DIV, MAX_DIV);
		
		eb = new EmptyBorder(PADDING_SIZE, PADDING_SIZE, 0, PADDING_SIZE);
		axisMinMaxValuesPanel.setLayout(gl);
		axisMinMaxValuesPanel.setBorder(eb);
		axisMinMaxValuesPanel.setBackground(WHITE_COLOR);
		this.buttonPanel.add(axisMinMaxValuesPanel, BorderLayout.NORTH);
	}
	
	private void addMinMaxValueField(JLabel attrTitle, JComponent attr, JButton roundButton, JLabel axisStep,
									AttrFocusListener afl, AttrMouseListener aml, ActionListener rbl) {
		attr.setPreferredSize(d);
		attr.setBorder(BorderFactory.createLineBorder(DARK_GREY_COLOR, 1));
		
		roundButton.setPreferredSize(d);
		roundButton.setFont(FONT_REGULAR_10);
		roundButton.setBorder(BorderFactory.createLineBorder(DARK_GREY_COLOR, 1));
		roundButton.setBackground(LIGHT_GREY_COLOR);
		roundButton.setFocusPainted(false);
		
		axisMinMaxValuesPanel.add(attrTitle);
		axisMinMaxValuesPanel.add(attr);
		
		if (attr instanceof JTextField) {
			JFormattedTextField ftf = null;
			((JTextField) attr).setHorizontalAlignment(JTextField.RIGHT);
			attr.addMouseListener(aml);
			attr.addFocusListener(afl);
			ExtraListeners.axisMinMaxAddDocumentListener(attr, ftf, axisStep);
			roundButton.addActionListener(rbl);;
			roundButton.setFocusable(false);
			axisMinMaxValuesPanel.add(roundButton);
		}
		if (attr instanceof JSpinner){
			JFormattedTextField ftf = ((JSpinner.DefaultEditor) ((JSpinner) attr).getEditor()).getTextField();
			ftf.addFocusListener(afl);
			ftf.addMouseListener(aml);
			ExtraListeners.axisMinMaxAddDocumentListener(attr, ftf, axisStep);
			axisStep.setFont(FONT_REGULAR_10);
			axisMinMaxValuesPanel.add(axisStep);
		}
	}
	
	private void addButton(FlowLayout fl) {
		this.okButton = new JButton("OK");
		this.errorMessage = new JLabel();
		
		okButton.setPreferredSize(new Dimension(CELLS_DISTANCE * PADDING_SIZE, CELLS_DISTANCE * CELLS_DISTANCE));
		okButton.setBorder(BorderFactory.createLineBorder(DARK_GREY_COLOR, 1));
		okButton.setBackground(LIGHT_GREY_COLOR);
		okButton.setFocusPainted(false);

		errorMessage.setFont(this.getFont());
		errorMessage.setForeground(RED_COLOR);

		buttonField.setLayout(fl);
		buttonField.add(okButton);
		buttonField.add(errorMessage);
		buttonField.setBackground(WHITE_COLOR);
		
		this.buttonPanel.setBackground(WHITE_COLOR);
		this.buttonPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, DARK_GREY_COLOR));
		this.buttonPanel.add(buttonField, BorderLayout.SOUTH);
	}
	
	//Helper functions
	public void valueFieldMouseEntered(JTextField valueField, String axis) {
		valueField.requestFocusInWindow();
		if (valueField.getText().equals(axis + " value")) {
			valueField.setText("");
		}
		else {
			valueField.selectAll();
		}

	}
	
	public void valueFieldMouseExited(JTextField valueField, String axis) {
		if (valueField.getText().equals("")) {
			valueField.setText(axis + " value");
			valueField.setForeground(DARK_GREY_COLOR);
		}
		if (valueField.getText().equals(axis + " value")) {
			valueField.selectAll();
		}
	}
	
	private boolean validateAttrValue() {
		for (Component c : panelAttributes.getComponents()) {
			if (c instanceof JTextField) {
				if (((JTextField) c).getText().equals("")) {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean validateFieldValue(JTextField valueField) {
		if (!valueField.getText().equals("") && !valueField.getText().matches("[0-9]+")
				|| valueField.getText().length() > 1 && valueField.getText().startsWith("0")) {
			return false;
		}
		return true;
	}
	
	public void validateField(JTextField valueField, int i, String axis) {

		String message = "<html>Invalid value entered in" + CSS + " Peek " + i + ": " + axis + " value</span>! Only positive integers allowed.";

		if (!valueField.getText().equals(axis + " value") && !validateFieldValue(valueField)) {
			errorMessage.setText(message);
			valueField.setBorder(BorderFactory.createLineBorder(RED_COLOR, 1));
		}
		else {
			errorMessage.setText("");
			valueField.setBorder(BorderFactory.createLineBorder(DARK_GREY_COLOR, 1));
		}
	}
	
	public boolean validateMinMaxValue() {
		for (Component c : axisMinMaxValuesPanel.getComponents()) {
			if (c instanceof JTextField) {
				if (((JTextField) c).getText().equals("") || !validateFieldValue((JTextField) c)) {
					return false;
				}
			}
		}
		for (Component c : axisMinMaxValuesPanel.getComponents()) {
			if (c instanceof JTextField) {
				if (!((JTextField) c).getText().equals("")){
					int minX = Integer.parseInt(axisXMinValue.getText());
					int maxX = Integer.parseInt(axisXMaxValue.getText());
					int minY = Integer.parseInt(axisYMinValue.getText());
					int maxY = Integer.parseInt(axisYMaxValue.getText());
					if (minX >= maxX || minY >= maxY) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public void attrSetValue(JTextField valueField){
		if (validateFieldValue(valueField)){
			list.removeAll(list);
			for (Object o : valuesField.getComponents()) {
				if (o instanceof JTextField) {
					if (!validateFieldValue((JTextField) o)) {
						return;
					}
					else {
						try {
							list.add(Integer.parseInt(((JTextField) o).getText()));
						}
						catch (NumberFormatException nfe) {
							//Console cleared from exception!
						}
					}
				}
			}
			listMinMaxDivValue();
		}
	}
	
	private void listMinMaxDivValue(){
		if (!list.isEmpty() && list.size() == (int) attrNodes.getValue() * columns) {
			smallestPeekX = list.get(0);
			biggestPeekX = list.get(0);
			smallestPeekY = list.get(1);
			biggestPeekY = list.get(1);
			for (int i = 0, j = 1; i < list.size() && j < list.size(); i += columns, j += columns) {
				if (smallestPeekX > list.get(i)) {
					smallestPeekX = list.get(i);
				}
				if (biggestPeekX < list.get(i)) {
					biggestPeekX = list.get(i);
				}
				if (smallestPeekY > list.get(j)) {
					smallestPeekY = list.get(j);
				}
				if (biggestPeekY < list.get(j)) {
					biggestPeekY = list.get(j);
				}
			}
			setAttrMinMaxValues();
		}
		else {
			axisXMinValue.setText("");
			axisXMaxValue.setText("");
			axisXDiv.setValue(1);
			axisYMinValue.setText("");
			axisYMaxValue.setText("");
			axisYDiv.setValue(1);
		}
		chartTypeMinMaxAttr();
	}
	
	private void setAttrMinMaxValues(){
		axisXMinValue.setText(smallestPeekX + "");
		axisXMaxValue.setText(biggestPeekX + "");
		axisYMinValue.setText(smallestPeekY + "");
		axisYMaxValue.setText(biggestPeekY + "");
	}
	
	private void chartTypeMinMaxAttr(){
		int chartChoice = chartType.getSelectedIndex();
		if (!validateMinMaxValue()) {
			return;
		}
		else {
			if (chartChoice == 0) {
				setAttrMinMaxValues();
			}
			else {
				int bufferX = (biggestPeekX - smallestPeekX) / ((int) attrNodes.getValue() - 1);
				int bufferY = (biggestPeekY - smallestPeekY) / ((int) attrNodes.getValue() - 1);
				if (smallestPeekX - bufferX < 0 || bufferX <= 0){
					bufferX = 1;
				}
				if (smallestPeekY - bufferY < 0 || bufferY <= 0){
					bufferY = 1;
				}
				axisXMinValue.setText(smallestPeekX - bufferX + "");
				axisYMinValue.setText(smallestPeekY - bufferY + "");
				if (chartChoice == 1) {
					axisXMaxValue.setText(biggestPeekX + bufferX + "");
					axisYMaxValue.setText(biggestPeekY + "");
				}
				if (chartChoice == 2) {
					axisXMaxValue.setText(biggestPeekX + "");
					axisYMaxValue.setText(biggestPeekY + bufferY + "");
				}
			}
		}
	}

	public void divValues(){
		if (ImportGraphDataListener.isImported()){
			return;
		}
		else if (validateMinMaxValue()){
			int divXValue = MAX_DIV;
			while ((Integer.parseInt(axisXMaxValue.getText()) - Integer.parseInt(axisXMinValue.getText()))
					% divXValue != 0) {
				divXValue--;
			}
			axisXDiv.setValue(divXValue);

			int divYValue = MAX_DIV;
			while ((Integer.parseInt(axisYMaxValue.getText()) - Integer.parseInt(axisYMinValue.getText()))
					% divYValue != 0) {
				divYValue--;
			}
			axisYDiv.setValue(divYValue);
		}
	}
	
	public void divLengthMessage(JComponent attr, JLabel axisStep){
			if (attr.equals(axisXDiv)) {
				axisStep.setText(AXIS_STEP + (Integer.parseInt(axisXMaxValue.getText())
								- Integer.parseInt(axisXMinValue.getText()))
								/ (int) ((JSpinner) attr).getValue());
			}
			if (attr.equals(axisYDiv)) {
				axisStep.setText(AXIS_STEP + (Integer.parseInt(axisYMaxValue.getText())
								- Integer.parseInt(axisYMinValue.getText()))
								/ (int) ((JSpinner) attr).getValue());
			}
	}
	
	//Inner classes (Listeners)
	private class SpinnerNumber implements ChangeListener {
		
		@Override
		public void stateChanged(ChangeEvent e) {
			if ((int) ((JSpinner) e.getSource()).getValue() > PEEKS_NUMBER){
				((JSpinner) e.getSource()).setValue(PEEKS_NUMBER);
			}
			int rowNumber = (int) ((JSpinner) e.getSource()).getValue();
			if (rowNumber > rows) {
				for (int i = rows; i < rowNumber; i++) {
					createPeekValueField(i + 1, d);
					if (i < VALUES_NUMBER) {
						dialogHeight += VALUES_HEIGHT;
					} else {
						valuesScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					}
				}
			} else {
				for (int i = rowNumber; i < rows; i++) {
					valuesField.remove(rowNumber * panelValuesGrid.getColumns());
					valuesField.remove(rowNumber * panelValuesGrid.getColumns());
					valuesField.remove(rowNumber * panelValuesGrid.getColumns());
					for (Component c : valuesField.getComponents()){
						if (c instanceof JTextField){
							attrSetValue((JTextField) c);
						}
					}
					listMinMaxDivValue();
					if (i < VALUES_NUMBER) {
						dialogHeight -= VALUES_HEIGHT;
					} else {
						valuesScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
					}
				}
			}
			rows = rowNumber;
			panelValuesGrid.setRows(rows);
			setSize(getWidth(), dialogHeight);
		}

	}
	
	private class RoundButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (validateMinMaxValue()) {
				PositionRulers r = new PositionRulers();
				if (e.getSource().equals(roundDownMinXValue)) {
					axisXMinValue.setText(r.roundDownValue(Integer.parseInt(axisXMinValue.getText())) + "");
				}
				if (e.getSource().equals(roundUpMaxXValue)) {
					axisXMaxValue.setText(r.roundUpValue(Integer.parseInt(axisXMaxValue.getText())) + "");
				}
				if (e.getSource().equals(roundDownMinYValue)) {
					axisYMinValue.setText(r.roundDownValue(Integer.parseInt(axisYMinValue.getText())) + "");
				}
				if (e.getSource().equals(roundUpMaxYValue)) {
					axisYMaxValue.setText(r.roundUpValue(Integer.parseInt(axisYMaxValue.getText())) + "");
				}
				errorMessage.setText("");
			}
			else {
				errorMessage.setText(ERROR_MESSAGE_EXTRA_ATTR);
			}
		}

	}
	
	private class OkButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			list.removeAll(list);
			valuesList.removeAll(valuesList);
			positionList.removeAll(positionList);
			
			for (Component c : panelAttributes.getComponents()) {
				if (c instanceof JTextField) {
					if (!validateAttrValue()) {
						errorMessage.setText(ERROR_MESSAGE_ATTR);
						return;
					}
				}
			}
			for (Component c : valuesField.getComponents()) {
				if (c instanceof JTextField) {
					if (!validateFieldValue((JTextField) c)) {
						errorMessage.setText(ERROR_MESSAGE_VALUES);
						return;
					}
					else {
						list.add(Integer.parseInt(((JTextField) c).getText()));
					}
				}
			}
			for (Component c : axisMinMaxValuesPanel.getComponents()) {
				if (c instanceof JTextField) {
					if (!validateMinMaxValue()) {
						errorMessage.setText(ERROR_MESSAGE_EXTRA_ATTR);
						return;
					}
				}
			}
			if ((Integer.parseInt(axisXMaxValue.getText()) - Integer.parseInt(axisXMinValue.getText())) % (int) axisXDiv.getValue() != 0
					&& ImportGraphDataListener.isActive() ||
					(Integer.parseInt(axisXMaxValue.getText()) - Integer.parseInt(axisXMinValue.getText())) < (int) axisXDiv.getValue()){
				errorMessage.setText(ERROR_MESSAGE_DIV_X);
				return;
			}
			if ((Integer.parseInt(axisYMaxValue.getText()) - Integer.parseInt(axisYMinValue.getText())) % (int) axisYDiv.getValue() != 0
					&& ImportGraphDataListener.isActive() ||
					(Integer.parseInt(axisYMaxValue.getText()) - Integer.parseInt(axisYMinValue.getText())) < (int) axisYDiv.getValue()){
				errorMessage.setText(ERROR_MESSAGE_DIV_Y);
				return;
			}
			
			if (chartType.getSelectedIndex() == 0) {
				if (Integer.parseInt(axisXMinValue.getText()) > smallestPeekX) {
					errorMessage.setText(ERROR_MESSAGE_X_MIN_ATTR);
					return;
				}
				if (Integer.parseInt(axisXMaxValue.getText()) < biggestPeekX) {
					errorMessage.setText(ERROR_MESSAGE_X_MAX_ATTR);
					return;
				}
				if (Integer.parseInt(axisYMinValue.getText()) > smallestPeekY) {
					errorMessage.setText(ERROR_MESSAGE_Y_MIN_ATTR);
					return;
				}
				if (Integer.parseInt(axisYMaxValue.getText()) < biggestPeekY) {
					errorMessage.setText(ERROR_MESSAGE_Y_MAX_ATTR);
					return;
				}
			}

			if (chartType.getSelectedIndex() == 1) {
				if (Integer.parseInt(axisXMinValue.getText()) >= smallestPeekX) {
					errorMessage.setText(ERROR_MESSAGE_X_MIN_ATTR_COL);
					return;
				}
				if (Integer.parseInt(axisXMaxValue.getText()) <= biggestPeekX) {
					errorMessage.setText(ERROR_MESSAGE_X_MAX_ATTR_COL);
					return;
				}
				if (Integer.parseInt(axisYMinValue.getText()) >= smallestPeekY) {
					errorMessage.setText(ERROR_MESSAGE_Y_MIN_ATTR_COL);
					return;
				}
				if (Integer.parseInt(axisYMaxValue.getText()) < biggestPeekY) {
					errorMessage.setText(ERROR_MESSAGE_Y_MAX_ATTR);
					return;
				}
			}
			if (chartType.getSelectedIndex() == 2) {
				if (Integer.parseInt(axisXMinValue.getText()) >= smallestPeekX) {
					errorMessage.setText(ERROR_MESSAGE_X_MIN_ATTR_COL);
					return;
				}
				if (Integer.parseInt(axisXMaxValue.getText()) < biggestPeekX) {
					errorMessage.setText(ERROR_MESSAGE_X_MAX_ATTR);
					return;
				}
				if (Integer.parseInt(axisYMinValue.getText()) >= smallestPeekY) {
					errorMessage.setText(ERROR_MESSAGE_Y_MIN_ATTR_COL);
					return;
				}
				if (Integer.parseInt(axisYMaxValue.getText()) <= biggestPeekY) {
					errorMessage.setText(ERROR_MESSAGE_Y_MAX_ATTR_COL);
					return;
				}
			}
			
			mainPanel = new MainPanel();
			
			if (!list.isEmpty()) {
				PositionRulers r = new PositionRulers();
				int[] arrayValuesX = new int[(int) attrNodes.getValue()];
				int[] arrayValuesY = new int[arrayValuesX.length];
				int[] arrayPositionX = new int[arrayValuesX.length];
				int[] arrayPositionY = new int[arrayValuesX.length];
				for (int i = 0, j = 0; i < list.size() && j < list.size() / 2; i += columns, j++) {
					arrayValuesX[j] = list.get(i);
					arrayPositionX[j] = r.positionX(list.get(i), Integer.parseInt(axisXMinValue.getText()));
				}
				for (int i = 1, j = 0; i < list.size() && j < list.size() / 2; i += columns, j++) {
					arrayValuesY[j] = list.get(i);
					arrayPositionY[j] = r.positionY(list.get(i), Integer.parseInt(axisYMinValue.getText()));
				}
				valuesList.add(arrayValuesX);
				valuesList.add(arrayValuesY);
				positionList.add(arrayPositionX);
				positionList.add(arrayPositionY);
			}

			MainPanel.setChartType(chartType.getSelectedItem().toString());
			MainPanel.setTitleGraph(attrTitle.getText());
			MainPanel.setTitleX(attrX.getText());
			MainPanel.setTitleY(attrY.getText());
			MainPanel.setPeeksNumber((int) attrNodes.getValue());
			
			MainPanel.setChartColor(colorButton.getBackground().getRGB());
			
			MainPanel.setSmallestX(smallestPeekX);
			MainPanel.setBiggestX(biggestPeekX);
			MainPanel.setSmallestY(smallestPeekY);
			MainPanel.setBiggestY(biggestPeekY);

			MainPanel.setAxisXStartsWith(Integer.parseInt(axisXMinValue.getText()));
			MainPanel.setAxisXEndsWith(Integer.parseInt(axisXMaxValue.getText()));
			MainPanel.setAxisXDivision((int) axisXDiv.getValue());

			MainPanel.setAxisYStartsWith(Integer.parseInt(axisYMinValue.getText()));
			MainPanel.setAxisYEndsWith(Integer.parseInt(axisYMaxValue.getText()));
			MainPanel.setAxisYDivision((int) axisYDiv.getValue());

			MainPanel.setList(list);
			MainPanel.setValuesList(valuesList);
			MainPanel.setPositionList(positionList);

			GraphGeneratorExec.getFrame().remove(MainFrame.getPanel());
			mainPanel.setVisible(false);
			GraphGeneratorExec.getFrame().add(mainPanel);
			MainFrame.setPanel(mainPanel);
			GraphGeneratorExec.getFrame().setNewGraphDialog(NewGraphListener.getNewGraphDialog());
			mainPanel.setVisible(true);
			errorMessage.setText("");
			MainFrame.getEditGraph().setEnabled(true);
			MainFrame.getExportMenu().setEnabled(true);
			if (ImportGraphDataListener.isImported()){
				MainPanel.setGraphIsExported(true);
			}
			else {
				MainPanel.setGraphIsExported(false);
			}
			setVisible(false);
		}

	}

	//Getters and Setters
	
	public JTextField getAxisXMinValue() {
		return axisXMinValue;
	}

	public JTextField getAttrTitle() {
		return attrTitle;
	}

	public void setAttrTitle(JTextField attrTitle) {
		this.attrTitle = attrTitle;
	}

	public JTextField getAttrX() {
		return attrX;
	}

	public void setAttrX(JTextField attrX) {
		this.attrX = attrX;
	}

	public JTextField getAttrY() {
		return attrY;
	}

	public void setAttrY(JTextField attrY) {
		this.attrY = attrY;
	}

	public JComboBox<String> getChartType() {
		return chartType;
	}

	public void setChartType(JComboBox<String> chartType) {
		this.chartType = chartType;
	}

	public void setAttrNodes(JSpinner attrNodes) {
		this.attrNodes = attrNodes;
	}

	public JButton getColorButton() {
		return colorButton;
	}

	public void setColorButton(JButton colorButton) {
		this.colorButton = colorButton;
	}

	public JTextField getAxisXMaxValue() {
		return axisXMaxValue;
	}

	public JTextField getAxisYMinValue() {
		return axisYMinValue;
	}

	public JTextField getAxisYMaxValue() {
		return axisYMaxValue;
	}

	public JSpinner getAttrNodes() {
		return attrNodes;
	}
	
	public static MainPanel getMainPanel() {
		return mainPanel;
	}
	
	public JLabel getErrorMessage() {
		return errorMessage;
	}

	public static String[] getChartStrings() {
		return CHART_STRINGS;
	}

	public LinkedList<Integer> getList() {
		return list;
	}

	public void setList(LinkedList<Integer> list) {
		this.list = list;
	}

	public JPanel getValuesField() {
		return valuesField;
	}

	public JSpinner getAxisXDiv() {
		return axisXDiv;
	}

	public JSpinner getAxisYDiv() {
		return axisYDiv;
	}

	public int getSmallestPeekX() {
		return smallestPeekX;
	}

	public void setSmallestPeekX(int smallestPeekX) {
		this.smallestPeekX = smallestPeekX;
	}

	public int getBiggestPeekX() {
		return biggestPeekX;
	}

	public void setBiggestPeekX(int biggestPeekX) {
		this.biggestPeekX = biggestPeekX;
	}

	public int getSmallestPeekY() {
		return smallestPeekY;
	}

	public void setSmallestPeekY(int smallestPeekY) {
		this.smallestPeekY = smallestPeekY;
	}

	public int getBiggestPeekY() {
		return biggestPeekY;
	}

	public void setBiggestPeekY(int biggestPeekY) {
		this.biggestPeekY = biggestPeekY;
	}

}