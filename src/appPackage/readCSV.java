package appPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class readCSV {
	
	private static String COMMA_DELIMITER = ",";
	
	private static ArrayList<ArrayList<String>> wholeSheet = new ArrayList<>();
	
	public static ArrayList<ArrayList<String>> getWholeSheet() {
		if (wholeSheet.isEmpty()) {
			loadCSV();
		}		
		return wholeSheet;
	}
	
	public static void loadCSV() {
		
		try (Scanner scanner = new Scanner(new File("sample.csv"));) {
			int i = 0;
			while (scanner.hasNextLine()) { 
				wholeSheet.add(getRows(scanner.nextLine(), i));
				i++;
			}
		}		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static ArrayList<String> getRows(String row, int i){
		ArrayList<String> rowValues = new ArrayList<String>();
		try (Scanner rowScanner = new Scanner (row)){
			rowScanner.useDelimiter(COMMA_DELIMITER);
			String startCell = "";
			while (rowScanner.hasNext()) {
				String currentCell = rowScanner.next();				
				if (currentCell.startsWith("\"", 0)) {
					startCell = currentCell.replace("\"", "");
				} else if (currentCell.endsWith("\"")) {
					String endCell = currentCell.replace("\"", "");
					String fullCell = startCell + endCell;
					rowValues.add(fullCell);
				} else {
					rowValues.add(currentCell);
				}				
			}
		}	
		return rowValues; 
	}

}
