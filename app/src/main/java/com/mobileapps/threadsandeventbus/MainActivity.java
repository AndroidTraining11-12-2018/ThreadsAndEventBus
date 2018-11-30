package com.mobileapps.threadsandeventbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    TextView tvThreadResultsDisplay;
    TextView tvRunnableResultsDisplay;
    TextView tvAsyncResultsDisplay;
    TextView tvEventBusReturn;
    EditText etMessageToSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvThreadResultsDisplay = findViewById(R.id.tvThreadReturnDisplay);
        tvRunnableResultsDisplay = findViewById(R.id.tvRunnableReturnDisplay);
        tvAsyncResultsDisplay= findViewById(R.id.tvAsyncReturnDisplay);
        tvEventBusReturn = findViewById(R.id.tvEventBusReturn);
        etMessageToSend = findViewById(R.id.etEventBusMsg);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
       EventBus.getDefault().unregister(this);
    }

    public void onButtonClicked(View view) {

        switch (view.getId()) {
            case R.id.btnStartThread:
                TestThreads testThreads = new TestThreads(tvThreadResultsDisplay);
                testThreads.start();
                break;
            case R.id.btnStartRunnable:
                TestRunnables testRunnables = new TestRunnables(tvRunnableResultsDisplay);
                Thread threadForRunnable = new Thread(testRunnables);
                threadForRunnable.start();
                break;
            case R.id.btnStartAsync:
                TestAsync testAsync = new TestAsync(tvAsyncResultsDisplay);
                testAsync.execute("Starting");
                break;
           // case R.id.btnSendMessageEvent:

            }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MyMessageEvents event) {
        String msgRecieved = event.getEventMessage();
        tvEventBusReturn.setText(msgRecieved);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(AsyncEvent event) {
        String msgRecieved = event.getMessage();
        tvEventBusReturn.setText(msgRecieved);
        Toast.makeText(this,msgRecieved,  Toast.LENGTH_SHORT).show();
    }



}
