package com.example.jeronimo.aiofit.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jeronimo.aiofit.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RMFragment extends Fragment {

    private Button calcButton, calcButton2;
    private EditText weightText, repText, weightText2, repText2;
    private TextView result1Text, resultText2;

    public static RMFragment newInstance() {
        RMFragment fragment = new RMFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public RMFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rm, container, false);
        weightText = (EditText) view.findViewById(R.id.weightText);
        repText = (EditText) view.findViewById(R.id.repText);
        result1Text = (TextView) view.findViewById(R.id.result1Text);
        calcButton = (Button) view.findViewById(R.id.calcButton);
        calcButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Double aux = Double.parseDouble(weightText.getText().toString())/(1.0278 - 0.0278*(Double.parseDouble(repText.getText().toString())));
                String result = String.format("%.2f", aux);
                result1Text.setText(result);
            }
        });
        weightText2 = (EditText) view.findViewById(R.id.weightText2);
        repText2 = (EditText) view.findViewById(R.id.repText2);
        resultText2 = (TextView) view.findViewById(R.id.resultText2);
        calcButton2 = (Button) view.findViewById(R.id.calcButton2);
        calcButton2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Double aux = 0.033*Double.parseDouble(weightText2.getText().toString())*Double.parseDouble(repText2.getText().toString())+Double.parseDouble(weightText2.getText().toString());
                String result = String.format("%.2f", aux).toString();
                resultText2.setText(result);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }



    @Override
    public void onDetach() {
        super.onDetach();
    }

}
