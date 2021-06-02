package android.btth.notemanagementsystem.dao;

import android.btth.notemanagementsystem.entity.Note;
import android.btth.notemanagementsystem.entity.NoteDetails;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM note")
    List<Note> getAll();

    @Insert
    void insertNote(Note note);

    @Delete
    void delete(Note note);

    @Query("SELECT * FROM NoteDetails")
    List<NoteDetails> getAllNoteDetails();

    @Query("SELECT noteID,noteName,catName,prioName,sttName,timePlan,note.timeCre as timeCre " +
            "FROM note,category,priority,status " +
            "Where note.catID= category.catID and note.prioID = priority.prioID and note.sttID= status.sttID and note.userID =:userID")
    List<NoteDetails> getNoteByUserID(int userID);

    @Query("select sttID from note where userID = :userID group by sttID")
    int[] getStatusIDByUserID(int userID);

    @Query("select count(*) from note where sttID=:sID and userID=:uID")
    int getNumberNoteByUserIDandStatusID(int uID, int sID);

    @Query("select * from note where noteID=:noteID")
    Note getNotebyNoteID(int noteID);

    @Update
    void Update(Note note);

    @Query("select count(*) from note where sttID=:statustocheck")
    int countNotewithStatusID(int statustocheck);

    @Query("select count(*) from note where catID=:categorytocheck")
    int countNotewithCategoryID(int categorytocheck);

    @Query("select count(*) from note where prioID=:priotocheck")
    int countNotewithPrioID(int priotocheck);

//Lọc theo priority
    @Query("SELECT noteID,noteName,catName,prioName,sttName,timePlan,note.timeCre as timeCre " +
            "FROM note,category,priority,status " +
            "Where note.catID= category.catID and note.prioID = priority.prioID and note.sttID= status.sttID and note.userID =:userID and priority.prioName=:proName")
    List<NoteDetails> getNoteByPriority(int userID, String proName);



    @Query("SELECT noteID,noteName,catName,prioName,sttName,timePlan,note.timeCre as timeCre " +
            "FROM note,category,priority,status " +
            "Where note.catID= category.catID and note.prioID = priority.prioID and note.sttID= status.sttID and note.noteID =:noteIDID")
    NoteDetails getNoteDetailByNoteIDID(int noteIDID);

}
