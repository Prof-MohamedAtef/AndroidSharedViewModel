package mo.ed.aad.fragmentssharedviewmodel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mo.ed.aad.fragmentssharedviewmodel.adapters.NavPanelListAdapter;
import mo.ed.aad.fragmentssharedviewmodel.fragment.FragmentA;
import mo.ed.aad.fragmentssharedviewmodel.fragment.FragmentB;
import mo.ed.aad.fragmentssharedviewmodel.fragment.HomeFragment;
import mo.ed.aad.fragmentssharedviewmodel.fragment.ProfileFragment;
import mo.ed.aad.fragmentssharedviewmodel.fragment.ProfilesListFragment;

public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.app_bar)
    Toolbar toolbar;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView navBottom;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.navigation)
    NavigationView navigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_Chat:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container, new ChatFragment()).commit();
                return true;
            case R.id.navigation_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container, new HomeFragment()).commit();
                return true;
            case R.id.navigation_sessions:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container, new ProfilesListFragment()).commit();
                return true;
            case R.id.navigation_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container, new ProfileFragment()).commit();
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, 0, 0);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //CustomAdapter we created for Customize Navigation
        TextView tvTutorName = findViewById(R.id.tvUserName);
        TextView tvJobTitle = findViewById(R.id.tvJobTitle);
        tvTutorName.setText("Mohamed Atef");
        tvJobTitle.setText("Associate Android Developer");

        ListView navList=(ListView) findViewById(R.id.list);
        NavPanelListAdapter adapter = new NavPanelListAdapter(this);
        navList.setAdapter(adapter);

        adapter.setOnItemClickListener(new NavPanelListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (drawer.isDrawerOpen(GravityCompat.START))
                    drawer.closeDrawer(GravityCompat.START);

                switch (position) {

                    case 0:
//                        startActivity(new Intent(MainActivity.this, TutorMapActivity.class));
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
//                        startActivity(new Intent(MainActivity.this, AccountActivity.class));
                        break;
                    case 3:
//                        Intent intent = new Intent(MainActivity.this, ScheduleActivity.class);
//                        startActivity(intent);
                        break;

                    case 4:
                        Toast.makeText(MainActivity.this, "4", Toast.LENGTH_SHORT).show();
                        break;

                    case 5:
//                        startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                        break;

                    case 6:
                        break;

                    default:
                        break;
                }
            }
        });


        navBottom.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navBottom.setSelectedItemId(R.id.navigation_home);

        toolbar.setNavigationOnClickListener(view -> {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                onBackPressed();
            } else {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container, new HomeFragment()).commit();
        }

    }

    @Override
    public void onBackPressed() {
        if (this.drawer.isDrawerOpen(GravityCompat.START)) {
            this.drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}