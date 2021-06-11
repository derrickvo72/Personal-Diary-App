package android.btth.notemanagementsystem.entity;

import androidx.room.DatabaseView;

@DatabaseView("SELECT noteID,noteName,catName,prioName,sttName,timePlan,note.timeCre as timeCre " +
        "FROM note,category,priority,status " +
        "Where note.catID= category.catID and note.prioID = priority.prioID and note.sttID= status.sttID")
public class NoteDetails {
    public int noteID;
    public String noteName,catName,prioName,sttName,timePlan,timeCre;

    public int getNoteID() {
        return noteID;
    }

    public String getNoteName() {
        return noteName;
    }

    public String getCatName() {
        return catName;
    }

    public String getPrioName() {
        return prioName;
    }

    public String getSttName() {
        return sttName;
    }

    public String getTimePlan() {
        return timePlan;
    }

    public String getTimeCre() {
        return timeCre;
    }

    public NoteDetails(int noteID, String noteName, String catName, String prioName, String sttName, String timePlan, String timeCre) {
        this.noteID = noteID;
        this.noteName = noteName;
        this.catName = catName;
        this.prioName = prioName;
        this.sttName = sttName;
        this.timePlan = timePlan;
        this.timeCre = timeCre;
    }
}
