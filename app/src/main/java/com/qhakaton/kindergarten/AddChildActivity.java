package com.qhakaton.kindergarten;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.qhakaton.kindergarten.model.Beacon;
import com.qhakaton.kindergarten.model.Child;

/**
 * Created by Dawid Drozd aka Gelldur on 20.02.16.
 */
public class AddChildActivity extends AppCompatActivity {

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_child);

		//Sorry i don't have time to implement this sh$t right :D
		_editTextName = (EditText) findViewById(R.id.editText);
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

			Child child = new Child(_editTextName.getText().toString(), new Beacon(0, 0, ""));
			Intent intent = new Intent("ret");
			intent.putExtra("child", child);

			setResult(RESULT_OK, intent);
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private EditText _editTextName;
}
