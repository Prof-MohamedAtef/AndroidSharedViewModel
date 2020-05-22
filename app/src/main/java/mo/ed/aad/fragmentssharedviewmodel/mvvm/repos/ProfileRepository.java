package mo.ed.aad.fragmentssharedviewmodel.mvvm.repos;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.util.List;
import mo.ed.aad.fragmentssharedviewmodel.mvvm.room.AppDatabase;
import mo.ed.aad.fragmentssharedviewmodel.mvvm.room.AppDatabaseInitializer;
import mo.ed.aad.fragmentssharedviewmodel.mvvm.dao.ProfileDao;
import mo.ed.aad.fragmentssharedviewmodel.mvvm.model.Profile;

public class ProfileRepository {

    private static ProfileRepository profileRepositoryInstance;
    private final AppDatabase appDatabase;
    ProfileDao profileDao;

    public ProfileRepository(Context context) {
        appDatabase= AppDatabaseInitializer.getAppDatabase(context);
        this.profileDao=appDatabase.profileDao();
//        getProfileRepositoryInstance(context);
    }

    // 1. get instance of the class
    public static ProfileRepository getProfileRepositoryInstance(Context context) {
        if (profileRepositoryInstance==null){
            synchronized (ProfileRepository.class){
                if (profileRepositoryInstance==null){
                    profileRepositoryInstance=new ProfileRepository(context);
                }
            }
        }
        return profileRepositoryInstance;
    }

    // for returning list - not paginated
    public LiveData<List<Profile>> getProfilesList(){
        return appDatabase.profileDao().getAllProfiles();
    }

    // for returning list from rawQuery
    public LiveData<List<Profile>> getProfilesRqwQuery(SupportSQLiteQuery query){
        return appDatabase.profileDao().getMutableList(query);
    }

    // for returning list from rawQuery for Pagination
    public LiveData<PagedList<Profile>> getProfilesRqwQueryPaged(SupportSQLiteQuery query, PagedList.Config config){
        DataSource.Factory<Integer, Profile>factory=this.profileDao.getPagedList(query);
        return new LivePagedListBuilder<>(factory,config)
                .build();
    }

    //for pagination
    public LiveData<PagedList<Profile>> getProfilesPaged(PagedList.Config config){
        DataSource.Factory<Integer, Profile>factory=this.profileDao.getAllProfilesPaged();
        return new LivePagedListBuilder<>(factory,config)
                .build();
    }
}