package com.example.danielt.pestcontrol;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class JobsDoneToday extends Fragment {


    public JobsDoneToday() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_jobs_done_today, container, false);

        Button update = (Button)view.findViewById(R.id.btnUpdate);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MenuPage.class);
                startActivity(i);
            }
        });


        //Set time for the first job
        TextView timeJob1 = (TextView)view.findViewById(R.id.txtVTableRow1StartTime);

        Calendar cal = Calendar.getInstance();
      //  SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        String time = "14:23";

       // cal.setTime(new Date());

        //String timeDisplay = sdf.format(time);
        timeJob1.setText(time);


        //populate table with list of jobs to do
        String dateData="", addressData = "";
        //on click send data to pestconsultlog.java
        SharedPreferences buildingData = getActivity().getSharedPreferences("buildingData", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = buildingData.edit();
        edit.putString("date", dateData);
        edit.putString("Address", addressData);
        edit.commit();
        //set page view
        return view;
    }


}
