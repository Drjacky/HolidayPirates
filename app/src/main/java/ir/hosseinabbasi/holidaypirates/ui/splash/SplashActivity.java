package ir.hosseinabbasi.holidaypirates.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.graphics.drawable.Animatable2Compat;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.util.Log;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import ir.hosseinabbasi.holidaypirates.R;
import ir.hosseinabbasi.holidaypirates.data.db.model.Posts;
import ir.hosseinabbasi.holidaypirates.ui.base.BaseActivity;
import ir.hosseinabbasi.holidaypirates.ui.main.MainActivity;

public class SplashActivity extends BaseActivity implements SplashMvpView {
    @BindView(R.id.activity_splash_img_logo) ImageView imvLogo;

    @Inject
    SplashMvpPresenter<SplashMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(SplashActivity.this);

        final AnimatedVectorDrawableCompat avd = AnimatedVectorDrawableCompat.create(this, R.drawable.vd_vector_anim);
        imvLogo.setImageDrawable(avd);

        ///////
        //AnimatedVectorDrawable Callback just works on API level 23+
        /*final Animatable anim = (Animatable) imvLogo.getDrawable();
        avd.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
            @Override
            public void onAnimationEnd(Drawable drawable) {
                //super.onAnimationEnd(drawable);
                avd.start();
            }
        });
        anim.start();*/

        //Use postDelayed to loop animation instead
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                avd.start();
                handler.postDelayed(this, 1);
            }
        });
        ///////
    }

    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.getStartIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void openMainActivityWithPostsData(List<Posts> posts) {
        Intent intent = MainActivity.getStartIntent(SplashActivity.this);
        Bundle bundle = new Bundle();
        bundle.putSerializable("postResponse", (Serializable) posts);//Fix this!
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    @Override
    public void startSyncService() {
//        SyncService.start(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {

    }
}
