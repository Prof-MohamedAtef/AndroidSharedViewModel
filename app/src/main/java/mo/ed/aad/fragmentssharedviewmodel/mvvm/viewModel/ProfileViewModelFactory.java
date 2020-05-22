package mo.ed.aad.fragmentssharedviewmodel.mvvm.viewModel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import mo.ed.aad.fragmentssharedviewmodel.mvvm.repos.ProfileRepository;

public class ProfileViewModelFactory   implements ViewModelProvider.Factory {
    private final Context mContext;

    ProfileRepository profileRepository;

    public ProfileViewModelFactory(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProfileSharedViewModel.class)) {
            profileRepository=new ProfileRepository(this.mContext);
            return (T) new ProfileSharedViewModel(profileRepository);
        }else {
            new IllegalArgumentException("unknown model class " + modelClass);
            return null;
        }
    }
}
