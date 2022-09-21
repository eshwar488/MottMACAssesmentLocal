
package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;

public class DataReaders { 

	/*
	 * This function takes two parameters
	 * 1. Excel file name
	 * 2. Sheet name
	 * Returns a two dimensional String Array
	 * This function uses PoI library to read the excel sheet
	 */
	public static String[][] getExcelDataUsingPoi
	(String fileName, String sheetName) throws IOException {
		
		String[][] arrayExcelData = null;
		
		org.apache.poi.ss.usermodel.Workbook wb = null;
		
		try {
			File file = new File(fileName);


			FileInputStream fs = new FileInputStream(file);
			//			.xls
			if
			(fileName.substring(fileName.indexOf(".")).equals(".xlsx"))
			{

				//If it is xlsx file then create object of XSSFWorkbook class

				wb = new org.apache.poi.xssf.usermodel.XSSFWorkbook(fs);
			}
			else if
			(fileName.substring(fileName.indexOf(".")).equals(".xls"))
			{
				//If it is xls file then create object of HSSFWorkbook class
				wb = new org.apache.poi.hssf.usermodel.HSSFWorkbook(fs);
			}

			if (wb==null)
			{
				//Error Sheet name not found
				Exception exp = new Exception("WORKBOOK CREATION ERROR - May be File **NOT** found " + sheetName );
				throw exp;
			}		

			org.apache.poi.ss.usermodel.Sheet sh = wb.getSheet(sheetName);

			if (sh==null)
			{
				//Error Sheet name not found
				Exception exp = new Exception("Sheet Name **NOT** found " + sheetName );
				throw exp;
			}

			int totalNoOfRows = sh.getPhysicalNumberOfRows();
			
			int totalNoOfCols = 
					sh.getRow(0).getPhysicalNumberOfCells();
			

			System.out.println("totalNoOfRows="+totalNoOfRows+","
					+ " totalNoOfCols="+totalNoOfCols);
			
			arrayExcelData = 
					new String[totalNoOfRows-1][totalNoOfCols];
			
			System.out.println("Reading excel file now");
			// End reading the excel file if the column value is -1
			boolean continueReading = true;
			for (int i= 1 ; i <= totalNoOfRows-1; i++) {
				for (int j=0; j <= totalNoOfCols-1; j++) {
					String rawCellVal = null;
					try {

						DataFormatter formatter = new DataFormatter();
						rawCellVal = formatter.formatCellValue(sh.getRow(i).getCell(j));

						// following is poi 3.5 etc
						// sh.getRow(i).getCell(j)..setCellType(1);
						//rawCellVal = sh.getRow(i).getCell(j).getStringCellValue();
					}
					catch(Exception e) {
						// error reading cell value or row value
						// looks like it may be null
						System.out.println("error reading value from the row and cell - null may be");
					}
					if (rawCellVal == null || rawCellVal.toString().contains("-1"))
					{
						continueReading= false;
						break;
					}
					String cellStringVal = rawCellVal.toString();
					arrayExcelData[i-1][j] = cellStringVal;
					System.out.print(arrayExcelData[i-1][j]+":");
					
				} // inner for loop - j
				if (continueReading == false) {
					System.out.println("Completed reading -1 or null found: breaking now.");
					break;
				}
				System.out.println();
			} // outer for loop i
		} catch (Exception e) {
			System.out.println("EXCEPTION error in getExcelData()");
			System.out.println(e.getMessage());
			if (arrayExcelData==null)
			{
				IOException exp = new IOException(e.getMessage());
				throw exp;
			}
		}
		return arrayExcelData;
	}

}
