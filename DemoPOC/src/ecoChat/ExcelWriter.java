package ecoChat;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelWriter {

	private void writeBook(UserBean aBook, Row row) {
		Cell cell = row.createCell(1);
		cell.setCellValue(aBook.getUserid());

		cell = row.createCell(2);
		cell.setCellValue(aBook.getName());

		cell = row.createCell(3);
		cell.setCellValue(aBook.getMobile());
		
		cell = row.createCell(4);
		cell.setCellValue(aBook.getEmail());
		
		cell = row.createCell(5);
		cell.setCellValue(aBook.getGender());
		
		cell = row.createCell(6);
		cell.setCellValue(aBook.getdOB());
		
		cell = row.createCell(7);
		cell.setCellValue(aBook.getCategory());
	}

	public void writeExcel(ArrayList<UserBean> listBook,String filename) throws IOException {
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet();

		int rowCount = 0;

		for (UserBean aBook : listBook) {
			Row row = sheet.createRow(++rowCount);
			writeBook(aBook, row);
		}

		try (FileOutputStream outputStream = new FileOutputStream(filename+".xls")) {
			workbook.write(outputStream);
			System.out.println("candidates data exported");
		}
	
	}

}
	
		

