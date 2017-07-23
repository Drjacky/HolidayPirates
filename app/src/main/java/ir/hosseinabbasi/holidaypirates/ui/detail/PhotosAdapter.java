package ir.hosseinabbasi.holidaypirates.ui.detail;

/**
 * Created by Dr.jacky on 2017/07/20.
 */

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import ir.hosseinabbasi.holidaypirates.R;
import ir.hosseinabbasi.holidaypirates.data.db.model.Photos;
import ir.hosseinabbasi.holidaypirates.utils.Blur;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.MyViewHolder>{
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


    public PhotosAdapter(Context mContext, List<Photos> photosList) {
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
                .placeholder(R.drawable.activity_detail_bg_image)
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

        holder.mThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowOriginalPhoto(photo.getUrl(), holder.mThumbnail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return photosList.size();
    }

    Transformation blurTransformation = new Transformation() {
        @Override
        public Bitmap transform(Bitmap source) {
            Bitmap blurred = Blur.fastblur(mContext, source, 10);
            source.recycle();
            return blurred;
        }

        @Override
        public String key() {
            return "blur()";
        }
    };

    private void ShowOriginalPhoto(final String originalUrl, ImageView imgThumbnail){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setPositiveButton("Link", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        final AlertDialog dialog = builder.create();
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);//(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View inflatedView = layoutInflater.inflate(R.layout.photo_popup, null,false);
        dialog.setView(inflatedView);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.show();
        final ImageView image = (ImageView) dialog.findViewById(R.id.photo_popup_imgPhoto);
        Picasso.with(mContext)
                .load(originalUrl) //Image URL
                //.placeholder(imgThumbnail.getDrawable())
                //.fit().centerCrop()
                //.transform(blurTransformation)
                .into(image);

        /*dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface d) {
                Bitmap icon = BitmapFactory.decodeStream(image.getDrawable())
                float imageWidthInPX = (float)icon.getWidth();

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Math.round(imageWidthInPX),
                        Math.round(imageWidthInPX * (float)icon.getHeight() / (float)icon.getWidth()));
                image.setLayoutParams(layoutParams);
            }
        });*/
    }
}
