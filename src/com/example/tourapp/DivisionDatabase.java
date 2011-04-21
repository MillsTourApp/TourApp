package com.example.tourapp;

import java.io.IOException;
import java.io.InputStream;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DivisionDatabase extends SQLiteOpenHelper{
	
	private Context myContext;
	
	//constructor
	public DivisionDatabase(Context ctx){
		super(ctx, "db", null, 1);
		this.myContext = ctx;
	}// end constructor
	
	String commands;
	
	public void onCreate(SQLiteDatabase db){
		//new try from http://groups.google.com/group/android-developers/browse_thread/thread/b09066ecbcb0c434
		//http://www.coderanch.com/t/498137/Android/Mobile/Reading-text-file-assets-folders
		
		AssetManager myAssetManager = myContext.getResources().getAssets();
		try{
			InputStream is = myAssetManager.open("experimental_file.txt");
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
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
	}
}