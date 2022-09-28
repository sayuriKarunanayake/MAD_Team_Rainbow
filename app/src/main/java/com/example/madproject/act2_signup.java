package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class act2_signup extends AppCompatActivity {
    TextView signinLink;
    EditText etEmail, etPwd, etConf0rmPwd;
    Button btn_signup;


    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act2_signup);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //initializaion
        signinLink = findViewById(R.id.signupLink);

        etEmail = findViewById(R.id.etEmail);
        etPwd = findViewById(R.id.etPwd);
        etConf0rmPwd = findViewById(R.id.etConf0rmPwd);
        btn_signup = findViewById(R.id.btn_signup);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();


        //dbRef = FirebaseDatabase.getInstance().getReference().child("Member");

        signinLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(act2_signup.this, MainActivity_login.class));
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PerforAuth();
            }
        });


    }

    private void PerforAuth() {
        String email = etEmail.getText().toString();
        String password = etPwd.getText().toString();
        String conformPassword = etConf0rmPwd.getText().toString();

        if (!email.matches(emailPattern)) {
            etEmail.setError("incorrect email pattern!");
        } else if (password.isEmpty() || password.length()< 6) {
            etPwd.setError("Password length must be more than 6 characters!");
        } else if(password.equals(conformPassword)){
            etConf0rmPwd.setError("Password not matched");

        }else{
            progressDialog.setMessage("Please wait while Registration..");
            progressDialog.setTitle("registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(act2_signup.this, "registration Successfull", Toast.LENGTH_SHORT).show();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(act2_signup.this, "" +task.getException(), Toast.LENGTH_SHORT).show();

                    }
                }
            });


        }
    }


    private void sendUserToNextActivity() {
        Intent intent = new Intent(act2_signup.this, MainActivity3_req_book.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}         //dbRef.child("Member").setValue(memObj);
                //Toast.makeText(getApplicationContext(), "data inserted successfully", Toast.LENGTH_SHORT).show();


