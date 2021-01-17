package genericlibs;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class GenericXLLibrary 
{
	public static String getData(String xlFIlePath, String sheetName, int row, int cell) {
		try {
			FileInputStream xlFile= new FileInputStream(xlFIlePath);
			Workbook workbook = WorkbookFactory.create(xlFile);
			
			Sheet sheet = workbook.getSheet(sheetName);
			
			return sheet.getRow(row).getCell(cell).toString();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String[][] getMultipleData(String xlFIlePath, String sheetName) {
		try {
			FileInputStream xlFile= new FileInputStream(xlFIlePath);
			Workbook workbook = WorkbookFactory.create(xlFile);			
			Sheet sheet = workbook.getSheet(sheetName);			
			int rowCount=sheet.getPhysicalNumberOfRows();			
			String[][] sarr=new String[rowCount-1][];
			for(int i=1, k=0;i<=rowCount-1;i++,k++) {
				int cellCount=sheet.getRow(i).getPhysicalNumberOfCells();
				sarr[k]=new String[cellCount];
				for(int j=0;j<=cellCount-1;j++) {
					sarr[k][j]=sheet.getRow(i).getCell(j).toString();
				}
			}
			return sarr;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
