package com.example.user.timerapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.timerapplication.R;
import com.example.user.timerapplication.ui.HourMinutePickerDialog;
import com.example.user.timerapplication.util.CountDownTimerPausable;
import com.example.user.timerapplication.util.MilsToReadableFormat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TimerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimerFragment extends Fragment
        implements CountDownTimerPausable.Listener, HourMinutePickerDialog.Listener{

    final private String TAG = TimerFragment.class.getSimpleName();
    final private long COUNTDONW_INTERVAL_MIL = 100;

    private TextView timerDisp;
    private CountDownTimerPausable countDownTimerPausable;

    public TimerFragment() {
        // Required empty public constructor
    }

    public static TimerFragment newInstance() {
        return new TimerFragment();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        countDownTimerPausable = new CountDownTimerPausable();
        countDownTimerPausable.setListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_timer, container, false);
        // Inflate the layout for this fragment

        timerDisp = (TextView) v.findViewById(R.id.timer_value);

        initButtons(v);

        return v;
    }

    private void initButtons (View v) {
        initStartButton(v);
        initPauseButton(v);
        initStopButton(v);
        initSettingButton(v);
    }

    private void initStartButton(View v) {
        final Button b = (Button) v.findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimerPausable.start();
            }
        });
    }

    private void initPauseButton(View v){
        final Button b = (Button) v.findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimerPausable.pause();
            }
        });
    }

    private void initStopButton(View v){
        final Button b = (Button) v.findViewById(R.id.button3);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimerPausable.stop();
                timerDisp.setText(MilsToReadableFormat.format(0));
            }
        });
    }

    private void initSettingButton(View v){
        final Button b = (Button) v.findViewById(R.id.button4);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final HourMinutePickerDialog d = new HourMinutePickerDialog(getActivity());
                d.setListener(TimerFragment.this);
                d.build();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void CountDownTimerTick(long milsUntilFinished) {
        timerDisp.setText(MilsToReadableFormat.format(milsUntilFinished));
    }

    @Override
    public void CountDownTimerFinish() {
        timerDisp.setText(MilsToReadableFormat.format(0));
    }

    @Override
    public void onPositiveClicked(long mils) {
        timerDisp.setText(MilsToReadableFormat.format(mils));
        countDownTimerPausable.init(mils, COUNTDONW_INTERVAL_MIL);
    }
}
