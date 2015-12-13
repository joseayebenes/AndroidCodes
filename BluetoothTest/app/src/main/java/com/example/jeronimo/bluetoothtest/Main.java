package com.example.jeronimo.bluetoothtest;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class Main extends ActionBarActivity {

    public final String TAG = "Main";

    /*private SeekBar elevation;
    private TextView debug;*/
    private TextView status;
    private Bluetooth bt;

    private Button up, down, left, right, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        up = (Button)findViewById(R.id.upButton);
        down = (Button)findViewById(R.id.downButton);
        left = (Button)findViewById(R.id.leftButton);
        right = (Button)findViewById(R.id.rightButton);
        stop = (Button)findViewById(R.id.stopButton);

        /*findViewById(R.id.upButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bt.sendMessage("L");
            }
        });

        findViewById(R.id.upButton).setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                bt.sendMessage("L");
                return true;
            }
        });*/

        findViewById(R.id.upButton).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) bt.sendMessage("F");
                //if(motionEvent.getAction() == MotionEvent.ACTION_UP) bt.sendMessage("l");
                return true;
            }
        });

        findViewById(R.id.downButton).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) bt.sendMessage("B");
                //if(motionEvent.getAction() == MotionEvent.ACTION_UP) bt.sendMessage("l");
                return true;
            }
        });

        findViewById(R.id.rightButton).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) bt.sendMessage("R");
                //if(motionEvent.getAction() == MotionEvent.ACTION_UP) bt.sendMessage("l");
                return true;
            }
        });

        findViewById(R.id.leftButton).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) bt.sendMessage("L");
                //if(motionEvent.getAction() == MotionEvent.ACTION_UP) bt.sendMessage("l");
                return true;
            }
        });

        findViewById(R.id.stopButton).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) bt.sendMessage("S");
                //if(motionEvent.getAction() == MotionEvent.ACTION_UP) bt.sendMessage("l");
                return true;
            }
        });

        /*debug = (TextView) findViewById(R.id.textDebug);
        status = (TextView) findViewById(R.id.textStatus);

        findViewById(R.id.restart).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                connectService();
            }
        });

        elevation = (SeekBar) findViewById(R.id.seekBar);
        elevation.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("Seekbar","onStopTrackingTouch ");
                int progress = seekBar.getProgress();
                String p = String.valueOf(progress);
                debug.setText(p);
                bt.sendMessage(p);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("Seekbar","onStartTrackingTouch ");
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Log.d("Seekbar", "onProgressChanged " + progress);
            }
        });*/

        bt = new Bluetooth(this, mHandler);
        connectService();

    }

    public void connectService(){
        try {
            //status.setText("Connecting...");
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (bluetoothAdapter.isEnabled()) {
                bt.start();
                bt.connectDevice("HC-06");
                Log.d(TAG, "Btservice started - listening");
                //status.setText("Connected");
            } else {
                Log.w(TAG, "Btservice started - bluetooth is not enabled");
                //status.setText("Bluetooth Not enabled");
            }
        } catch(Exception e){
            Log.e(TAG, "Unable to start bt ", e);
            //status.setText("Unable to connect " +e);
        }
    }


    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Bluetooth.MESSAGE_STATE_CHANGE:
                    Log.d(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
                    break;
                case Bluetooth.MESSAGE_WRITE:
                    Log.d(TAG, "MESSAGE_WRITE ");
                    break;
                case Bluetooth.MESSAGE_READ:
                    Log.d(TAG, "MESSAGE_READ ");
                    break;
                case Bluetooth.MESSAGE_DEVICE_NAME:
                    Log.d(TAG, "MESSAGE_DEVICE_NAME "+msg);
                    break;
                case Bluetooth.MESSAGE_TOAST:
                    Log.d(TAG, "MESSAGE_TOAST "+msg);
                    break;
            }
        }
    };

}
