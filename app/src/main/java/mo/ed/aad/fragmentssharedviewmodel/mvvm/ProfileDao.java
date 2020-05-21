package mo.ed.aad.fragmentssharedviewmodel.mvvm;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;

import java.util.List;
import mo.ed.aad.fragmentssharedviewmodel.mvvm.model.Profile;

@Dao
public interface ProfileDao {

    @Insert
    long InsertArticle(Profile profile);

    @Query("DELETE FROM Profiles WHERE UserName LIKE :UserName")
    abstract int deleteByUserName(String UserName);

    @Query("DELETE FROM Profiles")
    abstract int deleteAllProfiles();

    @Query("SELECT * From Profiles where UserName LIKE :UseName")
    LiveData<List<Profile>> getProfilesByUserName(String UseName);

    @Query("SELECT * From Profiles where ProfileID LIKE :UserID")
    LiveData<List<Profile>> getProfilesByUserID(String UserID);

    @Query("SELECT * From Profiles")
    LiveData<List<Profile>> getAllProfiles();


    // TODO: 5/21/20      @RawQuery()
}