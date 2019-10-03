package com.ais.admin.isso_s;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Black on 22.09.2017.
 */

@SuppressLint("ValidFragment")
public class InfoFragment extends Fragment {

    private final static String[] items = new String[] { "Без изменений", "Незначительное ухудшение",
            "Ухудшение", "Значительное ухудшение", "Авария", "Улучшение" };         // Критерии ухудшения
    private final static Integer[] icons = new Integer[] { R.drawable.draw_1,       //
            R.drawable.draw_2, R.drawable.draw_3, R.drawable.draw_4,                // Иконки
            R.drawable.draw_6, R.drawable.draw_5};                                  //
    public SeekBar seekBar;                                                         // Ползунок
    public TextView tvAdvancedChooseRating;                                         // Текст при улучшении
    public Spinner spinnerExample;                                                  // Выпадающий список
    public int AllRating;                                                           // Полный рейтинг
    public int CurrentRating;                                                       // Текущий рейтинг
    private AddNewRating context;
    public CheckBox checkBox;
    public EditText editRatingComments;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public InfoFragment(){}

    public InfoFragment (AddNewRating context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.info_fragment_layout, container, false);
        if(!PreferenceManager.getDefaultSharedPreferences(getContext()).getString("Theme", "0").equals("0")) {
            view.findViewById(R.id.InfoLayout).setBackgroundColor(getResources().getColor(R.color.background_material_light));
        }
        // инициализация всех элементов UI
        seekBar = (SeekBar) view.findViewById(R.id.seekBarRatingUp);
        tvAdvancedChooseRating = (TextView) view.findViewById(R.id.tvAdvancedChooseRating);
        seekBar.setVisibility(View.GONE);
        tvAdvancedChooseRating.setVisibility(View.GONE);
        spinnerExample = (Spinner) view.findViewById(R.id.addRatingSpinner);
        editRatingComments = (EditText) view.findViewById(R.id.editTextCommentRating);
        editRatingComments.setImeOptions(EditorInfo.IME_ACTION_DONE);

        // Набор данных в зависимости от того, какой общий рейтинг
        ArrayList<SpinnerModel> CustomListViewValuesArr = new ArrayList<>();
        if(AllRating < 0)
            for (int i = 0; i < 6; i++ ) {
                SpinnerModel spinnerModel = new SpinnerModel();
                spinnerModel.setRating(items[i]);
                spinnerModel.setImage(icons[i]);
                CustomListViewValuesArr.add(spinnerModel);
            }
        else {
            for (int i = 0; i < 5; i++ ) {
                SpinnerModel spinnerModel = new SpinnerModel();
                spinnerModel.setRating(items[i]);
                spinnerModel.setImage(icons[i]);
                CustomListViewValuesArr.add(spinnerModel);
            }
        }

        // Создание кастомного адаптера для выпадающего списка с категориями оценок
        AddRatingAdapter adapter = new AddRatingAdapter(this, R.layout.rating_spinner_row, CustomListViewValuesArr);
        spinnerExample.setAdapter(adapter);

