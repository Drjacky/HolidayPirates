package ir.hosseinabbasi.holidaypirates.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import ir.hosseinabbasi.holidaypirates.data.db.model.Posts;
import ir.hosseinabbasi.holidaypirates.data.db.model.Users;
import ir.hosseinabbasi.holidaypirates.ui.base.BaseActivity;
import ir.hosseinabbasi.holidaypirates.ui.main.MainActivity;

public class DetailActivity extends BaseActivity implements DetailMvpView {

    @BindView(R.id.activity_detail_rcyComments)
    RecyclerView mRecyclerViewComments;

    @BindView(R.id.activity_detail_txtUser)
    TextView mTextViewUser;

    @BindView(R.id.activity_detail_grdPhotos)
    GridView mGridViewPhotos;

    private CommentsAdapter mCommentsAdapter;
    private PhotosAdapter mPhotosAdapter;
    private List<Object> combinedList = new ArrayList<Object>();
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
        setContentView(R.layout.activity_detail);
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
        mPresenter.onViewInitialized();
    }

    @Override
    public void loadWholeData() {
        Intent i = getIntent();
        combinedList = (List<Object>) i.getSerializableExtra("commentResponse");//Fix this!
        Users user = (Users)combinedList.get(combinedList.size() - 1);
        combinedList.remove(user);
        mTextViewUser.setText(user.toString());
        mCommentsAdapter = new CommentsAdapter(mContext, combinedList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerViewComments.setLayoutManager(mLayoutManager);
        mRecyclerViewComments.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewComments.addItemDecoration(new RowDivider(this, LinearLayoutManager.VERTICAL)); //Row Divider in the List
        mRecyclerViewComments.setAdapter(mCommentsAdapter);

        //mPhotosAdapter = new PhotosAdapter(mContext);
        //mGridViewPhotos.setAdapter(mPhotosAdapter);
    }
}
