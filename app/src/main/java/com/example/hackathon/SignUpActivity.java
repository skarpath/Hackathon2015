package com.example.hackathon;


import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SignUpActivity extends Activity {


	String email, password, confrimPassword, usertype;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		Parse.initialize(this, "PFGGNzxy9lafjalixHiKUvDlFu3WCWoJcFfEXAPz", "4gEtYg6QhBrEoKVzioF3K39ajofDL82h1xNsyhrw");


		findViewById(R.id.signUpBtn).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				int error = 0;

				// GATHER THE INPUTS
				EditText input;

				input = (EditText) findViewById(R.id.signInUserName);
				email = input.getText().toString();
				if(email.equals("")) {
					error = 1;
					Toast.makeText(getApplicationContext(), "Please Enter your email adress", Toast.LENGTH_SHORT).show();
					return;
				}


				if( !email.contains("@")) {
					error = 1;
					Toast.makeText(getApplicationContext(), "Please enter a valid email adress", Toast.LENGTH_SHORT).show();
					return;
				}
				if( !email.contains(".com")) {
					error = 1;
					Toast.makeText(getApplicationContext(), "Please enter a valid email adress", Toast.LENGTH_SHORT).show();
					return;
				}



				input = (EditText) findViewById(R.id.signUpUserPassword);
				password = input.getText().toString();
				if(password.equals("")) {
					error = 1;
					Toast.makeText(getApplicationContext(), "Please Enter Password", Toast.LENGTH_SHORT).show();
					return;
				}



				input = (EditText) findViewById(R.id.UserSignUpConfirmPassword);
				confrimPassword = input.getText().toString();
				if(confrimPassword.equals("")) {
					Toast.makeText(getApplicationContext(), "Please Enter Your Password again", Toast.LENGTH_SHORT).show();
					return;
				}
				if (!password.equals(confrimPassword)) {
					Toast.makeText(getApplicationContext(), "Passwords Do Not Match", Toast.LENGTH_SHORT).show();
					return;
				}



				// Returns an integer which represents the selected radio button's ID


				// Gets a reference to our "selected" radio button
				RadioButton DonorType = (RadioButton) findViewById(R.id.donorType);

				if ( DonorType.isChecked())
				{
					usertype="Donor";
				}
				RadioButton Customer = (RadioButton) findViewById(R.id.CustomerType);
				if ( Customer.isChecked())
				{
					usertype="Customer";
				}

				// Now you can get the text or whatever you want from the "selected" radio button

				//String x = b.getText().toString();
				//Toast.makeText(getApplicationContext(), x, Toast.LENGTH_SHORT).show();

				ParseUser currentUser = ParseUser.getCurrentUser();
				if (currentUser != null) {
					// do stuff with the user
					currentUser.logOut();
				}



				//Signs up user
				if ( error == 0 )
				{


					//Signup user						
					String UserEmail = email; //SignUpFirstName.getText().toString();

					String Userpassword = password; //SignUpEmail.getText().toString();
					String UserType = usertype;//SignUpPassword.getText().toString();

					ParseUser user = new ParseUser();		



					user.setUsername(UserEmail);
					user.setPassword(Userpassword);
					user.put("usertype", UserType);



					user.signUpInBackground(new SignUpCallback() {
						@Override
						public void done(com.parse.ParseException e) {
							// TODO Auto-generated method stub
							if (e == null) {
								// Hooray! Let them use the app now.
								Toast.makeText(getApplicationContext(), "You are now signed up and logged in", Toast.LENGTH_SHORT).show();

								Intent intent = new Intent(getApplicationContext(), MainActivity.class);
								startActivity(intent);		
							} else {
								// Sign up didn't succeed. Look at the ParseException
								// to figure out what went wrong
								if ( e.toString().contains("already taken"))
								{
									Toast.makeText(getApplicationContext(), "Error in signing up - Email already registered", Toast.LENGTH_LONG).show();
								}
								else
								{
									Toast.makeText(getApplicationContext(), "Error in signing up"+e.toString(), Toast.LENGTH_LONG).show();
								}
							}
						}
					}); //end of user signup



				}


			} // end signUP


		});	

		
		findViewById(R.id.btnSignUpCancel).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(intent);

			}
		});







	}



}
