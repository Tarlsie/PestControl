package com.example.danielt.pestcontrol;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;


public class PestConsultLog extends ActionBarActivity {
    LinearLayout newLinLay;
    LinearLayout lin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pest_consult_log);

        String dateData="", addressData = "";
        //set up the data for name license num and date
        TextView name = (TextView)findViewById(R.id.txtVPestControlNameDisplay);
        TextView licenseNum = (TextView)findViewById(R.id.txtVPestControlLicNumDisplay);
        TextView date = (TextView)findViewById(R.id.txtVPestControlDateDisplay);
        TextView address = (TextView)findViewById(R.id.txtVPestControlAddress);
        Button continueBtn = (Button)findViewById(R.id.BtnMaintenceLog1);

        SharedPreferences techdataRead = getSharedPreferences("TechData", Context.MODE_PRIVATE);

        String techName = techdataRead.getString("TechName", "");
        String techLicense = techdataRead.getString("LicenseNum", "");

        Log.i("SharedPref Results", techName + " " + techLicense);
        name.setText(techName);
        licenseNum.setText(techLicense);

        //needs to receive date and address details from techdailyworkog.java or jobsdonetoday.java

        //create shared preference to store states of buttons etc

        //initialise switches so that common areas is not clickable unless the manager switch is set to yes
        Switch manager = (Switch)findViewById(R.id.swtManagerPermission);
        final Switch commonAreas = (Switch)findViewById(R.id.swtServiceCommonAreas);
        commonAreas.setClickable(false);




        manager.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                 if (isChecked){
                     commonAreas.setClickable(true);


                 }

                 else if (!isChecked){
                     commonAreas.setClickable(false);


                 }
             }
         });

       /* Button press = (Button)findViewById(R.id.button);
        lin= (LinearLayout)findViewById(R.id.pesContLinLay);
        press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              newLinLay  = new LinearLayout(getApplicationContext());
                LinearLayout.LayoutParams newLinLayParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                TextView new_tx = new TextView(getApplicationContext());
                lin.addView(newLinLay,newLinLayParams);
                newLinLay.addView(new_tx,500,500);

                new_tx.setTextColor(Color.BLUE);
                new_tx.setText("This is a message");

            }
        });*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pest_consult_log, menu);
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
