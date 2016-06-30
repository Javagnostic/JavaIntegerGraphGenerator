package GraphGeneratorProject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import javax.swing.JPanel;

import GraphGeneratorProject.Dialogs.DialogCreateGraph;
import GraphGeneratorProject.Helpers.Colors;
import GraphGeneratorProject.Helpers.Fonts;
import GraphGeneratorProject.Helpers.Integers;
import GraphGeneratorProject.Helpers.PositionRulers;

public class MainPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	
	private static final int CIRCLE_D = Integers.getCircleD();
	private static final Font
			FONT_GRAPH_TITLE = Fonts.getFontBold30(),
			FONT_AXIS_TITLE = Fonts.getFontBold14(),
			FONT_AXIS_NUMBERS = Fonts.getFontRegular10();
	private static String chartType, titleGraph, titleX, titleY;
	private static int peeksNumber, chartColor, smallestX, biggestX, smallestY, biggestY, axisXStartsWith, axisXEndsWith, axisXDivision, axisYStartsWith, axisYEndsWith, axisYDivision;
	private static LinkedList<int[]> valuesList, positionList;
	private static LinkedList<Integer> list;
	private PositionRulers r;
	private static boolean graphIsExported;
	
	public MainPanel(){
		super();
		this.setBackground(Colors.getWhiteColor());
		valuesList = new LinkedList<>();
		positionList = new LinkedList<>();
		list = new LinkedList<>();
		this.r = new PositionRulers();
	}
	
	//Paint Graphics method
	@Override
	protected void paintComponent (Graphics g){
		super.paintComponent(g);
		FontRenderContext frc = new FontRenderContext(null, false, true);
		Color graphColor = new Color(chartColor);
		if (chartType.equals(DialogCreateGraph.getChartStrings()[0])){
			drawPolyline(g, frc, graphColor);
		}
		if (chartType.equals(DialogCreateGraph.getChartStrings()[1])){
			drawVerticalColumns(g, frc, graphColor);
		}
		if (chartType.equals(DialogCreateGraph.getChartStrings()[2])){
			drawHorizontalColumns(g, frc, graphColor);
		}
		drawGraphTitle(g, FONT_GRAPH_TITLE, frc);
		drawAxisX(g, 0, axisXStartsWith, axisXEndsWith, axisXDivision, FONT_AXIS_NUMBERS, FONT_AXIS_TITLE, frc);
		drawAxisY(g, 0, axisYStartsWith, axisYEndsWith, axisYDivision, FONT_AXIS_NUMBERS, FONT_AXIS_TITLE, frc);
	}
	
	//Calculate drawn Strings rectangles width/height
	private int stringWidth(Font font, String s, FontRenderContext frc){
		Rectangle2D r2D = font.getStringBounds(s, frc);
		int sWidth = (int) Math.round(r2D.getWidth());
		return sWidth;
	}
	
	private int stringHeight(Font font, String s, FontRenderContext frc){
		Rectangle2D r2D = font.getStringBounds(s, frc);
		int sHeight = (int) Math.round(r2D.getHeight());
		return sHeight;
	}
	
	//Draw Graph Title
	private void drawGraphTitle (Graphics g, Font font, FontRenderContext frc){
		Rectangle2D r2D = font.getStringBounds(titleGraph, frc);
		int sWidth = (int) Math.round(r2D.getWidth());
		g.setFont(font);
		g.drawString(titleGraph, (int) ((r.positionAxisX(0) + r.getGRAPH_END_X()) / 2 - sWidth / 2), (int) r.getAXISES_MARGINS());
	}
	
	//Draw Axis X Title
	private void drawAxisX (Graphics g, int axisZero, int startsWith, int endsWith, int axisDiv,
							Font font, Font titleFont, FontRenderContext frc){
		int x = r.positionAxisX(axisZero);
		
		for (int i = startsWith; i <= endsWith;){
			String s = i + "";
			int sWidth = stringWidth(font, s, frc);
			int sHeight = stringHeight(font, s, frc);
			
			g.drawLine(x, r.positionAxisY(-sHeight / 2), x, r.positionAxisY(axisZero));
			g.setFont(font);
			g.drawString(s, x - sWidth / 2, r.positionAxisY(- 2 * sHeight));
			i += (endsWith - startsWith) / axisDiv;
			if (i <= endsWith){
				x += (r.getGRAPH_END_X() - r.positionAxisX(axisZero)) / axisDiv;
			}
		}
		g.drawLine(r.positionAxisX(axisZero), r.positionAxisY(axisZero), x, r.positionAxisY(axisZero));

		
		int sWidth = stringWidth(titleFont, titleX, frc);
		int sHeight = stringHeight(titleFont, titleX, frc);
		g.setFont(titleFont);
		g.drawString(titleX, (int) ((r.positionAxisX(axisZero) + r.getGRAPH_END_X()) / 2 - sWidth / 2),
				r.positionAxisY(- 4 * sHeight));
	}
	
	//Draw Axis Y Title
	private void drawAxisY (Graphics g, int axisZero, int startsWith, int endsWith, int axisDiv,
							Font font, Font titleFont, FontRenderContext frc){
		int y = r.positionAxisY(axisZero);
		String s = null;
		
		for (int i = startsWith; i <= endsWith;){
			s = i + "";
			int sWidth = stringWidth(font, s, frc);
			int sHeight = stringHeight(font, s, frc);
			
			g.drawLine(r.positionAxisX(- sHeight / 2), y, r.positionAxisX(axisZero), y);
			g.setFont(font);
			g.drawString(s, r.positionAxisX(- sWidth - sHeight), y + sHeight / 3);
			i += (endsWith - startsWith) / axisDiv;
			if (i <= endsWith){
				y -= (r.positionAxisY(axisZero) - r.getGRAPH_END_Y()) / axisDiv;
			}
		}
		g.drawLine(r.positionAxisX(axisZero), r.positionAxisY(axisZero), r.positionAxisX(axisZero), y);
		
		int width = stringWidth(titleFont, titleY, frc);
		int height = stringHeight(titleFont, titleY, frc);
		int sWidth = stringWidth(font, s, frc);
		Graphics2D g2D = (Graphics2D) g;
		g2D.setFont(titleFont);
        g2D.getTransform();
        AffineTransform at = new AffineTransform();
        at.rotate(- Math.PI / 2);
        g2D.setTransform(at);
        g2D.drawString(titleY, (int) ((- r.positionAxisY(axisZero) + (r.positionAxisY(axisZero) - r.getGRAPH_END_Y()) / 2) - width / 2),
        		(int) (r.positionAxisX(- 3 * height) - sWidth));
	}
	
	// Draw Results
	private void drawPolyline (Graphics g, FontRenderContext frc, Color graphColor){
		Graphics2D line = (Graphics2D) g;
		line.setColor(graphColor);
		line.drawPolyline(positionList.getFirst(), positionList.getLast(), peeksNumber);
		for (int i = 0; i < positionList.getFirst().length; i++){
			if (MainFrame.getPeeksValues().isSelected()) {
				String s = valuesList.getFirst()[i] + " / " + valuesList.getLast()[i];
				int sHeight = stringHeight(FONT_AXIS_NUMBERS, s, frc);
				line.setFont(FONT_AXIS_NUMBERS);
				line.setColor(Colors.getBlackColor());
				line.drawString(s, positionList.getFirst()[i] + sHeight / 2, positionList.getLast()[i] - CIRCLE_D / 2 - sHeight);
			}
			
			line.setColor(graphColor);
			line.fillOval(positionList.getFirst()[i] - CIRCLE_D / 2, positionList.getLast()[i] - CIRCLE_D / 2, CIRCLE_D, CIRCLE_D);
		}
		g.setColor(Colors.getBlackColor());
	}
	
	private void drawVerticalColumns (Graphics g, FontRenderContext frc, Color graphColor){
		Graphics2D col = (Graphics2D) g;
		for (int i = 0; i < positionList.getFirst().length; i++){
			if (MainFrame.getPeeksValues().isSelected()) {
				String s = valuesList.getLast()[i] + "";
				int sWidth = stringWidth(FONT_AXIS_NUMBERS, s, frc);
				int sHeight = stringHeight(FONT_AXIS_NUMBERS, s, frc);
				col.setFont(FONT_AXIS_NUMBERS);
				col.drawString(s, positionList.getFirst()[i] - sWidth / 2, positionList.getLast()[i] - sHeight / 2);
			}
			
			int colWidth = (int) (PositionRulers.getMARGINS());
			int colHeight = r.positionAxisY(0) - positionList.getLast()[i];
			col.setColor(graphColor);
			col.fillRect(positionList.getFirst()[i] - colWidth / 2, positionList.getLast()[i],
					colWidth, colHeight);
			g.setColor(Colors.getBlackColor());
		}
	}
	
	private void drawHorizontalColumns (Graphics g, FontRenderContext frc, Color graphColor){
		Graphics2D col = (Graphics2D) g;
		for (int i = 0; i < positionList.getFirst().length; i++){
			if (MainFrame.getPeeksValues().isSelected()){
				String s = valuesList.getFirst()[i] + "";
				int sHeight = stringHeight(FONT_AXIS_NUMBERS, s, frc);
				col.setFont(FONT_AXIS_NUMBERS);
				col.drawString(s, positionList.getFirst()[i] + sHeight, positionList.getLast()[i] + sHeight / 4);
			}
			
			int colLength = positionList.getFirst()[i] - r.positionAxisX(0);
			int colWidth = (int) (PositionRulers.getMARGINS());
			col.setColor(graphColor);
			col.fillRect(r.positionAxisX(0), positionList.getLast()[i] - colWidth / 2,
					colLength, colWidth);
			g.setColor(Colors.getBlackColor());
		}
	}
	
	//Getters and Setters
	public static String getChartType() {
		return chartType;
	}

	public static void setChartType(String chartType) {
		MainPanel.chartType = chartType;
	}

	public static String getTitleGraph() {
		return titleGraph;
	}

	public static void setTitleGraph(String titleGraph) {
		MainPanel.titleGraph = titleGraph;
	}

	public static String getTitleX() {
		return titleX;
	}

	public static void setTitleX(String titleX) {
		MainPanel.titleX = titleX;
	}

	public static String getTitleY() {
		return titleY;
	}

	public static void setTitleY(String titleY) {
		MainPanel.titleY = titleY;
	}

	public static int getPeeksNumber() {
		return peeksNumber;
	}

	public static void setPeeksNumber(int peeksNumber) {
		MainPanel.peeksNumber = peeksNumber;
	}

	public static LinkedList<Integer> getList() {
		return list;
	}

	public static void setList(LinkedList<Integer> list) {
		MainPanel.list = list;
	}

	public static LinkedList<int[]> getValuesList() {
		return valuesList;
	}

	public static void setValuesList(LinkedList<int[]> valuesList) {
		MainPanel.valuesList = valuesList;
	}

	public static LinkedList<int[]> getPositionList() {
		return positionList;
	}

	public static void setPositionList(LinkedList<int[]> positionList) {
		MainPanel.positionList = positionList;
	}

	public static int getChartColor() {
		return chartColor;
	}

	public static void setChartColor(int chartColor) {
		MainPanel.chartColor = chartColor;
	}

	public static int getSmallestX() {
		return smallestX;
	}

	public static void setSmallestX(int smallestX) {
		MainPanel.smallestX = smallestX;
	}

	public static int getBiggestX() {
		return biggestX;
	}

	public static void setBiggestX(int biggestX) {
		MainPanel.biggestX = biggestX;
	}

	public static int getSmallestY() {
		return smallestY;
	}

	public static void setSmallestY(int smallestY) {
		MainPanel.smallestY = smallestY;
	}

	public static int getBiggestY() {
		return biggestY;
	}

	public static void setBiggestY(int biggestY) {
		MainPanel.biggestY = biggestY;
	}

	public static int getAxisXStartsWith() {
		return axisXStartsWith;
	}

	public static void setAxisXStartsWith(int axisXStartsWith) {
		MainPanel.axisXStartsWith = axisXStartsWith;
	}

	public static int getAxisXEndsWith() {
		return axisXEndsWith;
	}

	public static void setAxisXEndsWith(int axisXEndsWith) {
		MainPanel.axisXEndsWith = axisXEndsWith;
	}

	public static int getAxisXDivision() {
		return axisXDivision;
	}

	public static void setAxisXDivision(int axisXDivision) {
		MainPanel.axisXDivision = axisXDivision;
	}

	public static int getAxisYStartsWith() {
		return axisYStartsWith;
	}

	public static void setAxisYStartsWith(int axisYStartsWith) {
		MainPanel.axisYStartsWith = axisYStartsWith;
	}

	public static int getAxisYEndsWith() {
		return axisYEndsWith;
	}

	public static void setAxisYEndsWith(int axisYEndsWith) {
		MainPanel.axisYEndsWith = axisYEndsWith;
	}

	public static int getAxisYDivision() {
		return axisYDivision;
	}

	public static void setAxisYDivision(int axisYDivision) {
		MainPanel.axisYDivision = axisYDivision;
	}

	public static boolean isGraphIsExported() {
		return graphIsExported;
	}
	
	public static void setGraphIsExported(boolean graphIsExportedAsPDF) {
		MainPanel.graphIsExported = graphIsExportedAsPDF;
	}

}