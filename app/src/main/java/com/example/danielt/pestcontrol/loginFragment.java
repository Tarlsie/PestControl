package com.example.danielt.pestcontrol;

import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class loginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_login_fragment, container, false);

        Log.i("LogInFragment onCreateView", "started");

        String name="D";
        String licenseNum="11";

        //SharedPreferences techDataRead = getActivity().getSharedPreferences("TechData", Context.MODE_PRIVATE);

        // name = techDataRead.getString("TechName","");
        // licenseNum = techDataRead.getString("LicenseNum","");

        // name = getArguments().getString("TechName");
        // licenseNum = getArguments().getString("LicenseNum");
/*
        Log.i("LogInFragment SharedPref Results", name + " " + licenseNum);

        TextView loginTechNameDisplay = (TextView) view.findViewById(R.id.txtVLoginTechNameDisplay);
        TextView loginLicenseDisplay = (TextView) view.findViewById(R.id.txtVLoginlicenseNumDisplay);
        Log.i("LogInFragment TextViews", "assigned" );
        loginTechNameDisplay.setBackgroundColor(Color.BLUE);
        loginTechNameDisplay.setTextColor(Color.YELLOW);
        loginTechNameDisplay.setText(name);
        Log.i("LogInFragment LoginName ", "assigned ");
        loginLicenseDisplay.setText(licenseNum);
        loginLicenseDisplay.setBackgroundColor(Color.BLUE);
        loginLicenseDisplay.setTextColor(Color.YELLOW);
        Log.i("LogInFragment  LoginNumber ", "assigned ");
        */
        return view;
    }


}
