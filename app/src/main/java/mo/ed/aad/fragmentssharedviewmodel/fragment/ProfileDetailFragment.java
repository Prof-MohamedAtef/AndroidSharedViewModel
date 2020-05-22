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

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import mo.ed.aad.fragmentssharedviewmodel.R;
import mo.ed.aad.fragmentssharedviewmodel.mvvm.model.Profile;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.profile_detail_fragment, container,false);
        ButterKnife.bind(this,view);

        Bundle bundle=getArguments();
        if (bundle!=null){
            textTransitionName=bundle.getString("text");
            imageTransitionName=bundle.getString("image");
            imageBitmap=bundle.getParcelable("imageBitmap");
            Profile profile=(Profile) bundle.getSerializable("profileDetails");
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                profile_pic.setTransitionName(imageTransitionName);
                userName.setTransitionName(textTransitionName);
            }

            profile_pic.setImageBitmap(imageBitmap);
            userName.setText(profile.getUserName());
            jobTitle.setText(profile.getJobTitle());
            txt_address.setText(profile.getAddress());
            txt_distance_value.setText(profile.getDistance());
            txt_time_value.setText(profile.getTime());
        }
        return view;
    }
}
