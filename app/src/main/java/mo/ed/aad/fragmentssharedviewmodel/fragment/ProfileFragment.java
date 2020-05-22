package mo.ed.aad.fragmentssharedviewmodel.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import mo.ed.aad.fragmentssharedviewmodel.R;
import mo.ed.aad.fragmentssharedviewmodel.mvvm.model.Profile;
import mo.ed.aad.fragmentssharedviewmodel.mvvm.room.AppDatabase;
import mo.ed.aad.fragmentssharedviewmodel.mvvm.room.AppDatabaseInitializer;
import mo.ed.aad.fragmentssharedviewmodel.mvvm.room.roomHelper.InsertAsyncTask;

public class ProfileFragment extends Fragment implements InsertAsyncTask.InsertProfile {

    @BindView(R.id.edit_userName)
    EditText edit_userName;

    @BindView(R.id.edit_position)
    EditText edit_position;

    @BindView(R.id.edit_address)
    EditText edit_address;


    @BindView(R.id.edit_distance)
    EditText edit_distance;

    @BindView(R.id.edit_time)
    EditText edit_time;

    @BindView(R.id.btn_submit)
    Button btn_submit;
    private AppDatabase appDatabase;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appDatabase = AppDatabaseInitializer.getAppDatabase(getActivity());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_submit.setEnabled(false);
                if (TextUtils.isEmpty(edit_userName.getText()) ||
                        TextUtils.isEmpty(edit_position.getText()) ||
                        TextUtils.isEmpty(edit_address.getText()) ||
                        TextUtils.isEmpty(edit_time.getText()) ||
                        TextUtils.isEmpty(edit_distance.getText())) {
                    if (TextUtils.isEmpty(edit_userName.getText())) {
                        Toast.makeText(getActivity(), getResources().getString(R.string.enter_username), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(edit_position.getText())) {
                        Toast.makeText(getActivity(), getResources().getString(R.string.enter_jobTitle), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(edit_address.getText())) {
                        Toast.makeText(getActivity(), getResources().getString(R.string.enter_address), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(edit_time.getText())) {
                        Toast.makeText(getActivity(), getResources().getString(R.string.enter_Time), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(edit_distance.getText())) {
                        Toast.makeText(getActivity(), getResources().getString(R.string.enter_distance), Toast.LENGTH_SHORT).show();
                        return;
                    }
                }else {
                    InsertAsyncTask insertAsyncTask = new InsertAsyncTask(
                            appDatabase,
                            (InsertAsyncTask.InsertProfile) ProfileFragment.this,
                            String.valueOf(edit_userName.getText()),
                            String.valueOf(edit_position.getText()),
                            String.valueOf(edit_address.getText()),
                            String.valueOf(edit_distance.getText()),
                            String.valueOf(edit_time.getText()));
                    insertAsyncTask.execute();
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.profile_fragment, container, false);
        ButterKnife.bind(this,view);

        return view;
    }

    @Override
    public void onProfileInsertedSuccessful(Profile profile) {
        btn_submit.setEnabled(true);
        Toast.makeText(getActivity(), getResources().getString(R.string.success), Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragments_container, new HomeFragment()).commit();
    }

    @Override
    public void onProfileInsertedFailed(boolean b) {
        btn_submit.setEnabled(true);
        Toast.makeText(getActivity(), getResources().getString(R.string.notsuccess), Toast.LENGTH_SHORT).show();
    }
}