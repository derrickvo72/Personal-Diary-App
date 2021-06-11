package android.btth.notemanagementsystem.dao;


import android.btth.notemanagementsystem.entity.Priority;
import android.btth.notemanagementsystem.entity.Status;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StatusDao {
    @Query("SELECT * FROM status")
    List<Status> getListStatus();

    @Insert
    void insertStatus(Status status);

    @Delete
    void delete(Status status);

    @Query("DELETE FROM status")
    void deleteAllStatus();

    @Update
    void update (Status status);

    @Query("Select sttName From status")
    String[] getSttName();
    @Query("select sttID from status where sttName=:sName Limit 1")
    int getSttIdBySttName(String sName);
    @Query("select sttName from status where sttID=:sID")
    String getStatusNameBySttID(int sID);

    //Check sttName in database
    @Query("Select Count(*) From status where sttName=:sttNameCheck")
    int checkSttNameinDb(String sttNameCheck);






}
