package mo.ed.aad.fragmentssharedviewmodel.mvvm.dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

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

    @Query("SELECT * FROM Profiles")
    DataSource.Factory<Integer, Profile> getAllProfilesPaged();


    // TODO: 5/21/20      @RawQuery()

    @RawQuery(observedEntities = Profile.class)
    LiveData<List<Profile>> getMutableList(SupportSQLiteQuery query);

    //for paging
    @RawQuery(observedEntities = Profile.class)
    DataSource.Factory<Integer, Profile> getPagedList(SupportSQLiteQuery query);
}