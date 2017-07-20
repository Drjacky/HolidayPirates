package ir.hosseinabbasi.holidaypirates.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

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
import ir.hosseinabbasi.holidaypirates.data.db.model.Photos;
import ir.hosseinabbasi.holidaypirates.data.db.model.Posts;
import ir.hosseinabbasi.holidaypirates.data.db.model.Users;
import ir.hosseinabbasi.holidaypirates.ui.base.BaseActivity;
import ir.hosseinabbasi.holidaypirates.ui.main.MainActivity;

public class DetailActivity extends BaseActivity implements DetailMvpView {

    /*@BindView(R.id.activity_detail_rcyComments)
    RecyclerView mRecyclerViewComments;

    @BindView(R.id.activity_detail_txtUser)
    TextView mTextViewUser;*/

    /*@BindView(R.id.activity_detail_grdPhotos)
    GridView mGridViewPhotos;*/

    @BindView(R.id.activity_detail_recycler_txtUser)
    TextView mTextViewUser;

    @BindView(R.id.activity_detail_recycler_rcyPhotos) //~~~//
    RecyclerView mRecyclerViewPhotos;

    @BindView(R.id.activity_detail_recycler_toolbar) //~~~//
    Toolbar toolbar;

    private CommentsAdapter mCommentsAdapter;
    private PhotosAdapter mPhotosAdapter;//For regular GridView
    private PhotosRecyclerAdapter mPhotosRecyclerAdapter;//Instead of regular GridView //~~~//
    private List<Object> combinedList = new ArrayList<Object>();
    private List<Photos> photosList = new ArrayList<Photos>();
    private static Context mContext;

    @Inject
    DetailMvpPresenter<DetailMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        mContext = context;
        Intent intent = new Intent(context, DetailActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_detail);
        setContentView(R.layout.activity_detail_recycler);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(DetailActivity.this);
        setUp();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {
        setSupportActionBar(toolbar);//~~~//
        initCollapsingToolbar();//~~~//
        mPresenter.onViewInitialized();
    }

    @Override
    public void loadWholeData() {
        Intent i = getIntent();
        combinedList = (List<Object>) i.getSerializableExtra("commentResponse");//Fix this!
        Users user = (Users)combinedList.get(combinedList.size() - 1);
        combinedList.remove(user);
        mTextViewUser.setText(user.getName());//Just show the user name
        mCommentsAdapter = new CommentsAdapter(mContext, combinedList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        /*mRecyclerViewComments.setLayoutManager(mLayoutManager);
        mRecyclerViewComments.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewComments.addItemDecoration(new RowDivider(this, LinearLayoutManager.VERTICAL)); //Row Divider in the List
        mRecyclerViewComments.setAdapter(mCommentsAdapter);*/
    }

    @Override
    public void loadPhotos(List<Photos> photos) { //Load photos separately, to prevent the user waiting too much

        photosList = photos;
        /*mPhotosAdapter = new PhotosAdapter(mContext, photosList);
        mGridViewPhotos.setAdapter(mPhotosAdapter);*/
        mPhotosRecyclerAdapter = new PhotosRecyclerAdapter(mContext, photosList);//~~~//
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);//~~~//
        mRecyclerViewPhotos.setLayoutManager(mLayoutManager);//~~~//
        mRecyclerViewPhotos.addItemDecoration(new GridRecyclerSpacingItemDecoration(2, ConvertDpToPx(10), true));//~~~//
        mRecyclerViewPhotos.setItemAnimator(new DefaultItemAnimator());//~~~//
        mRecyclerViewPhotos.setAdapter(mPhotosRecyclerAdapter);//~~~//
    }

    private void initCollapsingToolbar() {//~~~//
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.activity_detail_recycler_collToolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    private int ConvertDpToPx(int dp) {//~~~//
        Resources r = mContext.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
