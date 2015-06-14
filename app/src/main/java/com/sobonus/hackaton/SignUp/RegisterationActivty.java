package com.sobonus.hackaton.SignUp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sobonus.hackaton.Login.LoginActivity;
import com.sobonus.hackaton.R;


/**
 * Created by vicknesh on 6/13/2015.
 */
public class RegisterationActivty extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registeration_activity);

        Button login = (Button) findViewById(R.id.button_login);
        TextView signup = (TextView) findViewById(R.id.signup);

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Do some job here
                Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(login);


            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do some job here
                Intent login = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(login);


            }
        });

    }
}
