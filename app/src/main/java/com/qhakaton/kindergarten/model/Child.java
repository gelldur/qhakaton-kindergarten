package com.qhakaton.kindergarten.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Dawid Drozd aka Gelldur on 20.02.16.
 */
public class Child implements Parcelable {

	public Child(final String name, final Beacon beaconIdentifier) {
		_name = name;
		_beaconIdentifier = beaconIdentifier;
	}

	protected Child(Parcel in) {
		_distance = in.readInt();
		_name = in.readString();
		_beaconIdentifier = in.readParcelable(Beacon.class.getClassLoader());
	}

	public static final Creator<Child> CREATOR = new Creator<Child>() {
		@Override
		public Child createFromParcel(Parcel in) {
			return new Child(in);
		}

		@Override
		public Child[] newArray(int size) {
			return new Child[size];
		}
	};

	public String getName() {
		return _name;
	}

	public Beacon getBeaconIdentifier() {
		return _beaconIdentifier;
	}

	public int getDistance() {
		return _distance;
	}

	public void updateBeacon(final Beacon beacon) {
		_beaconIdentifier = beacon;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(final Parcel dest, final int flags) {
		dest.writeInt(_distance);
		dest.writeString(_name);
		dest.writeParcelable(_beaconIdentifier, flags);
	}

	private int _distance = -1;
	private String _name;
	private Beacon _beaconIdentifier;
}
