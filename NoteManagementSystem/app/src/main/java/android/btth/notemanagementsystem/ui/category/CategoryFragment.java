package android.btth.notemanagementsystem.ui.category;

import android.app.AlertDialog;
import android.btth.notemanagementsystem.Adapter.CatAdapter;
import android.btth.notemanagementsystem.AppDatabase;
import android.btth.notemanagementsystem.Controller.Category_Controller;
import android.btth.notemanagementsystem.R;
import android.btth.notemanagementsystem.dao.CategoryDao;
import android.btth.notemanagementsystem.entity.Category;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CategoryFragment extends Fragment {

    public RecyclerView rcvCat;
    private List<Category> mListCategory;
    private CatAdapter catAdapter;
    private FloatingActionButton fbtnCat;
    private Button btnAdd;
    private Button btnClose;
    private Spinner spnCAT;

    String[] catNameDefault = {"Check our available Category...","Working", "Study", "Relax"};

    public CategoryDao categoryDao;

    AppDatabase appDatabase;

    //
    Category_Controller category_controller;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /**
         * tao layout recylerview
         */
        View root = inflater.inflate(R.layout.fragment_category, container, false);
        rcvCat = (RecyclerView) root.findViewById(R.id.rcv_Cat);


        spnCAT=  (Spinner) root.findViewById(R.id.spnCatName);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,catNameDefault);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCAT.setAdapter(arrayAdapter);

//        appDatabase.getInstance(getContext()).categoryDao().insertCat(new Category("dsafgeywyugdddd","uueueiwueiwueiw"));

        /**
         *  lay cac method tu DAO
         */
        categoryDao = appDatabase.getInstance(getContext()).categoryDao();

        //khoi tao controller
        category_controller = new Category_Controller(new String[] {"Working", "Study", "Relax"}, appDatabase.getInstance(getContext()).categoryDao());

        /**
         *  mo 1 dialog them category khi an vao floating button
         */
        fbtnCat = (FloatingActionButton) root.findViewById(R.id.fbtnCat);
        fbtnCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                OpenInfoDialog();

            }
        });


        rcvCat.setHasFixedSize(true);

        catAdapter = new CatAdapter();
        mListCategory = new ArrayList<>();

//        catAdapter.setData(mListCategory);
        /**
         * tao va set layout cho recylerview
         */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvCat.setLayoutManager(linearLayoutManager);
//        rcvCat.setAdapter(catAdapter);


        //gan cho mlist
//        mListCategory= CategoryDatabase.getInstance(getContext()).categoryDao().getListCategory();

        /**
         * Lay du lieu tu list category
         */
        mListCategory = categoryDao.getListCategory();
        /**
         * dua du lieu vo adapter va chuyen vao recyclerview
         */
        catAdapter.setData(mListCategory);
        rcvCat.setAdapter(catAdapter);

        return root;
    }

    /**
     * dialog them category
     */
    public void OpenInfoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.layout_dialog, null);
        AlertDialog alertDialog = builder.create();
//        builder.setTitle("dsafhuie").setView(view).show();
        alertDialog.setView(view);
//        alertDialog.setTitle("Category Form");
        alertDialog.show();

        /**
         * su kien nut close
         */
        btnClose = view.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(v -> {
            alertDialog.cancel();
//            categoryDao.deleteAll();
        });

/**
 * su kien nut add voi 1 category
 */
        btnAdd = view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(v -> {


            /**
             * anh xa du lieu tu view
             */


            EditText edtCatName = view.findViewById(R.id.edtCat);
            String txtCatName = edtCatName.getText().toString();


//Error
            String err = category_controller.add(txtCatName);
            if(err!= null){
                edtCatName.setError(err);
                return;
            }
            mListCategory = categoryDao.getListCategory();
            catAdapter.setData(mListCategory);
            rcvCat.setAdapter(catAdapter);
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
        Category c = mListCategory.get(item.getGroupId());


        int categoryDetailsID = c.catID;

        int numberofnote = appDatabase.getInstance(getContext()).noteDao().countNotewithCategoryID(categoryDetailsID);


        switch (item.getItemId()) {
            /**
             * case 001 la nut delete
             * case 002 la nut edit
             */
            case 001:

                System.out.println(numberofnote);
                if (numberofnote > 0) {
                    Toast.makeText(getContext(), "Khong the xoa vi category nay co note su dung", Toast.LENGTH_LONG).show();

                } else {
                    mListCategory.remove(item.getGroupId());
                    appDatabase.getInstance(getContext()).categoryDao().delete(c);
                    catAdapter.notifyDataSetChanged();
                }

                return true;
            case 002:

                OpenInfoDialog2(c, item);
                return true;
        }
        return super.onContextItemSelected(item);
    }

    /**
     * mo dialog khi an nut edit tu 1 item trong category
     *
     * @param c
     * @param item
     */
    public void OpenInfoDialog2(Category c, MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.layout_dialog, null);
        Button save = view.findViewById(R.id.btnAdd);
        EditText edtCatName = view.findViewById(R.id.edtCat);
//        System.out.println(newCatName);
        Calendar cal = Calendar.getInstance();

        String strDate = DateFormat.format("yyyy-MM-dd hh:mm:ss", cal).toString();
        edtCatName.setText(c.catName);

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

            String newCatName = edtCatName.getText().toString();
            boolean flagforadd = false;
            for (String obj : catNameDefault
            ) {
                if (obj.equals(newCatName)) {
                    flagforadd = true;
                }
            }
            if (flagforadd == true) {
                if (categoryDao.checkCatNameinDb(newCatName) > 0) {
                    edtCatName.setError("Category nay da ton tai");
                    return;
                } else {
                    c.setCatName(newCatName);
                    c.setTimeCre(strDate);
                    appDatabase.getInstance(getContext()).categoryDao().update(c);
                    mListCategory.remove(item.getGroupId());
                    mListCategory.add(c);
                    catAdapter.notifyDataSetChanged();
                    alertDialog.cancel();
                }
            } else {
                edtCatName.setError("Vui long nhap dung ten Category");
                return;
            }


        });

        /**
         * su kien nut close
         */
        btnClose = view.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(v -> {
            alertDialog.cancel();
//            categoryDao.deleteAll();
        });


    }

}