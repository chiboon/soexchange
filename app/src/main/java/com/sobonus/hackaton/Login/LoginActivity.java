package com.sobonus.hackaton.Login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.sobonus.hackaton.Home.HomeActivity;
import com.sobonus.hackaton.R;
import com.sobonus.hackaton.Transfer.TransferActivity;


/**
 * Created by vicknesh on 6/13/2015.
 */
public class LoginActivity extends Activity {

    private Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);


        final EditText etMobile = (EditText) findViewById(R.id.etMobile);
        final EditText etPassword= (EditText) findViewById(R.id.etPassword);
        Button letsgo = (Button) findViewById(R.id.letsgo);




        letsgo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Do some job here
                String mobile = etMobile.getText().toString();
                String password = etPassword.getText().toString();

                if(password.length() == 0 || mobile.length() == 0)
                {
                    Toast.makeText(v.getContext(),"Please fill the login", Toast.LENGTH_LONG).show();
                    return;
                }
                else{
                    ParseUser.logInInBackground(mobile, password, new LogInCallback() {
                        public void done(ParseUser user, ParseException e) {
                            if (user != null) {
                                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);

                            } else {
                                // Signup failed. Look at the ParseException to see what happened.\
                                Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                }





            }
        });




    }
}
