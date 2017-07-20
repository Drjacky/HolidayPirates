package ir.hosseinabbasi.holidaypirates.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import ir.hosseinabbasi.holidaypirates.R;
import ir.hosseinabbasi.holidaypirates.data.db.model.Comments;
import ir.hosseinabbasi.holidaypirates.data.db.model.CommentsUserCombined;
import ir.hosseinabbasi.holidaypirates.data.db.model.Posts;
import ir.hosseinabbasi.holidaypirates.ui.base.BaseActivity;
import ir.hosseinabbasi.holidaypirates.ui.detail.DetailActivity;

/**
 * Created by Dr.jacky on 2017/07/13.
 *
 */
public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.rcyPosts)
    RecyclerView mRecyclerViewPosts;

    private PostsAdapter mPostsAdapter;
    private List<Posts> postsList = new ArrayList<Posts>();
    private static Context mContext;

    public static Intent getStartIntent(Context context) {
        mContext = context;
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    public void refreshPostsList(List<Posts> pList) {
        Intent i = getIntent();
        postsList = (List<Posts>) i.getSerializableExtra("postResponse");//Fix this!
        mPostsAdapter = new PostsAdapter(mContext, postsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerViewPosts.setLayoutManager(mLayoutManager);
        mRecyclerViewPosts.setItemAnimator(new DefaultItemAnimator());
        //mRecyclerViewPosts.addItemDecoration(new RowDivider(this, LinearLayoutManager.VERTICAL)); //Row Divider in the List
        mRecyclerViewPosts.setAdapter(mPostsAdapter);
        mPostsAdapter.getPositionClicks().subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                JsonObject jsonObject = new Gson().fromJson( s.replace("Posts{","{"), JsonObject.class); //Fix this!
                String clickedPostId = jsonObject.get("id").toString();
                String clickedUserId = jsonObject.get("userId").toString();
                clickedPostId = clickedPostId.replace("\"",""); //Fix this!
                clickedUserId = clickedUserId.replace("\"",""); //Fix this!
                mPresenter.onPostsItemClicked(clickedPostId, clickedUserId);
            }
        });
    }

    @Override
    public void openDetailActivityWithData(List<Object> combinedLisr) {
        Intent intent = DetailActivity.getStartIntent(MainActivity.this);
        Bundle bundle = new Bundle();
        bundle.putSerializable("commentResponse", (Serializable) combinedLisr);//Fix this!
        intent.putExtras(bundle);
        startActivity(intent);
        //finish();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onFragmentDetached(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .remove(fragment)
                    .commitNow();
        }
    }

    @Override
    protected void setUp() {
        setSupportActionBar(mToolbar);
        //setupCardContainerView();
        mPresenter.onViewInitialized();
    }
}
