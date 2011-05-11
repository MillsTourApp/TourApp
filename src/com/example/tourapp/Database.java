package com.example.tourapp;

import java.io.IOException;
import java.io.InputStream;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

//Database class that takes a text file containing SQL commands,
//and executes the commands in Java to create a new SQLite Database.
/**
 *Takes a text file containing SQL commands and executes the commands in Java
 *to create a new SQLite Database. 
 */
public class Database extends SQLiteOpenHelper{
    //context on which to call getAssets()
	/**context on which to call getAssets() when
	 * declaring myAssetManager
	 */
	private Context myContext;
	//name of text file that holds SQL commands.
	/**
	 * Name of the text file that holds the SQL commands.
	 */
	private String mTextFile;
	//string to hold the commands from the text file.
	/**
	 * String to hold the commands from the text file.
	 */
	private String commands;
	
	//constructor
	/**
	 * Class constructor
	 * @param ctx Context within which to find assets, where the text file is stored
	 * @param textFileToUse name of text file that holds the SQL commands, as a String.  This file should be located in the assets folder.
	 * @param nameOfDatabase desired name of database that will be stored on the phone.
	 */
	public Database(Context ctx, String textFileToUse, String nameOfDatabase){
		super(ctx, nameOfDatabase, null, 1);
		this.myContext = ctx;
		mTextFile = textFileToUse;
	}// end constructor
	
	//onCreate method
	/**
	 * called when the activity is first created
	 */
	public void onCreate(SQLiteDatabase db){
		
		AssetManager myAssetManager = myContext.getAssets();
		try{
			InputStream is = myAssetManager.open(mTextFile);
			byte[] bytes = new byte[is.available()];
			is.read(bytes);
			commands = new String(bytes);
		} catch(IOException e){
			Toast.makeText(myContext, e.getMessage(), Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}//try-catch
		
		String[] arrayOfCommands = commands.split("\n");
		for(String command: arrayOfCommands){
			if(!(command.equals(""))){
				db.execSQL(command);
			}//end if-statement
		}//end for-each
	}// end onCreate method

	//onUpgrade method for database - the program currently does not specify what should be done here.
	/**
	 * currently a stub
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}//end onUpgrade
}