        // Делегируем, что происходит при выборе той или иной категории в выпадающем списке
        spinnerExample.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                switch (position) {
                    case 0:
                        // Случай при оценке "Не изменяется"
                        CurrentRating = 0;
                        seekBar.setVisibility(View.GONE);
                        tvAdvancedChooseRating.setVisibility(View.GONE);
                        if (!context.once)
                            context.changed = true;
                        break;
                    case 1:
                        // Случай при оценке "Незначительное ухудшение"
                        CurrentRating = -1;
                        seekBar.setVisibility(View.GONE);
                        tvAdvancedChooseRating.setVisibility(View.GONE);
                        if (!context.once)
                            context.changed = true;
                        break;
                    case 2:
                        // Случай при оценке "Ухудшение"
                        CurrentRating = -2;
                        seekBar.setVisibility(View.GONE);
                        tvAdvancedChooseRating.setVisibility(View.GONE);
                        if (!context.once)
                            context.changed = true;
                        break;
                    case 3:
                        // Случай при оценке "Значительное ухудшение"
                        CurrentRating = -3;
                        seekBar.setVisibility(View.GONE);
                        tvAdvancedChooseRating.setVisibility(View.GONE);
                        if (!context.once)
                            context.changed = true;
                        break;
                    case 4:
                        // Случай при оценке "Авария"
                        CurrentRating = -10;
                        seekBar.setVisibility(View.GONE);
                        tvAdvancedChooseRating.setVisibility(View.GONE);
                        if (!context.once)
                            context.changed = true;
                        break;
                    case 5:
                        // Случай при оценке "Улучшение"
                        // Если в сумме ухужшение ниже 0, то добавляем "ползунок" и выставляем максимальное значение степени улучшения как суммарный рейтинг
                        if(AllRating < -1) {
                            seekBar.setVisibility(View.VISIBLE);
                            tvAdvancedChooseRating.setVisibility(View.VISIBLE);
                            CurrentRating = seekBar.getProgress() + 1;
                            tvAdvancedChooseRating.setText(getResources().getString(R.string.advancedChooseRating) + " (1-" + -AllRating + "): " + String.valueOf(CurrentRating));
                            seekBar.setMax(-AllRating - 1);
                            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                @Override
                                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                    CurrentRating = progress + 1;
                                    tvAdvancedChooseRating.setText(getResources().getString(R.string.advancedChooseRating) + " (1-" + -AllRating + "): " + String.valueOf(CurrentRating));
                                    if(!context.once)
                                        context.changed = true;
                                }

                                @Override
                                public void onStartTrackingTouch(SeekBar seekBar) {

                                }

                                @Override
                                public void onStopTrackingTouch(SeekBar seekBar) {

                                }
                            });
                        }
                        else if (AllRating < 0) {
                            CurrentRating = 1;
                        }
                        if (!context.once)
                            context.changed = true;
                        break;
                }
                context.once = false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        editRatingComments.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!context.once)
                    context.changed = true;
            }
        });

        // Подключаем слушателя на нажатие на Linear Layout c checkBox'ом
        checkBox = (CheckBox) view.findViewById(R.id.chk);
        view.findViewById(R.id.linLayoutWithCheck).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(!checkBox.isChecked());
                context.changed = true;
            }
        });


        // Выбираем заранее какая степень ухудшения, если можно редактировать или только просмотр
        if(context.editable || context.previewed) {
            ((EditText) view.findViewById(R.id.editTextCommentRating)).setText(context.getIntent().getStringExtra("Comments"));
            ((CheckBox) view.findViewById(R.id.chk)).setChecked(context.getIntent().getIntExtra("isNeedInChecking", 0) == 1);
            switch(context.getIntent().getIntExtra("CurrentRating", 0)) {
                case 0:
                    spinnerExample.setSelection(0);
                    break;
                case -1:
                    spinnerExample.setSelection(1);
                    break;
                case -2:
                    spinnerExample.setSelection(2);
                    break;
                case -3:
                    spinnerExample.setSelection(3);
                    break;
                case -10:
                    spinnerExample.setSelection(4);
                    break;
                default:
                    if(AllRating < -1) {
                        spinnerExample.setSelection(5);
                        seekBar.setVisibility(View.VISIBLE);
                        tvAdvancedChooseRating.setVisibility(View.VISIBLE);
                        tvAdvancedChooseRating.setText(getResources().getString(R.string.advancedChooseRating) + " (1-" + -AllRating + "): " + String.valueOf(CurrentRating + 1));
                        seekBar.setMax(-AllRating - 1);
                        seekBar.setProgress(CurrentRating);
                    }
                    else if (AllRating < 0) {
                        spinnerExample.setSelection(5);
                    }
                    break;
            }
        }

        if (context.previewed) {
            spinnerExample.setClickable(false);
            spinnerExample.setEnabled(false);
            seekBar.setEnabled(false);
            view.findViewById(R.id.editTextCommentRating).setFocusable(false);
            view.findViewById(R.id.linLayoutWithCheck).setFocusable(false);
            view.findViewById(R.id.linLayoutWithCheck).setClickable(false);
        }

        return view;
    }
}
