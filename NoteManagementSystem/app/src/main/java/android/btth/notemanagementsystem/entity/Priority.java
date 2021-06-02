package android.btth.notemanagementsystem.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "priority")
public class Priority {
    @PrimaryKey(autoGenerate = true)
    public int prioID;

    @ColumnInfo(name ="prioName")
    public String prioName;

    @ColumnInfo(name="timeCre")
    public String timeCre;

    public Priority(String prioName, String timeCre) {
        this.prioName = prioName;
        this.timeCre = timeCre;
    }
    public int getPrioID() {
        return prioID;
    }

    public void setPrioID(int prioID) {
        this.prioID = prioID;
    }

    public String getPrioName() {
        return prioName;
    }

    public void setPrioName(String prioName) {
        this.prioName = prioName;
    }

    public String getTimeCre() {
        return timeCre;
    }

    public void setTimeCre(String timeCre) {
        this.timeCre = timeCre;
    }
}
