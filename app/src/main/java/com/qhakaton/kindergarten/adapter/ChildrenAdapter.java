package com.qhakaton.kindergarten.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.qhakaton.kindergarten.R;
import com.qhakaton.kindergarten.adapter.holder.ChildHolder;
import com.qhakaton.kindergarten.model.Child;

import java.util.ArrayList;

/**
 * Created by Dawid Drozd aka Gelldur on 20.02.16.
 */
public class ChildrenAdapter extends RecyclerAdapter {
	public ChildrenAdapter(final Activity activity) {
		super(activity);
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
		return new ChildHolder(getLayoutInflater().inflate(R.layout.row_child, parent, false));
	}

	@Override
	public void onBindViewHolder(final RecyclerView.ViewHolder yourHolder, final int position) {
		ChildHolder holder = (ChildHolder) yourHolder;
		holder.setChild(_childs.get(position));
	}

	@Override
	public int getItemCount() {
		return _childs.size();
	}

	public void addChild(Child child) {
		_childs.add(child);
		notifyItemInserted(_childs.size() - 1);
	}

	private ArrayList<Child> _childs;
}
