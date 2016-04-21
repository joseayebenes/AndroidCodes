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
 * {@link MacroFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MacroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MacroFragment extends Fragment {

    private EditText kcal,kg,prot,gras;
    private TextView prote,grasa,carb;
    private Button calcular;

    public static MacroFragment newInstance() {
        MacroFragment fragment = new MacroFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public MacroFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_macro, container, false);
        kcal = (EditText) view.findViewById(R.id.kcalText);
        kg = (EditText) view.findViewById(R.id.kgText);
        prot = (EditText) view.findViewById(R.id.protText);
        gras = (EditText) view.findViewById(R.id.fatText);
        prote = (TextView) view.findViewById(R.id.proteinaView);
        grasa = (TextView) view.findViewById(R.id.fatView);
        carb = (TextView) view.findViewById(R.id.carbView);
        calcular = (Button) view.findViewById(R.id.calcButtonMacr);
        calcular.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Double proteina = Double.parseDouble(prot.getText().toString())*Double.parseDouble(kg.getText().toString());
                Double grass = Double.parseDouble(gras.getText().toString())*Double.parseDouble(kg.getText().toString());
                Double carbo = (Double.parseDouble(kcal.getText().toString())-4*proteina - 9*grass)/4;
                prote.setText(String.format("%.2f", proteina) + " gr");
                grasa.setText(String.format("%.2f", grass) + " gr");
                carb.setText(String.format("%.2f", carbo) + " gr");
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
