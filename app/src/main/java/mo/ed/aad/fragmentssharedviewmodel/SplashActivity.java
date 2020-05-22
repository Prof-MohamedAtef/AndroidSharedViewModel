package mo.ed.aad.fragmentssharedviewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.btn_clickMe)
    Button btn_clickMe;

    @BindView(R.id.btnAnimatePhoto)
    Button btnAnimatePhoto;

    @BindView(R.id.profile_pic)
    ImageView profile_pic;

    @BindView(R.id.edit_text)
    EditText edit_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        btn_clickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
//                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
            }
        });

        btnAnimatePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), AnimationHolderActivity.class);
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                    profile_pic.setTransitionName(getString(R.string.animated_profile_pic));
                    edit_text.setTransitionName(getString(R.string.animated_text));

                    Pair<View, String> imagePair=Pair.create(profile_pic,profile_pic.getTransitionName());
//                    Pair<View, String> textPair=Pair.create(edit_text, edit_text.getTransitionName());

                    ActivityOptionsCompat optionsCompat=ActivityOptionsCompat
                            .makeSceneTransitionAnimation( SplashActivity.this,profile_pic, profile_pic.getTransitionName());
                    startActivity(intent,optionsCompat.toBundle());
                }else {
                    startActivity(intent);
                }
            }
        });
    }
}