package com.qhakaton.kindergarten.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.qhakaton.kindergarten.R;
import com.qhakaton.kindergarten.adapter.holder.ChildHolder;
import com.qhakaton.kindergarten.model.Beacon;
import com.qhakaton.kindergarten.model.Child;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

		sort();
		notifyDataSetChanged();
	}

	public void updateBeacon(final Beacon beacon) {
		int foundPosition = -1;
		for (int i = 0; i < _childs.size(); ++i) {
			final Child child = _childs.get(i);
			if (child.getBeaconIdentifier().equals(beacon)) {
				foundPosition = i;
				child.updateBeacon(beacon);
				break;
			}
		}

		if (foundPosition < 0) {
			return;
		}

		sort();

		for (int i = 0; i < _childs.size(); ++i) {
			final Child child = _childs.get(i);
			if (child.getBeaconIdentifier().equals(beacon)) {
				notifyItemMoved(foundPosition, i);
				return;
			}
		}

	}

	private void sort() {
		Collections.sort(_childs, new Comparator<Child>() {
			@Override
			public int compare(final Child lhs, final Child rhs) {
				return lhs.getDistance() - rhs.getDistance();
			}
		});
	}

	private ArrayList<Child> _childs = new ArrayList<>(16);
}
