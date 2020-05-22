package mo.ed.aad.fragmentssharedviewmodel.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mo.ed.aad.fragmentssharedviewmodel.R;
import mo.ed.aad.fragmentssharedviewmodel.mvvm.model.Profile;

public class PagedProfilesListAdapter extends PagedListAdapter<Profile, PagedProfilesListAdapter.ProfileViewHolder> {
    private final Context mContext;

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
        }
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_userName) TextView txt_userName;
        @BindView(R.id.job_title) TextView job_title;
        @BindView(R.id.txt_address) TextView txt_address;
        @BindView(R.id.txt_distance_value) TextView txt_distance_value;
        @BindView(R.id.txt_time_value) TextView txt_time_value;

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
}
