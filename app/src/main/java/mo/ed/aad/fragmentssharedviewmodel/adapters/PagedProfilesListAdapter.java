package mo.ed.aad.fragmentssharedviewmodel.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import mo.ed.aad.fragmentssharedviewmodel.R;
import mo.ed.aad.fragmentssharedviewmodel.mvvm.model.Profile;

public class PagedProfilesListAdapter extends PagedListAdapter<Profile, PagedProfilesListAdapter.ProfileViewHolder> {
    private final Context mContext;
    private String imageTransitionName;
    private String textTransitionName;
    private String IMAGE="image";
    private String ACTION="text";

    public PagedProfilesListAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.mContext=context;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_item,
                parent, false);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        Profile profile=getItem(position);

        if (profile!=null){
            holder.txt_userName.setText(profile.getUserName());
            holder.txt_address.setText(profile.getAddress());
            holder.txt_distance_value.setText(profile.getDistance());
            holder.txt_time_value.setText(profile.getTime());
            holder.job_title.setText(profile.getJobTitle());
            holder.profile_pic.setTransitionName(mContext.getString(R.string.animated_profile_pic)+position);
            holder.txt_userName.setTransitionName(mContext.getString(R.string.animated_text)+position);
            holder.item_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "Clicked position :  "+String.valueOf(position),Toast.LENGTH_LONG);
                    imageTransitionName= holder.profile_pic.getTransitionName();
                    textTransitionName=holder.txt_userName.getTransitionName();
                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                        Bundle bundle=new Bundle();
                        bundle.putString(IMAGE, imageTransitionName);
                        bundle.putString(ACTION, textTransitionName);
                        bundle.putSerializable("profileDetails", profile);
                        bundle.putParcelable("imageBitmap", ((BitmapDrawable)holder.profile_pic.getDrawable()).getBitmap());
                        ((InflateFragmentsListener)mContext).ProfileDetailFragmentLaunch(bundle, imageTransitionName,
                                holder.profile_pic,
                                textTransitionName,
                                holder.txt_userName);
                    }
                }
            });
        }
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_userName) TextView txt_userName;
        @BindView(R.id.job_title) TextView job_title;
        @BindView(R.id.txt_address) TextView txt_address;
        @BindView(R.id.txt_distance_value) TextView txt_distance_value;
        @BindView(R.id.txt_time_value) TextView txt_time_value;
        @BindView(R.id.item_container) ConstraintLayout item_container;
        @BindView(R.id.profile_pic) CircleImageView profile_pic;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    private static DiffUtil.ItemCallback<Profile> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Profile>() {
                // Concert details may have changed if reloaded from the database,
                // but ID is fixed.
                @Override
                public boolean areItemsTheSame(Profile oldConcert, Profile newConcert) {
                    return oldConcert.getProfileID() == newConcert.getProfileID();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(Profile oldConcert,
                                                  Profile newConcert) {
                    return oldConcert.equals(newConcert);
                }
            };


    public interface InflateFragmentsListener {
        public void ProfileDetailFragmentLaunch(Bundle bundle, String image, View imageView, String text, View textView);
    }
}
