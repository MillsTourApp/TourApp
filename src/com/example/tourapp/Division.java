package com.example.tourapp;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.TextView;

public class Division extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.division);
		
		TextView name = (TextView) findViewById(R.id.division_name);
		TextView description = (TextView) findViewById(R.id.division_descript);

		Bundle extras = getIntent().getExtras();
		String divisionName = extras.getString("dName");
		String divisionDescription = extras.getString("description");
		name.setText(divisionName);
		description.setText(divisionDescription);
	}
}
