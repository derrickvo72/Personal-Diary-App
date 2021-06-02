package android.btth.notemanagementsystem.dao;

import android.btth.notemanagementsystem.entity.User;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {

    @Insert(onConflict = REPLACE)
    public void Insert(User user);

    @Query("select * from user where email = :email")
    public User getUserByEmail(String email);

    @Query("Update user set firstname = :firstname, lastname=:lastname, email=:email where userID = :uID")
    public void updateProfile(String firstname, String lastname, String email, int uID);

    @Query("Update user set password = :password where userID = :uID")
    public void changePassword(String password, int uID);



}
