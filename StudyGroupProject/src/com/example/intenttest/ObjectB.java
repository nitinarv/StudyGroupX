package com.example.intenttest;

import android.os.Parcel;
import android.os.Parcelable;

public class ObjectB implements Parcelable {
	
	private ObjectA obj;
	private Long longVal;
	
	
	public ObjectB(){
		
	}
	
	/**
	 * Constructor we are going to use when
	 * reconstructing this variable form the parcel
	 * */
	public ObjectB(Parcel in){
		readFromParcel(in);
	}
	
	public ObjectA getObj() {
		return obj;
	}



	public void setObj(ObjectA obj) {
		this.obj = obj;
	}



	public Long getLongVal() {
		return longVal;
	}



	public void setLongVal(Long longVal) {
		this.longVal = longVal;
	}

	@Override
	public int describeContents() {
	
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		/**
		 * The writeParcel method needs the flag as well,
		 */
		
		dest.writeParcelable(obj, flags);
		dest.writeLong(longVal);

	}
	
	/**
	 * called from the constructor to create this object form a parcel
	 * @param in parcel from which to re-create object,
	 * */
	private void readFromParcel(Parcel in){
		/**
		 * readParcalable needs the classloader
		 * but that can be picked up from the class , 
		 * this will solve the badParcelableException,
		 * Because of the Classnotfound exception
		 * */
		obj = in.readParcelable(ObjectA.class.getClassLoader());
		
		//this is normal code,
		longVal = in.readLong();
		
	}

	/**
	 * Creator call for allowing android to be able to create new
	 * objects individually or as arrays, 
	 * */
	public static final Parcelable.Creator CREATOR = 
			new Parcelable.Creator() {
		public ObjectB createFromParcel(Parcel in){
			return new ObjectB(in);
		}
		
		public ObjectB[] newArray(int size){
			return new ObjectB[size];
		}
		
	};

}
