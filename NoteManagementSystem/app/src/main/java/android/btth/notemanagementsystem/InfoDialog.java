package android.btth.notemanagementsystem;

import android.app.Dialog;
import android.btth.notemanagementsystem.Adapter.CatAdapter;
import android.btth.notemanagementsystem.dao.CategoryDatabase;
import android.btth.notemanagementsystem.entity.Category;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.List;

import io.reactivex.annotations.NonNull;

public class InfoDialog extends AppCompatDialogFragment {

    private EditText edtCat;
    private InfoDialogListener listener;
    private Button btnAdd;
    private Button btnClose;
    private List<Category> mListCat;
    private CatAdapter catAdapter;

    public RecyclerView rcvCat;

    @NonNull
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);
        edtCat = (EditText) view.findViewById(R.id.edtCat);
        btnAdd = (Button) view.findViewById(R.id.btnAdd);
        btnClose = (Button) view.findViewById(R.id.btnClose);
        builder.setView(view);
        btnAdd.setOnClickListener(v->{
            String strCat = edtCat.getText().toString().trim();
            Calendar cal = Calendar.getInstance();

            String strDate = DateFormat.format("EEE, MMMM/d/yyyy",cal).toString();
            //kiem tra du lieu co hay ko
            if(TextUtils.isEmpty(strCat) || TextUtils.isEmpty(strDate)){
                return;
            }

            Category category = new Category(strCat,strDate);
            CategoryDatabase.getInstance(getContext()).categoryDao().insertCat(category);
            Toast.makeText(getContext(),"Add success",Toast.LENGTH_SHORT).show();
            dismiss();

//            mListCat = CategoryDatabase.getInstance(getContext()).categoryDao().getListCategory();



        });


        return builder.create();
    }



    public interface InfoDialogListener{
        void applyText(String info);
    }
}
