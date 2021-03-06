package com.example.user.timerapplication.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import com.example.user.timerapplication.R;

/**
 *
 */
public class HourMinutePickerDialog {

    final private String TAG = HourMinutePickerDialog.class.getSimpleName();
    final private Activity activity;
//    final private Context context;

    private NumberPicker picker1;
    private NumberPicker picker2;
    private NumberPicker picker3;
    private NumberPicker picker4;
    private Listener listener;

    private long configuredValue = 0;

    public HourMinutePickerDialog(Activity activity){
        this.activity = activity;
    }

    public void build(){
        final View v = getCustomView();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.dialog_setting);
        builder.setView(v);
        initNumberPickers(v);

        builder.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                configuredValue
                        = ((picker1.getValue() * 10 + picker2.getValue()) * 60
                        + (picker3.getValue() * 10 + picker4.getValue())) * 1000;
                Log.v(TAG, "value : " + configuredValue);
                listener.onPositiveClicked(configuredValue);
            }
        });

        builder.show();

    }

    private View getCustomView(){
        return LayoutInflater
                .from(activity)
                .inflate(R.layout.timer_setting,
                        (ViewGroup)activity.findViewById(R.id.timerSetting));
    }

    private void initNumberPickers(View v){
        picker1 = (NumberPicker) v.findViewById(R.id.numberPicker);
        picker2 = (NumberPicker) v.findViewById(R.id.numberPicker2);
        picker3 = (NumberPicker) v.findViewById(R.id.numberPicker3);
        picker4 = (NumberPicker) v.findViewById(R.id.numberPicker4);

        picker1.setMaxValue(5);
        picker1.setMinValue(0);
        picker2.setMaxValue(9);
        picker2.setMinValue(0);
        picker3.setMaxValue(5);
        picker3.setMinValue(0);
        picker4.setMaxValue(9);
        picker4.setMinValue(0);
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }
    public interface Listener {
        void onPositiveClicked(long mils);
    }
}
