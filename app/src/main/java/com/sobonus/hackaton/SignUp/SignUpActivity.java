package com.sobonus.hackaton.SignUp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.sobonus.hackaton.R;
import com.sobonus.hackaton.Transfer.TransferActivity;


/**
 * Created by vicknesh on 6/13/2015.
 */
public class SignUpActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        final EditText etMobileNumber = (EditText) findViewById(R.id.etMobileNumber);
        final EditText etNewPassword = (EditText) findViewById(R.id.etNewPassword);
        final EditText etNewPasswordAgain = (EditText) findViewById(R.id.etNewPasswordAgain);
        final EditText etNewEmail = (EditText) findViewById(R.id.etNewEmail);
        final Button registerationbutton = (Button) findViewById(R.id.registerationbutton);


        registerationbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String mobilenumber = etMobileNumber.getText().toString();
                String password = etNewPassword.getText().toString();
                String confirmpassword = etNewPasswordAgain.getText().toString();
                String email = etNewEmail.getText().toString();

                if (password.length() == 0 || confirmpassword.length() == 0 || mobilenumber.length() == 0) {
                    Toast.makeText(v.getContext(), "Please fill the login", Toast.LENGTH_LONG).show();
                    return;
                } else {


                    ParseUser user = new ParseUser();

                    user.setUsername(mobilenumber);
                    user.setPassword(password);
                    user.setEmail(email);



                    user.signUpInBackground(new SignUpCallback() {
                        public void done(ParseException e) {
                            if (e == null) {
                                // Hooray! Let them use the app now.
                                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), TransferActivity.class);
                                startActivity(intent);
                            } else {
                                // Sign up didn't succeed. Look at the ParseException
                                // to figure out what went wrong
                                Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_LONG).show();
                            }
                        }
                    });





                }




            }
        });


    }
}
