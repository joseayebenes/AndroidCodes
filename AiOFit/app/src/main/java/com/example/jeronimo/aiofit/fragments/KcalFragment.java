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


public class KcalFragment extends Fragment {

    private EditText weightText, heightText, yearText;
    private TextView basalView, sedentarioView, ligeroView, moderadoView, fuerteView, ffuerteView;
    private Button calcButton;

    public static KcalFragment newInstance() {
        KcalFragment fragment = new KcalFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public KcalFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kcal, container, false);
        weightText = (EditText) view.findViewById(R.id.weightText);
        heightText = (EditText) view.findViewById(R.id.heightText);
        yearText = (EditText) view.findViewById(R.id.yearText);
        basalView = (TextView) view.findViewById(R.id.basalText);
        sedentarioView = (TextView) view.findViewById(R.id.sedentarioText);
        ligeroView = (TextView) view.findViewById(R.id.ligeroText);
        moderadoView = (TextView) view.findViewById(R.id.moderadoText);
        fuerteView = (TextView) view.findViewById(R.id.fuerteText);
        ffuerteView = (TextView) view.findViewById(R.id.ffuerteText);
        calcButton = (Button) view.findViewById(R.id.calcButton);
        calcButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Double basales = 10*Double.parseDouble(weightText.getText().toString()) + 6.25*Double.parseDouble(heightText.getText().toString()) - 5*Double.parseDouble(yearText.getText().toString()) + 5;
                Double sedentario = 1.2*basales, ligero = 1.375*basales, moderado = 1.55*basales, fuerte = 1.725*basales, ffuerte = 1.9*basales;
                basalView.setText(String.format("%.2f", basales) + " Kcal");
                sedentarioView.setText(String.format("%.2f", sedentario) + " Kcal");
                ligeroView.setText(String.format("%.2f", ligero) + " Kcal");
                moderadoView.setText(String.format("%.2f", moderado) + " Kcal");
                fuerteView.setText(String.format("%.2f", fuerte) + " Kcal");
                ffuerteView.setText(String.format("%.2f", ffuerte) + " Kcal");
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
