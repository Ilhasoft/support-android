package br.com.ilhasoft.support.widget;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by johncordeiro on 17/07/15.
 */
public class DatePickerFragment extends DialogFragment {

    private static final String EXTRA_MAX_DATE = "maxDate";

    private static final int INVALID_VALUD = -1;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    private long maxDate;

    public static DatePickerFragment newInstance(Date maxDate) {
        Bundle args = new Bundle();
        args.putLong(EXTRA_MAX_DATE, maxDate.getTime());

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        maxDate = getArguments() != null ? getArguments().getLong(EXTRA_MAX_DATE, INVALID_VALUD) : INVALID_VALUD;

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), onDateSetListener, year, month, day);
        setMaxDate(datePickerDialog);
        return datePickerDialog;
    }

    private void setMaxDate(DatePickerDialog datePickerDialog) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB && maxDate != INVALID_VALUD) {
            DatePicker datePicker = datePickerDialog.getDatePicker();
            datePicker.setMaxDate(Calendar.getInstance().getTimeInMillis());
        }
    }

    public void setOnDateSetListener(DatePickerDialog.OnDateSetListener onDateSetListener) {
        this.onDateSetListener = onDateSetListener;
    }
}
