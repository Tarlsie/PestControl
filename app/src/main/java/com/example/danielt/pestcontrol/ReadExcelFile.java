package com.example.danielt.pestcontrol;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.*;


/**
 * Created by danieltarlow on 2/11/15.
 */
public class ReadExcelFile extends Activity {

    public ReadExcelFile(){

    }

    public static void readExcelFile(Context context, String fileName){

        if(!isExternalStorageAvailable()|| isExternalStorageReadOnly()){
            Log.w("Fileuntils", "Storage not availble");
            return;
        }

        try {
            File file = new File(context.getExternalFilesDir(null), fileName);
            FileInputStream fis = new FileInputStream(file);

            POIFSFileSystem myFileSystem = new POIFileSystem(fis);


        }
        catch(){}
    }

}
