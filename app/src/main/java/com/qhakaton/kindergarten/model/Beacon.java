package com.qhakaton.kindergarten.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Dawid Drozd aka Gelldur on 20.02.16.
 */
public class Beacon implements Parcelable {

	protected Beacon(Parcel in) {
		major = in.readInt();
		minor = in.readInt();
		uuid = in.readString();
		distance = in.readFloat();
	}

	public static final Creator<Beacon> CREATOR = new Creator<Beacon>() {
		@Override
		public Beacon createFromParcel(Parcel in) {
			return new Beacon(in);
		}

		@Override
		public Beacon[] newArray(int size) {
			return new Beacon[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(final Parcel dest, final int flags) {
		dest.writeInt(major);
		dest.writeInt(minor);
		dest.writeString(uuid);
		dest.writeFloat(distance);
	}

	public int major;
	public int minor;
	public String uuid;
	public float distance;

	public Beacon(final int major, final int minor, final String uuid, final float distance) {
		this.major = major;
		this.minor = minor;
		this.uuid = uuid;
		this.distance = distance;
	}

	public Beacon(org.altbeacon.beacon.Beacon beacon) {
		this.major = beacon.getId2().toInt();
		this.minor = beacon.getId3().toInt();
		this.uuid = beacon.getId1().toString();
		this.distance = (float) beacon.getDistance();
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		final Beacon beacon = (Beacon) o;

		if (major != beacon.major) {
			return false;
		}
		if (minor != beacon.minor) {
			return false;
		}
		return !(uuid != null ? !uuid.equals(beacon.uuid) : beacon.uuid != null);

	}

	@Override
	public int hashCode() {
		int result = major;
		result = 31 * result + minor;
		result = 31 * result + (uuid != null ? uuid.hashCode() : 0);
		return result;
	}
}
