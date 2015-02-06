package com.example.danielt.pestcontrol;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class SpecialReportAdden1 extends ActionBarActivity {
        int numOfFragments;
    RelativeLayout addenRelLayout;
    AddendumLinInputFragment addenNew;
    TextView report;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_report_adden1);
        numOfFragments=0;
        addenRelLayout = (RelativeLayout)findViewById(R.id.RellayAddenComments);
        Button addenSave = (Button)findViewById(R.id.btnAddendumSave);
        final Button addenAddComment = (Button)findViewById(R.id.btnAddendumAddComment);

        addenSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this should save all comments including those added in dynamically produced fragments

                //save database

                Intent i = new Intent (SpecialReportAdden1.this, MenuPage.class);
                startActivity(i);
            }
        });


        addenAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("number of Fragments at start of OnClick", Integer.toString(numOfFragments));

               // FragmentManager fm = getFragmentManager();
              //  FragmentTransaction ft = fm.beginTransaction();
                addenNew = new AddendumLinInputFragment();

               // ft.add(addenRelLayout.getId(), addenNew);
                RelativeLayout.LayoutParams addenParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                if (numOfFragments ==0){
               //     addenParams.addRule(RelativeLayout.BELOW, R.id.addendumCommentsFragment);
                  //  addenParams.addRule();
                }

                else  {
                    String fragmentid = "addendumCommentsFragment" + numOfFragments;
                    int fragId = getResources().getIdentifier(fragmentid, "id", getCallingPackage());
                    addenParams.addRule(RelativeLayout.BELOW, fragId);
                    report = (TextView)findViewById(R.id.txtVAddendumFragReportNum);
                    String repNum = "Report "+numOfFragments +":";
                    report.setText(repNum);

                }


               // ft.commit();
                numOfFragments++;
                Log.i("number of Fragments updated", Integer.toString(numOfFragments));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_special_report_adden, menu);
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
