package android.btth.notemanagementsystem.Controller;

import android.app.AsyncNotedAppOp;
import android.btth.notemanagementsystem.dao.NoteDao;
import android.btth.notemanagementsystem.entity.Note;
import android.btth.notemanagementsystem.entity.NoteDetails;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class Note_Controller extends Manage {
    Context context;
    NoteDao noteDao;
    public Note_Controller(Context context, NoteDao noteDao) {
        this.context = context;
        this.noteDao = noteDao;
    }
    @Override
    public String add(Object object) {
        Note note = (Note) object;


        if(note.noteName.length()==0) {
            return "Vui long nhap ten";
        }
        else{
            if(note.catID ==0 ) {
                Toast.makeText(context, "Vui long chon Category", Toast.LENGTH_LONG).show();
            }
            if(note.prioID ==0 ) {
                Toast.makeText(context, "Vui long chon Priority", Toast.LENGTH_LONG).show();
            }
            if(note.sttID==0 ){
                Toast.makeText(context,"Vui long chon Status", Toast.LENGTH_LONG).show();
            }
            noteDao.insertNote(note);
            return  null;
        }

    }

    @Override
    public String edit(Object id, Object object) {
        Note note = (Note) object;


        if(note.noteName.length()==0) {
            return "Vui long nhap ten";
        }
        else{
            if(note.catID ==0 ) {
                Toast.makeText(context, "Vui long chon Category", Toast.LENGTH_LONG).show();
            }
            if(note.prioID ==0 ) {
                Toast.makeText(context, "Vui long chon Priority", Toast.LENGTH_LONG).show();
            }
            if(note.sttID==0 ){
                Toast.makeText(context,"Vui long chon Status", Toast.LENGTH_LONG).show();
            }
            noteDao.Update(note);
            return null;
        }
    }

    @Override
    public String delete(Object note) {
        noteDao.delete((Note) note);
            return null;
    }

    @Override
    public List<Object> getAll() {
        return null;
    }

    //Lọc theo priority
    public List<NoteDetails> getByPriority(int userId, String proName){
        return noteDao.getNoteByPriority(userId,proName);
    }
}
