package ir.hosseinabbasi.holidaypirates.data.db.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Geo implements Parcelable {

	@SerializedName("lng")
	private String lng;

	@SerializedName("lat")
	private String lat;

	public void setLng(String lng){
		this.lng = lng;
	}

	public String getLng(){
		return lng;
	}

	public void setLat(String lat){
		this.lat = lat;
	}

	public String getLat(){
		return lat;
	}

	@Override
	public String toString(){
		return
				"Geo{" +
						"lng = '" + lng + '\'' +
						",lat = '" + lat + '\'' +
						"}";
	}

	protected Geo(Parcel in) {
		lng = in.readString();
		lat = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(lng);
		dest.writeString(lat);
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<Geo> CREATOR = new Parcelable.Creator<Geo>() {
		@Override
		public Geo createFromParcel(Parcel in) {
			return new Geo(in);
		}

		@Override
		public Geo[] newArray(int size) {
			return new Geo[size];
		}
	};
}