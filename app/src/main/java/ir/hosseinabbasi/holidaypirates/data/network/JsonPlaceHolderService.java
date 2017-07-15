package ir.hosseinabbasi.holidaypirates.data.network;

import io.reactivex.Observable;
import ir.hosseinabbasi.holidaypirates.data.db.model.Comments;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by abbasi on 7/15/2017.
 */

public interface JsonPlaceHolderService {

    @GET("posts/{postId}/comments")
    Observable<Comments> getComments(@Path("postId") String postId);
}
