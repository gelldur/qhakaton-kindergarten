package com.qhakaton.kindergarten;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qhakaton.kindergarten.adapter.ChildrenAdapter;
import com.qhakaton.kindergarten.bus.GlobalEventBus;
import com.qhakaton.kindergarten.bus.event.EventAddChild;
import com.qhakaton.kindergarten.bus.event.EventUpdateBeacon;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		_adapter = new ChildrenAdapter(getActivity());
		GlobalEventBus.registerSafe(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_main, container, false);
	}

	@Override
	public void onViewCreated(final View view, final Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		_recyclerView = (RecyclerView) view.findViewById(R.id.childrenList);
		_recyclerView.setItemAnimator(new DefaultItemAnimator());
		_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
		_recyclerView.setAdapter(_adapter);
	}

	@Override
	public void onDestroy() {
		GlobalEventBus.unregisterSafe(this);
		super.onDestroy();
	}

	public void onEvent(EventAddChild event) {
		_adapter.addChild(event.child);
	}

	public void onEvent(EventUpdateBeacon event) {
		_adapter.updateBeacon(event.beacon);
	}

	private ChildrenAdapter _adapter;
	private RecyclerView _recyclerView;
}
