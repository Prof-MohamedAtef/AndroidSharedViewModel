package mo.ed.aad.fragmentssharedviewmodel.mvvm.room;

import android.content.Context;

import mo.ed.aad.fragmentssharedviewmodel.mvvm.dao.ProfileDao;

public class AppDatabaseInitializer {
    public static AppDatabase appDatabase=null;
    private static AppExecutors mAppExecutors;
    public static AppDatabase getAppDatabase(Context context){
        if (appDatabase==null){
            appDatabase=new AppDatabase() {
                @Override
                public ProfileDao profileDao() {
                    return null;
                }

                @Override
                public void clearAllTables() {

                }
            };
            mAppExecutors=new AppExecutors();
            appDatabase=AppDatabase.getAppDatabase(context,mAppExecutors);
        }

        return appDatabase;
    }
}
