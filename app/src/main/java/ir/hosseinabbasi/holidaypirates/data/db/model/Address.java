package ir.hosseinabbasi.holidaypirates.data.db.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Address implements Parcelable {

	@SerializedName("zipcode")
	private String zipcode;

	@SerializedName("geo")
	private Geo geo;

	@SerializedName("suite")
	private String suite;

	@SerializedName("city")
	private String city;

	@SerializedName("street")
	private String street;

	public void setZipcode(String zipcode){
		this.zipcode = zipcode;
	}

	public String getZipcode(){
		return zipcode;
	}

	public void setGeo(Geo geo){
		this.geo = geo;
	}

	public Geo getGeo(){
		return geo;
	}

	public void setSuite(String suite){
		this.suite = suite;
	}

	public String getSuite(){
		return suite;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setStreet(String street){
		this.street = street;
	}

	public String getStreet(){
		return street;
	}

	@Override
	public String toString(){
		return
				"Address{" +
						"zipcode = '" + zipcode + '\'' +
						",geo = '" + geo + '\'' +
						",suite = '" + suite + '\'' +
						",city = '" + city + '\'' +
						",street = '" + street + '\'' +
						"}";
	}

	protected Address(Parcel in) {
		zipcode = in.readString();
		geo = (Geo) in.readValue(Geo.class.getClassLoader());
		suite = in.readString();
		city = in.readString();
		street = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(zipcode);
		dest.writeValue(geo);
		dest.writeString(suite);
		dest.writeString(city);
		dest.writeString(street);
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
		@Override
		public Address createFromParcel(Parcel in) {
			return new Address(in);
		}

		@Override
		public Address[] newArray(int size) {
			return new Address[size];
		}
	};
}