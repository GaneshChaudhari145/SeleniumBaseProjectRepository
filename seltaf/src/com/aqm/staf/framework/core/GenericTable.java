package com.aqm.staf.framework.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GenericTable extends BasePage{
	String tableColumnHeaderRow;
	HashMap<Integer, String> columnHeaderIndex=new HashMap<Integer, String>();
	List<HashMap<Integer,String>> tableRows;
	String tablePath;
	String tableColumnHeaderData;
	public HashMap<Integer, String> visibleColumnIndexNames = new HashMap<Integer, String>();
	public HashMap<String, Integer> visibleColumnNamesIndex = new HashMap<String, Integer>();
	public HashMap<Integer, String> columnIndexNames = new HashMap<Integer, String>();
	private HashMap<String, Integer> columnNamesIndex = new HashMap<String, Integer>();
	public ArrayList<HashMap<String, String>> allRows;
	private ArrayList<Integer> rowVisibleHiddenMapper;

	public GenericTable(String tablePath, WebDriver driver) {
		//table Path=//table[3]//table
		super(driver, tablePath);
		this.driver=driver;
		this.tablePath=tablePath;
		this.tableColumnHeaderRow=tablePath+"//tr[1]";
		this.tableColumnHeaderData=tableColumnHeaderRow+"//td";
		
		getTableHeaders();
	}
	public void getTableHeaders(){
		
		int columnIndex=0;
		int visibleColumnIndex=0;
		String columnName;
		WebElement ColumnHeader=driver.findElement(By.xpath(tableColumnHeaderRow));
		List<WebElement> columnHead=ColumnHeader.findElements(By.xpath(tableColumnHeaderData));
		System.out.println(columnHead.size());
		for(WebElement column:columnHead){
			columnIndex++;
			if(columnIndex==1 && column.getText().equalsIgnoreCase("")){
				columnName="SelectRow";
			}
			else{
				WebElement columnHeadElement;
				columnHeadElement=column.findElement(By.xpath(".//b"));
				columnName=columnHeadElement.getText();
			}
			visibleColumnIndex++;
			columnHeaderIndex.put(columnIndex, columnName);
			visibleColumnIndexNames.put(visibleColumnIndex, columnName);
			visibleColumnNamesIndex.put(columnName, visibleColumnIndex);
			columnNamesIndex.put(columnName, columnIndex);
		}
	}
public void getTableRows(){


	rowVisibleHiddenMapper = new ArrayList<Integer>();

		allRows = new ArrayList<HashMap<String, String>>();
		ArrayList<Integer> columnIndexes = new ArrayList<Integer>(columnHeaderIndex.keySet());
		/*
}

/*
	public void getRows(String tablePath ) throws Throwable {
	List<WebElement> allWebElemnet=driver.findElements()
		for(int i=2)
	 *//** WebElement dataTable = panel.findElement(By.xpath(gridlocator +
	 * "/div[3]/div/table/tbody"));




			WebElement dataTable = getWebElement(datatable());
			allRows = new ArrayList<HashMap<String, String>>();
			ArrayList<Integer> columnIndexes = new ArrayList<>(columnIndex_Names.keySet());
			List<WebElement> Rows = dataTable.findElements(By.tagName("tr"));
			for (int j = 0; j < Rows.size(); j++) {
				if (Rows.get(j).isDisplayed()) {
					List<WebElement> cells = Rows.get(j).findElements(By.tagName("td"));
					HashMap<String, String> row = new HashMap<String, String>();
					for (int i = 0; i < columnIndexes.size(); i++) {
						row.put(columnIndex_Names.get(columnIndexes.get(i)),
								cells.get(columnIndexes.get(i) - 1).getText());
					}
					rowVisibleHiddenMapper.add(j + 1);
					allRows.add(row);
				}
			}
			// System.out.println("allRows " + allRows.size());


	  * for (int i = 0; i < allRows.size(); i++) {
	  * System.out.println("Row Data Captured :"+allRows.get(i)); }

		return allRows.size();
	  *//*}

	public HashMap<String, String> getRow(int rowNo) {
		if (allRows.size() != 0) {
			HashMap<String, String> row = allRows.get(rowNo - 1);
			return row;
		} else {

			return null;
		}

	}

	public String getCellValue(int row, int column) {
		HashMap<String, String> rowLine = getRow(row);
		String columnName = visibleColumnIndex_Names.get(column);
		String cellValue = rowLine.get(columnName);
		frameworkServices.logMessage("Fetched '"+cellValue+"' from Column '"+columnName+"' for Row '" +row+"'", logger);
		return cellValue;
	}

	public String getCellValue(int row, String columnName) {
		try {
			HashMap<String, String> rowLine = getRow(row);
			String cellValue = rowLine.get(columnName);
			frameworkServices.logMessage("Fetched '"+cellValue+"' from Column '"+columnName+"' for Row '" +row+"'", logger);
			return cellValue;
		} catch (Exception e) {
			return "";
		}

	}

	   

	public Map<String, List<String>> readTable(){

	    Map<String, List<String>> objTable = new HashMap<String, List<String>>();
	    Xpath tableColumnHeaderRow= //table[3]//table//tr[1]
	    WebElement columnHeader=driver.findElement(By.xpath(tableColumnHeaderRow));
	    List<WebElement> objRows = columnHeader.findElements(By.xpath(".//td"));
	    for(int iCount=0; iCount<objRows.size(); iCount++){
	        List<WebElement> objCol = objRows.get(iCount).findElements(By.cssSelector("td.tableTxt"));
	        List<String> columns = new ArrayList<String>();
	        for(int col=0; col<objCol.size(); col++){
	            columns.add(objCol.get(col).getText());
	        }
	        objTable.put(String.valueOf(iCount), columns);
	    }

	    return objTable;
	}

	public void getTableData(){
		//create hashmap of obj rows
		int columnIndex=0;
		String columnName;
		List<WebElement> allRows=driver.findElements(By.xpath(tablePath+"//tr"));
		for(WebElement allData:allRows){
			for(int i=1;i<=allRows.size();i++){
				List<WebElement> rowData=allData.findElements(By.xpath(".["+i+"]//td/*"));
				for(WebElement columnData:rowData){
					for(int j=1;j<=rowData.size();j++){
						WebElement column=columnData.findElement(By.xpath(xpathExpression))
					}
				}


			}
		}
	 this.tablePath=tablePath;
		int columnIndex=0;
		String columnName;
		List<WebElement> allrows=driver.findElements(By.xpath(tablePath));
		for(WebElement allrow:allrows){
			System.out.println(allrows.size());
			for(int i=1;i<=allrows.size();i++){
				List<WebElement> column=allrow.findElements(By.xpath(".//tr["+i+"]"));
				for(WebElement col:column){
					System.out.println(column.size());
					for(int j=1;j<=column.size();j++){
						WebElement co=col.findElement(By.xpath("//td[3]//*"));
						co.getText();
					}
				}
			}

		}

	}*/
	
}
}