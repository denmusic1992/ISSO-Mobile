package com.development.aisisso.isso_r;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import java.util.Calendar;

/**
 * Created by Black on 30.05.2017.
 */
public class MonthYearPickerDialog extends DialogFragment {
    private DatePickerDialog.OnDateSetListener listener;

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final Calendar cal = Calendar.getInstance();

        View dialog = inflater.inflate(R.layout.date_picker_dialog, null);
        final NumberPicker monthPicker = (NumberPicker) dialog.findViewById(R.id.picker_month);
        final NumberPicker yearPicker = (NumberPicker) dialog.findViewById(R.id.picker_year);

        monthPicker.setMinValue(1);
        monthPicker.setMaxValue(12);
        monthPicker.setValue(cal.get(Calendar.MONTH) + 1);
        monthPicker.setDisplayedValues(new String[]{"январь", "февраль", "март", "апрель", "май",
                "июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"});
        monthPicker.setWrapSelectorWheel(false);

        int year = 1970;
        yearPicker.setMinValue(year);
        yearPicker.setMaxValue(cal.get(Calendar.YEAR));
        yearPicker.setValue(cal.get(Calendar.YEAR));
        yearPicker.setWrapSelectorWheel(false);

        builder.setView(dialog)
                // Add action buttons
                .setPositiveButton("Выбрать", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // здесь выбираем, какое число ставить; если месяц с годом совпадают, то ту же дату и ставим
                        if(cal.get(Calendar.YEAR) == yearPicker.getValue() && (cal.get(Calendar.MONTH) + 1) == monthPicker.getValue())
                            listener.onDateSet(null, yearPicker.getValue(), monthPicker.getValue(), cal.get(Calendar.DATE));
                        else {
                            // иначе смотрим, совпадают ли годы
                            if (yearPicker.getValue() == cal.get(Calendar.YEAR)) {
                                if(monthPicker.getValue() > (cal.get(Calendar.MONTH) + 1))
                                    listener.onDateSet(null, yearPicker.getValue(), monthPicker.getValue(), 1);
                                else
                                    listener.onDateSet(null, yearPicker.getValue(), monthPicker.getValue(),
                                            returnDate(yearPicker.getValue(), monthPicker.getValue()));
                            }
                            // если год предыдущий, то в любом случае ставим дату с последним числом месяца
                            else {
                                listener.onDateSet(null, yearPicker.getValue(), monthPicker.getValue(),
                                        returnDate(yearPicker.getValue(), monthPicker.getValue()));
                            }
                        }
                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MonthYearPickerDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    private int returnDate(int year, int month) {
        int date = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                date = 31;
                break;
            case 2:
                if(year % 4 == 0)
                    date = 29;
                else
                    date = 28;
                break;
            default:
                date = 30;
                break;
        }
        return date;
    }
}
