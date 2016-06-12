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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import GraphGeneratorProject.GraphGeneratorExec;
import GraphGeneratorProject.MainPanel;
import GraphGeneratorProject.Helpers.ExtraListeners;
import GraphGeneratorProject.Helpers.ExtraListeners.AttrFocusListener;
import GraphGeneratorProject.Helpers.ExtraListeners.AttrMouseListener;
import GraphGeneratorProject.Helpers.PositionOnScreen;
import GraphGeneratorProject.Helpers.PositionRulers;
import GraphGeneratorProject.MenuListeners.NewGraphListener;

public class DialogCreateGraph extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	private static final String
			DIALOG_TITLE = "Create New Graph", ATTR_GRAPH_TITLE = "Graph Title",
			ATTR_X_TEXT = "Axis X Title", ATTR_Y_TEXT = "Axis Y Title", NUMBER_NODES_TEXT = "Number of Peeks",
			ATTR_X_MIN_TITLE = "X axis MIN Value", ATTR_X_MAX_TITLE = "X axis MAX Value", ATTR_X_DIV_TITLE = "X axis divisions",
			ATTR_Y_MIN_TITLE = "Y axis MIN Value", ATTR_Y_MAX_TITLE = "Y axis MAX Value", ATTR_Y_DIV_TITLE = "Y axis divisions",
			VALUE_FIELD_TEXT = "Peek ",
			AXIS_STEP = "Axis delimiter step: ",
			ERROR_MESAGE_ATTR = "No values entered for Graph or Axis title(s)!",
			ERROR_MESAGE_VALUES = "Only positive integers allowed for Peeks values!",
			ERROR_MESAGE_EXTRA_ATTR = "MIN axis value must be smaller than MAX one! Positive integers.",
			ERROR_MESAGE_MIN_ATTR = "MIN axis value must be smaller or equal to MIN peek value.",
			ERROR_MESAGE_MAX_ATTR = "MAX axis value must be bigger or equal to MAX peek value.",
			ERROR_MESAGE_DIV_X = "Invalid X axis divisions! The axis should be divisible by integers.",
			ERROR_MESAGE_DIV_Y = "Invalid Y axis divisions! The axis should be divisible by integers.";
	private static final Color
			DARK_GREY_COLOR = Color.decode("#d8dadb"),
			LIGHT_GREY_COLOR = Color.decode("#ecedee"),
			RED_COLOR = Color.RED;
	private static final Font REGULAR = new Font("Arial", Font.PLAIN, 10);
	private static final int
			CELLS_DISTANCE = 5, PADDING_SIZE = 20, VALUES_HEIGHT = CELLS_DISTANCE + PADDING_SIZE,
			VALUES_NUMBER = 5, MIN_DIV = 1, MAX_DIV = 10, PEEKS_NUMBER = 100, VALUES_ROW_ELEMENTS = 3;
	private JPanel panelAttributes, panelValues, valuesField, buttonPanel, axisMinMaxValuesPanel, buttonField;
	private JLabel
			attrGraphTitle, attrXTitle, attrYTitle, attrNodesTitle, attrXMinTitle, attrXMaxTitle, attrXDivTitle, attrYMinTitle,
			attrYMaxTitle, attrYDivTitle, nodeNumber, errorMessage, axisXStep, axisYStep;
	private JTextField attrTitle, attrX, attrY, attrXMinValue, attrXMaxValue, attrYMinValue, attrYMaxValue, nodeValueX, nodeValueY;
	private JSpinner attrNodes, attrXDiv, attrYDiv;
	private int rows, dialogHeight, columns = 2,
			smallestPeekX, biggestPeekX, smallestPeekY, biggestPeekY;
	private GridLayout panelValuesGrid;
	private JScrollPane valuesScroll;
	private Dimension d;
	private JButton okButton, roundDownMinXValue, roundUpMaxXValue, roundDownMinYValue, roundUpMaxYValue;
	private LinkedList<Integer> list;
	private LinkedList<int[]> valuesList;
	private static MainPanel mainPanel;
	private boolean hasValues;

	public DialogCreateGraph() {
		super();
		
		// Common stuff
		this.setTitle(DIALOG_TITLE);
		this.setModal(true);
		this.list = new LinkedList<>();
		this.valuesList = new LinkedList<>();

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
		GridLayout panelAttrGrid = new GridLayout(4, columns, CELLS_DISTANCE, CELLS_DISTANCE);
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
		okButton.registerKeyboardAction(okbl, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		this.getRootPane().registerKeyboardAction(cdl, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		int componentsRowsCount = (panelAttributes.getComponentCount() / 2) + (valuesField.getComponentCount() / 3)
							+ (axisMinMaxValuesPanel.getComponentCount() / 3) + (buttonField.getComponentCount() / 2);
		this.dialogHeight = (VALUES_HEIGHT * componentsRowsCount + (8 * PADDING_SIZE + CELLS_DISTANCE));
		this.setSize(getWidth(), dialogHeight);
		this.setResizable(false);
	}

	private void panelAttributesLayout(GridLayout gl, Dimension d, EmptyBorder eb, SpinnerModel sm,
										AttrFocusListener afl, AttrMouseListener aml) {
		this.attrGraphTitle = new JLabel(ATTR_GRAPH_TITLE);
		this.attrTitle = new JTextField();
		this.attrXTitle = new JLabel(ATTR_X_TEXT);
		this.attrX = new JTextField();
		this.attrYTitle = new JLabel(ATTR_Y_TEXT);
		this.attrY = new JTextField();
		this.attrNodesTitle = new JLabel(NUMBER_NODES_TEXT);
		this.attrNodes = new JSpinner(sm);

		this.addAttr(attrGraphTitle, attrTitle, d, afl, aml);
		this.addAttr(attrXTitle, attrX, d, afl, aml);
		this.addAttr(attrYTitle, attrY, d, afl, aml);
		this.addAttr(attrNodesTitle, attrNodes, d, afl, aml);

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
		panelAttributes.add(attrTitle);
		panelAttributes.add(attr);
	}

	private void panelValuesLayout(BoxLayout bl, GridLayout gl, EmptyBorder eb, Dimension d) {
		for (int i = 1; i <= rows; i++) {
			createPeekValueField(i, d);
		}
		valuesField.setBackground(null);
		valuesField.setLayout(gl);

		panelValues.add(valuesField);
		panelValues.setLayout(bl);
		panelValues.setBorder(eb);
		panelValues.setBackground(Color.WHITE);

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

		valueFieldX.setText("X value");
		valueFieldX.setPreferredSize(d);
		valueFieldX.setBorder(BorderFactory.createLineBorder(DARK_GREY_COLOR, 1));
		valueFieldX.setForeground(DARK_GREY_COLOR);
		valueFieldX.setHorizontalAlignment(JTextField.CENTER);
		valueFieldX.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				valueFieldMouseExited(valueFieldX, "X");
				validateField(valueFieldX, i, "X");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				valueFieldMouseEntered(valueFieldX, "X");
				validateField(valueFieldX, i, "X");
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		valueFieldX.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				valueFieldMouseExited(valueFieldX, "X");
				validateField(valueFieldX, i, "X");
			}

			@Override
			public void focusGained(FocusEvent e) {
				valueFieldMouseEntered(valueFieldX, "X");
				validateField(valueFieldX, i, "X");
			}
		});
		valueFieldX.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				attrSetValue(valueFieldX);
				valueFieldX.setForeground(Color.BLACK);
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				attrSetValue(valueFieldX);
				valueFieldX.setForeground(Color.BLACK);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				attrSetValue(valueFieldX);
				valueFieldX.setForeground(Color.BLACK);
			}
		});
		
		valueFieldY.setText("Y value");
		valueFieldY.setPreferredSize(d);
		valueFieldY.setBorder(BorderFactory.createLineBorder(DARK_GREY_COLOR, 1));
		valueFieldY.setForeground(DARK_GREY_COLOR);
		valueFieldY.setHorizontalAlignment(JTextField.CENTER);
		valueFieldY.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				valueFieldMouseExited(valueFieldY, "Y");
				validateField(valueFieldY, i, "Y");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				valueFieldMouseEntered(valueFieldY, "Y");
				validateField(valueFieldY, i, "Y");
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		valueFieldY.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				valueFieldMouseExited(valueFieldY, "Y");
				validateField(valueFieldY, i, "Y");
			}

			@Override
			public void focusGained(FocusEvent e) {
				valueFieldMouseEntered(valueFieldY, "Y");
				validateField(valueFieldY, i, "Y");
			}
		});
		valueFieldY.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				attrSetValue(valueFieldY);
				valueFieldY.setForeground(Color.BLACK);
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				attrSetValue(valueFieldY);
				valueFieldY.setForeground(Color.BLACK);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				attrSetValue(valueFieldY);
				valueFieldY.setForeground(Color.BLACK);
			}
		});
		
		valuesField.add(valueFieldX);
		valuesField.add(valueFieldY);
	}
	
	private void axisMinMaxValuesPanel(GridLayout gl, EmptyBorder eb, SpinnerModel smX, SpinnerModel smY,
										AttrFocusListener afl, AttrMouseListener aml, ActionListener rbl){
		this.attrXMinTitle = new JLabel(ATTR_X_MIN_TITLE);
		this.attrXMinValue = new JTextField();
		this.roundDownMinXValue = new JButton("Round Value");
		this.attrXMaxTitle = new JLabel(ATTR_X_MAX_TITLE);
		this.attrXMaxValue = new JTextField();
		this.roundUpMaxXValue = new JButton("Round Value");
		this.attrXDivTitle = new JLabel(ATTR_X_DIV_TITLE);
		this.attrXDiv = new JSpinner(smX);
		this.axisXStep = new JLabel("");

		this.attrYMinTitle = new JLabel(ATTR_Y_MIN_TITLE);
		this.attrYMinValue = new JTextField();
		this.roundDownMinYValue = new JButton("Round Value");
		this.attrYMaxTitle = new JLabel(ATTR_Y_MAX_TITLE);
		this.attrYMaxValue = new JTextField();
		this.roundUpMaxYValue = new JButton("Round Value");
		this.attrYDivTitle = new JLabel(ATTR_Y_DIV_TITLE);
		this.attrYDiv = new JSpinner(smY);
		this.axisYStep = new JLabel("");
		
		this.addMinMaxValueField(attrXMinTitle, attrXMinValue, roundDownMinXValue, axisXStep, afl, aml, rbl);
		this.addMinMaxValueField(attrXMaxTitle, attrXMaxValue, roundUpMaxXValue, axisXStep, afl, aml, rbl);
		this.addMinMaxValueField(attrXDivTitle, attrXDiv, roundUpMaxXValue, axisXStep, afl, aml, rbl);

		this.addMinMaxValueField(attrYMinTitle, attrYMinValue, roundDownMinYValue, axisYStep, afl, aml, rbl);
		this.addMinMaxValueField(attrYMaxTitle, attrYMaxValue, roundUpMaxYValue, axisYStep, afl, aml, rbl);
		this.addMinMaxValueField(attrYDivTitle, attrYDiv, roundUpMaxYValue, axisYStep, afl, aml, rbl);
		
		this.attrXDiv.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if ((int) attrXDiv.getValue() < MIN_DIV ){
					attrXDiv.setValue(MIN_DIV);
				}
				if ((int) attrXDiv.getValue() > MAX_DIV ){
					attrXDiv.setValue(MAX_DIV);
				}	
			}
		});
		this.attrYDiv.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if ((int) attrYDiv.getValue() < MIN_DIV ){
					attrYDiv.setValue(MIN_DIV);
				}
				if ((int) attrYDiv.getValue() > MAX_DIV ){
					attrYDiv.setValue(MAX_DIV);
				}	
			}
		});
		
		eb = new EmptyBorder(PADDING_SIZE, PADDING_SIZE, 0, PADDING_SIZE);
		axisMinMaxValuesPanel.setLayout(gl);
		axisMinMaxValuesPanel.setBorder(eb);
		axisMinMaxValuesPanel.setBackground(Color.WHITE);
		this.buttonPanel.add(axisMinMaxValuesPanel, BorderLayout.NORTH);
	}
	
	private void addMinMaxValueField(JLabel attrTitle, JComponent attr, JButton roundButton, JLabel axisStep,
									AttrFocusListener afl, AttrMouseListener aml, ActionListener rbl) {
		attr.setPreferredSize(d);
		attr.setBorder(BorderFactory.createLineBorder(DARK_GREY_COLOR, 1));
		
		roundButton.setPreferredSize(d);
		roundButton.setFont(REGULAR);
		roundButton.setBorder(BorderFactory.createLineBorder(DARK_GREY_COLOR, 1));
		roundButton.setBackground(LIGHT_GREY_COLOR);
		roundButton.setFocusPainted(false);
		
		axisMinMaxValuesPanel.add(attrTitle);
		axisMinMaxValuesPanel.add(attr);
		
		if (attr instanceof JTextField) {
			((JTextField) attr).setHorizontalAlignment(JTextField.RIGHT);
			attr.addMouseListener(aml);
			attr.addFocusListener(afl);
			((JTextField) attr).getDocument().addDocumentListener(new DocumentListener() {
				
				@Override
				public void removeUpdate(DocumentEvent e) {
					divValues();
					divLengthMessage(attr, axisStep);
				}
				
				@Override
				public void insertUpdate(DocumentEvent e) {
					divValues();
					divLengthMessage(attr, axisStep);
					
				}
				
				@Override
				public void changedUpdate(DocumentEvent e) {
					divValues();
					divLengthMessage(attr, axisStep);
					
				}
			});
			roundButton.addActionListener(rbl);;
			roundButton.setFocusable(false);
			axisMinMaxValuesPanel.add(roundButton);
		}
		if (attr instanceof JSpinner){
			JFormattedTextField ftf = ((JSpinner.DefaultEditor) ((JSpinner) attr).getEditor()).getTextField();
			ftf.addFocusListener(afl);
			ftf.addMouseListener(aml);
			ftf.getDocument().addDocumentListener(new DocumentListener() {
				
				@Override
				public void removeUpdate(DocumentEvent e) {
					divLengthMessage(attr, axisStep);
				}
				
				@Override
				public void insertUpdate(DocumentEvent e) {
					divLengthMessage(attr, axisStep);
				}
				
				@Override
				public void changedUpdate(DocumentEvent e) {
					divLengthMessage(attr, axisStep);
				}
			});
			axisStep.setFont(REGULAR);
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
		buttonField.setBackground(Color.WHITE);
		
		this.buttonPanel.setBackground(Color.WHITE);
		this.buttonPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, DARK_GREY_COLOR));
		this.buttonPanel.add(buttonField, BorderLayout.SOUTH);
	}
	
	private void valueFieldMouseEntered(JTextField valueField, String axis) {
		valueField.requestFocusInWindow();
		if (valueField.getText().equals(axis + " value")) {
			valueField.setText("");
		}
		else {
			valueField.selectAll();
		}

	}
	
	private void valueFieldMouseExited(JTextField valueField, String axis) {
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
	
	private void validateField(JTextField valueField, int i, String axis) {

		String CSS = "<span style = \"color: black; font-weight: bold;\">";
		String message = "<html>Invalid value entered in" + CSS + " Peek " + i + ": " + axis + " value</span>! Only positive integers alowed.";

		if (!valueField.getText().equals(axis + " value") && !validateFieldValue(valueField)) {
			errorMessage.setText(message);
			valueField.setBorder(BorderFactory.createLineBorder(RED_COLOR, 1));
		}
		else {
			errorMessage.setText("");
			valueField.setBorder(BorderFactory.createLineBorder(DARK_GREY_COLOR, 1));
		}
	}
	
	private boolean validateMinMaxValue() {
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
					int minX = Integer.parseInt(attrXMinValue.getText());
					int maxX = Integer.parseInt(attrXMaxValue.getText());
					int minY = Integer.parseInt(attrYMinValue.getText());
					int maxY = Integer.parseInt(attrYMaxValue.getText());
					if (minX >= maxX || minY >= maxY) {
						return false;
					}
				}
			}
		}
		return true;
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
			attrXMinValue.setText(smallestPeekX + "");
			attrXMaxValue.setText(biggestPeekX + "");
			attrYMinValue.setText(smallestPeekY + "");
			attrYMaxValue.setText(biggestPeekY + "");
		}
		else {
			attrXMinValue.setText("");
			attrXMaxValue.setText("");
			attrXDiv.setValue(1);
			attrYMinValue.setText("");
			attrYMaxValue.setText("");
			attrYDiv.setValue(1);
		}
	}
	
	private void divValues(){
		if (validateMinMaxValue()){
			int divXValue = MAX_DIV;
			while ((Integer.parseInt(attrXMaxValue.getText()) - Integer.parseInt(attrXMinValue.getText()))
					% divXValue != 0) {
				divXValue--;
			}
			attrXDiv.setValue(divXValue);

			int divYValue = MAX_DIV;
			while ((Integer.parseInt(attrYMaxValue.getText()) - Integer.parseInt(attrYMinValue.getText()))
					% divYValue != 0) {
				divYValue--;
			}
			attrYDiv.setValue(divYValue);
		}
	}
	
	private void attrSetValue(JTextField valueField){
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
	
	private void divLengthMessage(JComponent attr, JLabel axisStep){
		if (validateMinMaxValue()){
			if (attr.equals(attrXDiv)) {
				axisStep.setText(AXIS_STEP + (Integer.parseInt(attrXMaxValue.getText())
								- Integer.parseInt(attrXMinValue.getText()))
								/ (int) ((JSpinner) attr).getValue());
			}
			if (attr.equals(attrYDiv)) {
				axisStep.setText(AXIS_STEP + (Integer.parseInt(attrYMaxValue.getText())
								- Integer.parseInt(attrYMinValue.getText()))
								/ (int) ((JSpinner) attr).getValue());
			}
		}
		else {
			axisStep.setText("");
		}
	}
	
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
					if (!list.isEmpty()){
						list.removeLast();
						list.removeLast();
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
			PositionOnScreen pos = new PositionOnScreen();
			pos.centerScreen(NewGraphListener.getNewGraphDialog());
		}

	}
	
	private class RoundButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (validateMinMaxValue()) {
				PositionRulers r = new PositionRulers();
				if (e.getSource().equals(roundDownMinXValue)) {
					attrXMinValue.setText(r.roundDownValue(Integer.parseInt(attrXMinValue.getText())) + "");
				}
				if (e.getSource().equals(roundUpMaxXValue)) {
					attrXMaxValue.setText(r.roundUpValue(Integer.parseInt(attrXMaxValue.getText())) + "");
				}
				if (e.getSource().equals(roundDownMinYValue)) {
					attrYMinValue.setText(r.roundDownValue(Integer.parseInt(attrYMinValue.getText())) + "");
				}
				if (e.getSource().equals(roundUpMaxYValue)) {
					attrYMaxValue.setText(r.roundUpValue(Integer.parseInt(attrYMaxValue.getText())) + "");
				}
				errorMessage.setText("");
			}
			else {
				errorMessage.setText(ERROR_MESAGE_EXTRA_ATTR);
			}
		}

	}
	
	private class OkButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			list.removeAll(list);
			valuesList.removeAll(valuesList);
			for (Component c : panelAttributes.getComponents()) {
				if (c instanceof JTextField) {
					if (!validateAttrValue()) {
						errorMessage.setText(ERROR_MESAGE_ATTR);
						return;
					}
				}
			}
			for (Component c : valuesField.getComponents()) {
				if (c instanceof JTextField) {
					if (!validateFieldValue((JTextField) c)) {
						errorMessage.setText(ERROR_MESAGE_VALUES);
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
						errorMessage.setText(ERROR_MESAGE_EXTRA_ATTR);
						return;
					}
				}
			}
			if ((Integer.parseInt(attrXMaxValue.getText()) - Integer.parseInt(attrXMinValue.getText())) % (int) attrXDiv.getValue() != 0){
				errorMessage.setText(ERROR_MESAGE_DIV_X);
				return;
			}
			if ((Integer.parseInt(attrYMaxValue.getText()) - Integer.parseInt(attrYMinValue.getText())) % (int) attrYDiv.getValue() != 0){
				errorMessage.setText(ERROR_MESAGE_DIV_Y);
				return;
			}
			if (Integer.parseInt(attrXMinValue.getText()) > smallestPeekX || Integer.parseInt(attrYMinValue.getText()) > smallestPeekY){
				errorMessage.setText(ERROR_MESAGE_MIN_ATTR);
				return;
			}
			if (Integer.parseInt(attrXMaxValue.getText()) < biggestPeekX || Integer.parseInt(attrYMaxValue.getText()) < biggestPeekY){
				errorMessage.setText(ERROR_MESAGE_MAX_ATTR);
				return;
			}
			
			mainPanel = new MainPanel();
			if (!list.isEmpty()) {
				PositionRulers r = new PositionRulers();
				int[] arrayX = new int[(int) attrNodes.getValue()];
				int[] arrayY = new int[arrayX.length];
				for (int i = 0, j = 0; i < list.size() && j < list.size() / 2; i += columns, j++) {
					arrayX[j] = r.positionX(list.get(i), Integer.parseInt(attrXMinValue.getText()));
				}
				for (int i = 1, j = 0; i < list.size() && j < list.size() / 2; i += columns, j++) {
					arrayY[j] = r.positionY(list.get(i), Integer.parseInt(attrYMinValue.getText()));
				}
				valuesList.add(arrayX);
				valuesList.add(arrayY);
			}
			
			mainPanel.setTitleGraph(attrTitle.getText());
			mainPanel.setTitleX(attrX.getText());
			mainPanel.setTitleY(attrY.getText());
			mainPanel.setPeeksNumber((int) attrNodes.getValue());

			mainPanel.setAxisXStartsWith(Integer.parseInt(attrXMinValue.getText()));
			mainPanel.setAxisXEndsWith(Integer.parseInt(attrXMaxValue.getText()));
			mainPanel.setAxisXDivision((int) attrXDiv.getValue());

			mainPanel.setAxisYStartsWith(Integer.parseInt(attrYMinValue.getText()));
			mainPanel.setAxisYEndsWith(Integer.parseInt(attrYMaxValue.getText()));
			mainPanel.setAxisYDivision((int) attrYDiv.getValue());

			mainPanel.setValuesList(valuesList);

			GraphGeneratorExec.getFrame().remove(GraphGeneratorExec.getFrame().getPanel());
			mainPanel.setVisible(false);
			GraphGeneratorExec.getFrame().add(mainPanel);
			GraphGeneratorExec.getFrame().setPanel(mainPanel);
			GraphGeneratorExec.getFrame().setNewGraphDialog(NewGraphListener.getNewGraphDialog());
			mainPanel.setVisible(true);
			hasValues = true;
			errorMessage.setText("");
			setVisible(false);
		}

	}

	public JTextField getAttrXMinValue() {
		return attrXMinValue;
	}

	public JTextField getAttrXMaxValue() {
		return attrXMaxValue;
	}

	public JTextField getAttrYMinValue() {
		return attrYMinValue;
	}

	public JTextField getAttrYMaxValue() {
		return attrYMaxValue;
	}

	public JSpinner getAttrNodes() {
		return attrNodes;
	}
	
	public static MainPanel getMainPanel() {
		return mainPanel;
	}
	
	public boolean hasValues() {
		return hasValues;
	}
	
	public void setHasValues(boolean hasValues) {
		this.hasValues = hasValues;
	}
	
	public JLabel getErrorMessage() {
		return errorMessage;
	}

}