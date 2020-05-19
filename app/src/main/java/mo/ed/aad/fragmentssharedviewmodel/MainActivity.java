package mo.ed.aad.fragmentssharedviewmodel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import mo.ed.aad.fragmentssharedviewmodel.fragment.FragmentA;
import mo.ed.aad.fragmentssharedviewmodel.fragment.FragmentB;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container_a, new FragmentA())
                .add(R.id.container_b, new FragmentB())
                .commit();
    }
}