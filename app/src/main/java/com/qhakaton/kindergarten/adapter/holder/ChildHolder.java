package com.qhakaton.kindergarten.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.qhakaton.kindergarten.R;
import com.qhakaton.kindergarten.model.Child;

/**
 * Created by Dawid Drozd aka Gelldur on 20.02.16.
 */
public class ChildHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

	public ChildHolder(final View itemView) {
		super(itemView);

		itemView.setOnClickListener(this);

		_name = (TextView) itemView.findViewById(R.id.name);
		_distance = (TextView) itemView.findViewById(R.id.distance);
	}

	@Override
	public void onClick(final View v) {
		Toast.makeText(v.getContext(), "Die!", Toast.LENGTH_SHORT).show();
	}

	public void setChild(final Child child) {
		_name.setText(child.getName());
		if (child.getDistance() < 0) {
			_distance.setText("Unknown");
		} else {
			_distance.setText(String.valueOf(child.getDistance()));
		}
	}

	private TextView _name;
	private TextView _distance;
}
