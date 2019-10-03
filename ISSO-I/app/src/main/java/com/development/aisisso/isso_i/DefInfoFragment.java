package com.development.aisisso.isso_i;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DefInfoFragment extends Fragment {
    View view;
    ais7AdvancedDef def;

    public DefInfoFragment(ais7AdvancedDef def) {
        this.def = def;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.def_info_layout, container, false);
        if(MainActivity.theme.equals("0")) {
            view.findViewById(R.id.layout1).setBackground(getContext().getDrawable(R.drawable.cell_borders_dark));
            view.findViewById(R.id.layout11).setBackground(getContext().getDrawable(R.drawable.cell_shape_dark));
            view.findViewById(R.id.layout111).setBackground(getContext().getDrawable(R.drawable.cell_shape_dark));
            view.findViewById(R.id.layout112).setBackground(getContext().getDrawable(R.drawable.cell_shape_dark));
            view.findViewById(R.id.layout12).setBackground(getContext().getDrawable(R.drawable.cell_shape_dark));
            view.findViewById(R.id.layout121).setBackground(getContext().getDrawable(R.drawable.cell_shape_dark));
            view.findViewById(R.id.layout122).setBackground(getContext().getDrawable(R.drawable.cell_shape_dark));
            view.findViewById(R.id.layout2).setBackground(getContext().getDrawable(R.drawable.cell_borders_dark));
            view.findViewById(R.id.layout21).setBackground(getContext().getDrawable(R.drawable.cell_shape_dark));
            view.findViewById(R.id.layout211).setBackground(getContext().getDrawable(R.drawable.cell_shape_dark));
            view.findViewById(R.id.layout212).setBackground(getContext().getDrawable(R.drawable.cell_shape_dark));
            view.findViewById(R.id.layout22).setBackground(getContext().getDrawable(R.drawable.cell_shape_dark));
            view.findViewById(R.id.layout221).setBackground(getContext().getDrawable(R.drawable.cell_shape_dark));
            view.findViewById(R.id.layout222).setBackground(getContext().getDrawable(R.drawable.cell_shape_dark));
        }
        setInfoForDef(def);
        return view;
    }

    public void setInfoForDef(ais7AdvancedDef def) {
        ((TextView) view.findViewById(R.id.tvLocalizationDef)).setText(def.L_DEF);
        ((TextView) view.findViewById(R.id.tvAdvancedInfoDef)).setText(def.W_DEF);
        ((TextView) view.findViewById(R.id.tvRemInfoDef)).setText(def.N_REM);
        ((TextView) view.findViewById(R.id.tvRemDimensionDef)).setText("Объем ремонтной работы" + def.REM_DIMENSION);
        ((TextView) view.findViewById(R.id.tvRemSizeInfoDef)).setText(def.REM_SIZE);
    }
}
