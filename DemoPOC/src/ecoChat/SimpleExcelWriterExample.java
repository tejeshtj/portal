package ecoChat;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class SimpleExcelWriterExample {

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

	public void writeExcel(List<UserBean> listBook) throws IOException {
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet();

		int rowCount = 0;

		for (UserBean aBook : listBook) {
			Row row = sheet.createRow(++rowCount);
			writeBook(aBook, row);
		}

		try (FileOutputStream outputStream = new FileOutputStream("Candidates.xls")) {
			workbook.write(outputStream);
			System.out.println("candidates data exported");
		}
	}
public static void main(String[] args) {
	UserBean bean=new UserBean("userid", "name", "mobile", "email", "gender", "dOB", "category");
	UserBean bean2=new UserBean("266174031977", "tejesh", "6381733497", "startejesh7@gmail.com", "male", "05-01-1997", "experience");
	   
    List<UserBean> listBook = Arrays.asList(bean,bean2);
    SimpleExcelWriterExample example=new SimpleExcelWriterExample();
   
		try {
			example.writeExcel(listBook);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
	
		

