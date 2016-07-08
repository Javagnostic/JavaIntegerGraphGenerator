package GraphGeneratorProject.Helpers;

public class Strings {
	
	private static final String
		FRAME_TITLE = "iGraph Generator",
		MENU_IGRAPH = "iGraph",
		SUBMENU_EXPORT = "Export iGraph As",
		MENU_VIEW = "View",
		MENU_ABOUT = "About",
		MENUITEM_CREATE_IGRAPH = "Create iGraph",
		MENUITEM_EDIT_IGRAPH = "Edit iGraph",
		MENUITEM_IMPORT_IGRAPH = "Import iGraph Data",
		MENUITEM_EXPORT_AS_PDF = "PDF",
		MENUITEM_EXPORT_AS_TXT = "TXT Data",
		MENUITEM_EXIT = "Exit",
		MENUITEM_PEEKS_VALUE = "Peeks Values",
		MENUITEM_ABOUT = "About " + FRAME_TITLE,
		ABOUT_DIALOG_TITLE = MENUITEM_ABOUT,
		ABOUT_TITLE = "Integer Graph Generator",
		ABOUT_TEXT = "Create and edit your integer graphs.\n"
				+ "Author: Svetlin Burgov\n"
				+ "e-mail: info@standart.xyz",
		DIALOG_TITLE = "Create New iGraph",
		DIALOG_EDIT_GRAPH_TITLE = "Edit iGraph",
		DIALOG_IMPORT_GRAPH_TITLE = "Import iGraph",
		ATTR_GRAPH_TITLE = "iGraph Title",
		ATTR_X_TEXT = "Axis X Title",
		ATTR_Y_TEXT = "Axis Y Title",
		NUMBER_NODES_TEXT = "Number of Peeks",
		CHART_TYPE = "Chart Type",
		COLOR_CHOOSER_TITLE = "Choose iGraph Color",
		VALUE_X_TOOLTIP_TEXT = "X value",
		VALUE_Y_TOOLTIP_TEXT = "Y value",
		AXIS_X_MIN_TITLE = "X axis MIN Value",
		AXIS_X_MAX_TITLE = "X axis MAX Value",
		AXIS_X_DIV_TITLE = "X axis divisions",
		AXIS_Y_MIN_TITLE = "Y axis MIN Value",
		AXIS_Y_MAX_TITLE = "Y axis MAX Value",
		AXIS_Y_DIV_TITLE = "Y axis divisions",
		VALUE_FIELD_TEXT = "Peek ",
		AXIS_STEP = "Axis delimiter step: ",
		IMPORT_GRAPH_TITLE = "Open file",
		EXPORT_GRAPH_TXT_TITLE = "Export iGraph data as TXT",
		EXPORT_GRAPH_PDF_TITLE = "Export iGraph as PDF",
		FILE_TYPE_TXT = ".txt",
		FILE_TYPE_PDF = ".pdf",
		NEW_LINE = "\n",
		CSS = "<span style = \"color: black; font-weight: bold;\">",
		ERROR_MESSAGE_ATTR = "No values entered for iGraph or Axis title(s)!",
		ERROR_MESSAGE_VALUES = "Only positive integers allowed for Peeks values!",
		ERROR_MESSAGE_EXTRA_ATTR = "Axis MIN value must be smaller than MAX one! Positive integers.",
		ERROR_MESSAGE_X_MIN_ATTR = "Axis X MIN value must be smaller or equal to MIN X peek value.",
		ERROR_MESSAGE_X_MAX_ATTR = "Axis X MAX value must be bigger or equal to MAX X peek value.",
		ERROR_MESSAGE_Y_MIN_ATTR = "Axis Y MIN value must be smaller or equal to MIN Y peek value.",
		ERROR_MESSAGE_Y_MAX_ATTR = "Axis Y MAX value must be bigger or equal to MAX Y peek value.",
		ERROR_MESSAGE_X_MIN_ATTR_COL = "Axis X MIN value must be smaller than MIN X peek value.",
		ERROR_MESSAGE_X_MAX_ATTR_COL = "Axis X MAX value must be bigger than MAX X peek value.",
		ERROR_MESSAGE_Y_MIN_ATTR_COL = "Axis Y MIN value must be smaller than MIN Y peek value.",
		ERROR_MESSAGE_Y_MAX_ATTR_COL = "Axis Y MAX value must be bigger than MAX Y peek value.",
		ERROR_MESSAGE_DIV_X = "Invalid X axis divisions! The axis should be divisible by integers.",
		ERROR_MESSAGE_DIV_Y = "Invalid Y axis divisions! The axis should be divisible by integers.",
		NOT_EXPORTED_GRAPH_TITLE = "Warning!",
		NOT_EXPORTED_GRAPH_MESSAGE = "The generated iGraph is not exported.\n"
				+ "Continue WITHOUT exporting?",
		EXPORTED_GRAPH_TITLE = "Info",
		EXPORTED_GRAPH_MESSAGE_PDF = "The generated iGraph is exported to .pdf file.",
		EXPORTED_GRAPH_MESSAGE_TXT = "The generated iGraph data is exported to .txt file",
		EXCEPTION_MESSAGE_TITLE = "Wrong input",
		EXCEPTION_MESSAGE = "Invalid file data or wrong .txt file.";

	public static String getFrameTitle() {
		return FRAME_TITLE;
	}

	public static String getMenuIgraph() {
		return MENU_IGRAPH;
	}

	public static String getSubmenuExport() {
		return SUBMENU_EXPORT;
	}

	public static String getMenuView() {
		return MENU_VIEW;
	}

	public static String getMenuAbout() {
		return MENU_ABOUT;
	}

	public static String getMenuitemCreateIgraph() {
		return MENUITEM_CREATE_IGRAPH;
	}

