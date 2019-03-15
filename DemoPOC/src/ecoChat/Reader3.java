package ecoChat;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;

public class Reader3 {
	static String temp = "";
	static String name, email, school, hsc, graduate;
	static String mobile;

	public static void main(String[] args) throws IOException {

		File file = new File("C:\\Users\\btejesh\\Desktop\\New Microsoft Word Document.docx");
		FileInputStream fis = new FileInputStream(file);
		XWPFDocument doc = new XWPFDocument(fis);
		List<XWPFTable> tables = doc.getTables();

		for (XWPFTable table : tables) {
			for (int i = 0; i < table.getRows().size(); i++) {

				for (int j = 0; j < table.getRow(i).getTableCells().size(); j++) {
					temp = table.getRow(i).getCell(j).getText();
					if (temp.equalsIgnoreCase("name")) {
						name = table.getRow(i).getCell(j + 1).getText();
						System.out.println("name is: " + name);
					} else if (temp.equalsIgnoreCase("mobile")) {
						mobile = table.getRow(i).getCell(j + 1).getText();
						try {
							System.out.println("mobile is:"+Integer.parseInt(mobile) );
						} catch (Exception e) {
							System.out.println("mobile is: " + mobile);
						}
					}

					else if (temp.equalsIgnoreCase("email")) {
						email = table.getRow(i).getCell(j + 1).getText();
						System.out.println("email is: " + email);
					} else if (temp.equalsIgnoreCase("school")) {
						school = table.getRow(i).getCell(j + 1).getText();
						System.out.println("school is: " + school);
					} else if (temp.equalsIgnoreCase("hsc")) {
						hsc = table.getRow(i).getCell(j + 1).getText();
						System.out.println("hsc is: " + temp);
					} else if (temp.equalsIgnoreCase("graduate")) {
						graduate = table.getRow(i).getCell(j + 1).getText();
						System.out.println("college is: " + graduate);
					}
				}
			}

		}

	}

}