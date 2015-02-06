package com.example.danielt.pestcontrol;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class SpecialReportAdden extends FragmentActivity {
    int numOfFragments;
    LinearLayout addenHolder, addenInnerHolder;
    Bundle addenBundle1;

    TextView report;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_report_adden);
        numOfFragments=1;
        addenBundle1 = new Bundle();
        Button addenSave = (Button)findViewById(R.id.btnAddendumSave);
        Button addenAddComment = (Button)findViewById(R.id.btnAddendumAddComment);
        addenHolder =(LinearLayout)findViewById(R.id.AddenLinLayHolder);
        addenInnerHolder = (LinearLayout)findViewById(R.id.AddenLinLayInnerHolder);

        addenSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this should save all comments including those added in dynamically produced fragments

                //save database

                Intent i = new Intent (SpecialReportAdden.this, MenuPage.class);
                startActivity(i);
            }
        });


        addenAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater infalter = LayoutInflater.from(getApplicationContext());

                View addedFrag = infalter.inflate(R.layout.fragment_addendum_lin_input, addenInnerHolder, false);
                addedFrag.setBackgroundColor(Color.GREEN);

                addenInnerHolder.addView(addedFrag);


                Log.i("number of Fragments at start of OnClick", Integer.toString(numOfFragments));
                addenBundle1.putInt("FragNum", numOfFragments);
                AddendumLinInputFragment oldFrag = new AddendumLinInputFragment();

                AddendumLinInputFragment newAddenFrag = addNewfragment(numOfFragments, addenBundle1);

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.AddenLinLayInnerHolder, oldFrag, "first");
               // ft.add(R.id.fragmentHolder, newAddenFrag, "second");
                ft.addToBackStack(null);
                ft.commit();

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


    public  AddendumLinInputFragment addNewfragment(int number, Bundle b){


        AddendumLinInputFragment adden = new AddendumLinInputFragment();

        b.getInt("FragNum");
        TextView tx = (TextView) findViewById(R.id.txtVAddendumFragReportNum);
        tx.setText("Report "+number+" :");
        tx.setTextColor(Color.BLACK);
        TextView address = (TextView)findViewById(R.id.txtVAddendumFragAddress);
        address.setText("A new address");
        address.setTextColor(Color.BLUE);
        if(number==1){
            address.setBackgroundColor(Color.RED);
        }
        else if (number ==2){
            address.setBackgroundColor(Color.YELLOW);

        }else if(number==3){
            address.setBackgroundColor(Color.GRAY);
        }

        return  adden;
    }
}


 /*LinearLayout addenLinlLayoutAdded = new LinearLayout(getApplicationContext());
                LinearLayout.LayoutParams linAddenParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                String fragmentid = "addendumCommentsFragment" + numOfFragments;
                int fragId = getResources().getIdentifier(fragmentid, "id", getCallingPackage());

                addenLinlLayoutAdded.setId(fragId);


                // linAddenParams.  addRule(RelativeLayout.BELOW, fragId);
                report = (TextView) findViewById(R.id.txtVAddendumFragReportNum);
                String repNum = "Report " + numOfFragments + ":";
                report.setText(repNum);
                TextView report1 = new TextView(getApplicationContext());
                String repNumNew = "New TxtVReport " + numOfFragments + ":";
                report1.setText(repNumNew);
                addenLinlLayoutAdded.addView(report1, linAddenParams);
                //addenLinlLayoutAdded.setLayoutParams(linAddenParams);
                addenHolder.addView(addenLinlLayoutAdded);*/
// addenHolder.addView(newAddenFrag);
// ft.commit();
//String name="addenLinlLayoutAdded"+numOfFragments;

// LinearLayout addenLinlLayoutAdded2 = new LinearLayout(getApplicationContext());

//addenLinlLayoutAdded2.setOrientation(LinearLayout.VERTICAL);

// ft.add(addenRelLayout.getId(), addenNew);

/*
                if (numOfFragments == 0) {
                 //   addenParams.addRule(RelativeLayout.BELOW, R.id.addendumCommentsFragment);
                    //  addenParams.addRule();
                } else {*/



