package ir.hosseinabbasi.holidaypirates.data.db.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Company implements Parcelable {

	@SerializedName("bs")
	private String bs;

	@SerializedName("catchPhrase")
	private String catchPhrase;

	@SerializedName("name")
	private String name;

	public void setBs(String bs){
		this.bs = bs;
	}

	public String getBs(){
		return bs;
	}

	public void setCatchPhrase(String catchPhrase){
		this.catchPhrase = catchPhrase;
	}

	public String getCatchPhrase(){
		return catchPhrase;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	@Override
	public String toString(){
		return
				"Company{" +
						"bs = '" + bs + '\'' +
						",catchPhrase = '" + catchPhrase + '\'' +
						",name = '" + name + '\'' +
						"}";
	}

	protected Company(Parcel in) {
		bs = in.readString();
		catchPhrase = in.readString();
		name = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(bs);
		dest.writeString(catchPhrase);
		dest.writeString(name);
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<Company> CREATOR = new Parcelable.Creator<Company>() {
		@Override
		public Company createFromParcel(Parcel in) {
			return new Company(in);
		}

		@Override
		public Company[] newArray(int size) {
			return new Company[size];
		}
	};
}