package com.example.danielt.pestcontrol;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MenuPage extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);
        String techName="";
        String techLicense="";
     //   String[] techNameData;

        //check for tech name data passed from previous intent
        //what to do when returning from other

        SharedPreferences techdataRead = getSharedPreferences("TechData", Context.MODE_PRIVATE);

        techName = techdataRead.getString("TechName", "");
        techLicense = techdataRead.getString("LicenseNum", "");

        Log.i("SharedPref Results", techName + " " + techLicense);

        Log.i("SharedPref correct", "Fragment LogIn Opened");

      /*  try {
            Intent menuPage = getIntent();
            techNameData = menuPage.getStringArrayExtra("firstName_lastName");
            Log.i("techNameData retrieved", techNameData[0]);
            techName = techNameData[0];
            techLicense = techNameData[1];
            Log.i("techNameData retrieved and passed to strings", techName + " " + techLicense);
        }
        catch (StringIndexOutOfBoundsException e)
        {
            Log.i("error intent input doesnt exist", "no data");
        }
        finally
        {
            Log.i("no intent", "no data");
        }*/

        TextView mainTitle = (TextView)findViewById(R.id.txtVMenuPageTitle);
        LinearLayout menuPageTechLog1 = (LinearLayout) findViewById(R.id.LinMenuPageBox1);
        LinearLayout menuPagePestLog2 = (LinearLayout) findViewById(R.id.LinMenuPageBox2);
        LinearLayout menuPageIndCustom3 = (LinearLayout) findViewById(R.id.LinMenuPageBox3);
        LinearLayout menuPageSpecialReq4 = (LinearLayout) findViewById(R.id.LinMenuPageBox4);
        LinearLayout menuPageServiceAdden5 = (LinearLayout) findViewById(R.id.LinMenuPageBox5);
        ImageButton menupageTech = (ImageButton) findViewById(R.id.imgBMenuPageTechWork);
        ImageButton menuPagePest = (ImageButton) findViewById(R.id.imgBMenuPagePestLog);
        Button menuPagebutton = (Button) findViewById(R.id.btnMenuPage);

        mainTitle.setText("Hello "+ techName);

        View.OnClickListener menuPageTechLogClick = (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (MenuPage.this, TechDailyWorkLog.class );
                startActivity(i);
            }
        });

        View.OnClickListener menuPagePestLogClick = (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (MenuPage.this, PestConsultLog.class );
                startActivity(i);
            }
        });

        View.OnClickListener menuPageIndCustomClick = (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (MenuPage.this,IndivCustomer.class );
                startActivity(i);
            }
        });

        View.OnClickListener menuPageSpecialReqClick = (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (MenuPage.this, SpecialRequest.class );
                startActivity(i);
            }
        });

        View.OnClickListener menuPageServiceAddenClick = (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (MenuPage.this,SpecialReportAdden.class );
                startActivity(i);
            }
        });

        View.OnClickListener menuPageButtonClick = (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (MenuPage.this,SpecialReportAdden.class );
                startActivity(i);
            }
        });

        menuPageTechLog1.setOnClickListener(menuPageTechLogClick);
        menuPagePestLog2.setOnClickListener(menuPagePestLogClick);
        menuPageIndCustom3.setOnClickListener(menuPageIndCustomClick);
        menuPageSpecialReq4.setOnClickListener(menuPageSpecialReqClick);
        menuPageServiceAdden5.setOnClickListener(menuPageServiceAddenClick);
        menuPagebutton.setOnClickListener(menuPageButtonClick);

        menupageTech.setOnClickListener(menuPageTechLogClick);
        menuPagePest.setOnClickListener(menuPagePestLogClick);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_page, menu);
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
}
