package com.tamersarioglu.organized;



import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {


    private EditText editText_email_register, editText_password_register;
    private Button button_create_account;
    private TextView textView_alreadyAmember;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();


        viewIds();
        buttonCreateAccount();
        alreadyAmemberTextViewOnClick();

    }


    private void viewIds() {
        editText_email_register = findViewById(R.id.editText_Email_Register);
        editText_password_register = findViewById(R.id.editText_Password_Register);
        button_create_account = findViewById(R.id.button_create_account);
        textView_alreadyAmember = findViewById(R.id.textView_already_have_account);
    }

    private void alreadyAmemberTextViewOnClick() {

        textView_alreadyAmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void buttonCreateAccount() {

        button_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String mEmail = editText_email_register.getText().toString().trim();
                String mPassword = editText_password_register.getText().toString().trim();

                if (mEmail.isEmpty() || mPassword.isEmpty()){

                    editText_email_register.setError("Bu Alanı Doldurmanız Gerekmektedir");
                    editText_password_register.setError("Bu Alanı Doldurmanız Gerekmektedir");

                    Toast.makeText(Register.this, "Email ve Password Alanlarını Dodurunuz ! ", Toast.LENGTH_SHORT).show();

                } else {
                    mAuth.createUserWithEmailAndPassword(mEmail,mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()){
                                Toast.makeText(Register.this, "Kayıt Yapılamadı", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Register.this, "Kayıt Yapıldı", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}

