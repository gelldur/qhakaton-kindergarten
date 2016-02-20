package com.qhakaton.kindergarten.model;

/**
 * Created by Dawid Drozd aka Gelldur on 20.02.16.
 */
public class Child {

	public Child(final String name, final Beacon beaconIdentifier) {
		_name = name;
		_beaconIdentifier = beaconIdentifier;
	}

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

	private int _distance = -1;
	private String _name;
	private Beacon _beaconIdentifier;
}
