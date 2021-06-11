package android.btth.notemanagementsystem.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category")
public class Category {
    @PrimaryKey(autoGenerate = true)
    public int catID;

    @ColumnInfo(name ="catName")
    public String catName;

    @ColumnInfo(name="timeCre")
    public String timeCre;

    /**
     * constructer
     * @param catName
     * @param timeCre
     */
    public Category(String catName, String timeCre) {
        this.catName = catName;
        this.timeCre = timeCre;
    }

    /**
     * cac getter, setter
     * @return
     */
    public int getCatID() {
        return catID;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getTimeCre() {
        return timeCre;
    }

    public void setTimeCre(String timeCre) {
        this.timeCre = timeCre;
    }
}

