package com.qhakaton.kindergarten.bus;

import de.greenrobot.event.EventBus;

/**
 * Created by Dawid Drozd aka Gelldur on 20.02.16.
 */
public class GlobalEventBus extends EventBus {

	public static GlobalEventBus getInstance() {
		return _instance;
	}

	public static void registerSafe(Object object) {
		if (_instance.isRegistered(object) == false) {
			_instance.register(object);
		}
	}

	public static void unregisterSafe(Object object) {
		if (_instance.isRegistered(object)) {
			_instance.unregister(object);
		}
	}

	private static GlobalEventBus _instance = new GlobalEventBus();
}
