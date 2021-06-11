package android.btth.notemanagementsystem.ui.status;

import android.app.AlertDialog;
import android.btth.notemanagementsystem.Adapter.StatusAdapter;
import android.btth.notemanagementsystem.AppDatabase;
import android.btth.notemanagementsystem.Controller.Status_Controller;
import android.btth.notemanagementsystem.R;
import android.btth.notemanagementsystem.dao.StatusDao;
import android.btth.notemanagementsystem.entity.Note;
import android.btth.notemanagementsystem.entity.NoteDetails;
import android.btth.notemanagementsystem.entity.Priority;
import android.btth.notemanagementsystem.entity.Status;
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

public class StatusFragment extends Fragment {


    public RecyclerView rcvStatus;
    private List<Status> mListStatus;
    private StatusAdapter statusAdapter;
    private FloatingActionButton fbtnStatus;
    private Button btnAddStatus;
    private Button btnCloseStatus;
    String[] sttNameDefault= {"Check our available Status...","Processing","Done","Pending"};
    public StatusDao statusDao;
    List<String> test1;
    AppDatabase appDatabase;
    private Spinner spnSTATE;

    Status_Controller status_controller;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
/**
 * tao layout recylerview
 */
        View root = inflater.inflate(R.layout.fragment_status, container, false);
        rcvStatus = (RecyclerView)root.findViewById(R.id.rcv_Status);

        spnSTATE=  (Spinner) root.findViewById(R.id.spnStaName);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,sttNameDefault);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSTATE.setAdapter(arrayAdapter);
/**
 *  lay cac method tu DAO
 */
        statusDao = appDatabase.getInstance(getContext()).statusDao();
        status_controller = new Status_Controller(sttNameDefault,statusDao);
        fbtnStatus =(FloatingActionButton)root.findViewById(R.id.fbtnStatus);
        /**
         * nut mo dialog them status
         */
        fbtnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                OpenInfoDialog();
            }
        });


        rcvStatus.setHasFixedSize(true);

        statusAdapter = new StatusAdapter();
        mListStatus = new ArrayList<>();

/**
 * tao va set layout cho recylerview
 */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvStatus.setLayoutManager(linearLayoutManager);



        //gan cho mlist
/**
 * Lay du lieu tu list status
 */
        mListStatus = statusDao.getListStatus();
/**
 * dua du lieu vo adapter va chuyen vao recyclerview
 */
        statusAdapter.setData(mListStatus);
        rcvStatus.setAdapter(statusAdapter);

        return root;
    }


    /**
     * Dialog Add Status
     */
    public void OpenInfoDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.layout_dialog_status,null);
        AlertDialog alertDialog = builder.create();

        alertDialog.setView(view);
//        alertDialog.setTitle("Status Form");
        alertDialog.show();
        btnCloseStatus = view.findViewById(R.id.btnCloseStatus);
        btnCloseStatus.setOnClickListener(v -> {
            alertDialog.cancel();
//            statusDao.deleteAllStatus();
        });


        btnAddStatus = view.findViewById(R.id.btnAddStatus);
/**
 * su kien nut add voi 1 status
 */
        btnAddStatus.setOnClickListener(v -> {

            EditText edtStatusName = view.findViewById(R.id.edtStatus);
/**
 * flagforadd = true : du lieu dau vao dung
 * flagforadd = true : du lieu dau vao sai
 */
            String txtStatusName = edtStatusName.getText().toString().trim();


            String err = status_controller.add(txtStatusName);
            if(err!= null){
                edtStatusName.setError(err);
                return;
            }

            mListStatus = statusDao.getListStatus();
            statusAdapter.setData(mListStatus);
            rcvStatus.setAdapter(statusAdapter);

            alertDialog.cancel();
        });

    }
    /**
     * tao context menu cho tung item trong recyclerview
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Status c = mListStatus.get(item.getGroupId());
        int statusDetailsID = c.sttID;

        int numberofnote = appDatabase.getInstance(getContext()).noteDao().countNotewithStatusID(statusDetailsID);
        switch (item.getItemId()){
            /**
             * case 001 la nut delete
             * case 002 la nut edit
             */
            case 001:


                System.out.println(numberofnote);

                if(numberofnote > 0){
                    Toast.makeText(getContext(), "Khong the xoa vi status nay co note su dung", Toast.LENGTH_LONG).show();

                }
                else {
                    mListStatus.remove(item.getGroupId());
                    appDatabase.getInstance(getContext()).statusDao().delete(c);
                    statusAdapter.notifyDataSetChanged();
                }


                return true;
            case 002:
                OpenInfoDialog2(c,item);
                return true;
        }
        return super.onContextItemSelected(item);
    }
    /**
     * mo dialog khi an nut edit tu 1 item trong status
     * @param c
     * @param item
     */
    public void OpenInfoDialog2(Status c, MenuItem item){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.layout_dialog_status,null);
        Button save =view.findViewById(R.id.btnAddStatus);
        EditText edtStatusName = view.findViewById(R.id.edtStatus);
//        System.out.println(newCatName);
        Calendar cal = Calendar.getInstance();
        edtStatusName.setText(c.sttName);

        String strDate = DateFormat.format("yyyy-MM-dd hh:mm:ss",cal).toString();
        save.setText("Save");
        AlertDialog alertDialog = builder.create();
//        builder.setTitle("dsafhuie").setView(view).show();
        alertDialog.setView(view);
//        alertDialog.setTitle("Category Form");
        alertDialog.show();
        save.setOnClickListener(v -> {

            /**
             * flagforadd = true : du lieu dau vao dung
             * flagforadd = true : du lieu dau vao sai
             */
            String newStatusName=edtStatusName.getText().toString();
            boolean flagforadd = false;
            for (String obj: sttNameDefault
            ) {
                if(obj.equals(newStatusName)){
                    flagforadd = true;
                }
            }
            if(flagforadd==true) {
                if(statusDao.checkSttNameinDb(newStatusName)>0){
                    edtStatusName.setError("Status nay da ton tai");
                    return;
                }
                else{
                    c.setSttName(newStatusName);
                    c.setTimeCre(strDate);
                    appDatabase.getInstance(getContext()).statusDao().update(c);
                    mListStatus.remove(item.getGroupId());
                    mListStatus.add(c);
                    statusAdapter.notifyDataSetChanged();
                    alertDialog.cancel();
                }
            }
            else {
                edtStatusName.setError("Vui long nhap dung ten status");
                return;
            }

        });

        /**
         * su kien nut close
         */
        btnCloseStatus = view.findViewById(R.id.btnCloseStatus);
        btnCloseStatus.setOnClickListener(v -> {
            alertDialog.cancel();
//            categoryDao.deleteAll();
        });




    }
}
