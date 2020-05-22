package mo.ed.aad.fragmentssharedviewmodel.mvvm.room.roomHelper;

import android.os.AsyncTask;
import android.text.Editable;

import mo.ed.aad.fragmentssharedviewmodel.mvvm.model.Profile;
import mo.ed.aad.fragmentssharedviewmodel.mvvm.room.AppDatabase;

public class InsertAsyncTask extends AsyncTask<Void, Void, Boolean> {

    private final Profile profile;
    private final AppDatabase mAppDatabase;
    private final InsertProfile inserProfileListener;
    private String userName, JobTitle, Address, Distance, Time;
    private long returnedInsertVal;

    public InsertAsyncTask(AppDatabase appDatabase, InsertProfile insertProfile,String userNAme, String position, String address, String distance, String time) {
        this.userName=userNAme;
        this.JobTitle=position;
        this.Address=address;
        this.Distance=distance;
        this.Time=time;
        this.profile=new Profile(userName, JobTitle, Address, Distance, Time);
        this.inserProfileListener=insertProfile;
        this.mAppDatabase=appDatabase;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        returnedInsertVal=mAppDatabase.profileDao().InsertArticle(profile);
        if (returnedInsertVal>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if (aBoolean){
            inserProfileListener.onProfileInsertedSuccessful(profile);
        }else {
            inserProfileListener.onProfileInsertedFailed(false);
        }
    }

    public interface InsertProfile{
        public void onProfileInsertedSuccessful(Profile profile);
        public void onProfileInsertedFailed(boolean b);
    }
}