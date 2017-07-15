package ir.hosseinabbasi.holidaypirates.ui.main;

/**
 * Created by Dr.jacky on 2017/07/14.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import ir.hosseinabbasi.holidaypirates.R;
import ir.hosseinabbasi.holidaypirates.data.db.model.Posts;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.MyViewHolder>{
    private final PublishSubject<String> onClickSubject = PublishSubject.create();
    private List<Posts> postsList;
    private int lastPosition = -1;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle, mId, mBody;
        private RelativeLayout container;

        public MyViewHolder(View view) {
            super(view);
            container = (RelativeLayout) itemView.findViewById(R.id.rlvPostRow);
            mTitle = (TextView) view.findViewById(R.id.txtPostTitle);
            mBody = (TextView) view.findViewById(R.id.txtPostBody);
            mId = (TextView) view.findViewById(R.id.txtPostId);
        }

        public void clearAnimation() {
            container.clearAnimation();
        }
    }


    public PostsAdapter(Context context, List<Posts> postsList) {
        mContext = context;
        this.postsList = postsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Posts post = postsList.get(position);
        final String element = post.toString();
        holder.mTitle.setText(post.getTitle());
        holder.mBody.setText(post.getBody()/*.replace("\\\\n", " ")*/);//Fix this!
        holder.mId.setText(post.getId()+"");

        setAnimation(holder.container, position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSubject.onNext(element);
            }
        });
    }

    public Observable<String> getPositionClicks(){
        return onClickSubject;
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.push_left_in);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public void onViewDetachedFromWindow(MyViewHolder holder) {
        ((MyViewHolder)holder).clearAnimation();//To prevent from some problems with fast scrolling.
        super.onViewDetachedFromWindow(holder);
    }
}
