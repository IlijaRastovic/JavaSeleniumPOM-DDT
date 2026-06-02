package Base;

import java.util.ArrayList;
import java.util.List;

public class ExcelHelper {

    ExcelReader excelReader;

    private List<String> valuesList = new ArrayList<>();  //Creating of a list that will hold all needed values from Excel file.

    public ExcelHelper(ExcelReader excelReader) {  //Creating of a ExcelHelper constructor that will use ExcelReader
        this.excelReader = excelReader;
    }

    public List<String> getValuesList() {  //Getter that we will use to manipulate with the lists values
        return valuesList;
    }

    /*public List<String> loadValues(String sheetName, int cellNumber) {  // Method that loads all values from Excel in to the list
        int lastRow = excelReader.getLastRow(sheetName);
        for (int i = 1; i <= lastRow; i++) {
            String value = excelReader.getStringData(sheetName, i, cellNumber);
            getValuesList().add(value);
        }
        return  getValuesList();
    }*/
    public List<String> loadValues(String sheetName, int cellNumber) {

        List<String> values = new ArrayList<>();

        int lastRow = excelReader.getLastRow(sheetName);

        for (int i = 0; i <= lastRow; i++) {
            String value = excelReader.getStringData(sheetName, i, cellNumber);

            if (value != null && !value.isEmpty()) {
                values.add(value);
            }
        }

        return values;
    }

    public String loadSingleValue(String sheetName, int rowNumber, int cellNumber) { // Method that loads a single valid value that we have from Excel file
        return excelReader.getStringData(sheetName,rowNumber, cellNumber);
    }
    public int loadSingleIntValue(String sheetName, int rowNumber, int cellNumber) {
        return excelReader.getIntegerData(sheetName,rowNumber, cellNumber);
    }
    // Method that loads a single integer value that we have from Excel file




}
