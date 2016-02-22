package com.qhakaton.kindergarten.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.qhakaton.kindergarten.R;
import com.qhakaton.kindergarten.bus.GlobalEventBus;
import com.qhakaton.kindergarten.bus.event.EventBeaconPick;
import com.qhakaton.kindergarten.model.Beacon;

/**
 * Created by Maciej Kozłowski on 20.02.16.
 */
public class BeaconHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public BeaconHolder(final View itemView) {
        super(itemView);

        itemView.setOnClickListener(this);

        _distance = (TextView) itemView.findViewById(R.id.distance);
        _major = (TextView) itemView.findViewById(R.id.major);
        _minor = (TextView) itemView.findViewById(R.id.minor);
    }

    @Override
    public void onClick(final View v) {
        GlobalEventBus.getInstance().post(new EventBeaconPick(_beacon));
    }

    public void setBeacon(final Beacon beacon) {
        _beacon = beacon;
        _distance.setText(String.valueOf(beacon.distance));
        _major.setText(String.valueOf(beacon.major));
        _minor.setText(String.valueOf(beacon.minor));
    }

    private TextView _distance;
    private TextView _major;
    private TextView _minor;
    private Beacon _beacon;
}
