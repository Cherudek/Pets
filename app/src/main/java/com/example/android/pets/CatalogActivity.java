package com.example.android.pets;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.pets.data.PetContract.PetEntry;
import com.example.android.pets.data.PetDbHelper;


/**
 * Displays list of pets that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity {

    public static final String LOG_TAG = CatalogActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        displayDatabaseInfo();
    }


    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     */
    private void displayDatabaseInfo() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        PetDbHelper mDbHelper = new PetDbHelper(this);

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();


        // Perform this raw SQL query "SELECT * FROM pets"
        // to get a Cursor that contains all rows from the pets table.
        //Cursor cursor = db.rawQuery("SELECT * FROM " + PetEntry.TABLE_NAME, null);

        String[] projection = { PetEntry._ID,
                                PetEntry.COLUMN_PET_NAME,
                                PetEntry.COLUMN_PET_BREED,
                                PetEntry.COLUMN_PET_GENDER,
                                PetEntry.COLUMN_PET_WEIGHT };

        Cursor c = db.query(PetEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        TextView displayView = (TextView) findViewById(R.id.text_view_pet);



        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // pets table in the database).
            displayView.setText("The pets table contains " + c.getCount() + " pets.\n\n");
                       displayView.append(PetEntry._ID + " - " +
                                        PetEntry.COLUMN_PET_NAME + " - " +
                                        PetEntry.COLUMN_PET_BREED + " - " +
                                        PetEntry.COLUMN_PET_GENDER + " - " +
                                        PetEntry.COLUMN_PET_WEIGHT + "\n");

            // Figure out the index of each column
                        int idColumnIndex = c.getColumnIndex(PetEntry._ID);
                        int nameColumnIndex = c.getColumnIndex(PetEntry.COLUMN_PET_NAME);
                        int breedColumnIndex = c.getColumnIndex(PetEntry.COLUMN_PET_BREED);
                        int genderColumnIndex = c.getColumnIndex(PetEntry.COLUMN_PET_GENDER);
                        int weightColumnIndex = c.getColumnIndex(PetEntry.COLUMN_PET_WEIGHT);

            // Iterate through all the returned rows in the cursor
                        while (c.moveToNext()) {
                            int currentID = c.getInt(idColumnIndex);
                            String currentName = c.getString(nameColumnIndex);
                            String currentBreed = c.getString(breedColumnIndex);
                            int currentGender = c.getInt(genderColumnIndex);
                            int currentWeight = c.getInt(weightColumnIndex);

                            // Display the values from each column of the current row in the cursor in the TextView
                            displayView.append(("\n" + currentID + " - " +
                                    currentName + " - " +
                                    currentBreed + " - " +
                                    currentGender + " - " +
                                    currentWeight));
                        }

            } finally {
                // Always close the cursor when you're done reading from it. This releases all its
                // resources and makes it invalid.
                c.close();
            }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:

                displayDatabaseInfo();

                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}