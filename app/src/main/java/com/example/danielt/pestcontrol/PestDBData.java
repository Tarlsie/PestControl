package com.example.danielt.pestcontrol;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by danielT on 22/01/2015.
 */
public class PestDBData extends SQLiteOpenHelper {

    private static final String DBNAME = "PestDBData";

    //Set up table for technician name and license num
    public static final String TABLE_TECHDATA = "techData";
    public static final String KEY_ID = "_id";
    public static final String TECHNAME = "techName";
    public static final String LICENSE = "license";

    public static final String CREATE_TECHDATA_TABLE = "Create table if not exists "+TABLE_TECHDATA +
            " ( "+KEY_ID+ " integer primary key autoincrement, "
            + TECHNAME +" text, "
            + LICENSE +" text );";

    //Set up table for login and out times for tech on that day
    public static final String TABLE_TECH_DAILY_LOGIN_OUT = "tech_daily_login_out";

    public static final String TIME_IN_DAILY = "time_in_daily";
    public static final String TIME_OUT_DAILY = "time_out_daily";
    public static final String DATE = "date";
    public static final String CREATE_TECH_DAILY_LOGIN = "create table if not exists "+ TABLE_TECH_DAILY_LOGIN_OUT
            + DATE + " text, "
            + TIME_IN_DAILY + " text, "
            + TIME_OUT_DAILY + " text );";

    //Set up table for daily log data
    public static final String TABLE_DAILYLOG = "daily_Log";

    public static final String ADDRESS = "address";
    public static final String APT_TIME = "appointment_time";
    public static final String COMPLETE = "is_Completed";
    public static final String CONDITIONS = "conditions";
    //not sure if these should go in daily log tabl or techdata table??
    // still not sure maybe it should have a table of its own with the date

    //check options for storing date or times
    public static final String CREATE_DAILY_LOG_TABLE = "create table if not exists "+ TABLE_DAILYLOG +
            "( "+KEY_ID + " integer primary key autoincrement, "
            + ADDRESS +" text, "
            + DATE +" int, "
            + APT_TIME +" int, "
            + COMPLETE +" int, "
            + CONDITIONS +" text );";

    //set up table for multiple unit general data

    public static final String TABLE_MULTIPLE_UNIT_GEN_DATA = "multiple_unit_gen_data";
    public static final String TIME_IN = "time_in";
    public static final String ALLOWED_ENTRY = "allowed_entry";
    public static final String COMMON_AREAS_SERVICED = "common_areas_serviced";
    public static final String ADDEN_COMMENT = "addenComment";
    public static final String SIGNATURE = "signature_location";

    public static final String CREATE_MULTIPLE_UNIT_GEN_TABLE = "create table if not exists "+ TABLE_MULTIPLE_UNIT_GEN_DATA +
            "( " + KEY_ID + " integer primary key autoincrement, "
            + ADDRESS + "text, "
            + TIME_IN + "int, "
            + ALLOWED_ENTRY + "int, "
            + COMMON_AREAS_SERVICED + " int, "
            + SIGNATURE + " text, "
            + ADDEN_COMMENT + "text );";


    //set up table for multiple unit details data

    public static final String TABLE_MULTIPLE_UNIT_DETAILS_DATA = "multiple_unit_details_data";
    public static final String AREA_UNIT_NUM = "area_unit_num";
    public static final String SERVICED = "serviced";
    public static final String CHEM_USED = "chemical_used";
    public static final String RECIPE = "chemical_recipe";
    public static final String APPLICATION_LOCATION = "application_location";
    public static final String CHEMICAL_SITE = "chemical_site";
    public static final String TARGET_PEST = "target_pest";
    public static final String SIGNATURE_UNIT = "signature_unit";
    public static final String ADDEN_COMMENT_UNIT = "adden_comment_unit";

    public static final String CREATE_MULTIPLE_UNIT_DETAILS_TABLE = "create table if not exists "+ TABLE_MULTIPLE_UNIT_DETAILS_DATA +
            "( " + KEY_ID + " integer primary key autoincrement, "
            + AREA_UNIT_NUM +" text, "
            + SERVICED +" int, "
            + CHEM_USED +" int, "
            + RECIPE +" int, "
            + APPLICATION_LOCATION + "int, "
            + CHEMICAL_SITE + " varchar(2), "
            + TARGET_PEST + " int, "
            + SIGNATURE_UNIT + " text, "
            + ADDEN_COMMENT_UNIT + " text );";


