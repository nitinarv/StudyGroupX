package com.sgxp.intenttest;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * My basic object to be parcelled to transfer between objects
 * */
public class ObjectA implements Parcelable {

	private String strValue;
	private Integer intValue;

	public ObjectA() {

	}

	/**
	 * Constructor to use when re-constructing the object
	 * */
	public ObjectA(Parcel in) {
		readFromParcel(in);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		/**
		 * We just need to write each field into the parcel. when we read from
		 * parcel, they will come back in the same order
		 * */
		dest.writeString(strValue);
		dest.writeInt(intValue);

	}

	/**
	 * This field is needed for android to be able to create new objects,
	 * individually or as arrays, This also means that you can use the default
	 * constructor to create the object and use another method, to hydrate it as
	 * necessary, I just find it easier to use the constructor It makes sense
	 * for the way I think :P
	 * */
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public ObjectA createFromParcel(Parcel in) {
			return new ObjectA(in);
		}

		public ObjectA[] newArray(int size) {
			return new ObjectA[size];
		}

	};

	/**
	 * Called from the constructor to create this object from a parcel,
	 * 
	 * in parcel from which to re-create the object
	 * */
	private void readFromParcel(Parcel in) {
		/**
		 * We have to read back each field in the order that it was written to
		 * the parcel
		 * */
		strValue = in.readString();
		intValue = in.readInt();
	}

	public String getStrValue() {
		return strValue;
	}

	public void setStrValue(String strValue) {
		this.strValue = strValue;
	}

	public Integer getIntValue() {
		return intValue;
	}

	public void setIntValue(Integer intValue) {
		this.intValue = intValue;
	}

}
