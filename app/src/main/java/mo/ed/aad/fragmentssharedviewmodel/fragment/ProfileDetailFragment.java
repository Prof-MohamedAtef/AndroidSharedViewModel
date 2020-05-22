package mo.ed.aad.fragmentssharedviewmodel.fragment;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import mo.ed.aad.fragmentssharedviewmodel.R;
import mo.ed.aad.fragmentssharedviewmodel.mvvm.model.Profile;
import mo.ed.aad.fragmentssharedviewmodel.mvvm.viewModel.ProfileSharedViewModel;
import mo.ed.aad.fragmentssharedviewmodel.mvvm.viewModel.ProfileViewModelFactory;

public class ProfileDetailFragment extends Fragment {

    @BindView(R.id.profile_pic)
    CircleImageView profile_pic;

    @BindView(R.id.userName)
    TextView userName;

    @BindView(R.id.profilePosition)
    TextView jobTitle;

    @BindView(R.id.txt_address_value)
    TextView txt_address;

    @BindView(R.id.txt_distance_value)
    TextView txt_distance_value;

    @BindView(R.id.txt_time_value)
    TextView txt_time_value;



    private String textTransitionName;
    private String imageTransitionName;
    private Bitmap imageBitmap;
    private ProfileSharedViewModel profileViewModel;
    private Bundle bundle;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putParcelable("imageBitmap", imageBitmap);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ProfileViewModelFactory profileViewModelFactory=new ProfileViewModelFactory(getActivity());
        profileViewModel=new ViewModelProvider(getActivity(), profileViewModelFactory).get(ProfileSharedViewModel.class);
        if (bundle!=null){
            textTransitionName=bundle.getString("text");
            imageTransitionName=bundle.getString("image");
            imageBitmap=bundle.getParcelable("imageBitmap");
            profileViewModel.setTextTransitionName(textTransitionName);
            profileViewModel.setImageTransitionName(imageTransitionName);
            Profile profile=(Profile) bundle.getSerializable("profileDetails");
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                profile_pic.setTransitionName(imageTransitionName);
                userName.setTransitionName(textTransitionName);
            }

            if (profile!=null){
                profileViewModel.setProfile(profile);
            }

            setDataToViews(profile);
        }else {
            profileViewModel.getProfile().observe(getViewLifecycleOwner(), new Observer<Profile>() {
                @Override
                public void onChanged(Profile profile) {
                    setDataToViews(profile);
                }
            });
            profileViewModel.getTextTransitionName().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    userName.setTransitionName(s);
                }
            });

            profileViewModel.getImageTransitionName().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    profile_pic.setTransitionName(s);
                }
            });
//            if (savedInstanceState!=null){
//                imageBitmap= (Bitmap)savedInstanceState.getParcelable("imageBitmap");
//                profile_pic.setImageBitmap(imageBitmap);
//            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.profile_detail_fragment, container,false);
        ButterKnife.bind(this,view);

        bundle=getArguments();
        return view;
    }

    private void setDataToViews(Profile profile) {
        profile_pic.setImageBitmap(imageBitmap);
        userName.setText(profile.getUserName());
        jobTitle.setText(profile.getJobTitle());
        txt_address.setText(profile.getAddress());
        txt_distance_value.setText(profile.getDistance());
        txt_time_value.setText(profile.getTime());
    }
}