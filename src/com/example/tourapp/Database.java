package com.example.tourapp;

import java.io.IOException;
import java.io.InputStream;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class Database extends SQLiteOpenHelper{

	private Context myContext;
	private String textFile;
	
	//constructor
	public Database(Context ctx, String textFileToUse, String nameOfDatabase){
		super(ctx, nameOfDatabase, null, 1);
		this.myContext = ctx;
		textFile = textFileToUse;
	}// end constructor
	
	String commands;
	
	public void onCreate(SQLiteDatabase db){
		
		AssetManager myAssetManager = myContext.getAssets();
		try{
			InputStream is = myAssetManager.open(textFile);
			byte[] bytes = new byte[is.available()];
			is.read(bytes);
			commands = new String(bytes);
		} catch(IOException e){
			Toast.makeText(myContext, e.getMessage(), Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}//try-catch
		
		String[] arrayOfCommands = commands.split(";");
		for(String command: arrayOfCommands){
			if(!(command.equals(""))){
				db.execSQL(command);
			}
		}
	}// end onCreate method

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}
}