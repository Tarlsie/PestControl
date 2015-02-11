package com.example.danielt.pestcontrol;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;


/**
 * Created by danieltarlow on 2/11/15.
 */
public class ReadExcelFile extends Activity {

    public ReadExcelFile(){

    }
        String result="";
    public static void readExcelFile(Context context, String fileName){

        if(!isExternalStorageAvailable()|| isExternalStorageReadOnly()){
            Log.w("Fileuntils", "Storage not availble");
            return;
        }

        try {
            File file = new File(context.getExternalFilesDir(null), fileName);
            FileInputStream fis = new FileInputStream(file);

            POIFSFileSystem myFileSystem = new POIFileSystem(fis);

            HSSFWorkbook workBook = new HSSFWorkbook(myFileSystem);

            HSSFSheet sheet = workBook.getSheetAt(0);

            Iterator<Row> rowIter = sheet.rowIterator();

            while (rowIter.hasNext()){

                HSSFRow row = (HSSFRow) rowIter.next();
                Iterator<Cell> cellIter = row.cellIterator();

                while (cellIter.hasNext()){
                    HSSFCell cell = (HSSFCell) cellIter.next();
                    result += cell.toString()+" ";
                    Log.i("cell result", cell.toString());
                }

            }


        }
        catch(Exception e){e.printStackTrace();}
        return;
    }

}
