package com.example.jeronimo.aiofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class RegisterActivity extends AppCompatActivity {

    private  EditText emailText;
    private EditText passwordText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // TODO: Comprobar el formulario
        final Firebase ref = new Firebase("https://aiofit.firebaseio.com/");
        emailText = (EditText) findViewById(R.id.email_register);
        passwordText = (EditText) findViewById(R.id.password_register);

        Button btn = (Button) findViewById(R.id.register_button_register);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.createUser(emailText.getText().toString(), passwordText.getText().toString(), new Firebase.ResultHandler() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getApplicationContext(), "Registrado", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        Log.d("REGISTER_ACTIVITY", firebaseError.getDetails());
                        Toast.makeText(getApplicationContext(),"Error: "+firebaseError.getDetails(),Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

}
