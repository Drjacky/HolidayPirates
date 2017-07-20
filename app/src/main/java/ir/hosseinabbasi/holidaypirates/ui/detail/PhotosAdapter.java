package ir.hosseinabbasi.holidaypirates.ui.detail;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import io.reactivex.subjects.PublishSubject;
import ir.hosseinabbasi.holidaypirates.R;
import ir.hosseinabbasi.holidaypirates.data.db.model.Photos;
import ir.hosseinabbasi.holidaypirates.utils.Blur;

/**
 * Created by abbasi on 7/19/2017.
 */

public class PhotosAdapter extends BaseAdapter {
    private final PublishSubject<String> onClickSubject = PublishSubject.create();
    private List<Photos> photosList;
    private TextView mTextViewTitle;
    private ImageView mImageViewPhoto;
    private int lastPosition = -1;
    private Context mContext;
    private static LayoutInflater inflater = null;
    private MyViewHolder holder;

    public PhotosAdapter(Context context, List<Photos> photosList) {
        mContext = context;
        this.photosList = photosList;
        //Log.wtf("Photos", photosList.toString());
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return photosList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View rowView = convertView;
        final Photos photo = (Photos) photosList.get(i);

        if (rowView == null) {
            rowView = inflater.inflate(R.layout.photo_row, viewGroup, false);
            holder = new MyViewHolder();
            holder.mTextViewTitle = (TextView) rowView.findViewById(R.id.photo_card_row_TxtTitle);
            holder.mImageViewPhoto = (ImageView) rowView.findViewById(R.id.photo_card_row_ImgPhoto);


            rowView.setTag(holder);
        }
        else {
            holder = (MyViewHolder) rowView.getTag();
        }

        holder.mTextViewTitle.setText(photo.getTitle());
        Picasso.with(mContext)
                .load(photo.getThumbnailUrl()) //Thumbnail URL
                //.placeholder()
                .into(holder.mImageViewPhoto, new Callback() {
                    @Override
                    public void onSuccess() {
                        Picasso.with(mContext)
                                .load(photo.getUrl()) //Image URL
                                .placeholder(holder.mImageViewPhoto.getDrawable())
                                .into(holder.mImageViewPhoto);
                    }
                    @Override
                    public void onError() {

                    }
                });

/*        rowView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
            }
        });*/

        return rowView;
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

    public class MyViewHolder {
        TextView mTextViewTitle;
        ImageView mImageViewPhoto;
    }
}
