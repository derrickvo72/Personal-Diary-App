package android.btth.notemanagementsystem.Controller;

import android.btth.notemanagementsystem.dao.PriorityDao;
import android.btth.notemanagementsystem.dao.StatusDao;
import android.btth.notemanagementsystem.entity.Priority;
import android.btth.notemanagementsystem.entity.Status;
import android.text.format.DateFormat;
import android.util.Log;

import java.util.Calendar;
import java.util.List;

public class Status_Controller extends Manage {
    String[] sttNameDefault;
    public StatusDao statusDao;

    public Status_Controller(String[] sttNameDefault, StatusDao statusDao) {
        this.statusDao = statusDao;
        this.sttNameDefault = sttNameDefault;
    }

    @Override
    public String add(Object object) {
        String txtStatusName = object.toString();
        Log.d("kiemtra", "add: " + txtStatusName);

        boolean flagforadd = false;
        for (String obj: sttNameDefault
        ) {
            if(obj.equals(txtStatusName)){
                flagforadd = true;
            }
        }
        if(flagforadd==true) {
            /**
             * kiem tra status da co trong db chua
             */
            if(statusDao.checkSttNameinDb(txtStatusName)>0){

                return "Da ton tai";
            }
            else{
                Calendar cal = Calendar.getInstance();

                String strDate = DateFormat.format("yyyy-MM-dd hh:mm:ss",cal).toString();

                statusDao.insertStatus(  new Status( txtStatusName, strDate));

                return  null;
            }
        }
        else {
            return "Vui long nhap dung ten Status";
        }

    }

    @Override
    public String edit(Object id, Object object) {
        return null;
    }

    @Override
    public String delete(Object status) {
        statusDao.delete((Status) status);

        return null;
    }

    @Override
    public List<Object> getAll() {
        return null;
    }
}
