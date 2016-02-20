package com.qhakaton.kindergarten.bus.event;

import com.qhakaton.kindergarten.model.Child;

/**
 * Created by Dawid Drozd aka Gelldur on 20.02.16.
 */
public class EventAddChild {
	public EventAddChild(final Child child) {
		this.child = child;
	}

	public Child child;
}
