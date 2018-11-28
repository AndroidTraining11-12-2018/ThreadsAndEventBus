package com.mobileapps.threadsandeventbus;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import static com.mobileapps.threadsandeventbus.Constants.TAG_ERROR_RUNNABLE;

public class TestRunnables implements Runnable {
    private TextView tvRunnableResultsDisplay;
    private Handler runnableHandler = new Handler(Looper.getMainLooper());

    public TestRunnables(TextView tvRunnableResultsDisplay) {
        this.tvRunnableResultsDisplay = tvRunnableResultsDisplay;
    }

    @Override
    public void run() {
        for(int secondCounter = 0; secondCounter < Constants.DURATION; secondCounter++) {
            final int currentSecond = secondCounter + 1;
            try {
                Thread.sleep(Constants.ONE_SECOND_IN_MILLISECONDS);
            } catch (Exception e) {
                Log.e(TAG_ERROR_RUNNABLE, "NOT SLEEPY: ", e);;
            }
            runnableHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    tvRunnableResultsDisplay.setText(Constants.RUNNABLE_RUN_STRING + currentSecond);
                }

            }, 2 * Constants.ONE_SECOND_IN_MILLISECONDS);

        }
    }
}
