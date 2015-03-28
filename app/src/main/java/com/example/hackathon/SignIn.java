package com.example.hackathon;



import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class SignIn extends Activity {
	
	String UserEmail, password; 
	
	@Override	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);
		
		
		Parse.initialize(this, "PFGGNzxy9lafjalixHiKUvDlFu3WCWoJcFfEXAPz", "4gEtYg6QhBrEoKVzioF3K39ajofDL82h1xNsyhrw");
		
		findViewById(R.id.btnSignInSubmit).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				
				//Check if is not null
				int error = 0;
				
				// GATHER THE INPUTS
				EditText input;

				input = (EditText) findViewById(R.id.signInUserName);
				UserEmail = input.getText().toString();
				if(UserEmail.equals("")) {
					error = 1;
					Toast.makeText(getApplicationContext(), "Please Enter your email adress", Toast.LENGTH_SHORT).show();
					return;
				}				
				
				if( !UserEmail.contains("@")) {
					error = 1;
					Toast.makeText(getApplicationContext(), "Please enter a valid email adress", Toast.LENGTH_SHORT).show();
					return;
				}
				if( !UserEmail.contains(".com")) {
					error = 1;
					Toast.makeText(getApplicationContext(), "Please enter a valid email adress", Toast.LENGTH_SHORT).show();
					return;
				}
				input = (EditText) findViewById(R.id.signInUserPassword);
				password = input.getText().toString();
				if(password.equals("")) {
					error = 1;
					Toast.makeText(getApplicationContext(), "Please Enter Password", Toast.LENGTH_SHORT).show();
					return;
				}
				
				

				//Sign in User
				if (error == 0)					
				{
					ParseUser.logInInBackground(UserEmail, password, new LogInCallback() {
						  public void done(ParseUser user, ParseException e) {
						    if (user != null) {
						      // Hooray! The user is logged in.
						    	// Hooray! The user is logged in.
								Toast.makeText(getApplicationContext(), "User has successfully signed in.", Toast.LENGTH_SHORT).show();
								Intent intent = new Intent(getApplicationContext(), MainActivity.class);
								startActivity(intent);	
								
								
						    } else {
						      // Signup failed. Look at the ParseException to see what happened.
						    	Toast.makeText(getApplicationContext(), "Error signing in - Check username/password. ", Toast.LENGTH_SHORT).show();
						    }
						  }
						});
				}

				
			}
		});
		
		findViewById(R.id.btnSignInCancel).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(intent);
			}

				
		});
		
		
	}
	
	
	
}
