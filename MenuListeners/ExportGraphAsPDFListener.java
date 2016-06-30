package GraphGeneratorProject.MenuListeners;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.awt.FontMapper;
import com.itextpdf.awt.PdfGraphics2D;
import com.itextpdf.text.Document;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import GraphGeneratorProject.GraphGeneratorExec;
import GraphGeneratorProject.MainFrame;
import GraphGeneratorProject.MainPanel;
import GraphGeneratorProject.Helpers.LookAndFeelManager;
import GraphGeneratorProject.Helpers.Strings;

public class ExportGraphAsPDFListener implements ActionListener {

	private static final String
		TITLE = Strings.getExportGraphPdfTitle(),
		FILE_TYPE = Strings.getFileTypePdf();
	private Document document;
	private JFileChooser chooseFile;
	private Preferences prefs;
	private FontMapper fontMapper;
	
	public ExportGraphAsPDFListener() {
		this.document = new Document();
		this.document.setPageSize(new Rectangle(MainFrame.getFRAME_WIDTH(), MainFrame.getFRAME_HEIGHT()));
		this.prefs = Preferences.userRoot().node(getClass().getName());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		LookAndFeelManager.systemLookAndFeel(true);
		this.chooseFile = new JFileChooser(prefs.get("DEFAULT_PATH", new File(".").getAbsolutePath()));
		this.chooseFile.setAcceptAllFileFilterUsed(false);
		this.chooseFile.setFileFilter(new FileNameExtensionFilter(FILE_TYPE, FILE_TYPE.substring(1)));
		exportPDF();
	}
	
	private void exportPDF() {
		chooseFile.setDialogTitle(TITLE);
		chooseFile.setSelectedFile(new File(""));
		int actionDialog = chooseFile.showSaveDialog(GraphGeneratorExec.getFrame());
		if (actionDialog == JFileChooser.APPROVE_OPTION) {
			checkIfFileExists(actionDialog);
		}
		else {
			LookAndFeelManager.systemLookAndFeel(false);
			return;
		}
	}
	
	private void checkIfFileExists(int actionDialog) {
		File file = chooseFile.getSelectedFile();
		String fileNameWithExtension = file.getAbsolutePath();
		if (!fileNameWithExtension.endsWith(FILE_TYPE)) {
			file = new File(fileNameWithExtension + FILE_TYPE);
		}
		prefs.put("DEFAULT_PATH", file.getParent());
		
		if (file.exists()) {
			actionDialog = JOptionPane.showConfirmDialog(chooseFile, "Replace existing file?", "Choose",
					JOptionPane.YES_NO_OPTION);
			if (actionDialog == JOptionPane.NO_OPTION) {
				exportPDF();
				return;
			}
			else {
				file.delete();
			}
		}
		LookAndFeelManager.systemLookAndFeel(false);
		
		PdfWriter pdfWriter = null;
		try {
			pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
			
			GraphGeneratorExec gge = new GraphGeneratorExec();
			BaseFont fontRegular = BaseFont.createFont(gge.getClass().getResource(
					"Resources/Fonts/arialr.ttf").toString(), "Cp1251", BaseFont.EMBEDDED);
			BaseFont fontBold = BaseFont.createFont(gge.getClass().getResource(
					"Resources/Fonts/arialb.ttf").toString(), "Cp1251", BaseFont.EMBEDDED);
			
			fontMapper = new FontMapper() {
				
				@Override
				public java.awt.Font pdfToAwt(BaseFont arg0, int arg1) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public BaseFont awtToPdf(java.awt.Font font) {
					if (font.getName().equals("sanserif")){
						return fontRegular;
					}
					else {
						return fontBold;
					}
				}
			};
			PdfContentByte cb = pdfWriter.getDirectContent();
			PdfTemplate template = cb.createTemplate(MainFrame.getFRAME_WIDTH(),
					MainFrame.getFRAME_HEIGHT());
			Graphics2D g2D = new PdfGraphics2D(template, MainFrame.getFRAME_WIDTH(), MainFrame.getFRAME_HEIGHT(), fontMapper);
			MainFrame.getPanel().print(g2D);
			g2D.dispose();
			cb.addTemplate(template, 0, 0);
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
		finally {
			document.close();
		}
		MainPanel.setGraphIsExported(true);
		LookAndFeelManager.systemLookAndFeel(true);
		JOptionPane.showMessageDialog(
				GraphGeneratorExec.getFrame(),
				Strings.getExportedGraphMessagePdf(),
				Strings.getExportedGraphTitle(),
				JOptionPane.INFORMATION_MESSAGE);
		LookAndFeelManager.systemLookAndFeel(false);
	}
	
}