/**
 * File Name: DataHelper.java<br>
 * Munteanu, Alex<br>
 * Java Boot Camp Exercise<br>
 * Instructor: Jean-francois Nepton<br>
 * Created: Jan 30, 2017
 */
package com.amunteanu.helpers;

import java.io.*;
import java.sql.*;
import java.util.*;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import com.amunteanu.helpers.exceptions.*;

/**
 * DataHelper //ADDD (description of class)
 * <p>
 * //ADDD (description of core fields)
 * <p>
 * //ADDD (description of core methods)
 *
 * @author Munteanu, Alex
 * @version 1.0.0
 * @since 1.0
 */
public class DataHelper {

	private Object[][] data;

	public DataHelper(Object[][] data) {
		super();
		this.data = data;
	}

	// Static method
	public static String displayData(Object[][] data) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				sb.append(data[i][j] + " ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	// Instance method
	public String displayData() {
		return displayData(this.data);
	}

	public static Object[][] evalDatabaseTable(String driverClassString, String databaseStringUrl, String username,
			String password, String tableName) throws ClassNotFoundException, SQLException {
		return evalDatabaseTable(driverClassString, databaseStringUrl, username, password, tableName, 0, 0);
	}

	public static Object[][] evalDatabaseTable(String driverClassString, String databaseStringUrl, String username,
			String password, String tableName, int rowOffset, int colOffset)
			throws ClassNotFoundException, SQLException {
		Object[][] myData;
		ArrayList<Object> myArrayData = new ArrayList<Object>();
		// this throws an exception when you do not have the proper dependency
		// in your pom
		Class.forName(driverClassString);
		// Create connection to database
		Connection dbconn = DriverManager.getConnection(databaseStringUrl, username, password);
		Statement stmt = dbconn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from " + tableName);
		int numOfColumns = rs.getMetaData().getColumnCount();
		int curRow = 1;
		while (rs.next()) {
			if (curRow > rowOffset) {
				Object[] rowData = new Object[numOfColumns - colOffset];
				for (int i = 0, j = colOffset; i < rowData.length; i++) {
					rowData[i] = rs.getString(i + colOffset + 1);
				}
				myArrayData.add(rowData);
			}
			curRow++;
		}
		myData = new Object[myArrayData.size()][];
		for (int i = 0; i < myData.length; i++) {
			myData[i] = (Object[]) myArrayData.get(i);
		}
		rs.close();
		stmt.close();
		dbconn.close();
		return myData;
	}

	public static Object[][] getExcelFileData(String fileLocation, String fileName, Boolean hasLabels)
			throws InvalidExcelExtensionException {
		Object[][] resultsObject;
		String[] fileNameParts = fileName.split("[.]");
		String extension = fileNameParts[fileNameParts.length - 1];
		ArrayList<Object> results = null;
		if (extension.equalsIgnoreCase("xlsx")) {
			results = getNewExcelFileResults(fileLocation, fileName, hasLabels);
		} else if (extension.equalsIgnoreCase("xls")) {
			results = getOldExcelFileResults(fileLocation, fileName, hasLabels);
		} else {
			throw new InvalidExcelExtensionException();
		}
		resultsObject = new Object[results.size()][];
		results.toArray(resultsObject);
		return resultsObject;
	}

	@SuppressWarnings("deprecation")
	private static ArrayList<Object> getNewExcelFileResults(String fileLocation, String fileName, Boolean hasLabels) {
		ArrayList<Object> results = new ArrayList<Object>();
		try {
			String fullFilePath = fileLocation + fileName;
			InputStream newExcelFormatFile = new FileInputStream(new File(fullFilePath));
			XSSFWorkbook workbook = new XSSFWorkbook(newExcelFormatFile);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			if (hasLabels) {
				rowIterator.next();
			}
			while (rowIterator.hasNext()) {
				ArrayList<Object> rowData = new ArrayList<Object>();
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BOOLEAN:
						System.out.print(cell.getBooleanCellValue() + "\t\t\t");
						rowData.add(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_NUMERIC:
						System.out.print(cell.getNumericCellValue() + "\t\t\t");
						rowData.add(cell.getNumericCellValue());
						break;
					case Cell.CELL_TYPE_STRING:
						System.out.print(cell.getStringCellValue() + "\t\t\t");
						rowData.add(cell.getStringCellValue());
						break;
					}
				}
				Object[] rowDataObject = new Object[rowData.size()];
				rowData.toArray(rowDataObject);
				results.add(rowDataObject);
				System.out.println("");
			}
			newExcelFormatFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return results;
	}

	private static ArrayList<Object> getOldExcelFileResults(String fileLocation, String fileName, Boolean hasLabels) {
		ArrayList<Object> results = new ArrayList<Object>();
		try {
			String fullFilePath = fileLocation + fileName;
			InputStream newExcelFormatFile = new FileInputStream(new File(fullFilePath));
			HSSFWorkbook workbook = new HSSFWorkbook(newExcelFormatFile);
			HSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			if (hasLabels) {
				rowIterator.next();
			}
			while (rowIterator.hasNext()) {
				ArrayList<Object> rowData = new ArrayList<Object>();
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BOOLEAN:
						System.out.print(cell.getBooleanCellValue() + "\t\t\t");
						rowData.add(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_NUMERIC:
						System.out.print(cell.getNumericCellValue() + "\t\t\t");
						rowData.add(cell.getNumericCellValue());
						break;
					case Cell.CELL_TYPE_STRING:
						System.out.print(cell.getStringCellValue() + "\t\t\t");
						rowData.add(cell.getStringCellValue());
						break;
					}
				}
				Object[] rowDataObject = new Object[rowData.size()];
				rowData.toArray(rowDataObject);
				results.add(rowDataObject);
				System.out.println("");
			}
			newExcelFormatFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return results;
	}

	public static void clearArray(Object[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = " . ";
			}
		}
	}

	private static void insertArray(Object[][] origArray, Object[][] newData, int insertX, int insertY) {
		for (int i = insertX, x = 0; i < newData.length + insertX; i++, x++) {
			for (int j = insertY, y = 0; j < newData[x].length + insertY; j++, y++) {
				origArray[i][j] = newData[x][y];
			}
		}
	}

	/**
	 * @param credentials
	 * @param v
	 * @return
	 */
	public static Object[][] joinData(Object[][] primaryArray, Object[][] joinArray) {
		// Check for square Matrix and if not through an exception0
		int totalDimX = primaryArray.length * joinArray.length;
		int totalDimY = primaryArray[0].length + joinArray[0].length;
		Object[][] data = new Object[totalDimX][totalDimY];
		clearArray(data);
		for (int i = 0; i < joinArray.length; i++) {
			DataHelper.insertArray(data, primaryArray, primaryArray.length * i, 0);
		}
		for (int i = 0; i < primaryArray.length; i++) {
			DataHelper.insertArray(data, joinArray, joinArray.length * i, primaryArray[0].length);
		}
		return data;
	}

	public static Object[][] joinData(Object[][]... data) {
		Object[][] newData = new Object[][] { {} };
		// Object[][] finalData = null;
		for (int i = 0; i < data.length; i++) {
			newData = DataHelper.joinData(newData, data[i]);
		}
		return newData;
	}
}
