package ecoChat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class Reader2 {
 static String temp = "";
static String cellValue;
static int count=0;
	public static void main(String[] args) throws IOException {
		
		
		File file = new File("C:\\Users\\btejesh\\Desktop\\New Microsoft Word Document.docx");
		FileInputStream fis = new FileInputStream(file);
		XWPFDocument doc = new XWPFDocument(fis);
		List<XWPFTable> tables = doc.getTables();
	
		for (XWPFTable table : tables) {
			for (XWPFTableRow row : table.getRows()) {
				for (XWPFTableCell cell : row.getTableCells()) {
					count++;
					if(count%2==0) {
					System.out.print(cell.getText());
					}
					
					/*String sFieldValue = cell.getText();
					if (sFieldValue.matches("Name")) {
						System.out.println(cell.getText());
					}*/
//					System.out.println("\t");
				}
				System.out.println(" ");
			}
		}
		
	}

}