package GraphGeneratorProject.Helpers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

import GraphGeneratorProject.MenuListeners.AboutListener;
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
			// TODO Auto-generated method stub
			
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

}