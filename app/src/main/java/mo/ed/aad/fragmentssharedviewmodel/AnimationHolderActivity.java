package mo.ed.aad.fragmentssharedviewmodel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimationHolderActivity extends AppCompatActivity {

    @BindView(R.id.profile_pic)
    ImageView profile_pic;

    @BindView(R.id.edit_text)
    EditText edit_text;

    @BindView(R.id.btnHome)
    Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_holder);
        ButterKnife.bind(this);


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            profile_pic.setTransitionName(getString(R.string.animated_profile_pic));
            edit_text.setTransitionName(getString(R.string.animated_text));
        }



        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnimationHolderActivity.this, MainActivity.class));
            }
        });
    }
}
