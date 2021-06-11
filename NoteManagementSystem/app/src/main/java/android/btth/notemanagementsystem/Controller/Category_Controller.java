package android.btth.notemanagementsystem.Controller;

import android.btth.notemanagementsystem.dao.CategoryDao;
import android.btth.notemanagementsystem.entity.Category;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

public class Category_Controller extends Manage {
    String[] catNameDefault;
    public CategoryDao categoryDao;

    public Category_Controller(String[] catNameDefault, CategoryDao categoryDao) {
        this.catNameDefault = catNameDefault;
        this.categoryDao = categoryDao;
    }
    @Override
    public String add(Object object) {
        String txtCategoryName = object.toString();
        boolean flagforadd = false;
        for (String obj: catNameDefault
        ) {
            if(obj.equals(txtCategoryName)){
                flagforadd = true;
            }
        }
        if(flagforadd==true) {
            /**
             * kiem tra status da co trong db chua
             */
            if(categoryDao.checkCatNameinDb(txtCategoryName)>0){

                return "Da ton tai";
            }
            else{
                Calendar cal = Calendar.getInstance();

                String strDate = DateFormat.format("yyyy-MM-dd hh:mm:ss",cal).toString();

                categoryDao.insertCat(  new Category( txtCategoryName, strDate));

                return  null;
            }
        }
        else {
            return "Vui long nhap dung ten Category";
        }

    }

    @Override
    public String edit(Object id, Object object) {
        return null;
    }

    @Override
    public String delete(Object id) {
        return null;
    }

    @Override
    public List<Object> getAll() {
        return null;
    }
}
