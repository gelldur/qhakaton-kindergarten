package com.qhakaton.kindergarten;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.qhakaton.kindergarten.bus.GlobalEventBus;
import com.qhakaton.kindergarten.bus.event.EventAddChild;
import com.qhakaton.kindergarten.model.Child;

public class MainActivity extends AppCompatActivity {

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
}
