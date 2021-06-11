package android.btth.notemanagementsystem;

import android.btth.notemanagementsystem.dao.CategoryDao;
import android.btth.notemanagementsystem.dao.NoteDao;
import android.btth.notemanagementsystem.dao.PriorityDao;
import android.btth.notemanagementsystem.dao.StatusDao;
import android.btth.notemanagementsystem.dao.UserDao;
import android.btth.notemanagementsystem.entity.Category;
import android.btth.notemanagementsystem.entity.Note;
import android.btth.notemanagementsystem.entity.NoteDetails;
import android.btth.notemanagementsystem.entity.Priority;
import android.btth.notemanagementsystem.entity.Status;
import android.btth.notemanagementsystem.entity.User;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Category.class, Priority.class, Status.class, Note.class, User.class},
        views = {NoteDetails.class},version = 2 )
public abstract class AppDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "notemanagement.db";
    private static AppDatabase instance;
    public static synchronized AppDatabase getInstance(Context context) {
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
    public abstract UserDao userDao();
    public abstract CategoryDao categoryDao();
    public abstract PriorityDao priorityDao();
    public abstract StatusDao statusDao();
    public abstract NoteDao noteDao();

}
