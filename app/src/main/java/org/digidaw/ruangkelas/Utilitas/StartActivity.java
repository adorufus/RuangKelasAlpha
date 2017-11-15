package org.digidaw.ruangkelas.Utilitas;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import org.digidaw.ruangkelas.Home.MainActivity;
import org.digidaw.ruangkelas.R;
import org.w3c.dom.Text;

public class StartActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private TextView mLupaPasswordLink;
    private EditText mLoginNis;
    private EditText mLoginPass;
    private ProgressBar mProgresBar;
    private Button mLoginButton;
    private CoordinatorLayout coordinatorLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mAuth = FirebaseAuth.getInstance();

        mProgresBar = (ProgressBar) findViewById(R.id.loginProgress);
        mProgresBar.setVisibility(View.GONE);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.loginCoorLayout);
        mLoginNis = (EditText) findViewById(R.id.editTextNis);
        mLoginPass = (EditText) findViewById(R.id.editTextPassword);
        mLoginButton = (Button) findViewById(R.id.btnMasuk);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nis = mLoginNis.getEditableText().toString();
                String pass = mLoginPass.getEditableText().toString();

                if(!TextUtils.isEmpty(nis) || !TextUtils.isEmpty(pass)){
                    mProgresBar.setVisibility(View.VISIBLE);

                    loginUser(nis, pass);
                }
            }
        });
    }

    private void loginUser(String nis, String pass){
        mAuth.signInWithEmailAndPassword(nis, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    mProgresBar.setVisibility(View.GONE);

                    Intent startIntent = new Intent(StartActivity.this, MainActivity.class);
                    startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(startIntent);
                    finish();
                }
                else{
                    mProgresBar.setVisibility(View.GONE);
                    String err = "";

                    try{
                        throw task.getException();
                    }
                    catch (FirebaseAuthInvalidUserException e){
                        err = "NIS atau Password kamu salah, coba periksa lagi";
                    }
                    catch (FirebaseAuthInvalidCredentialsException e){
                        err = "Email / Password salah!";
                    }
                    catch (FirebaseNetworkException e){
                        err = "Tidak ada koneksi internet, coba periksa jaringan anda";
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                    Snackbar snackbar = Snackbar.make(coordinatorLayout, err, Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(Color.BLUE);
                    TextView snackbarTextView = snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    snackbarTextView.setTextColor(Color.WHITE);
                    snackbar.show();

//                    Toast.makeText(StartActivity.this, err, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
