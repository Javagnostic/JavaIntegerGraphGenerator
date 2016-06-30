package GraphGeneratorProject.Helpers;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import GraphGeneratorProject.GraphGeneratorExec;
import GraphGeneratorProject.MenuListeners.AboutListener;
import GraphGeneratorProject.MenuListeners.ImportGraphDataListener;
import GraphGeneratorProject.MenuListeners.NewGraphListener;

public class ExtraListeners {
	
	public class AttrFocusListener implements FocusListener{

		@Override
		public void focusGained(FocusEvent e) {
			Component c = e.getComponent();
			if (c instanceof JTextField){
				((JTextField) c).selectAll();
			}
			if (c instanceof JFormattedTextField) {
				((JFormattedTextField) c).setText(((JFormattedTextField) c).getText());
				((JFormattedTextField) c).selectAll();
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			
		}

	}
	
	public class AttrMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			Component c = e.getComponent();
			if (c instanceof JTextField){
				c.requestFocusInWindow();
				((JTextField) c).selectAll();
			}
			if (c instanceof JFormattedTextField){
				c.requestFocusInWindow();
				((JFormattedTextField) c).setText(((JFormattedTextField) c).getText());
				((JFormattedTextField) c).selectAll();
			}
		}
		
	}

	public class CloseDialogListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			NewGraphListener.getNewGraphDialog().getErrorMessage().setText("");
			NewGraphListener.getNewGraphDialog().setVisible(false);
		}
		
	}
	
	public class CloseAboutListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			AboutListener.getDialogAbout().dispose();
			AboutListener.getDialogAbout().setVisible(false);
		}
		
	}
	
	public static void chooseColorListener (JButton attr, JColorChooser colorChooser){
		attr.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LookAndFeelManager.systemLookAndFeel(true);
				Color newColor = JColorChooser.showDialog(
						GraphGeneratorExec.getFrame(), Strings.getColorChooserTitle(), attr.getBackground());
				if (newColor != null) {
					if (newColor.getAlpha() < 255){
						newColor = new Color(newColor.getRed(), newColor.getGreen(), newColor.getBlue(), 255);
					}
					attr.setBackground(newColor);
				}
				LookAndFeelManager.systemLookAndFeel(false);
			}
		});
	}

	public static void valueFieldAddMouseListener(JTextField valueField, int i, String axis){
		valueField.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				NewGraphListener.getNewGraphDialog().validateField(valueField, i, axis);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				NewGraphListener.getNewGraphDialog().valueFieldMouseExited(valueField, axis);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				NewGraphListener.getNewGraphDialog().valueFieldMouseEntered(valueField, axis);
				NewGraphListener.getNewGraphDialog().validateField(valueField, i, axis);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	public static void valueFieldAddFocusListener(JTextField valueField, int i, String axis){
		valueField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				NewGraphListener.getNewGraphDialog().valueFieldMouseExited(valueField, axis);
				NewGraphListener.getNewGraphDialog().validateField(valueField, i, axis);
				NewGraphListener.getNewGraphDialog().attrSetValue(valueField);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				NewGraphListener.getNewGraphDialog().valueFieldMouseEntered(valueField, axis);
				NewGraphListener.getNewGraphDialog().validateField(valueField, i, axis);
			}
		});
	}
	
	public static void valueFieldAddDocumentListener(JTextField valueField, Color color){
		valueField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				valueField.setForeground(color);
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				valueField.setForeground(color);
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				valueField.setForeground(color);
				
			}
		});
	}
	
	public static void axisMinMaxSpinnerAddChangeListener(JSpinner spinner, int min, int max) {
		spinner.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if ((int) spinner.getValue() < min ){
					spinner.setValue(min);
				}
				if ((int) spinner.getValue() > max ){
					spinner.setValue(max);
				}
			}
		});
	}

	public static void axisMinMaxAddDocumentListener(JComponent attr, JFormattedTextField ftf, JLabel axisStep){
		if (attr instanceof JTextField) {
			((JTextField) attr).getDocument().addDocumentListener(new DocumentListener() {
				
				@Override
				public void removeUpdate(DocumentEvent e) {
					NewGraphListener.getNewGraphDialog().divValues();
					NewGraphListener.getNewGraphDialog().divLengthMessage(attr, axisStep);
				}
				
				@Override
				public void insertUpdate(DocumentEvent e) {
					NewGraphListener.getNewGraphDialog().divValues();
					NewGraphListener.getNewGraphDialog().divLengthMessage(attr, axisStep);
				}
				
				@Override
				public void changedUpdate(DocumentEvent e) {
					NewGraphListener.getNewGraphDialog().divValues();
					NewGraphListener.getNewGraphDialog().divLengthMessage(attr, axisStep);
				}
			});
		}
		if (attr instanceof JSpinner) {
			ftf.getDocument().addDocumentListener(new DocumentListener() {
				
				@Override
				public void removeUpdate(DocumentEvent e) {
					if (NewGraphListener.getNewGraphDialog().validateMinMaxValue()
							|| ImportGraphDataListener.isImported()){
						NewGraphListener.getNewGraphDialog().divLengthMessage(attr, axisStep);
					}
				}
				
				@Override
				public void insertUpdate(DocumentEvent e) {
					if (NewGraphListener.getNewGraphDialog().validateMinMaxValue()
							|| ImportGraphDataListener.isImported()){
						NewGraphListener.getNewGraphDialog().divLengthMessage(attr, axisStep);
					}
				}
				
				@Override
				public void changedUpdate(DocumentEvent e) {
					if (NewGraphListener.getNewGraphDialog().validateMinMaxValue()
							|| ImportGraphDataListener.isImported()){
						NewGraphListener.getNewGraphDialog().divLengthMessage(attr, axisStep);
					}
				}
			});
		}
	}
	
}