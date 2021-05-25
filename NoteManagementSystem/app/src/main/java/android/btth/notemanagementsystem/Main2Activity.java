package android.btth.notemanagementsystem;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class Main2Activity extends AppCompatActivity implements InfoDialog.InfoDialogListener {

    private AppBarConfiguration mAppBarConfiguration;
    private Button btnAdd;
    private Button btnClose;
    private EditText edtCat;
    private TextView txtDiaForm;
    FloatingActionButton fab;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        fab =(FloatingActionButton) findViewById(R.id.fab);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                    OpenInfoDialog();
//
//
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        NavigationView navigationView1 = (NavigationView) findViewById(R.id.nav_view);
        View headerlayout = navigationView1.getHeaderView(0);
        TextView txtEmail = headerlayout.findViewById(R.id.txtEmail);
        txtEmail.setText(sharedPreferences.getString("email","kylh84@gmail.com"));

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_category, R.id.nav_priority,
                R.id.nav_status, R.id.nav_note, R.id.nav_editprofile,
                R.id.nav_changepassword)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void OpenInfoDialog(){
        InfoDialog infoDialog = new InfoDialog();
        infoDialog.show(getSupportFragmentManager(),"Info Dialog");
    }

    @Override
    public void applyText(String info) {

    }
}