package com.aqm.staf.framework.core.scenarioResult;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;

public class WorkbookBuilder {

	private SXSSFWorkbook workbook;

	public Boolean mkXlsx(String workbookName, String filepath,
			String[] headerString, StringBuilder[][] table) {

		try {
			workbook = new SXSSFWorkbook(100);
			Sheet sheet = workbook.createSheet();
			workbook.setSheetName(0, workbookName);

			/* Header Style- Start */

			XSSFCellStyle headerStyle = (XSSFCellStyle) workbook
					.createCellStyle();
			XSSFFont headerLineFont = (XSSFFont) workbook.createFont();
			/*
			 * below lines to change font
			 * headerLineFont.setFontName("Maiandra GD");
			 */

			headerLineFont.setFontHeight(12);
			XSSFColor headerFontColor = new XSSFColor(Color.WHITE);
			headerLineFont.setColor(headerFontColor);
			headerLineFont.setBold(true);
			headerStyle.setFont(headerLineFont);
			byte[] color = { (byte) 79, (byte) 129, (byte) 189 };
			XSSFColor headerFillColor = new XSSFColor(color);
			headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			headerStyle.setFillForegroundColor(headerFillColor);
			headerStyle.setBorderBottom((short) 1);
			headerStyle.setBorderTop((short) 1);
			headerStyle.setBorderRight((short) 1);
			headerStyle.setBorderLeft((short) 1);

			/* Header Style- End */

			/* Table Style- Start */

			XSSFCellStyle tableStyle = (XSSFCellStyle) workbook
					.createCellStyle();
			tableStyle.setBorderBottom((short) 1);
			tableStyle.setBorderTop((short) 1);
			tableStyle.setBorderRight((short) 1);
			tableStyle.setBorderLeft((short) 1);

			/* Table Style- End */

			Row headerRow = sheet.createRow(0);

			for (int cellnum = 0; cellnum < headerString.length; cellnum++) {
				Cell cell = headerRow.createCell(cellnum);
				cell.setCellStyle(headerStyle);
				cell.setCellValue(headerString[cellnum]);

			}

			int rownum = 1;
			ArrayList<String> allLobNames = new ArrayList<String>();
			for (StringBuilder[] sb : table) {
				Row row = sheet.createRow(rownum);

				int cellnum = 0;
				int columnOfLobName = 3;

				int iterationCounter = 0;
				for (StringBuilder s : sb) {
					Cell cell = row.createCell(cellnum);
					cell.setCellStyle(tableStyle);
					cell.setCellValue(s.toString());
					if (iterationCounter == columnOfLobName) {
						allLobNames.add(s.toString());
					}
					cellnum++;
					iterationCounter++;
				}

				rownum++;
			}

			for (int cellnum = 0; cellnum < headerString.length; cellnum++) {
				sheet.autoSizeColumn(cellnum);
				int currentWidth = sheet.getColumnWidth(cellnum);
				double desiredWidth = (headerString[cellnum].length() + 5) * 256;
				int desiredWidthTruncted = (int) desiredWidth;
				if (currentWidth < desiredWidthTruncted) {
					sheet.setColumnWidth(cellnum, desiredWidthTruncted);
				}

			}

			CellRangeAddress cra = new CellRangeAddress(0, rownum - 1, 0,
					headerString.length - 1);
			sheet.setAutoFilter(cra);

			FileOutputStream out;

			String timeNow = DateFormatUtils.format(new Date().getTime(),
					"dd-MM-yyyy HH-mm-ss");
			timeNow = "_" + timeNow;
			Set<String> uniqueLobName = new TreeSet();
			uniqueLobName.addAll(allLobNames);
			String lobName = "";
			for (String lob : uniqueLobName) {
				lobName = lobName + "_" + lob;
			}
			workbookName = workbookName + timeNow + lobName;
			String compliantPath = filepath + "\\" + workbookName + ".xlsx";

			out = new FileOutputStream(compliantPath);
			workbook.write(out);
			out.close();
			workbook.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;

	}

}
