package ru.development.aisisso.maincore;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;

public class TablesFragment extends Fragment implements Serializable{

    private View v;
    private TextView tvFar;
    private TextView tvFarDistance;
    private TextView tvNear;
    private TextView tvNearDistance;
    private TextView tvBehind;
    private TextView tvBehindDistance;

    public String getTVText() {
        return this.tvFar.getText().toString();
    }

    public void setTVText(String x, String y) {
        this.tvFar.setText(x);
        this.tvFarDistance.setText(y);
        this.tvNear.setText(x);
        this.tvNearDistance.setText(y);
        this.tvBehind.setText(x);
        this.tvBehindDistance.setText(y);
    }

    public void setTheRest (HttpsIsso isso, TextView tv, int rating, Drawable[] draw) {
        try {
            String str = String.format("%.3f", isso.WIsso);
            str = str.replaceAll(",", "+");
            str = str.replaceAll("\\.", "+");
            SpannableString ss1 = new SpannableString("   " + isso.Name + "\n" + String.format("%s, км %s (%s)\n", isso.DorName, str, isso.Obstacle));
            Drawable d;
            switch (rating) {
                case 20:
                    d = draw[1];
                    break;
                case 30:
                    d = draw[2];
                    break;
                case 40:
                    d = draw[3];
                    break;
                case 50:
                    d = draw[4];
                    break;
                case 60:
                    d = draw[5];
                    break;
                case 70:
                    d = draw[6];
                    break;
                default:
                    d = draw[0];
                    break;
            }

            assert d != null;
             d.setBounds(0, 0, 20, 20);
            ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
            ss1.setSpan(span, 0, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

            tv.setText(ss1);
            tv.setTag(isso.CIsso);

            final TextView finalTv = tv;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), Autoselect.issoActivity.getClass());
                    intent.putExtra("C_ISSO", finalTv.getTag().toString());
                    startActivity(intent);
                }
            });
        } catch (IllegalStateException e) {
            if(!isAdded()) {
                return;
            }
        }
    }

    public void setTextForTables (ArrayVector hashMap, HttpsIsso[] listOfIssos, int[] rating, Drawable[] draw) {
        if(hashMap.getIndex(0) != -1)
            tvNearDistance.setText(String.format("%.0f м", hashMap.getDistance(0)));
        else
            tvNearDistance.setText("");
        if(hashMap.getIndex(1) != -1)
            tvFarDistance.setText(String.format("%.0f м", hashMap.getDistance(1)));
        else
            tvFarDistance.setText("");
        if(hashMap.getIndex(2) != -1)
            tvBehindDistance.setText(String.format("%.0f м", hashMap.getDistance(2)));
        else
            tvBehindDistance.setText("");


        if(hashMap.getIndex(0) != -1) {
            setTheRest(listOfIssos[hashMap.getIndex(0)], tvNear, rating[hashMap.getIndex(0)], draw);
        }
        else
            tvNear.setText("Впереди нет ближайшего объекта.");
        if(hashMap.getIndex(1) != -1) {
            setTheRest(listOfIssos[hashMap.getIndex(1)], tvFar, rating[hashMap.getIndex(0)], draw);
        }
        else
            tvFar.setText("Впереди нет следующего за ближайшим объекта.");
        if(hashMap.getIndex(2) != -1) {
            setTheRest(listOfIssos[hashMap.getIndex(2)], tvBehind, rating[hashMap.getIndex(0)], draw);
        }
        else {
            tvBehind.setText("Позади нет ближайшего объекта.");
            tvBehind.setClickable(false);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        /** Обьявляем layout с таблицей */
        setRetainInstance(true);
        if(v != null)
            v.destroyDrawingCache();
        v = inflater.inflate(R.layout.table_layout, container, false);
        if( PreferenceManager.getDefaultSharedPreferences(v.getContext()).getString("Theme", "0").equals("0")) {
            tvFar = (TextView) v.findViewById(R.id.textViewFar);
            tvFar.setTextColor(getResources().getColor(R.color.textAheadDark));
            //tvFar.setBackgroundResource(R.drawable.row_border_dark);
            tvFarDistance = (TextView) v.findViewById(R.id.textViewFarDistance);
            tvFarDistance.setTextColor(getResources().getColor(R.color.textAheadDark));
            tvNear = (TextView) v.findViewById(R.id.textViewNear);
            tvNear.setTextColor(getResources().getColor(R.color.textAheadDark));
            //tvNear.setBackgroundResource(R.drawable.row_border_dark);
            tvNearDistance = (TextView) v.findViewById(R.id.textViewNearDistance);
            tvNearDistance.setTextColor(getResources().getColor(R.color.textAheadDark));
            tvBehind = (TextView) v.findViewById(R.id.textViewBehind);
            tvBehind.setTextColor(getResources().getColor(R.color.textBehindDark));
            //tvBehind.setBackgroundResource(R.drawable.row_border_dark);
            tvBehindDistance = (TextView) v.findViewById(R.id.textViewBehindDistance);
            tvBehindDistance.setTextColor(getResources().getColor(R.color.textBehindDark));
            /*((TextView) v.findViewById(R.id.textViewFar)).setTextColor(getResources().getColor(R.color.textAheadDark));
            ((TextView) v.findViewById(R.id.textViewFarDistance)).setTextColor(getResources().getColor(R.color.textAheadDark));
            ((TextView) v.findViewById(R.id.textViewNear)).setTextColor(getResources().getColor(R.color.textAheadDark));
            ((TextView) v.findViewById(R.id.textViewNearDistance)).setTextColor(getResources().getColor(R.color.textAheadDark));
            ((TextView) v.findViewById(R.id.textViewBehind)).setTextColor(getResources().getColor(R.color.textBehindDark));
            ((TextView) v.findViewById(R.id.textViewBehindDistance)).setTextColor(getResources().getColor(R.color.textBehindDark));
            (v.findViewById(R.id.tableLayout1)).setBackgroundResource(R.drawable.row_border_dark);
            (v.findViewById(R.id.textViewFar)).setBackgroundResource(R.drawable.row_border_dark);
            (v.findViewById(R.id.textViewNear)).setBackgroundResource(R.drawable.row_border_dark);
            (v.findViewById(R.id.textViewBehind)).setBackgroundResource(R.drawable.row_border_dark);*/
        }
        else {
            tvFar = (TextView) v.findViewById(R.id.textViewFar);
            tvFar.setTextColor(getResources().getColor(R.color.textAheadLight));
            //tvFar.setBackgroundResource(R.drawable.row_border_light);
            tvFarDistance = (TextView) v.findViewById(R.id.textViewFarDistance);
            tvFarDistance.setTextColor(getResources().getColor(R.color.textAheadLight));
            tvNear = (TextView) v.findViewById(R.id.textViewNear);
            tvNear.setTextColor(getResources().getColor(R.color.textAheadLight));
            //tvNear.setBackgroundResource(R.drawable.row_border_light);
            tvNearDistance = (TextView) v.findViewById(R.id.textViewNearDistance);
            tvNearDistance.setTextColor(getResources().getColor(R.color.textAheadLight));
            tvBehind = (TextView) v.findViewById(R.id.textViewBehind);
            tvBehind.setTextColor(getResources().getColor(R.color.textBehindLight));
            //tvBehind.setBackgroundResource(R.drawable.row_border_light);
            tvBehindDistance = (TextView) v.findViewById(R.id.textViewBehindDistance);
            tvBehindDistance.setTextColor(getResources().getColor(R.color.textBehindLight));
            /*((TextView) v.findViewById(R.id.textViewFar)).setTextColor(getResources().getColor(R.color.textAheadLight));
            ((TextView) v.findViewById(R.id.textViewFarDistance)).setTextColor(getResources().getColor(R.color.textAheadLight));
            ((TextView) v.findViewById(R.id.textViewNear)).setTextColor(getResources().getColor(R.color.textAheadLight));
            ((TextView) v.findViewById(R.id.textViewNearDistance)).setTextColor(getResources().getColor(R.color.textAheadLight));
            ((TextView) v.findViewById(R.id.textViewBehind)).setTextColor(getResources().getColor(R.color.textBehindLight));
            ((TextView) v.findViewById(R.id.textViewBehindDistance)).setTextColor(getResources().getColor(R.color.textBehindLight));
            (v.findViewById(R.id.tableLayout1)).setBackgroundResource(R.drawable.row_border_light);
            (v.findViewById(R.id.textViewFar)).setBackgroundResource(R.drawable.row_border_light);
            (v.findViewById(R.id.textViewNear)).setBackgroundResource(R.drawable.row_border_light);
            (v.findViewById(R.id.textViewBehind)).setBackgroundResource(R.drawable.row_border_light);*/
        }
        tvFar.setText("Получение координат .");
        tvFarDistance.setText(".");
        tvNear.setText("Получение координат .");
        tvNearDistance.setText(".");
        tvBehind.setText("Получение координат .");
        tvBehindDistance.setText(".");
        return v;
    }
}
