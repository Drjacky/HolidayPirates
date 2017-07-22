package ir.hosseinabbasi.holidaypirates.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.hosseinabbasi.holidaypirates.R;
import ir.hosseinabbasi.holidaypirates.data.db.model.Photos;
import ir.hosseinabbasi.holidaypirates.data.db.model.Users;
import ir.hosseinabbasi.holidaypirates.ui.base.BaseActivity;

public class DetailActivity extends BaseActivity implements DetailMvpView {

    /*@BindView(R.id.activity_detail_rcyComments)
    RecyclerView mRecyclerViewComments;

    @BindView(R.id.activity_detail_txtUser)
    TextView mTextViewUser;*/

    /*@BindView(R.id.activity_detail_grdPhotos)
    GridView mGridViewPhotos;*/

    @BindView(R.id.activity_detail_recycler_txtUser)
    TextView mTextViewUser;

    @BindView(R.id.activity_detail_recycler_rcyPhotos)
    RecyclerView mRecyclerViewPhotos;

    @BindView(R.id.activity_detail_recycler_toolbar)
    Toolbar toolbar;

    @BindView(R.id.activity_detail_recycler_btnComments)
    Button btnComments;

    private CommentsAdapter mCommentsAdapter;
    private PhotosAdapter mPhotosRecyclerAdapter;//Instead of regular GridView
    private List<Object> combinedList = new ArrayList<Object>();
    private List<Photos> photosList = new ArrayList<Photos>();
    private Context mContext;
    private PopupWindow popWindow;

    @Inject
    DetailMvpPresenter<DetailMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {

        Intent intent = new Intent(context, DetailActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mContext = this;
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
        setSupportActionBar(toolbar);
        initCollapsingToolbar();
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


        btnComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowCommentsPopup(view);
            }
        });
    }

    @Override
    public void loadPhotos(List<Photos> photos) { //Load photos separately, to prevent the user waiting too much
        photosList = photos;
        mPhotosRecyclerAdapter = new PhotosAdapter(mContext, photosList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerViewPhotos.setLayoutManager(mLayoutManager);
        mRecyclerViewPhotos.addItemDecoration(new GridRecyclerSpacingItemDecoration(2, ConvertDpToPx(10), true));
        mRecyclerViewPhotos.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewPhotos.setAdapter(mPhotosRecyclerAdapter);
    }

    private void initCollapsingToolbar() {
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

    private int ConvertDpToPx(int dp) {
        Resources r = mContext.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    private void ShowCommentsPopup(View v){
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Display display = getWindowManager().getDefaultDisplay();
        final Point size = new Point();
        display.getSize(size);
        final View inflatedView = layoutInflater.inflate(R.layout.comment_popup, null,false);
        RecyclerView rcyComments = (RecyclerView) inflatedView.findViewById(R.id.comment_popup_rcyComments);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        rcyComments.setLayoutManager(mLayoutManager);
        rcyComments.setItemAnimator(new DefaultItemAnimator());
        rcyComments.addItemDecoration(new RowDivider(this, LinearLayoutManager.VERTICAL)); //Row Divider in the List

        rcyComments.setAdapter(mCommentsAdapter);

        popWindow = new PopupWindow(inflatedView, size.x - 50,size.y - 50, true );
        popWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.comment_round_corner));
        popWindow.setOutsideTouchable(true);
        popWindow.showAtLocation(v, Gravity.CENTER, 0,0);
    }
}
