package mo.ed.aad.fragmentssharedviewmodel.mvvm.viewModel;

import android.app.Application;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.util.List;

import mo.ed.aad.fragmentssharedviewmodel.mvvm.model.Profile;
import mo.ed.aad.fragmentssharedviewmodel.mvvm.repos.ProfileRepository;

public class ProfileSharedViewModel extends ViewModel {
    private final ProfileRepository profileRepository;
    private LiveData<PagedList<Profile>> mProfilesList;

    private MutableLiveData<Profile> profile=new MutableLiveData<>();
    private MutableLiveData<String> textTransitionName=new MutableLiveData<>();
    private MutableLiveData<String> imageTransitionName=new MutableLiveData<>();

    private MediatorLiveData<List<Profile>> mObserverMediatorLiveDataProfilesList;


    public void setTextTransitionName(String transitionName){
        textTransitionName.setValue(transitionName);
    }

    public LiveData<String> getTextTransitionName(){
        return textTransitionName;
    }

    public void setImageTransitionName(String transitionName){
        imageTransitionName.setValue(transitionName);
    }

    public LiveData<String> getImageTransitionName(){
        return imageTransitionName;
    }

    private final static PagedList.Config  config=(new PagedList.Config.Builder())
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(10)
            .setPageSize(20)
            .setPrefetchDistance(4)
            .build();

    public ProfileSharedViewModel(ProfileRepository profileRepository){
        this.profileRepository=profileRepository;
        mObserverMediatorLiveDataProfilesList=new MediatorLiveData<>();
        mObserverMediatorLiveDataProfilesList.setValue(null);
    }

    public void setProfile(Profile profile){
        this.profile.setValue(profile);
    }

    public LiveData<Profile> getProfile(){
        return this.profile;
    }

    //return mediatorLiveData
    public MediatorLiveData<List<Profile>> getObserverMediatorLiveDataProfilesList() {
        LiveData<List<Profile>> LoadedProfilesList = profileRepository.getProfilesList();
        mObserverMediatorLiveDataProfilesList.addSource(LoadedProfilesList,mObserverMediatorLiveDataProfilesList::setValue);
        return mObserverMediatorLiveDataProfilesList;
    }

    // return paged list
    public LiveData<PagedList<Profile>> getPagedProfilesList(){
        if (mProfilesList==null){
            mProfilesList=profileRepository.getProfilesPaged(config);
        }
        return mProfilesList;
    }


    // return paged list from RawQuery
    public LiveData<PagedList<Profile>> getPagedProfilesListRawQuery(SupportSQLiteQuery query){
        if (mProfilesList==null){
            mProfilesList=profileRepository.getProfilesRqwQueryPaged(query, config);
        }
        return mProfilesList;
    }

    //return mediatorLiveData from RawQuery
    public MediatorLiveData<List<Profile>> getmObserverMediatorLiveDataProfilesListRqwQuery(SupportSQLiteQuery query){
        LiveData<List<Profile>> LoadedProfilesList = profileRepository.getProfilesRqwQuery(query);
        mObserverMediatorLiveDataProfilesList.addSource(LoadedProfilesList,mObserverMediatorLiveDataProfilesList::setValue);
        return mObserverMediatorLiveDataProfilesList;
    }
}