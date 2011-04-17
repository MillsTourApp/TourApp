package com.example.tourapp;

import java.io.IOException;
import java.io.InputStream;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class Database extends SQLiteOpenHelper{
	
	private Context myContext;
	
	//constructor
	public Database(Context ctx){
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
	
	//methods for retrieving info from text file
	
	public SQLiteDatabase db = this.getReadableDatabase();
	
	public SQLiteDatabase getDatabase(){
		return db;
	}
	
	/**
	 * creates a string with all (string) information associated with 1 location object
	 * @param db the SQLite database that contains the information
	 * @param item the particular item you want to get out, for example address or zip code
	 * @param index the index of your item, for example index of Museum Tour or index of Mills College
	 * @return a string with information associated with index object
	 */
	public String place(SQLiteDatabase db, String item, int index) {
		Cursor myCursor = db.rawQuery("select " + item + " from Place", null);
		String[] myStringArray = makeString(myCursor);
		return findDataAtIndex(index, myStringArray);
	} //location

	/**
	 * creates an array with all info of 1 type, for example address or zip
	 * @param myCursor the cursor of the entry you go over
	 * @return an array with all enries of the same sort
	 */
	private String[] makeString(Cursor myCursor) {
		String sqlString = "";
		while(myCursor.moveToNext()) {
			sqlString = sqlString + myCursor.getString(0) + "::";
		} //while

		String[] myStringArray = sqlString.split("::", 15);

		return myStringArray;
	} //makeString

	/**
	 * finds an entry of a specific object at a specific index
	 * @param index the index of the item sought
	 * @param myArray the array searched through
	 * @return the entry at the index
	 */
	private String findDataAtIndex(int index, String[] myArray) {
		String myString = "";
		if(myArray[index]==null) {
			myString = myString+ "This index is null";
		}else{
			myString = myString+myArray[index];
		}
		return myString;
	} //findDataAtIndex
	
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
	}
}