package com.example.danielt.pestcontrol;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

//Get ready to leave.
// save all details
// correct align issue
//

public class MainActivity extends ActionBarActivity {
    SignInFragment sif;
     usernameCheck lif;
    SQLiteDatabase db;
    PestDBData dbHelper;
    boolean resultCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String data="";
        // save to file instead of db

        Intent i = getIntent();
        if (i!=null){
            resultCheck= i.getBooleanExtra("FromUsernameCheck",true );
        }
        else {
            dbHelper = new PestDBData(this);

            resultCheck = dbHelper.checkIfUserExist(this);
        }

        String name, licenseNum;
        if (resultCheck) {
            SharedPreferences techdataRead = getApplicationContext().getSharedPreferences("TechData", Context.MODE_PRIVATE);

            name = techdataRead.getString("TechName", "");
            licenseNum = techdataRead.getString("LicenseNum", "");

            Log.i("SharedPref Results", name + " " + licenseNum);

            Log.i("SharedPref correct", "Fragment LogIn Opened");

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            Bundle dataToFragment = new Bundle();
            dataToFragment.putString("TechName", name);
            dataToFragment.putString("LicenseNum", licenseNum);
            lif = new usernameCheck();
            lif.setArguments(dataToFragment);

            ft.add(R.id.userCheckFragment, lif);
            ft.addToBackStack(null);
            ft.commit();

        }
        else if (resultCheck== false){

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            sif = new SignInFragment();
            ft.add(R.id.signInFragment, sif);
            Log.i("SharedPref empty", "Fragment SignIn Opened");
            ft.addToBackStack(null);
            ft.commit();


        }



        /*FileInputStream inputStream = null;
        BufferedReader read = null;
        InputStreamReader streamReader = null;

        try{
            byte[] bytes = new byte[50];
            inputStream = openFileInput("SignInData.txt");

            if (inputStream!=null)
            {
                streamReader = new InputStreamReader(inputStream);
                read = new BufferedReader(streamReader);
                StringBuffer strBuffer = new StringBuffer();
                StringBuffer strBuffer2 = new StringBuffer();
                String strName = read.readLine();
                strBuffer.append(strName);
                String licNum = read.readLine();
                strBuffer2.append(licNum);

                *//*while (str !=null){
                    strBuffer.append(str +"\n");
                    str = read.readLine();}*//*


                data = strBuffer.toString();
            }
           Log.i("OutPut", data);
        }
     catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }*/
        //String name = "";
       // String licenseNum = "";
        //check for existing id in database
        //load name and number if exist
        // else display login fragment
        /*int colTechName =0;
        int colLicense=0;
        Cursor techData = null;
        String [] data = new String[] {PestDBTechData.TECHNAME, PestDBTechData.LICENSE };
        dbHelper = new PestDBTechData(this);
        db = dbHelper.getWritableDatabase();
        ContentValues newcv = new ContentValues();

        newcv.put(PestDBTechData.TECHNAME, "DanielInit");
        newcv.put(PestDBTechData.LICENSE, "12222");
        db.insert(PestDBTechData.TABLE_NAME, null, newcv);
        dbHelper.onCreate(db);
        String name = "";
                String licenseNum = "";

        try {

            techData = db.query(PestDBTechData.TABLE_NAME, data, null, null, null, null, null);
            colTechName = techData.getColumnIndex(PestDBTechData.TECHNAME);
            colLicense = techData.getColumnIndex(PestDBTechData.LICENSE);

            name=techData.getString(colTechName);
            licenseNum = techData.getString(colLicense);
        }
        catch (Exception e)
        {
            Log.d("Exception", "Exception");
        }*/



        //if (name.equals("")){          }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void dbChecker(SQLiteDatabase db){


    }
}
