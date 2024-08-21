package sih.practice.teststs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;

public class Authentication extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
SignInButton sign;
private GoogleApiClient mGoogleApiClient;
private GoogleSignInClient mGoogleSignInClient;
private static final int RC_SIGN_IN = 007;
    /*GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build();*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        sign = (SignInButton) findViewById(R.id.SignIn);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        sign.setOnClickListener(new SignInButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);

    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
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
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Sign", "signInResult:failed code= " + e.getStatusCode());
            updateUI(null);
        }
    }

    private void updateUI(GoogleSignInAccount acc) {
        if (acc!=null) {
            Log.d("Sign","Welcome "+acc.getDisplayName()+" Id = "+acc.getId());
            //UserDB obj = new UserDB();
            //obj.InsertData(""+acc.getId(),""+acc.getDisplayName(),"",""+acc.getEmail(),"","");
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            Uri photo = acc.getPhotoUrl();
            Log.d("Sign","URI "+photo.toString()+" Email "+acc.getEmail()+" Name "+acc.getDisplayName());
            i.putExtra("UID",acc.getId());
            i.putExtra("Photo",photo.toString());
            i.putExtra("Name",acc.getDisplayName());
            i.putExtra("Email",acc.getEmail());
            startActivity(i);
            finish();
        } else {
            Snackbar.make(findViewById(android.R.id.content), "Need an account", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}
