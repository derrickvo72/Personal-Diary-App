package android.btth.notemanagementsystem.dao;

import android.btth.notemanagementsystem.entity.Category;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CategoryDao {
    @Query("SELECT * FROM category")
    List<Category> getListCategory();

    @Insert
    void insertCat(Category category);

    @Delete
    void delete(Category category);

    @Update
    void update(Category category);


    @Query("DELETE FROM category")
    void deleteAll();

    @Query("Select catName From category")
    String[] getCatName();

    @Query("select catID from category where catName=:cName Limit 1")
    int getCatIdByCatName(String cName);

    @Query("SELECT COUNT(catID) as NumberofCat from category")
    int getNumberCat();

    //Check catName in database
    @Query("Select Count(*) From category where catName=:catNameCheck")
    int checkCatNameinDb(String catNameCheck);
}
