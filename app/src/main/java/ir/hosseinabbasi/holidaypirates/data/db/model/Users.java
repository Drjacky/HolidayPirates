package ir.hosseinabbasi.holidaypirates.data.db.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Users implements Parcelable {

	@SerializedName("website")
	private String website;

	@SerializedName("address")
	private Address address;

	@SerializedName("phone")
	private String phone;

	@SerializedName("name")
	private String name;

	@SerializedName("company")
	private Company company;

	@SerializedName("id")
	private int id;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	public void setWebsite(String website){
		this.website = website;
	}

	public String getWebsite(){
		return website;
	}

	public void setAddress(Address address){
		this.address = address;
	}

	public Address getAddress(){
		return address;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCompany(Company company){
		this.company = company;
	}

	public Company getCompany(){
		return company;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
	public String toString(){
		return
				"Users{" +
						"website = '" + website + '\'' +
						",address = '" + address + '\'' +
						",phone = '" + phone + '\'' +
						",name = '" + name + '\'' +
						",company = '" + company + '\'' +
						",id = '" + id + '\'' +
						",email = '" + email + '\'' +
						",username = '" + username + '\'' +
						"}";
	}

	protected Users(Parcel in) {
		website = in.readString();
		address = (Address) in.readValue(Address.class.getClassLoader());
		phone = in.readString();
		name = in.readString();
		company = (Company) in.readValue(Company.class.getClassLoader());
		id = in.readInt();
		email = in.readString();
		username = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(website);
		dest.writeValue(address);
		dest.writeString(phone);
		dest.writeString(name);
		dest.writeValue(company);
		dest.writeInt(id);
		dest.writeString(email);
		dest.writeString(username);
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<Users> CREATOR = new Parcelable.Creator<Users>() {
		@Override
		public Users createFromParcel(Parcel in) {
			return new Users(in);
		}

		@Override
		public Users[] newArray(int size) {
			return new Users[size];
		}
	};
}