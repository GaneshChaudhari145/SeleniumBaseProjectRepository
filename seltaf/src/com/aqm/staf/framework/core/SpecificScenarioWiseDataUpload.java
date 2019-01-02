package com.aqm.staf.framework.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;


public class SpecificScenarioWiseDataUpload {

	
	private Properties prop;
	private InputStream input;
	private Properties tableConfigProp;
	private InputStream tableConfigInput;
	
	public SpecificScenarioWiseDataUpload() throws IOException {
		loadProperties();
		
	}
	
	
	private void loadProperties() throws IOException {
		prop = new Properties();
		input = new FileInputStream("config.properties");
		prop.load(input);

		tableConfigProp = new Properties();
		tableConfigInput = new FileInputStream("tableconfig.properties");
		tableConfigProp.load(tableConfigInput);
	}
	

	public void specificUploadFileInDB(String tableUploadTableNameInDB,
			XSSFSheet dataWorksheet, Connection dataDBConnectionObject) {
		
		ScenarioUnderExecution sue=new ScenarioUnderExecution();
		
		
		try {
			Iterator<Row> rows = dataWorksheet.rowIterator();
			Row columnHeaderRow;
			Row columnTypesRow;

			if (rows.hasNext()) {
				columnHeaderRow = rows.next();
			} else
				throw new RuntimeException(
						"Header Row Not Found. Please check the datasheet - "
								+ dataWorksheet.getSheetName());

			if (rows.hasNext()) {
				columnTypesRow = rows.next();
			} else
				throw new RuntimeException(
						"Column Type Row Not Found. Please check the datasheet - "
								+ dataWorksheet.getSheetName());

			Iterator<Cell> columnTitles = columnHeaderRow.cellIterator();
			Iterator<Cell> columnTypes = columnTypesRow.cellIterator();

			String columnNameString = "";
			String columnValueString = "";

			while (rows.hasNext()) {
				Row currentRow = rows.next();
				columnNameString = "";
				columnValueString = "";

				if (sue.listOfTestScenariosUnderExecution
						.contains((currentRow.getCell(1)
								.getStringCellValue()))) {
					
				for (int i = 0; i < columnHeaderRow.getPhysicalNumberOfCells(); i++) {

						Cell currentCell = currentRow.getCell(i);
						columnNameString = columnNameString
								+ columnHeaderRow.getCell(i);

						if (currentCell != null) {
							if (columnTypesRow.getCell(i).getStringCellValue()
									.equalsIgnoreCase("integer")
									|| columnTypesRow.getCell(i)
											.getStringCellValue()
											.equalsIgnoreCase("int")
									|| columnTypesRow.getCell(i)
											.getStringCellValue().toLowerCase()
											.startsWith("number")
									|| columnTypesRow.getCell(i)
											.getStringCellValue().toLowerCase()
											.startsWith("long")
									|| columnTypesRow.getCell(i)
											.getStringCellValue().toLowerCase()
											.startsWith("short")
									|| columnTypesRow.getCell(i)
											.getStringCellValue()
											.equalsIgnoreCase("bit")) {
								double tmpValue;
								try {
									tmpValue = currentCell
											.getNumericCellValue();
								} catch (Exception e) {
									tmpValue = 0.0;
								}
								columnValueString = columnValueString
										+ Integer.toString((int) tmpValue);
							} else if (columnTypesRow.getCell(i)
									.getStringCellValue().toLowerCase()
									.startsWith("float")
									|| columnTypesRow.getCell(i)
											.getStringCellValue().toLowerCase()
											.startsWith("double")) {
								double tmpValue;
								try {
									tmpValue = currentCell
											.getNumericCellValue();
								} catch (Exception e) {
									tmpValue = 0.0;
								}
								columnValueString = columnValueString
										+ Double.toString(tmpValue);
							}

							else if (columnTypesRow.getCell(i)
									.getStringCellValue().toLowerCase()
									.startsWith("varchar")
									|| columnTypesRow.getCell(i)
											.getStringCellValue().toLowerCase()
											.startsWith("varchar2")
									|| columnTypesRow.getCell(i)
											.getStringCellValue().toLowerCase()
											.startsWith("text")
									|| columnTypesRow.getCell(i)
											.getStringCellValue().toLowerCase()
											.startsWith("date")
									|| columnTypesRow.getCell(i)
											.getStringCellValue().toLowerCase()
											.startsWith("time")
									|| columnTypesRow.getCell(i)
											.getStringCellValue().toLowerCase()
											.startsWith("timestamp")
									|| columnTypesRow.getCell(i)
											.getStringCellValue().toLowerCase()
											.startsWith("char")) {
								int originalCellType = currentCell
										.getCellType();
								currentCell.setCellType(Cell.CELL_TYPE_STRING);
								String tmpValue = currentCell
										.getStringCellValue();
								if (tmpValue == null) {
									tmpValue = "";
								}

								columnValueString = columnValueString + "'"
										+ tmpValue + "'";
								currentCell.setCellType(originalCellType);
							}
						} else
							columnValueString = columnValueString + "null";

						if (i < columnHeaderRow.getPhysicalNumberOfCells() - 1) {
							columnNameString = columnNameString + ", ";
							columnValueString = columnValueString + ", ";
						}
				}
						try {
							String insertStatement = "insert into "
									+ tableUploadTableNameInDB + " ("
									+ columnNameString + ") values ("
									+ columnValueString + ")";
							// System.out.println(insertStatement);
							Statement statement = dataDBConnectionObject
									.createStatement();
							statement.executeUpdate(insertStatement);
						} catch (SQLException sqle) {
							sqle.printStackTrace();
						}
					}
				else {
						continue;
					}

				}

			

		} catch (Exception e) {
			System.out.println("e = " + e);
		}
	}

}
