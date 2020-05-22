package mo.ed.aad.fragmentssharedviewmodel.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.sqlite.db.SimpleSQLiteQuery;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mo.ed.aad.fragmentssharedviewmodel.R;
import mo.ed.aad.fragmentssharedviewmodel.mvvm.model.Profile;
import mo.ed.aad.fragmentssharedviewmodel.mvvm.viewModel.ProfileSharedViewModel;
import mo.ed.aad.fragmentssharedviewmodel.mvvm.viewModel.ProfileViewModelFactory;

public class HomeFragment extends Fragment {

    private ProfileViewModelFactory profileViewModelFactory;
    private ProfileSharedViewModel profileSharedViewModel;

    @BindView(R.id.userName)
    TextView userName;

    @BindView(R.id.profilePosition)
    TextView profilePosition;

    @BindView(R.id.txt_distance_value)
    TextView txt_distance;

    @BindView(R.id.txt_time_value)
    TextView txt_time_value;

    @BindView(R.id.txt_address_value)
    TextView txt_address_value;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        profileViewModelFactory=new ProfileViewModelFactory(getActivity());
        profileSharedViewModel=new ViewModelProvider(this,profileViewModelFactory).get(ProfileSharedViewModel.class);
        // query for rawQuery
        SimpleSQLiteQuery query =new SimpleSQLiteQuery("SELECT * FROM Profiles WHERE ProfileID = ?", new Object[]{1});

        profileSharedViewModel.getmObserverMediatorLiveDataProfilesListRqwQuery(query).observe(getViewLifecycleOwner(), new Observer<List<Profile>>() {
            @Override
            public void onChanged(List<Profile> profiles) {
                if (profiles!=null){
                    for (Profile profile: profiles){
                        userName.setText(profile.getUserName());
                        profilePosition.setText(profile.getJobTitle());
                        txt_distance.setText(profile.getDistance());
                        txt_time_value.setText(profile.getTime());
                        txt_address_value.setText(profile.getAddress());
                    }
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.home_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
