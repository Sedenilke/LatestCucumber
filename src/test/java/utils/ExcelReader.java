package utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {
    static Workbook book;
    static Sheet sheet;
    //this method will open the excel book
    public static void openExcel(String filePath){
        try {
            FileInputStream fis = new FileInputStream(filePath);
            book = new XSSFWorkbook(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //this method will open the excel work sheet: excelin altindaki specific taba gidiyoruz.
    public static void getSheet(String sheetName){
        sheet = book.getSheet(sheetName);
    }
    //this method will give the row count.Burada  satirlari atlaya atlaya da yazmis olsam.
    //bosluklu da yazmis olsam toplamda kac satirda yazilmis seyler varsa onun sayisini donuyor.
    public static int getRowCount(){
        return sheet.getPhysicalNumberOfRows();
    }
    //this method will give the column count based on the given row.
    // DIKKAT!!!!!!--------------Diyelim ki bir row oldugu gibi bos, burada null pointer----------------
    //exception veriyor.Diyelim ki bir rowda atliya atliya columnlari yazmisim.Toplamda kac hucrede yazi varsa onun sayisini
    //donuyor.

    //column sayisini hesaplamak icin row hesaplamasindaki gibi direkt bir formul yok.
    //bunun icin her row'a tek tek bakmali ve baktigim rowdaki ici dolu cellerin toplamini donmeliyim.
    //asagidaki formul bunu yapiyor. Burada soyle bir nuans var: en son elemen 17. sutunda yer alabilir ama
    //aradaki sutunlarda bosluk varsa yani herhangi ibr data yoksa bu getColsCount sadece icinde data olan sutunlarin
    //toplami uzerinden hesaplaniyor.
    public static int getColsCount(int rowIndex){

        return sheet.getRow(rowIndex).getPhysicalNumberOfCells();
    }

    //this method will give the cell data in string format
    //getRow() ve getCell() sheet'in methodlari aslinda.
    //bunlari toString() olmadan da kullanabildim.
    // yani sheet.getRow(1).getCell(1) --> seden
    //sheet.getRow(5).getCell(4) --> 60
    //ve getCell'i Sheet'ten hemen sonra kullanamiyorum. method chainde getRow methodundan sonra sunulan methodlar
    //arasinda gorunuyor.
    public static String getCellData(int rowIndex, int colIndex){

        return sheet.getRow(rowIndex).getCell(colIndex).toString();
    }

//this method will return list of maps having all the data from excel file

    public static List<Map<String, String>> excelListIntoMap
            (String filePath, String sheetName){
        openExcel(filePath);
        getSheet(sheetName);

        //creating a list of maps for all the rows- Here we create a container for our data.
        List<Map<String, String>> listData = new ArrayList<>();

        //loops - outer loop is always take care of rows
        for (int row=1; row<getRowCount(); row++){
            //every row is creating a map:
            Map<String, String> map = new LinkedHashMap<>();

            for (int col=0; col<getColsCount(row); col++){
                //row "0" is dedicated for the key values: name, middleName, lastName, etc....
                //everytime I bring together each key with corresponding value in the related cell:
                map.put(getCellData(0, col), getCellData(row, col));
            }
            //before going to create the next map, I need to add this map to the list otherwise it will overwrite.
            listData.add(map);
        }
        return listData;

    }

}