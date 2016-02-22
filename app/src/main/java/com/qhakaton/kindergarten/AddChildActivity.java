package com.qhakaton.kindergarten;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.qhakaton.kindergarten.adapter.BeaconAdapter;
import com.qhakaton.kindergarten.bus.GlobalEventBus;
import com.qhakaton.kindergarten.bus.event.EventBeaconPick;
import com.qhakaton.kindergarten.model.Beacon;
import com.qhakaton.kindergarten.model.Child;

import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Dawid Drozd aka Gelldur on 20.02.16.
 */
public class AddChildActivity extends AppCompatActivity implements BeaconConsumer {

    private BeaconAdapter _beaconAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);

        //Sorry i don't have time to implement this sh$t right :D
        _editTextName = (EditText) findViewById(R.id.editText);

        _beaconManager = BeaconManager.getInstanceForApplication(this);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.beaconList);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        _beaconAdapter = new BeaconAdapter(this);
        recyclerView.setAdapter(_beaconAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        GlobalEventBus.getInstance().register(this);
        _beaconManager.bind(this);
    }

    @Override
    protected void onPause() {
        GlobalEventBus.getInstance().unregister(this);
        _beaconManager.unbind(this);
        super.onPause();
    }

    public void onEvent(EventBeaconPick event) {
        addChild(event.beacon);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_add_child, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == R.id.actionAdd) {
            addChild(new Beacon(0, 0, "", 0));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addChild(Beacon beacon){
        Child child = new Child(_editTextName.getText().toString(), beacon);
        Intent intent = new Intent("ret");
        intent.putExtra("child", child);

        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBeaconServiceConnect() {
        _beaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<org.altbeacon.beacon.Beacon> beacons, Region region) {
                Iterator<org.altbeacon.beacon.Beacon> iterator = beacons.iterator();
                while (iterator.hasNext()){
                    final Beacon beacon = new Beacon(iterator.next());
                    Log.i(TAG_BEACON, "The first beacon I see is about " + beacon.distance + " meters away.");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            _beaconAdapter.addBeacon(beacon);
                        }
                    });


                }
            }
        });

        try {
            _beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static final String TAG_BEACON = "BEACON";

    private EditText _editTextName;
    private BeaconManager _beaconManager;
}
