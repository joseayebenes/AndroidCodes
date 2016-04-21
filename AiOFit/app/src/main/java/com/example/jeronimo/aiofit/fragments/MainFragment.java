package com.example.jeronimo.aiofit.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jeronimo.aiofit.R;
import com.gigamole.library.ArcProgressStackView;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.ArrayList;


public class MainFragment extends Fragment {

    private ArcProgressStackView mArcProgressStackView;
    public final static int MODEL_COUNT = 4;
    private int[] mStartColors = new int[MODEL_COUNT];
    private int[] mEndColors = new int[MODEL_COUNT];

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);


        mArcProgressStackView = (ArcProgressStackView) view.findViewById(R.id.apsv);

        final String[] startColors = getResources().getStringArray(R.array.polluted_waves);
        final String[] endColors = getResources().getStringArray(R.array.default_preview);
        final String[] bgColors = getResources().getStringArray(R.array.white);

        // Parse colors
        for (int i = 0; i < MODEL_COUNT; i++) {
            mStartColors[i] = Color.parseColor(startColors[i]);
            mEndColors[i] = Color.parseColor(endColors[i]);
        }

        // Set models
        final ArrayList<ArcProgressStackView.Model> models = new ArrayList<ArcProgressStackView.Model>();
        models.add(new ArcProgressStackView.Model("Progreso", 70, Color.parseColor(bgColors[0]), mStartColors[0]));
        models.add(new ArcProgressStackView.Model("Peso", 80, Color.parseColor(bgColors[1]), mStartColors[1]));
        models.add(new ArcProgressStackView.Model("Grasa", 15, Color.parseColor(bgColors[2]), mStartColors[2]));
        models.add(new ArcProgressStackView.Model("IMC", 24, Color.parseColor(bgColors[3]), mStartColors[3]));
        mArcProgressStackView.setModels(models);
        mArcProgressStackView.setStartAngle(135);
        mArcProgressStackView.setSweepAngle(270);
        mArcProgressStackView.setIsShadowed(false);
        mArcProgressStackView.setModelBgEnabled(false);
        mArcProgressStackView.setIsRounded(true);

        ImageView icon = new ImageView(getContext()); // Create an icon
        icon.setImageResource(R.drawable.ic_add);

        FloatingActionButton actionButton = new FloatingActionButton.Builder(getActivity())
                .setContentView(icon)
                .build();

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(getActivity());
// repeat many times:
        ImageView itemIcon = new ImageView(getContext());
        itemIcon.setImageResource(R.drawable.ic_add);
        SubActionButton button1 = itemBuilder.setContentView(itemIcon).build();

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(getActivity())
                .addSubActionView(button1)
                        // ...
                .attachTo(actionButton)
                .build();

        return view;

    }


        @Override
    public void onDetach() {
        super.onDetach();
    }


}
