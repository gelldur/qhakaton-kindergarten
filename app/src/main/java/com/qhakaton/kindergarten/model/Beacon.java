package com.qhakaton.kindergarten.model;

/**
 * Created by Dawid Drozd aka Gelldur on 20.02.16.
 */
public class Beacon {
	public int major;
	public int minor;
	public String uuid;

	public Beacon(final int major, final int minor, final String uuid) {
		this.major = major;
		this.minor = minor;
		this.uuid = uuid;
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
