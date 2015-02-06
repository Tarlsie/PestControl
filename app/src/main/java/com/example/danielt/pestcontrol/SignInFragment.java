package com.example.danielt.pestcontrol;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SignInFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SignInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignInFragment extends Fragment {

    PestDBData dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View signInFrag = inflater.inflate(R.layout.fragment_sign_in, container,false);

        Button signInButton = (Button) signInFrag.findViewById(R.id.btnSignInEnter);

        View.OnClickListener signInButtonClick = (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText signInName = (EditText)signInFrag.findViewById(R.id.edVSignInname);
                EditText licenseNum = (EditText) signInFrag.findViewById(R.id.edVSignInIDNum);
                final String technicianName = signInName.getText().toString();
                final String license = licenseNum.getText().toString();
                Log.d("onclick techlic data", technicianName+"  "+license);

                Runnable saveData = new Runnable() {
                    @Override
                    public void run() {

                        SharedPreferences dataSaved = getActivity().getSharedPreferences("TechData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = dataSaved.edit();
                        editor.putString("TechName", technicianName);
                        editor.putString("LicenseNum", license);
                        editor.commit();
                        dbHelper = new PestDBData(getActivity());
                        boolean result = dbHelper.insertUserNPass(getActivity(), technicianName, license);

                        if (result)
                            Log.i("Return db insert", "success");

                        else
                            Log.i("Return db insert", "NOT success");


                    }
                };


               Handler saveID = new Handler();
                saveID.post(saveData);

              Runnable end = new Runnable() {
                    @Override
                    public void run() {
                        Intent signInButton = new Intent(getActivity(), MenuPage.class );
                        startActivity(signInButton);
                    }
                };

                Handler hand = new Handler();
                hand.postDelayed(end,5000);

            }
        });

        signInButton.setOnClickListener(signInButtonClick);

        return signInFrag;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
