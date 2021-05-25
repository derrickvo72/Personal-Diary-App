package android.btth.notemanagementsystem.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note")
public class Note {
    @PrimaryKey(autoGenerate = true)
    public int noteID;

    @ColumnInfo(name ="noteName")
    public String noteName;

    @ColumnInfo(name="catID")
    public int catID;

    @ColumnInfo(name="prioID")
    public int prioID;

    @ColumnInfo(name="sttID")
    public int sttID;

    @ColumnInfo(name="timePlan")
    public String timePlan;

    @ColumnInfo(name="timeCre")
    public String timeCre;

    public int userID;

    public Note(String noteName, int catID, int prioID, int sttID, String timePlan, String timeCre,int userID) {
        this.noteName = noteName;
        this.catID = catID;
        this.prioID = prioID;
        this.sttID = sttID;
        this.timePlan = timePlan;
        this.timeCre = timeCre;
        this.userID = userID;
    }
}
