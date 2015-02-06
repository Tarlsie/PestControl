package com.example.danielt.pestcontrol;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class usernameCheck extends Fragment {


    public usernameCheck() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_username_check, container, false);

        Log.i("LogInFragment onCreateView", "started");

        String name="D";
        String licenseNum="11";

        //SharedPreferences techDataRead = getActivity().getSharedPreferences("TechData", Context.MODE_PRIVATE);

        // name = techDataRead.getString("TechName","");
        // licenseNum = techDataRead.getString("LicenseNum","");

         name = getArguments().getString("TechName");
         licenseNum = getArguments().getString("LicenseNum");

        Log.i("LogInFragment SharedPref Results", name + " " + licenseNum);

        TextView loginTechNameDisplay = (TextView) view.findViewById(R.id.txtVLoginTechNameDisplay);
        TextView loginLicenseDisplay = (TextView) view.findViewById(R.id.txtVLoginlicenseNumDisplay);
        Log.i("LogInFragment TextViews", "assigned" );
        //loginTechNameDisplay.setBackgroundColor(Color.BLUE);
        //loginTechNameDisplay.setTextColor(Color.YELLOW);
        loginTechNameDisplay.setText(name);
        Log.i("LogInFragment LoginName ", "assigned ");
        loginLicenseDisplay.setText(licenseNum);
        //loginLicenseDisplay.setBackgroundColor(Color.BLUE);
       // loginLicenseDisplay.setTextColor(Color.YELLOW);
        Log.i("LogInFragment  LoginNumber ", "assigned ");

         Button confirm = (Button) view.findViewById(R.id.btnConfirmUsername);
        Button change = (Button) view.findViewById(R.id.btnChangeUserName);
        final String[] dataPassed = new String[] {name, licenseNum};



        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent confirmIntent = new Intent(getActivity(), MenuPage.class);
                SharedPreferences dataSaved = getActivity().getSharedPreferences("TechData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = dataSaved.edit();
                editor.putString("TechName", dataPassed[0]);
                Log.i("confirm Onclick", "updating sharedpref dataSaved");
                Log.i("TechName", dataPassed[0]);
                Log.i("LicenseNum", dataPassed[1]);
                editor.putString("LicenseNum", dataPassed[1]);
                editor.commit();
                confirmIntent.putExtra("firstName_lastName", dataPassed);
                startActivity(confirmIntent);
            }
        });

        //could put this here as well???
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent changeUsername = new Intent(getActivity(), MainActivity.class);
                changeUsername.putExtra("FromUsernameCheck", false);
                startActivity(changeUsername);
            }
        });

        return view;
    }


}
