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
import ir.hosseinabbasi.holidaypirates.ui.base.BaseActivity;
import ir.hosseinabbasi.holidaypirates.ui.main.MainActivity;

public class DetailActivity extends BaseActivity implements DetailMvpView {

    @BindView(R.id.activity_detail_rcyComments)
    RecyclerView mRecyclerViewComments;

    private CommentsAdapter mCommentsAdapter;
    private List<Comments> commentsList = new ArrayList<Comments>();
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
    public void refreshCommentsList() {
        Log.wtf("refreshCommentsList","Called");
        Intent i = getIntent();
        commentsList = (List<Comments>) i.getSerializableExtra("commentResponse");//Fix this!
        mCommentsAdapter = new CommentsAdapter(mContext, commentsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerViewComments.setLayoutManager(mLayoutManager);
        mRecyclerViewComments.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewComments.addItemDecoration(new RowDivider(this, LinearLayoutManager.VERTICAL)); //Row Devider in the List
        mRecyclerViewComments.setAdapter(mCommentsAdapter);
    }
}
