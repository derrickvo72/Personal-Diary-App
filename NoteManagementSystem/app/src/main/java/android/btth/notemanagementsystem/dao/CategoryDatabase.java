package android.btth.notemanagementsystem.dao;

import android.btth.notemanagementsystem.entity.Category;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Category.class},version = 1)
public abstract class CategoryDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "category.db";
    private  static CategoryDatabase instance;

    //khoi tao cho han instance
    public static synchronized CategoryDatabase getInstance(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),CategoryDatabase.class,DATABASE_NAME)
                .allowMainThreadQueries()  //cho phep query tren main thread
                    .build();
        }
        return instance;
    }
    public abstract CategoryDao categoryDao();
}
