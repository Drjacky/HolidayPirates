package ir.hosseinabbasi.holidaypirates.ui.detail;

/**
 * Created by Dr.jacky on 2017/07/20.
 */

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import ir.hosseinabbasi.holidaypirates.R;
import ir.hosseinabbasi.holidaypirates.data.db.model.Photos;

public class PhotosRecyclerAdapter extends RecyclerView.Adapter<PhotosRecyclerAdapter.MyViewHolder>{
    private Context mContext;
    private List<Photos> photosList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public ImageView mThumbnail;

        public MyViewHolder(View view) {
            super(view);
            mTitle = (TextView) view.findViewById(R.id.activity_detail_txtPhotoTitle);
            mThumbnail = (ImageView) view.findViewById(R.id.activity_detail_imgPhotoThumbnail);
        }
    }


    public PhotosRecyclerAdapter(Context mContext, List<Photos> photosList) {
        this.mContext = mContext;
        this.photosList = photosList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_card_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Photos photo = photosList.get(position);
        holder.mTitle.setText(photo.getTitle());

        Picasso.with(mContext)
                .load(photo.getThumbnailUrl()) //Thumbnail URL
                //.placeholder()
                .into(holder.mThumbnail/*, new Callback() {
                    @Override
                    public void onSuccess() {
                        Picasso.with(mContext)
                                .load(photo.getUrl()) //Image URL
                                .placeholder(holder.mThumbnail.getDrawable())
                                .into(holder.mThumbnail);
                    }
                    @Override
                    public void onError() {

                    }
                }*/);
    }

    @Override
    public int getItemCount() {
        return photosList.size();
    }
}
