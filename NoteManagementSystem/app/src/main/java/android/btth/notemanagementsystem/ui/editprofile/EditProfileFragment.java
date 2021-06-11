package android.btth.notemanagementsystem.ui.editprofile;

import android.btth.notemanagementsystem.AppDatabase;
import android.btth.notemanagementsystem.Main2Activity;
import android.btth.notemanagementsystem.R;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

public class EditProfileFragment extends Fragment {

    private EditText edtFirstname;
    private EditText edtLastname;
    private EditText edtEmail;
    private Button btnChange;
    private SharedPreferences sharedPreferences;
    private AppDatabase appDatabase;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_editprofile, container, false);
        initElement(root);
        sharedPreferences = this.getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        writeElement();
        //call database
        appDatabase = AppDatabase.getInstance(getContext());
        btnChange.setOnClickListener(v -> {
            changeProfile();
        });
        return root;
    }

    public void initElement(View root){
        edtEmail = root.findViewById(R.id.edtEmailProfile);
        edtFirstname = root.findViewById(R.id.edtFirstname);
        edtLastname = root.findViewById(R.id.edtLastname);
        btnChange = root.findViewById(R.id.btnChangeProfile);
    }

    public void writeElement(){
        edtFirstname.setText(sharedPreferences.getString("userFirstname",""));
        edtLastname.setText(sharedPreferences.getString("userLastname",""));
        edtEmail.setText(sharedPreferences.getString("userEmail",""));
    }

    public void changeProfile(){
        String firstname = edtFirstname.getText().toString();
        String lastname = edtLastname.getText().toString();
        String email = edtEmail.getText().toString();
        int userID = sharedPreferences.getInt("userID",0);
        if(email.length()==0){
            edtEmail.setError("Enter your new email");
            return;
        }
        else{
            edtEmail.setError(null);
        }
        appDatabase.userDao().updateProfile(firstname,lastname,email,userID);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userFirstname",firstname);
        editor.putString("userLastname",lastname);
        editor.putString("userEmail",email);
        editor.commit();
        NavigationView view = getActivity().findViewById(R.id.nav_view);
        TextView txtEmail = view.getHeaderView(0).findViewById(R.id.txtEmail);
        txtEmail.setText(email);
        Toast.makeText(getContext(),"Change profile successfully!!", Toast.LENGTH_LONG);
    }
}
