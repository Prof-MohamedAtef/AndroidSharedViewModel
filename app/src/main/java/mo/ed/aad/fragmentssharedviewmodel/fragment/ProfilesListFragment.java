package mo.ed.aad.fragmentssharedviewmodel.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.sqlite.db.SimpleSQLiteQuery;

import butterknife.BindView;
import butterknife.ButterKnife;
import mo.ed.aad.fragmentssharedviewmodel.R;
import mo.ed.aad.fragmentssharedviewmodel.adapters.PagedProfilesListAdapter;
import mo.ed.aad.fragmentssharedviewmodel.mvvm.model.Profile;
import mo.ed.aad.fragmentssharedviewmodel.mvvm.viewModel.ProfileSharedViewModel;
import mo.ed.aad.fragmentssharedviewmodel.mvvm.viewModel.ProfileViewModelFactory;

public class ProfilesListFragment extends Fragment {

    @BindView(R.id.profiles_list)
    RecyclerView recyclerView;

    private ProfileSharedViewModel profileSharedViewModel;
    private PagedProfilesListAdapter mPagedAdapter;
    private ProfileViewModelFactory mProfileViewModelFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profiles_list, container,false);
        ButterKnife.bind(this,view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mProfileViewModelFactory=new ProfileViewModelFactory(getActivity());
        profileSharedViewModel=new ViewModelProvider(this, mProfileViewModelFactory).get(ProfileSharedViewModel.class);

        // query for rawQuery
        SimpleSQLiteQuery query =new SimpleSQLiteQuery("SELECT * FROM Profiles");
        profileSharedViewModel.getPagedProfilesListRawQuery(query).observe(getViewLifecycleOwner(), new Observer<PagedList<Profile>>() {
            @Override
            public void onChanged(PagedList<Profile> profiles) {
                if (profiles!=null){
                    if (profiles.size()>0){
                        populateRecyclerView(profiles);
                    }
                }
            }
        });

    }

    private void populateRecyclerView(PagedList<Profile> profiles) {
        mPagedAdapter=new PagedProfilesListAdapter(getActivity());
        mPagedAdapter.submitList(profiles);
        mPagedAdapter.notifyDataSetChanged();
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {


            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));


        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mPagedAdapter);
    }
}
