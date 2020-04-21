package com.example.tripkuy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tripkuy.config.ApiClient;
import com.example.tripkuy.interfaces.ApiPenggunaInterface;
import com.example.tripkuy.models.Pengguna;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    public static final String NAMA = "NAMA";
    public static final String EMAIL = "EMAIL";
    public static final String ID = "ID";
    GoogleSignInClient mGoogleSignInClient;
    SignInButton signInButton;
    ApiPenggunaInterface apiPenggunaInterface;
    int RC_SIGN_IN = 0;
    private static final String LOG_TAG = LoginActivity.class.getSimpleName();
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialize Sign in button google
        signInButton = findViewById(R.id.btn_login_google);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        setGoogleSigninButtonText(signInButton, "Masuk Dengan Google");

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

    }

    @Override
    protected void onStart() {
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            startActivity(new Intent(LoginActivity.this, Dashboard.class));
        }
        super.onStart();
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

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            if (account != null) {
                final String personName = account.getDisplayName();
                final String personEmail = account.getEmail();
                final String id = account.getId();
                apiPenggunaInterface = ApiClient.getApiClient().create(ApiPenggunaInterface.class);
                Call<String> call = apiPenggunaInterface.checkRegistered(personEmail);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d("testId", "KONTOL LO BABI"+ response.body());
                        if(response.body().equals("Sukses") ){
                            Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                            intent.putExtra(NAMA, personName);
                            intent.putExtra(EMAIL, personEmail);
                            intent.putExtra(ID, id);
                            startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(getApplicationContext(), Pendaftaran.class);
                            intent.putExtra(NAMA, personName);
                            intent.putExtra(EMAIL, personEmail);
                            intent.putExtra(ID, id);
                            startActivity(intent);
                        }

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {


                    }
                });
            }


        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.

        }
    }

    public void loginFacebook(View view) {
        Intent intent = new Intent(this, Pendaftaran.class);
        startActivity(intent);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    protected void setGoogleSigninButtonText(SignInButton signInButton,
                                             String buttonText) {
        for (int i = 0; i < signInButton.getChildCount(); i++) {
            View v = signInButton.getChildAt(i);

            if (v instanceof TextView) {
                TextView tv = (TextView) v;
                tv.setTextSize(15);
                tv.setTypeface(null, Typeface.NORMAL);
                tv.setText(buttonText);
                return;
            }
        }
    }
}