	public static String getMenuitemEditIgraph() {
		return MENUITEM_EDIT_IGRAPH;
	}

	public static String getMenuitemImportIgraph() {
		return MENUITEM_IMPORT_IGRAPH;
	}

	public static String getMenuitemExportAsPdf() {
		return MENUITEM_EXPORT_AS_PDF;
	}

	public static String getMenuitemExportAsTxt() {
		return MENUITEM_EXPORT_AS_TXT;
	}

	public static String getMenuitemExit() {
		return MENUITEM_EXIT;
	}

	public static String getMenuitemPeeksValue() {
		return MENUITEM_PEEKS_VALUE;
	}

	public static String getMenuitemAbout() {
		return MENUITEM_ABOUT;
	}

	public static String getAboutDialogTitle() {
		return ABOUT_DIALOG_TITLE;
	}

	public static String getAboutTitle() {
		return ABOUT_TITLE;
	}

	public static String getAboutText() {
		return ABOUT_TEXT;
	}

	public static String getDialogTitle() {
		return DIALOG_TITLE;
	}

	public static String getDialogEditGraphTitle() {
		return DIALOG_EDIT_GRAPH_TITLE;
	}

	public static String getDialogImportGraphTitle() {
		return DIALOG_IMPORT_GRAPH_TITLE;
	}

	public static String getAttrGraphTitle() {
		return ATTR_GRAPH_TITLE;
	}

	public static String getAttrXText() {
		return ATTR_X_TEXT;
	}

	public static String getAttrYText() {
		return ATTR_Y_TEXT;
	}

	public static String getNumberNodesText() {
		return NUMBER_NODES_TEXT;
	}

	public static String getChartType() {
		return CHART_TYPE;
	}

	public static String getColorChooserTitle() {
		return COLOR_CHOOSER_TITLE;
	}

	public static String getValueXTooltipText() {
		return VALUE_X_TOOLTIP_TEXT;
	}

	public static String getValueYTooltipText() {
		return VALUE_Y_TOOLTIP_TEXT;
	}

	public static String getAxisXMinTitle() {
		return AXIS_X_MIN_TITLE;
	}

	public static String getAxisXMaxTitle() {
		return AXIS_X_MAX_TITLE;
	}

	public static String getAxisXDivTitle() {
		return AXIS_X_DIV_TITLE;
	}

	public static String getAxisYMinTitle() {
		return AXIS_Y_MIN_TITLE;
	}

	public static String getAxisYMaxTitle() {
		return AXIS_Y_MAX_TITLE;
	}

	public static String getAxisYDivTitle() {
		return AXIS_Y_DIV_TITLE;
	}

	public static String getValueFieldText() {
		return VALUE_FIELD_TEXT;
	}

	public static String getAxisStep() {
		return AXIS_STEP;
	}

	public static String getImportGraphTitle() {
		return IMPORT_GRAPH_TITLE;
	}

	public static String getExportGraphTxtTitle() {
		return EXPORT_GRAPH_TXT_TITLE;
	}

	public static String getExportGraphPdfTitle() {
		return EXPORT_GRAPH_PDF_TITLE;
	}

	public static String getFileTypeTxt() {
		return FILE_TYPE_TXT;
	}

	public static String getFileTypePdf() {
		return FILE_TYPE_PDF;
	}

	public static String getNewLine() {
		return NEW_LINE;
	}

	public static String getCss() {
		return CSS;
	}

	public static String getErrorMessageAttr() {
		return ERROR_MESSAGE_ATTR;
	}

	public static String getErrorMessageValues() {
		return ERROR_MESSAGE_VALUES;
	}

	public static String getErrorMessageExtraAttr() {
		return ERROR_MESSAGE_EXTRA_ATTR;
	}

	public static String getErrorMessageXMinAttr() {
		return ERROR_MESSAGE_X_MIN_ATTR;
	}

	public static String getErrorMessageXMaxAttr() {
		return ERROR_MESSAGE_X_MAX_ATTR;
	}

	public static String getErrorMessageYMinAttr() {
		return ERROR_MESSAGE_Y_MIN_ATTR;
	}

	public static String getErrorMessageYMaxAttr() {
		return ERROR_MESSAGE_Y_MAX_ATTR;
	}

	public static String getErrorMessageXMinAttrCol() {
		return ERROR_MESSAGE_X_MIN_ATTR_COL;
	}

	public static String getErrorMessageXMaxAttrCol() {
		return ERROR_MESSAGE_X_MAX_ATTR_COL;
	}

	public static String getErrorMessageYMinAttrCol() {
		return ERROR_MESSAGE_Y_MIN_ATTR_COL;
	}

	public static String getErrorMessageYMaxAttrCol() {
		return ERROR_MESSAGE_Y_MAX_ATTR_COL;
	}

	public static String getErrorMessageDivX() {
		return ERROR_MESSAGE_DIV_X;
	}

	public static String getErrorMessageDivY() {
		return ERROR_MESSAGE_DIV_Y;
	}

	public static String getNotExportedGraphTitle() {
		return NOT_EXPORTED_GRAPH_TITLE;
	}

	public static String getNotExportedGraphMessage() {
		return NOT_EXPORTED_GRAPH_MESSAGE;
	}

	public static String getExportedGraphTitle() {
		return EXPORTED_GRAPH_TITLE;
	}

	public static String getExportedGraphMessagePdf() {
		return EXPORTED_GRAPH_MESSAGE_PDF;
	}

	public static String getExportedGraphMessageTxt() {
		return EXPORTED_GRAPH_MESSAGE_TXT;
	}

	public static String getExceptionMessageTitle() {
		return EXCEPTION_MESSAGE_TITLE;
	}

	public static String getExceptionMessage() {
		return EXCEPTION_MESSAGE;
	}
	
}