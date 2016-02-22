package com.qhakaton.kindergarten.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.qhakaton.kindergarten.R;
import com.qhakaton.kindergarten.adapter.holder.BeaconHolder;
import com.qhakaton.kindergarten.model.Beacon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

/**
 * Created by Maciej Kozłowski on 20.02.16.
 */
public class BeaconAdapter extends RecyclerAdapter {

    public BeaconAdapter(Activity activity) {
        super(activity);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BeaconHolder(getLayoutInflater().inflate(R.layout.row_beacon, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder yourHolder, int position) {
        BeaconHolder holder = (BeaconHolder) yourHolder;
        holder.setBeacon(_beacons.get(position));
    }

    @Override
    public int getItemCount() {
        return _beacons.size();
    }

    public void addBeacon(Beacon beacon) {
        _beaconsHashSet.remove(beacon);
        _beaconsHashSet.add(beacon);
        _beacons.clear();
        _beacons.addAll(_beaconsHashSet);
        sort();
        if (_beacons.size() > 4) {
            _beacons = new ArrayList<>(_beacons.subList(0, 4));
        }
        notifyDataSetChanged();
    }

    private void sort() {
        Collections.sort(_beacons, new Comparator<Beacon>() {
            @Override
            public int compare(final Beacon lhs, final Beacon rhs) {
                float diff = lhs.distance - rhs.distance;
                if (Math.abs(diff) < 0.0001) {
                    return 0;
                }

                return diff > 0 ? 1 : -1;
            }
        });
    }

    private ArrayList<Beacon> _beacons = new ArrayList<>(16);
    private HashSet<Beacon> _beaconsHashSet = new HashSet<>(16);
}