    public PestDBData(Context context) {
        super(context, DBNAME, null, 1);
    }

    public PestDBData(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Data passed to Pest DB", "true");
        db.execSQL(CREATE_TECHDATA_TABLE);
        db.execSQL(CREATE_DAILY_LOG_TABLE);
        db.execSQL(CREATE_MULTIPLE_UNIT_GEN_TABLE);
        db.execSQL(CREATE_MULTIPLE_UNIT_DETAILS_TABLE);
        db.execSQL(CREATE_TECH_DAILY_LOGIN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        /*db.execSQL("insert into "+TABLE_NAME + " values "+
                "( null, '" +techName+"'"
                + TECHNAME +" text, "
                + LICENSE +" text );");*/
        Log.d("Data passed to onUpgrade DB", "true");

        //could deleting rows solve the problem instead of dropping the table
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_TECHDATA);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_TECH_DAILY_LOGIN_OUT);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_DAILYLOG);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_MULTIPLE_UNIT_GEN_DATA);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_MULTIPLE_UNIT_DETAILS_DATA);
        onCreate(db);

    }

    // Functions for TECHDATA table

    public boolean insertUserNPass(Context c, String username, String pass){
        PestDBData dbHelper;
        SQLiteDatabase db;

        Log.i("DB starting", "Data is "+username+" "+pass);
        /*int colTechName =0;
        int colLicense=0;
        Cursor techData = null;
        String [] data = new String[] {PestDBData.TECHNAME, PestDBData.LICENSE };
        */
        dbHelper = new PestDBData(c);
        db = dbHelper.getWritableDatabase();
        ContentValues newcv = new ContentValues();

        newcv.put(TECHNAME, username);
        newcv.put(LICENSE, pass);
        Long result = db.insert(PestDBData.TABLE_TECHDATA, null, newcv);
        Log.i("DB result", result.toString());

        if (result == 1)
            return  true;
        else
            return false;

    }

    public boolean checkIfUserExist(Context c){
        PestDBData dbHelper = new PestDBData(c);
        SQLiteDatabase db;

        Log.i("DB check if user exists", "checking");


        Cursor techData = null;
        String [] data = new String[] {TECHNAME, LICENSE };

        db = dbHelper.getWritableDatabase();
        techData= db.query(TABLE_TECHDATA,data,null,null,null,null,null );

        String techName="";
        String license="";
        int dataCount = techData.getCount();

            Log.i("Cursor data count",String.valueOf(dataCount));

        if (dataCount!=0 && dataCount!=-1 && techData.moveToFirst())
        {
            int colTechName = techData.getColumnIndex(TECHNAME);
            int colLicense = techData.getColumnIndex(LICENSE);

            techName = techData.getString(colTechName);
            license = techData.getString(colLicense);

            Log.i("Cursor First data", techName +" "+ license);
             if (techData.getString(colTechName)!=null) {
                 while (techData.moveToNext()) {
                     colTechName = techData.getColumnIndex(TECHNAME);
                     colLicense = techData.getColumnIndex(LICENSE);

                     techName = techData.getString(colTechName);
                     license = techData.getString(colLicense);

                     Log.i("Cursor data", techName + " " + license);
                 }

                 SharedPreferences newCV = c.getSharedPreferences("TechData", Context.MODE_PRIVATE);
                 SharedPreferences.Editor editor = newCV.edit();
                 editor.putString("TechName", techName);
                 editor.putString("LicenseNum", license);
                 editor.commit();
                 Log.i("DB check if user exists", "saved data to Shared Preference");
                   techData.close();
                 db.close();
                 return true;
             }
            else {
                 techData.close();
                 db.close();
                 return false;
             }
        }

        else

            techData.close();
        db.close();
           return false;

    }

    //Functions for Tech_Daily_Login_out

    public long insertTechDailyLogin_Out_LogInTime(Context c, String date, String timeInDaily){

        Log.i("TechDailyLogin_Out Login Data", "insert time In");
        PestDBData dbHelper = new PestDBData(c);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(DATE, date);
        cv.put(TIME_IN_DAILY, timeInDaily);

        long result = db.insert(TABLE_TECH_DAILY_LOGIN_OUT, null, cv); //may need to make timeout nullvariable
        db.close();
        Log.i("TechDailyLogin_Out Login Data", "insert time In Success "+String.valueOf(result));
        return result;
    }

    public long updateTechDailyLogin_Out_LogOutTime(String date, String timeOutDaily){
//does not need content for update???
        Log.i("TechDailyLogin_Out LogOut Data", "insert/update time Out");

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        //cv.put(PestDBData.DATE, date);
        cv.put(TIME_OUT_DAILY, timeOutDaily);

        //check these
        String forDate = "";
        long result = db.update(TABLE_TECH_DAILY_LOGIN_OUT, cv, DATE + " = "+date, null); //not sure this is correct
        db.close();
        Log.i("TechDailyLogin_Out LogOut Data", "insert time Out Success "+String.valueOf(result));
        return result;
    }

    //not sure if we need to take an id as argument???
    public Cursor getTechDailyLogIn_OutData (int id){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from "+TABLE_TECH_DAILY_LOGIN_OUT;

        Cursor data = db.rawQuery(query, null);
        db.close();
        return data;
    }
    //Functions for Daily_Log table

    /*public static final String CREATE_DAILY_LOG_TABLE = "create table if not exists "+ TABLE_DAILYLOG +
            "( "+KEY_ID + " integer primary key autoincrement, "
            + ADDRESS +" text, "
            + DATE +" int, "
            + APT_TIME +" int, "
            + COMPLETE +" int, "
            + CONDITIONS +" text );";*/

    public long insert_daily_log (Context c, String address, String date, int aptTime, int complete, String conditions){

        Log.i("Insert daily log", address+" "+ date+" "+aptTime+" "+complete+" "+conditions );
        PestDBData dbHelper = new PestDBData(c);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(ADDRESS, address);
        cv.put(DATE, date);
        cv.put(APT_TIME, aptTime);
        cv.put(COMPLETE, complete);
        cv.put(CONDITIONS, conditions);
        long result = db.insert(TABLE_DAILYLOG,CONDITIONS, cv);
        db.close();
        Log.i("insert completed", String.valueOf(result));
        return result;

    }

    public long update_daily_log_isCompleted (int id, String complete){

        SQLiteDatabase db = this.getWritableDatabase();
        Log.i("update daily log", "is completed "+complete);
        ContentValues cv = new ContentValues();
        cv.put(COMPLETE, complete);

        String update = "";
        long result = db.update(TABLE_DAILYLOG, cv, KEY_ID +" = "+id, null);
        db.close();
        Log.i("update daily log ", "is completed success");
        return result;
    }

    public long update_Daily_Log_Conditions(int id, String conditions){

        SQLiteDatabase db = this.getWritableDatabase();

        Log.i("update to daily log", "Conditions for id: "+id+" conditions "+conditions);

        ContentValues cv = new ContentValues();
        cv.put(CONDITIONS, conditions);

        long result = db.update(TABLE_DAILYLOG, cv, KEY_ID +"="+ id, null);
        db.close();
        Log.i("Daily Log Conditions Update","successful");

        return result;
    }

    //Functions for Multiple_unit_Gen_Data table
  /*  public static final String CREATE_MULTIPLE_UNIT_GEN_TABLE = "create table if not exists "+ TABLE_MULTIPLE_UNIT_GEN_DATA +
            "( " + KEY_ID + " integer primary key autoincrement, "
            + ADDRESS + "text, "
            + TIME_IN + "int, "
            + ALLOWED_ENTRY + "int, "
            + COMMON_AREAS_SERVICED + " int, "
            + SIGNATURE + " text, "
            + ADDEN_COMMENT + "text );";*/

    public long insertMultipleUnitGeneralData(Context c, String address, int time_in, int allowedEntry, int commonAreasServiced,
                                              String signature, String addenComments){
        Log.i("Insert daily log", address+" "+ time_in+" "+allowedEntry +" "+commonAreasServiced+" "+signature+" "+addenComments);

        PestDBData dbHelper = new PestDBData(c);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(ADDRESS, address);
        cv.put(SIGNATURE, signature);
        cv.put(ADDEN_COMMENT, addenComments);
        cv.put(TIME_IN, time_in);
        cv.put(ALLOWED_ENTRY,allowedEntry);
        cv.put(COMMON_AREAS_SERVICED, commonAreasServiced);

        long result = db.insert(TABLE_DAILYLOG, ADDEN_COMMENT, cv);
        db.close();
        Log.i("insert completed", String.valueOf(result));
        return result;
    }
    //there is no way to update any of these settings after initial save...
    //should this be changed if so where?

    //the only update that is necessary is to update the adden comments .. should this be removed from earlier db initialisation
    // because there will be nothing to input??

    public long updateMultipleUnitGeneral_Data_AddenComment(int id , String addenComment){

        SQLiteDatabase db = this.getWritableDatabase();
        Log.i("Multiple Unit General Data update", "Adding AddenComment "+ addenComment+" to id: "+id);

        ContentValues cv = new ContentValues();
        cv.put (ADDEN_COMMENT, addenComment);

        long result = db.update(TABLE_MULTIPLE_UNIT_GEN_DATA, cv, KEY_ID +" = "+id, null);

        Log.i("insert completed MultipleUnitGeneralData - addenComment", String.valueOf(result));
        return result;
    }

    //Functions for Multiple_unit_details_data table
