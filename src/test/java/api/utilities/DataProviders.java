package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="dp_all")
	public String[][] getAllData() throws IOException {
		
		String path = System.getProperty("user.dir") + "//testData//UserData.xlsx";
		ExcelUtility xu = new ExcelUtility(path);
		
		int rownum=xu.getRowCount("Sheet1");
		int colCount = xu.getCellCount("Sheet1", 1);
		String apidata[][] = new String[rownum][colCount];
		
		for(int i=1; i<=rownum; i++) {
			for(int j=0; j<colCount; j++) {
				apidata[i-1][j] = xu.getCellData("Sheet1", i, j);
			}
		}
		
		return apidata;
	}
	
	@DataProvider(name="dp_usernames")
	public String[] getUserNames() throws IOException {
		
		String path= System.getProperty("user.dir") + "//testData//UserData.xlsx";
		ExcelUtility xu = new ExcelUtility(path);
		
		int rownum = xu.getRowCount("Sheet1");
		String apidata[]= new String[rownum];
		
		for(int i=1; i<=rownum; i++) {
			apidata[i-1] = xu.getCellData("Sheet1", i, 1);
		}
		return apidata;
	}
	
} //class
