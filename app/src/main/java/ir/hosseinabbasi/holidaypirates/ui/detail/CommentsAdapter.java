package ir.hosseinabbasi.holidaypirates.ui.detail;

/**
 * Created by Dr.jacky on 2017/07/14.
 */

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import ir.hosseinabbasi.holidaypirates.R;
import ir.hosseinabbasi.holidaypirates.data.db.model.Comments;
import ir.hosseinabbasi.holidaypirates.data.db.model.Posts;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.MyViewHolder>{
    private final PublishSubject<String> onClickSubject = PublishSubject.create();
    private List<Object> commentsList;
    private int lastPosition = -1;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mName, mEmail, mBody;
        private RelativeLayout container;
        //private CardView container;

        public MyViewHolder(View view) {
            super(view);
            container = (RelativeLayout) itemView.findViewById(R.id.rlvCommentRow);
            //container = (CardView) itemView.findViewById(R.id.crdPostRow);
            mName = (TextView) view.findViewById(R.id.comment_row_txtCommentName);
            mBody = (TextView) view.findViewById(R.id.comment_row_txtCommentBody);
            mEmail = (TextView) view.findViewById(R.id.comment_row_txtCommentEmail);
        }

        public void clearAnimation() {
            container.clearAnimation();
        }
    }


    public CommentsAdapter(Context context, List<Object> commentsList) {
        mContext = context;
        this.commentsList = commentsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Comments comment = (Comments) commentsList.get(position);
        final String element = comment.toString();
        holder.mName.setText(comment.getName());
        holder.mBody.setText(comment.getBody()/*.replace("\\\\n", " ")*/);//Fix this!
        holder.mEmail.setText(comment.getEmail());

        setAnimation(holder.container, position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSubject.onNext(element);
            }
        });
    }

    @Override
    public int getItemCount() {
        return commentsList.size();
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
