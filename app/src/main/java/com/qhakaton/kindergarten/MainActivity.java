package com.qhakaton.kindergarten;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.qhakaton.kindergarten.bus.GlobalEventBus;
import com.qhakaton.kindergarten.bus.event.EventAddChild;
import com.qhakaton.kindergarten.bus.event.EventBeaconPick;
import com.qhakaton.kindergarten.bus.event.EventUpdateBeacon;
import com.qhakaton.kindergarten.model.Child;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements BeaconConsumer {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivityForResult(new Intent(MainActivity.this, AddChildActivity.class), REQUEST_ADD_CHILD);
			}
		});

		_beaconManager = BeaconManager.getInstanceForApplication(this);
		_beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:0-3=4c000215,i:4-19,i:20-21,i:22-23,p:24-24"));

	}

	@Override
	protected void onPause() {
		_beaconManager.unbind(this);
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		_beaconManager.bind(this);
	}

	@Override
	protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
		if (requestCode != MainActivity.REQUEST_ADD_CHILD) {
			super.onActivityResult(requestCode, resultCode, data);
			return;
		}
		if (resultCode != AddChildActivity.RESULT_OK) {
			super.onActivityResult(requestCode, resultCode, data);
			return;
		}

		Child child = data.getParcelableExtra("child");
		GlobalEventBus.getInstance().post(new EventAddChild(child));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public static final int REQUEST_ADD_CHILD = 123;

	@Override
	public void onBeaconServiceConnect() {
		_beaconManager.setRangeNotifier(new RangeNotifier() {
			@Override
			public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
				Iterator<Beacon> iterator = beacons.iterator();
				while (iterator.hasNext()){
					final com.qhakaton.kindergarten.model.Beacon beacon = new com.qhakaton.kindergarten.model.Beacon(iterator.next());
					Log.i(TAG_BEACON, "The first beacon I see is about " + beacon.distance + " meters away.");
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							EventUpdateBeacon event = new EventUpdateBeacon();
							event.beacon = beacon;
							GlobalEventBus.getInstance().post(event);
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
	private BeaconManager _beaconManager;
}
