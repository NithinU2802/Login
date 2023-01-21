package com.example.login;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    EditText emailId, password,conpass,age,phno,username;
    Button btnSignUp;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        conpass=findViewById(R.id.conpass);
        age=findViewById(R.id.age);
        phno=findViewById(R.id.phone);
        username=findViewById(R.id.username);
        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        btnSignUp = findViewById(R.id.button2);
        tvSignIn = findViewById(R.id.textView);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                String conpwd=conpass.getText().toString();
                String uage=age.getText().toString();
                String mob=phno.getText().toString();
                String em=emailId.getText().toString();
                String nam=username.getText().toString();
                String urname=username.getText().toString();
                if(email.isEmpty()){
                    emailId.setError("Please enter email id");
                    emailId.requestFocus();
                }
                else  if(pwd.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                }
                else if(uage.isEmpty())
                {
                    age.setError("Please enter your age");
                    age.requestFocus();
                }
                else if(mob.isEmpty())
                {
                    phno.setError("Please enter your phone number");
                    phno.requestFocus();
                }
                else if(urname.isEmpty())
                {
                    username.setError("Please enter your Name");
                    username.requestFocus();
                }
                else  if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(HomeActivity.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else  if(!pwd.equals(conpwd))
                {
                    Toast.makeText(HomeActivity.this,"please enter same password",Toast.LENGTH_SHORT).show();
                }





                else  if(!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(HomeActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(HomeActivity.this,"SignUp Unsuccessful, Please Try Again",Toast.LENGTH_SHORT).show();
                            }
                            else {
                             Intent n=   new Intent(HomeActivity.this,MainActivity.class);
//                                n.putExtra("name",nam);
//                                n.putExtra("email",em);
                                startActivity(n);
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(HomeActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

                }
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}

