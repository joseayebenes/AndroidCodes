package com.example.jeronimo.bluetoothtest;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
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
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Main extends ActionBarActivity {

    public final String TAG = "Main";

    private TextView status;
    private Bluetooth bt;

    private Button up, down, left, right, connect, disconnect;
    private ToggleButton velButton;

    private boolean forward, backward, leftt, rightt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        up = (Button)findViewById(R.id.upButton);
        down = (Button)findViewById(R.id.downButton);
        left = (Button)findViewById(R.id.leftButton);
        right = (Button)findViewById(R.id.rightButton);
        connect = (Button)findViewById(R.id.connectButton);
        disconnect = (Button)findViewById(R.id.disconnectButton);
        status = (TextView)findViewById(R.id.textView);
        velButton = (ToggleButton)findViewById(R.id.toggleButton);

        findViewById(R.id.downButton).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    backward = true;
                    if(forward) bt.sendMessage("S");
                    else if(leftt) bt.sendMessage("E");
                    else if(rightt) bt.sendMessage("D");
                    else bt.sendMessage("B");
                }
                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    backward = false;
                    if(forward) bt.sendMessage("F");
                    else if(leftt) bt.sendMessage("L");
                    else if(rightt) bt.sendMessage("R");
                    else bt.sendMessage("S");
                }
                return true;
            }
        });

        findViewById(R.id.rightButton).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    rightt = true;
                    if(forward) bt.sendMessage("C");
                    else if(backward) bt.sendMessage("D");
                    else if(leftt) bt.sendMessage("S");
                    else bt.sendMessage("R");
                }
                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    rightt = false;
                    if(forward) bt.sendMessage("F");
                    else if(backward) bt.sendMessage("B");
                    else if(leftt) bt.sendMessage("L");
                    else bt.sendMessage("S");
                }
                return true;
            }
        });

        findViewById(R.id.leftButton).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    leftt = true;
                    if(forward) bt.sendMessage("A");
                    else if(backward) bt.sendMessage("E");
                    else if(rightt) bt.sendMessage("S");
                    else bt.sendMessage("L");
                }
                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    leftt = false;
                    if(forward) bt.sendMessage("F");
                    else if(backward) bt.sendMessage("B");
                    else if(rightt) bt.sendMessage("R");
                    else bt.sendMessage("S");
                }
                return true;
            }
        });

        findViewById(R.id.upButton).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    forward = true;
                    if(backward) bt.sendMessage("S");
                    else if(leftt) bt.sendMessage("A");
                    else if(rightt) bt.sendMessage("C");
                    else bt.sendMessage("F");
                }
                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    forward = false;
                    if(backward) bt.sendMessage("B");
                    else if(leftt) bt.sendMessage("L");
                    else if(rightt) bt.sendMessage("R");
                    else bt.sendMessage("S");
                }
                return true;
            }
        });

        findViewById(R.id.connectButton).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    bt = new Bluetooth(Main.this, mHandler);
                    connectService();
                }
                return true;
            }
        });

        findViewById(R.id.disconnectButton).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    bt.stop();
                    status.setText(R.string.initialText);
                }
                return true;
            }
        });

        velButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    velButton.setText(R.string.fastVel);
                    bt.sendMessage("V");
                } else {
                    velButton.setText(R.string.normalVel);
                    bt.sendMessage("v");
                }
            }
        });

        bt = new Bluetooth(this, mHandler);
    }

    public void connectService(){
        try {
            status.setText(R.string.connectingText);
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if(bluetoothAdapter == null){
                Toast.makeText(getApplicationContext(), "Bluetooth Device Not Available", Toast.LENGTH_LONG).show();
                finish();
            }
            if (bluetoothAdapter.isEnabled()) {
                bt.start();
                bt.connectDevice("HC-06");
                Log.d(TAG, "Btservice started - listening");
                status.setText(R.string.connectedText);
            } else {
                Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(turnBTon,1);
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
