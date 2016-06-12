package GraphGeneratorProject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import GraphGeneratorProject.Helpers.PositionRulers;

public class MainPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	
	private static final Font
							FONT_GRAPH_TITLE = new Font("Arial", Font.BOLD, 30),
							FONT_AXIS_TITLE = new Font("Arial", Font.BOLD, 12),
							FONT_AXIS_NUMBERS = new Font("Arial", Font.PLAIN, 10);
	private String titleGraph, titleX, titleY;
	private int peeksNumber, axisXStartsWith, axisXEndsWith, axisXDivision, axisYStartsWith, axisYEndsWith, axisYDivision,
				circleD = 10;
	private LinkedList<int[]> valuesList;
	private JLabel textField;
	private PositionRulers r;
	
	public MainPanel(){
		super();
		this.setBackground(Color.white);
		this.valuesList = new LinkedList<>();
		this.r = new PositionRulers();
	}
	
	@Override
	protected void paintComponent (Graphics g){
		super.paintComponent(g);
		FontRenderContext frc = new FontRenderContext(null, false, true);
		
		drawGraphTitle(g, FONT_GRAPH_TITLE, frc);
		
		drawLineResult(g);
		
		drawAxisX(g, 0, axisXStartsWith, axisXEndsWith, axisXDivision, FONT_AXIS_NUMBERS, FONT_AXIS_TITLE, frc);
		drawAxisY(g, 0, axisYStartsWith, axisYEndsWith, axisYDivision, FONT_AXIS_NUMBERS, FONT_AXIS_TITLE, frc);
	}
	
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
	
	private void drawGraphTitle (Graphics g, Font font, FontRenderContext frc){
		Rectangle2D r2D = font.getStringBounds(titleGraph, frc);
		int sWidth = (int) Math.round(r2D.getWidth());
		g.setFont(font);
		g.drawString(titleGraph, (int) ((r.positionAxisX(0) + r.getGRAPH_END_X()) / 2 - sWidth / 2), (int) r.getAXISES_MARGINS());
	}
	
	private void drawAxisX (Graphics g, int axisZero, int startsWith, int endsWith, int axisDiv,
							Font font, Font titleFont, FontRenderContext frc){
		int x = r.positionAxisX(axisZero);
		
		for (int i = startsWith; i <= endsWith;){
			String s = i + "";
			int sWidth = stringWidth(font, s, frc);
			int sHeight = stringHeight(font, s, frc);getInsets();
			
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
        g2D.drawString(titleY, (int) (- r.positionAxisY(axisZero) + (r.positionAxisY(axisZero) - r.getGRAPH_END_Y()) / 2) - width / 2,
        		(int) (r.positionAxisX(- 3 * height) - sWidth));
	}
	
	private void drawLineResult (Graphics g){
		Graphics2D line = (Graphics2D) g;
		line.setColor(Color.RED);
		line.drawPolyline(valuesList.getFirst(), valuesList.getLast(), peeksNumber);
		for (int i = 0; i < valuesList.getFirst().length; i++){
			line.fillOval(valuesList.getFirst()[i] - circleD / 2, valuesList.getLast()[i] - circleD / 2, circleD, circleD);
		}
		g.setColor(Color.BLACK);
	}
	
	public JLabel getTextField() {
		return textField;
	}

	public String getTitleGraph() {
		return titleGraph;
	}

	public void setTitleGraph(String titleGraph) {
		this.titleGraph = titleGraph;
	}

	public String getTitleX() {
		return titleX;
	}

	public void setTitleX(String titleX) {
		this.titleX = titleX;
	}

	public String getTitleY() {
		return titleY;
	}

	public void setTitleY(String titleY) {
		this.titleY = titleY;
	}

	public int getPeeksNumber() {
		return peeksNumber;
	}

	public void setPeeksNumber(int peeksNumber) {
		this.peeksNumber = peeksNumber;
	}

	public LinkedList<int[]> getValuesList() {
		return valuesList;
	}

	public void setValuesList(LinkedList<int[]> valuesList) {
		this.valuesList = valuesList;
	}

	public int getAxisXEndsWith() {
		return axisXEndsWith;
	}

	public void setAxisXEndsWith(int axisXEndsWith) {
		this.axisXEndsWith = axisXEndsWith;
	}

	public int getAxisXStartsWith() {
		return axisXStartsWith;
	}

	public void setAxisXStartsWith(int smallestPeekX) {
		this.axisXStartsWith = smallestPeekX;
	}

	public int getAxisXDivision() {
		return axisXDivision;
	}

	public void setAxisXDivision(int axisXDivision) {
		this.axisXDivision = axisXDivision;
	}

	public int getAxisYStartsWith() {
		return axisYStartsWith;
	}

	public void setAxisYStartsWith(int smallestPeekY) {
		this.axisYStartsWith = smallestPeekY;
	}
	
	public int getAxisYEndsWith() {
		return axisYEndsWith;
	}

	public void setAxisYEndsWith(int axisYEndsWith) {
		this.axisYEndsWith = axisYEndsWith;
	}

	public int getAxisYDivision() {
		return axisYDivision;
	}

	public void setAxisYDivision(int axisYDivision) {
		this.axisYDivision = axisYDivision;
	}
	
}