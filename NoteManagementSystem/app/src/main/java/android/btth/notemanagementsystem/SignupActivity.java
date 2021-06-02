package android.btth.notemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.btth.notemanagementsystem.entity.User;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    EditText edtEmail;
    EditText edtPass;
    EditText edtConfirnPass;
    Button btnSignup;
    Button btnSignin;

    User user;
    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //create AppDatabase
        appDatabase = AppDatabase.getInstance(this);
        initElement();
        btnSignin.setOnClickListener(v -> {
            openSignIn();
        });
        btnSignup.setOnClickListener(v -> {
            doSignUp();
        });
    }

    public void initElement(){
        edtEmail = findViewById(R.id.edtEmailSignup);
        edtPass = findViewById(R.id.edtPasswordSignup);
        edtConfirnPass = findViewById(R.id.edtConfirmPass);
        btnSignin = findViewById(R.id.btnSigninForm);
        btnSignup = findViewById(R.id.btnSignupForm);
    }

    public void openSignIn(){
        startActivity(new Intent(this,SigninActivity.class));
        finish();
    }

    public void doSignUp(){
        String userEmail = edtEmail.getText().toString().trim();
        String password = edtPass.getText().toString();
        String confpass = edtConfirnPass.getText().toString();
        //Check Input
        User us = appDatabase.userDao().getUserByEmail(userEmail);
        if(us!=null){
            edtEmail.setError("Email already exist");
            return;
        }
        else{
            edtEmail.setError(null);
        }
        if(userEmail.length() == 0)
        {
            edtEmail.setError("Enter you email");
            return;
        }
        else{
            edtEmail.setError(null);
        }
        if(password.length() == 0){
            edtPass.setError("Enter your password");
            return;
        }
        else{
            edtPass.setError(null);
        }
        if(!password.equals(confpass)){
            edtConfirnPass.setError("Confirm Passsword isn't match");
            return;
        }
        else
        {
            edtConfirnPass.setError(null);
        }
        user = new User(userEmail,password,"","");
        appDatabase.userDao().Insert(user);

        User u = appDatabase.userDao().getUserByEmail(userEmail);
        edtEmail.setText("");
        edtPass.setText("");
        edtConfirnPass.setText("");
        Toast.makeText(getBaseContext(), "Dang ky thanh cong", Toast.LENGTH_LONG).show();
    }
}