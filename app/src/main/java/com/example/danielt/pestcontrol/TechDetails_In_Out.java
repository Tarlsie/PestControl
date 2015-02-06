package com.example.danielt.pestcontrol;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TechDetails_In_Out.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class TechDetails_In_Out extends Fragment {


    public TechDetails_In_Out() {
        // Required empty public constructor
    }

    int btnTimeInClicker = 0;
    String timeNowInput = null;
    String techName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tech_details__in__out, container, false);

        //EditText techName = (EditText)view.findViewById(R.id.edVTechName);
        TextView date = (TextView) view.findViewById(R.id.txtVDateTitle);
        TextView name = (TextView) view.findViewById(R.id.txtVTechNameDisplay);
        final Button btnTimeIn = (Button) view.findViewById(R.id.btnTimeIn);
        Button btnTimeOut = (Button) view.findViewById(R.id.btntimeOut);
        final TextView txtVTimeInDis = (TextView) view.findViewById(R.id.txtVTimeInDisplay);
        final TextView txtVTimeoutDisplay = (TextView) view.findViewById(R.id.txtVTimeOutDisplay);


        //search for shared preferences
           //will these work on first time opening
        //shared pref for tech name and id num
        SharedPreferences techNameData = getActivity().getSharedPreferences("TechData", Context.MODE_PRIVATE);
        techName = techNameData.getString("TechName", "");

        name.setText(techName);


        if (btnTimeInClicker>0) {
            SharedPreferences loginTimeData = getActivity().getSharedPreferences("LogInTimeandDate", Context.MODE_PRIVATE);
            String loginTime = loginTimeData.getString("LogIn_Time", "1");
            Log.i("loginTime Data", loginTime);

            String loginDate = loginTimeData.getString("Login_Date", "1");
            Log.i("loginDate Data", loginDate);

            setTime(loginTime, txtVTimeInDis, btnTimeIn);
        }
        timeNowInput = "old time";



        Calendar calDate = Calendar.getInstance();
        SimpleDateFormat dateToday = new SimpleDateFormat("dd MMMM yyyy");
        String dateTodayStr = dateToday.format(calDate.getTime());
        date.setText(dateTodayStr);

        //check if TimeIn has a value
        // if time has value btnTimeIne.setVisibility(....invisible);


        btnTimeIn.setOnClickListener(new View.OnClickListener() {

                     @Override
                     public void onClick(View v) {

                         if (btnTimeInClicker == 0) {


                             if (txtVTimeInDis.getText().toString().equals("Time In Display")) {

                                 Calendar cal = Calendar.getInstance();
                                 SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
                                 String timeNow = time.format(cal.getTime());
                                 SimpleDateFormat dateNow = new SimpleDateFormat("dd:MM:yyyy");
                                 String dateToday = dateNow.format(cal.getTime());
                                 Log.i("dateIn", dateToday);
                                 Log.i("timeIn", timeNow);

                                 setTime(timeNow, txtVTimeInDis, btnTimeIn);


                                 timeNowInput = timeNow;

                                 //save time and date to shared preferences
                                 SharedPreferences timeDateLoginIn = getActivity().getSharedPreferences("LogInTimeandDate", Context.MODE_PRIVATE);

                                 SharedPreferences.Editor edit = timeDateLoginIn.edit();
                                 edit.putString("LogIn_Time", timeNow);
                                 edit.putString("Login_Date", dateToday);
                                 edit.commit();
                                Bundle revisitingAct = new Bundle();

                               boolean revisitingActivity= true;
                                 revisitingAct.putBoolean("RevisitActivity",revisitingActivity);
                                 btnTimeInClicker++;

                             } else if
                                 //display loaded data
                                     (txtVTimeInDis.getText() != "Time In Display") {
                                 Log.i("timeIn", "time else if");
                                 txtVTimeInDis.setVisibility(View.VISIBLE);
                                 txtVTimeInDis.setText(timeNowInput);
                             }
                         } else if
                                 (btnTimeInClicker != 0) {
                             Log.i("timeIn", "time else if "+timeNowInput);
                             txtVTimeInDis.setVisibility(View.VISIBLE);
                             txtVTimeInDis.setText(timeNowInput);
                         }
                     }
                 }
             );



            btnTimeOut.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
                String timeNow = time.format(cal.getTime());
                SimpleDateFormat dateNow = new SimpleDateFormat("dd:MM:yyyy");
                String dateToday = dateNow.format(cal.getTime());
                Log.i("dateOUt", dateToday);
                Log.i("timeOut", timeNow);

                txtVTimeoutDisplay.setVisibility(View.VISIBLE);
                txtVTimeoutDisplay.setText(timeNow);
            }
            }

            );



            return view;
        }


        @Override
        public void onAttach (Activity activity){
            super.onAttach(activity);

            Log.i("TechDetails Fragment","OnAttach");
            //add here intialised values of name, time date etc
            Bundle getActRevisited = getArguments();
            if (getActRevisited!=null) {
                boolean haveRevisted = getActRevisited.getBoolean("RevisitActivity");

                Log.i("revisted Bool", String.valueOf(haveRevisted));
            }
        }

        @Override
        public void onDetach () {
            super.onDetach();

        }

        public void setTime(String time, TextView timeDisplay, Button button){
            timeDisplay.setVisibility(View.VISIBLE);
            timeDisplay.setText(time);


            //make button invisible and move time textview up to fill space
            button.setVisibility(View.INVISIBLE);
            RelativeLayout.LayoutParams moveTime = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
            moveTime.addRule( RelativeLayout.ALIGN_PARENT_LEFT );
            //how to set margins without error?
            moveTime.setMargins(0,30,0,0);

            moveTime.addRule(RelativeLayout.BELOW, R.id.txtVDateTitle);
            timeDisplay.setLayoutParams(moveTime);

        }
    }
