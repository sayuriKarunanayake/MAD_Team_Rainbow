package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

 

public class MainActivity_login extends AppCompatActivity {
    TextView signupLink;
    EditText etEmail, etPwd;
    Button login;


    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        signupLink = findViewById(R.id.signinLink);

        etEmail = findViewById(R.id.etEmail);
        etPwd = findViewById(R.id.etPwd);
        login = findViewById(R.id.login);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();


        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity_login.this, act2_signup.class));
            }

        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perforLogin();
            }
        });
    }

    private void perforLogin() {
        String email = etEmail.getText().toString();
        String password = etPwd.getText().toString();

        if (!email.matches(emailPattern)) {
            etEmail.setError("incorrect email pattern!");

        } else if (password.isEmpty() || password.length() < 6) {
            etPwd.setError("Password length must be more than 6 characters!");

        } else {
            progressDialog.setMessage("Please wait while login..");
            progressDialog.setTitle("login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(MainActivity_login.this, "registration Successfull", Toast.LENGTH_SHORT).show();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity_login.this, "" + task.getException(), Toast.LENGTH_SHORT).show();

                    }
                }
            });


        }


    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(MainActivity_login.this, MainActivity3_req_book.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}