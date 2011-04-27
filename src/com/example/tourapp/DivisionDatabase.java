
package com.example.tourapp;

import java.io.IOException;
import java.io.InputStream;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DivisionDatabase extends SQLiteOpenHelper{

	private Context myContext;
	
	//constructor
	public DivisionDatabase(Context ctx){
		super(ctx, "place.db", null, 1);
		this.myContext = ctx;
	}// end constructor
	
	String commands;
	
	public void onCreate(SQLiteDatabase db){
		//db.execSQL("CREATE TABLE Place (_id integer primary key autoincrement, name text, lon integer, lat integer, description text, directionsFromPrevious text, directionsToNext text);");
		//db.execSQL("INSERT INTO Place(name, lon, lat, description, directionsFromPrevious, directionsToNext) values ('Mills Hall', 754, 453, 'As you entered the, stately front gates of Mills College, you became part of a rich history of womens education.   Founded in 1852 in Benecia, California, Mills is the oldest womens college in the West and one of the oldest in the nation.  Originally known as the Young Ladies Seminary, it was purchased by two educational missionaries, Susan and Cyrus Mills.  They moved the College to our Oakland home in 1871 and built the second-oldest building on campus--Mills Hall.  Today, this California Historical Landmark remains as the architectural and administrative center of the College.  Its was built in only nine months on the juncture of Lion and Chimes Creeks.', 'Mills Hall is near the Oval', 'Campanil is on other side of oval');");
		//new try from http://groups.google.com/group/android-developers/browse_thread/thread/b09066ecbcb0c434
		//http://www.coderanch.com/t/498137/Android/Mobile/Reading-text-file-assets-folders
		String nameOfDatabase = "databasetwelve.txt";
		AssetManager myAssetManager = myContext.getAssets();
		try{
			InputStream is = myAssetManager.open(nameOfDatabase);
			byte[] bytes = new byte[is.available()];
			is.read(bytes);
			commands = new String(bytes);
		} catch(IOException e){
			Toast.makeText(myContext, e.getMessage(), Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}//try-catch
		
		String[] arrayOfCommands = commands.split("\n");
		for(String command: arrayOfCommands){
			command.trim();
			if(!(command.equals(""))){
				Log.d("Command item read: ", command);
				try{
					db.execSQL(command);
				} catch (Exception e) {
					Log.d("exception details: ", e.toString());
					throw new RuntimeException("Exception on execSQL: " + command.substring(command.length()-20)+" Database: " + nameOfDatabase);
				}
			}
		}
	}// end onCreate method

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		//db.execSQL("DROP TABLE IF EXISTS Place");
		//onCreate(db);
	}
}