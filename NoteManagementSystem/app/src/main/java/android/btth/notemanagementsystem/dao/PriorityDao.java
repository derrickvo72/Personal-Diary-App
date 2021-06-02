package android.btth.notemanagementsystem.dao;


import android.btth.notemanagementsystem.entity.Priority;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PriorityDao {
    @Query("SELECT * FROM priority")
    List<Priority> getListPriority();

    @Insert
    void insertPrio(Priority priority);

    @Delete
    void deletePrio(Priority priority);

    @Query("DELETE FROM priority")
    void deleteAllPrio();

    @Update
    void update (Priority priority);

    @Query("Select prioName From priority")
    String[] getPrioName();

    @Query("select prioID from priority where prioName=:pName Limit 1")
    int getPrioIdByPrioName(String pName);

    @Query("SELECT COUNT(prioID) as NumberofPrio from priority")
    int getNumberPrio();

    //Check prioName in database
    @Query("Select Count(*) From priority where prioName=:prioNameCheck")
    int checkPrioNameinDb(String prioNameCheck);
}
