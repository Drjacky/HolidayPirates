package ir.hosseinabbasi.holidaypirates.data.db.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Comments implements Parcelable {

	public Comments(){}

	@SerializedName("name")
	private String name;

	@SerializedName("postId")
	private int postId;

	@SerializedName("id")
	private int id;

	@SerializedName("body")
	private String body;

	@SerializedName("email")
	private String email;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setPostId(int postId){
		this.postId = postId;
	}

	public int getPostId(){
		return postId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setBody(String body){
		this.body = body;
	}

	public String getBody(){
		return body;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
	public String toString(){
		return
				"Comments{" +
						"name = '" + name + '\'' +
						",postId = '" + postId + '\'' +
						",id = '" + id + '\'' +
						",body = '" + body + '\'' +
						",email = '" + email + '\'' +
						"}";
	}

	protected Comments(Parcel in) {
		name = in.readString();
		postId = in.readInt();
		id = in.readInt();
		body = in.readString();
		email = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeInt(postId);
		dest.writeInt(id);
		dest.writeString(body);
		dest.writeString(email);
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<Comments> CREATOR = new Parcelable.Creator<Comments>() {
		@Override
		public Comments createFromParcel(Parcel in) {
			return new Comments(in);
		}

		@Override
		public Comments[] newArray(int size) {
			return new Comments[size];
		}
	};
}