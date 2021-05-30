package android.btth.notemanagementsystem.Controller;

import android.btth.notemanagementsystem.dao.CategoryDao;
import android.btth.notemanagementsystem.dao.PriorityDao;
import android.btth.notemanagementsystem.entity.Category;
import android.btth.notemanagementsystem.entity.Priority;
import android.text.format.DateFormat;
import android.util.Log;

import java.util.Calendar;
import java.util.List;

public class Priority_Controller  extends  Manage{
    String[] priNameDefault;
    public PriorityDao priorityDao;

    public Priority_Controller(String[] priNameDefault, PriorityDao priorityDao) {
        this.priorityDao = priorityDao;
        this.priNameDefault = priNameDefault;
    }

    @Override
    public String add(Object object) {
        String txtPrigoryName = object.toString();
        Log.d("kiemtra", "add: " + txtPrigoryName);

        boolean flagforadd = false;
        for (String obj: priNameDefault
        ) {
            if(obj.equals(txtPrigoryName)){
                flagforadd = true;
            }
        }
        if(flagforadd==true) {
            /**
             * kiem tra status da co trong db chua
             */
            if(priorityDao.checkPrioNameinDb(txtPrigoryName)>0){

                return "Da ton tai";
            }
            else{
                Calendar cal = Calendar.getInstance();

                String strDate = DateFormat.format("yyyy-MM-dd hh:mm:ss",cal).toString();

                priorityDao.insertPrio(  new Priority( txtPrigoryName, strDate));

                return  null;
            }
        }
        else {
            return "Vui long nhap dung ten Priority";
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
