package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class DataWriter {

	
	// write or append to a file
	// column values are passed in ArrayList
	// filename if not there it will create
	// sheet name if not there it will create
	public static void writeToFile(String fileName, 
			String sheetName, 
			ArrayList<String> listOfValues, 
			boolean flag ) throws IOException
	{
		Sheet sheet;
		Workbook wb;
		try {

			System.out.println("Opening file" + fileName);
			InputStream inp = new FileInputStream(fileName); 
			wb = WorkbookFactory.create(inp); 
			sheet = wb.getSheet(sheetName);
			if (sheet == null) {
		        sheet = wb.createSheet(sheetName); 
			}

		}
		catch (Exception e)
		{
			System.out.println("file not found exception");
			System.out.println("Creating a new file" + fileName);
			
			// Blank workbook 
	        wb = new XSSFWorkbook(); 
	        // Create a blank sheet 
	        sheet = wb.createSheet(sheetName); 
		}
		
		System.out.println("Getting last row");
		int num = sheet.getLastRowNum(); 
		
		System.out.println("Creating a new row");
		Row row = sheet.createRow(++num); 
		
		System.out.println("For all the list items, create a cell");
		int i;
		for (i=0; i < listOfValues.size(); i++)
		{
			row.createCell(i).setCellValue(listOfValues.get(i)); 
		}

		System.out.println("Add the pass fail flag at the last column");
		
		row.createCell(i).setCellValue(""+flag);
		
		// Now this Write the output to a file 
		FileOutputStream fileOut = new FileOutputStream(fileName); 
		wb.write(fileOut); 
		fileOut.close(); 
	
	} // end of function
	
	
	// write or append to a file
	// Value to write gets written in a new line
	// filename if not there it will create
	// sheet name if not there it will create
	//here the pass fail parameter is not there
	public static void writeToFileSingleValue(String fileName, 
			String sheetName, 
			String valueToWrite
			 ) throws IOException
	{
		Sheet sheet;
		Workbook wb;
		try {

			//System.out.println("Opening file" + fileName);
			InputStream inp = new FileInputStream(fileName); 
			wb = WorkbookFactory.create(inp); 
			sheet = wb.getSheet(sheetName);
			if (sheet == null) {
		        sheet = wb.createSheet(sheetName); 
			}

		}
		catch (Exception e)
		{
			//System.out.println("file not found exception");
			//System.out.println("Creating a new file" + fileName);
			
			// Blank workbook 
	        wb = new XSSFWorkbook(); 
	        // Create a blank sheet 
	        sheet = wb.createSheet(sheetName); 
		}
		
		//System.out.println("Getting last row");
		int num = sheet.getLastRowNum(); 
		
		System.out.println("Creating a new row in the file");
		Row row = sheet.createRow(++num); 
		
		// write the value in the first colum of the row
		row.createCell(0).setCellValue(valueToWrite); 

		
		// Now this Write the output to a file 
		FileOutputStream fileOut = new FileOutputStream(fileName); 
		wb.write(fileOut); 
		fileOut.close(); 
	
	} // end of function
	
	
}
