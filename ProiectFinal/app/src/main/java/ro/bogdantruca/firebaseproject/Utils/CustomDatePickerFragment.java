package ro.bogdantruca.firebaseproject.Utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class CustomDatePickerFragment extends DialogFragment implements Constants {
    private int flag;

    private int dayStartDate;
    private int monthStartDate;
    private int yearStartDate;

    private int dayEndDate;
    private int monthEndDate;
    private int yearEndDate;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setDate(int day, int month, int year) {
        switch(getFlag()) {
            case START_DATE:
                dayStartDate = day;
                monthStartDate = month;
                yearStartDate = year;
                break;
            case END_DATE:
                dayEndDate = day;
                monthEndDate = month;
                yearEndDate = year;
                break;
        }
    }

    public boolean checkDate() {
        if (dayStartDate == 0 || monthStartDate == 0 || yearStartDate == 0 ||
                dayEndDate == 0 || monthEndDate == 0 || yearEndDate == 0) {
            return false;
        }

        if (yearEndDate < yearStartDate) {
            return false;
        }

        if (monthEndDate < monthStartDate && yearEndDate == yearStartDate) {
            return false;
        }

        if (dayEndDate < dayStartDate && monthEndDate == monthStartDate && yearEndDate == yearStartDate) {
            return false;
        }

        return true;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
    }
}