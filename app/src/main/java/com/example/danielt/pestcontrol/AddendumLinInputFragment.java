package com.example.danielt.pestcontrol;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddendumLinInputFragment extends Fragment {


    public AddendumLinInputFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view= inflater.inflate(R.layout.fragment_addendum_lin_input, container, false);

        EditText addendumComment1 = (EditText)view.findViewById(R.id.edTAddendumFragComment);
        String addenComment1 = addendumComment1.getText().toString();
        //save input
        return view;
    }

    public void setReportText(String input){
        TextView report = (TextView)getView().findViewById(R.id.txtVAddendumFragReportNum);
        report.setText("Report "+input+" :");
    }
}
