package com.qhakaton.kindergarten.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

/**
 * Created by Dawid Drozd aka Gelldur on 20.02.16.
 */
public abstract class RecyclerAdapter extends RecyclerView.Adapter {

	public RecyclerAdapter(final Activity activity) {
		_layoutInflater = activity.getLayoutInflater();
	}

	protected LayoutInflater getLayoutInflater() {
		return _layoutInflater;
	}

	private LayoutInflater _layoutInflater;
}