/*
    public static final String CREATE_MULTIPLE_UNIT_DETAILS_TABLE = "create table if not exists "+ TABLE_MULTIPLE_UNIT_DETAILS_DATA +
            "( " + KEY_ID + " integer primary key autoincrement, "
            + AREA_UNIT_NUM +" text, "
            + SERVICED +" int, "
            + CHEM_USED +" int, "
            + RECIPE +" int, "
            + APPLICATION_LOCATION + "int, "
            + CHEMICAL_SITE + " varchar(2), "
            + TARGET_PEST + " int, "
            + SIGNATURE_UNIT + " text, "
            + ADDEN_COMMENT_UNIT + " text );";*/


    //same question as above if should all of these be used in initial insert if they cannot all be added.
    public long insertMultipleUnitDetailsData( Context c, String areaUnitNum, int serviced, int chemUsed, int recipe, int appLocation,
                                               String chemSite, int pest, String sigUnit){
        Log.i("Insert multiple unit details data", areaUnitNum+" "+ serviced+" "+chemUsed+" "+recipe+" "+ appLocation+" "+
                chemSite+" "+ pest+" "+ sigUnit );

        PestDBData dbHelper = new PestDBData(c);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(AREA_UNIT_NUM ,areaUnitNum);
        cv.put(SERVICED ,serviced);
        cv.put(CHEM_USED ,chemUsed);
        cv.put(RECIPE ,recipe);
        cv.put(APPLICATION_LOCATION , appLocation);
        cv.put(CHEMICAL_SITE ,chemSite);
        cv.put(TARGET_PEST ,pest);
        cv.put(SIGNATURE_UNIT ,sigUnit);


        long result = db.insert(CREATE_MULTIPLE_UNIT_DETAILS_TABLE, ADDEN_COMMENT_UNIT, cv);
        db.close();
        Log.i("insert completed", String.valueOf(result));

        return result;
    }

    public long updateMultipleUnitDetailsData_AddenUnit (int id , String addenCommentUnit ) {

        SQLiteDatabase db = this.getWritableDatabase();
        Log.i("update Multiple Unit Details Data", "Adding adden unit comment "+ addenCommentUnit+" to id: "+id);

        ContentValues cv = new ContentValues();
        cv.put(ADDEN_COMMENT_UNIT, addenCommentUnit);

        long result = db.update(TABLE_MULTIPLE_UNIT_DETAILS_DATA, cv, KEY_ID +" = "+id, null);
        Log.i("adden unit comment added", String.valueOf(result));

        return result;
    }
}
