package datadriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Exc {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {


		FileInputStream fis = new FileInputStream("C:\\Users\\G\\Desktop\\data.xlsx");

		Workbook book = WorkbookFactory.create(fis);// open the excel

		Sheet sh = book.getSheet("Sheet2");// specify which sheet

		Row r = sh.createRow(5);// creating row with index as specified

		Cell c = r.createCell(0);// mentioning the cell number

		c.setCellValue("tecily");// specifying the data

		FileOutputStream fout = new FileOutputStream("C:\\Users\\G\\Desktop\\data.xlsx");// passing data from program to excel
		book.write(fout);

	}

}
