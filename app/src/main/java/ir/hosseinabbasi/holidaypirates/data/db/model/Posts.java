package ir.hosseinabbasi.holidaypirates.data.db.model;

import android.os.Parcel;
import android.os.Parcelable;

//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

//@Generated("com.robohorse.robopojogenerator")
@Entity
public class Posts implements Parcelable {

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("body")
	private String body;

	@SerializedName("userId")
	private int userId;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setBody(String body){
		this.body = body;
	}

	public String getBody(){
		return body;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	@Override
	public String toString(){
		return
				"Posts{" +
						"id = '" + id + '\'' +
						",title = '" + title + '\'' +
						",body = '" + body + '\'' +
						",userId = '" + userId + '\'' +
						"}";
	}

	protected Posts(Parcel in) {
		id = in.readInt();
		title = in.readString();
		body = in.readString();
		userId = in.readInt();
	}

	@Generated(hash = 429733921)
	public Posts(int id, String title, String body, int userId) {
					this.id = id;
					this.title = title;
					this.body = body;
					this.userId = userId;
	}

	@Generated(hash = 284033814)
	public Posts() {
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(title);
		dest.writeString(body);
		dest.writeInt(userId);
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<Posts> CREATOR = new Parcelable.Creator<Posts>() {
		@Override
		public Posts createFromParcel(Parcel in) {
			return new Posts(in);
		}

		@Override
		public Posts[] newArray(int size) {
			return new Posts[size];
		}
	};
}