package com.codetroopers.betterpickers.sample.activity.hmspicker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.codetroopers.betterpickers.hmspicker.HmsPickerBuilder;
import com.codetroopers.betterpickers.hmspicker.HmsPickerDialogFragment;
import com.codetroopers.betterpickers.sample.R;
import com.codetroopers.betterpickers.sample.activity.BaseSampleActivity;

/**
 * User: derek Date: 3/17/13 Time: 3:59 PM
 */
public class SampleHmsMultipleHandlers extends BaseSampleActivity implements HmsPickerDialogFragment.HmsPickerDialogHandlerV2 {

    private TextView mResultTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_and_button);

        mResultTextView = (TextView) findViewById(R.id.text);
        Button button = (Button) findViewById(R.id.button);

        mResultTextView.setText(R.string.no_value);
        button.setText(R.string.hms_picker_set);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HmsPickerBuilder hpb = new HmsPickerBuilder()
                        .setFragmentManager(getSupportFragmentManager())
                        .setStyleResId(R.style.BetterPickersDialogFragment)
                        .addHmsPickerDialogHandler(new MyCustomHandler());
                hpb.show();
            }
        });
    }

    class MyCustomHandler implements HmsPickerDialogFragment.HmsPickerDialogHandler {

        @Override
        public void onDialogHmsSet(int reference, int hours, int minutes, int seconds) {
            Toast.makeText(SampleHmsMultipleHandlers.this, "MyCustomHandler onDialogHmsSet!", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public void onDialogHmsSet(int reference, boolean isNegative, int hours, int minutes, int seconds) {
        mResultTextView.setText(getString(R.string.hms_picker_result_value_multiline, hours, minutes, seconds, isNegative));
    }
}
