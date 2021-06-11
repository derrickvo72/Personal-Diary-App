package android.btth.notemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.btth.notemanagementsystem.entity.User;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SigninActivity extends AppCompatActivity {

    private Button btnSignin;
    private Button btnExit;
    private FloatingActionButton btnSignup;
    private EditText edtEmail;
    private EditText edtPass;
    private CheckBox cbRemember;

    private User user;
    private AppDatabase appDatabase;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        //call Database
        appDatabase = AppDatabase.getInstance(this);
        initView();
        //init SharedPreferences
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        //check remember me
        rememberUser();
        btnSignup.setOnClickListener(v -> {
            openSignUpForm();
        });
        btnExit.setOnClickListener(v -> {
            finish();
        });
        btnSignin.setOnClickListener(v -> {
            doSignIn();
        });


        if(sharedPreferences.getInt("userID",-1) != -1){
            startActivity(new Intent(this, Main2Activity.class));
            finish();
        }
    }

    public void rememberUser(){
        edtEmail.setText(sharedPreferences.getString("email",""));
        edtPass.setText(sharedPreferences.getString("password",""));
        cbRemember.setChecked(sharedPreferences.getBoolean("checked",false));
    }

    public void initView() {
        btnSignin = findViewById(R.id.btnSignin);
        btnExit = findViewById(R.id.btnExit);
        btnSignup = findViewById(R.id.btnSignup);
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPassword);
        cbRemember = findViewById(R.id.cbRemember);
    }

    public void openSignUpForm() {
        startActivity(new Intent(this, SignupActivity.class));
        finish();
    }

    public void doSignIn() {
        String email = edtEmail.getText().toString().trim();
        String password = edtPass.getText().toString();
        if (email.length() == 0) {
            edtEmail.setError("Enter your email");
            return;
        } else {
            edtEmail.setError(null);
        }
        if (password.length() == 0) {
            edtPass.setError("Enter your password");
            return;
        } else {
            edtPass.setError(null);
        }
        user = appDatabase.userDao().getUserByEmail(email);
        if (user != null) {
            if (!password.equals(user.password)) {
                edtPass.setError("Your password is incorrect");
                return;
            }
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("userID", user.userID);
            editor.putString("userEmail", user.email);
            editor.putString("userPass",user.password);
            editor.putString("userFirstname", user.firstname);
            editor.putString("userLastname", user.lastname);
            if (cbRemember.isChecked()) {
                editor.putString("email", email);
                editor.putString("password", password);
                editor.putBoolean("checked", true);
            } else {
                editor.remove("email");
                editor.remove("password");
                editor.remove("checked");
            }
            editor.commit();
            startActivity(new Intent(this, Main2Activity.class));
            finish();
        } else {
            edtEmail.setError("Email doesn't exist");
            return;
        }


    }
}