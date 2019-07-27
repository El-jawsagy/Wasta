package com.devneoxmy.wasta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

/**
 * <h1> LOGIN IN GOOGLE ACCOUNT!</h1>
 * The code allows you to sign in to a Google Account
 * In the absence of an account inform you
 *
 *
 * @author  MOHAMED QUD
 * @version 1.0
 * @since   2019-22-04
 */
public class LogIn extends AppCompatActivity {
    int RC_SIGN_IN = 0;
    SignInButton signInButton;
    GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        //Initializing Views
        signInButton = findViewById(R.id.sign_in_button);
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    // The ApiException status code indicates the detailed failure reason.
    // Please refer to the GoogleSignInStatusCodes class reference for more information.
    private void handleSignInResult(Task<GoogleSignInAccount> task) {

        Log.w("Google Sign In Error", "signInResult:failed code=" );
        Toast.makeText(LogIn.this, "Failed", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        // Check for existing Google Sign In account, if the user is already signed in
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        super.onStart();

    }

    // LogIn by Reham

    int counter;
    Button login;
    EditText username, password;
    TextView tvsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        login = findViewById(R.id.loginbtn);
        username = findViewById(R.id.name);
        password = findViewById(R.id.pass);
        tvsignup = findViewById(R.id.signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        // clickable textView "sign up"
        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupint = new Intent(LogIn.this, NewUser.class);
                startActivity(signupint);
            }
        });
    }
    //explain login method
    public void login() {
        String user = username.getText().toString();
        String pw = password.getText().toString();

        if (user.equals(username.getText().toString()) && pw.equals(password.getText().toString())) {
            Toast.makeText(this, "Redirecting", Toast.LENGTH_LONG).show();
            Intent intlog = new Intent(LogIn.this, MainActivity.class);
            startActivity(intlog);

        } else {
            Toast.makeText(this, "Incorrect user name or password", Toast.LENGTH_LONG).show();
            //to give user 3 trials for username & password entry.
            counter = 3;
            counter--;
            if (counter == 0) {
                login.setEnabled(false);
                Toast.makeText(this, "forgot your password?", Toast.LENGTH_LONG).show();
            }
        }

}

