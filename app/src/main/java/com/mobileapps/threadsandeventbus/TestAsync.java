package com.mobileapps.threadsandeventbus;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import static com.mobileapps.threadsandeventbus.Constants.TAG_ERROR_ASYNC;

public class TestAsync extends AsyncTask<String, Integer, String> {
    private static final int INDEX_OF_FIRST_VALUE = 0;
    TextView tvAsyncTaskResultsDisplay;

    public TestAsync(TextView passedPara) {
        this.tvAsyncTaskResultsDisplay = passedPara;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        tvAsyncTaskResultsDisplay.setText(Constants.ASYNC_PROGRESS_PRE_STRING);

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Integer progressResult = values[INDEX_OF_FIRST_VALUE];
        String resultStringToDisplay = Constants.ASYNC_PROGRESS_RUN_STRING + progressResult;
        tvAsyncTaskResultsDisplay.setText(resultStringToDisplay);

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        String finalStringToDisplay = Constants.ASYNC_PROGRESS_END_STRING + s;
        tvAsyncTaskResultsDisplay.setText(finalStringToDisplay);

    }

    @Override
    protected String doInBackground(String... strings) {
        for (int secondCounter = 0; secondCounter < Constants.DURATION; secondCounter++) {
            try {
                Thread.sleep(Constants.ONE_SECOND_IN_MILLISECONDS);
                strings[0] = String.valueOf(secondCounter);
            } catch (Exception e) {
                Log.e(TAG_ERROR_ASYNC, "NOT SLEEPY: ", e);
            }

            publishProgress(secondCounter);
        }
        return strings[0];
    }
}
