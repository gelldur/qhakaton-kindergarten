package com.qhakaton.kindergarten.bus.event;

import com.qhakaton.kindergarten.model.Beacon;

/**
 * Created by Maciej Kozłowski on 20.02.16.
 */
public class EventBeaconPick {
    public EventBeaconPick(Beacon beacon){
        this.beacon = beacon;
    }

    public Beacon beacon;
}
