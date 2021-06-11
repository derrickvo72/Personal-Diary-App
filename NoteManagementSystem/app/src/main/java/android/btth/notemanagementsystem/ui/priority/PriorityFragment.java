package android.btth.notemanagementsystem.ui.priority;

import android.app.AlertDialog;
import android.btth.notemanagementsystem.Adapter.PrioAdapter;
import android.btth.notemanagementsystem.AppDatabase;
import android.btth.notemanagementsystem.Controller.Priority_Controller;
import android.btth.notemanagementsystem.R;
import android.btth.notemanagementsystem.dao.PriorityDao;
import android.btth.notemanagementsystem.entity.Priority;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PriorityFragment extends Fragment {

    public RecyclerView rcvPrio;
    private List<Priority> mListPriority;
    private PrioAdapter prioAdapter;
    private FloatingActionButton fbtnPrio;
    private Button btnAddPrio;
    private Button btnClosePrio;
    private Spinner spnPRI;

    Priority_Controller priority_controller;

    String[] sttNameDefault = {"Check our available Priority ...","High", "Medium", "Low"};

    public PriorityDao priorityDao;

    AppDatabase appDatabase;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //Controller
/**
 * tao layout recylerview
 */
        View root = inflater.inflate(R.layout.fragment_priority, container, false);
        rcvPrio = (RecyclerView) root.findViewById(R.id.rcv_Prio);

        spnPRI=  (Spinner) root.findViewById(R.id.spnPriName);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,sttNameDefault);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPRI.setAdapter(arrayAdapter);
/**
 *  lay cac method tu DAO
 */
        priorityDao = appDatabase.getInstance(getContext()).priorityDao();
        priority_controller = new Priority_Controller(sttNameDefault,priorityDao);

        fbtnPrio = (FloatingActionButton) root.findViewById(R.id.fbtnPrio);
        /**
         * nhan nut mo dialog them priority
         */
        fbtnPrio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OpenInfoDialog();
            }
        });


        rcvPrio.setHasFixedSize(true);

        prioAdapter = new PrioAdapter();
        mListPriority = new ArrayList<>();

        /**
         * tao va set layout cho recylerview
         */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvPrio.setLayoutManager(linearLayoutManager);


        //gan cho mlist
/**
 * Lay du lieu tu list category
 */
        mListPriority = priorityDao.getListPriority();
        /**
         * dua du lieu vo adapter va chuyen vao recyclerview
         */
        prioAdapter.setData(mListPriority);
        rcvPrio.setAdapter(prioAdapter);

        return root;
    }

    /**
     * dialog them priority
     */
    public void OpenInfoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.layout_dalog_prio, null);
        AlertDialog alertDialog = builder.create();

        alertDialog.setView(view);
//        alertDialog.setTitle("Priority Form");
        alertDialog.show();
        btnClosePrio = view.findViewById(R.id.btnClosePrio);
        btnClosePrio.setOnClickListener(v -> {
            alertDialog.cancel();
//            priorityDao.deleteAllPrio();
        });


/**
 * su kien nut add voi 1 priority
 */
        btnAddPrio = view.findViewById(R.id.btnAddPrio);
        btnAddPrio.setOnClickListener(v -> {

            EditText edtPrioName = view.findViewById(R.id.edtPrio);
            String txtPriorityName = edtPrioName.getText().toString().trim();


            /**
             * flagforadd = true : du lieu dau vao dung
             * flagforadd = true : du lieu dau vao sai
             */

            String error = priority_controller.add(txtPriorityName);
            if(error != null){
                edtPrioName.setError(error);
                return;
            }


            mListPriority = priorityDao.getListPriority();
            prioAdapter.setData(mListPriority);
            rcvPrio.setAdapter(prioAdapter);

            alertDialog.cancel();


        });

    }

    /**
     * tao context menu cho tung item trong recyclerview
     *
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Priority c = mListPriority.get(item.getGroupId());

        int prioDetailsID = c.prioID;

        int numberofnote = appDatabase.getInstance(getContext()).noteDao().countNotewithPrioID(prioDetailsID);

        switch (item.getItemId()) {
            /**
             * case 001 la nut delete
             * case 002 la nut edit
             */
            case 001:

                System.out.println(numberofnote);

                if (numberofnote > 0) {
                    Toast.makeText(getContext(), "Khong the xoa vi priority nay co note su dung", Toast.LENGTH_LONG).show();

                } else {
                    mListPriority.remove(item.getGroupId());
                    appDatabase.getInstance(getContext()).priorityDao().deletePrio(c);
                    prioAdapter.notifyDataSetChanged();
                }


                return true;
            case 002:

                OpenInfoDialog2(c, item);
                return true;
        }
        return super.onContextItemSelected(item);
    }

    /**
     * mo dialog khi an nut edit tu 1 item trong priority
     *
     * @param c
     * @param item
     */
    public void OpenInfoDialog2(Priority c, MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.layout_dalog_prio, null);
        Button save = view.findViewById(R.id.btnAddPrio);
        EditText edtPrioName = view.findViewById(R.id.edtPrio);
//        System.out.println(newCatName);
        Calendar cal = Calendar.getInstance();
        edtPrioName.setText(c.prioName);
        String strDate = DateFormat.format("yyyy-MM-dd hh:mm:ss", cal).toString();
        save.setText("Save");
        AlertDialog alertDialog = builder.create();
//        builder.setTitle("dsafhuie").setView(view).show();
        alertDialog.setView(view);
//        alertDialog.setTitle("Category Form");
        alertDialog.show();
        /**
         * su kien nut save
         */
        save.setOnClickListener(v -> {

            /**
             * flagforadd = true : du lieu dau vao dung
             * flagforadd = true : du lieu dau vao sai
             */

            String newPrioName = edtPrioName.getText().toString();
            boolean flagforadd = false;
            for (String obj : sttNameDefault
            ) {
                if (obj.equals(newPrioName)) {
                    flagforadd = true;
                }
            }
            if (flagforadd == true) {
                if (priorityDao.checkPrioNameinDb(newPrioName) > 0) {
                    edtPrioName.setError("Priority nay da ton tai");
                    return;
                } else {
                    c.setPrioName(newPrioName);
                    c.setTimeCre(strDate);
                    appDatabase.getInstance(getContext()).priorityDao().update(c);
                    mListPriority.remove(item.getGroupId());
                    mListPriority.add(c);
                    prioAdapter.notifyDataSetChanged();
                    alertDialog.cancel();
                }
            } else {
                edtPrioName.setError("Vui long nhap dung ten Priority");
                return;
            }

        });

        /**
         * su kien nut close
         */
        btnClosePrio = view.findViewById(R.id.btnClosePrio);
        btnClosePrio.setOnClickListener(v -> {
            alertDialog.cancel();
//            categoryDao.deleteAll();
        });


    }

}