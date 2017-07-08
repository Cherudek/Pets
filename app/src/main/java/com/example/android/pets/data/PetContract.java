package com.example.android.pets.data;

import android.provider.BaseColumns;

/**
 * Created by Gregorio on 07/07/2017.
 */

public final class PetContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private PetContract() {}

    /* Inner class that defines the table contents */
    public static class PetEntry implements BaseColumns {



        public static final String TABLE_NAME = "pets";

        //Column ID <P>Type: INTEGER</P>
        public static final String _ID = BaseColumns._ID;

        //Column ID <P>Type: TEXT</P>
        public static final String COLUMN_PET_NAME = "name";

        //Column ID <P>Type: TEXT</P>
        public static final String COLUMN_PET_BREED = "breed";

        //Column ID <P>Type: INTEGER</P>
        public static final String COLUMN_PET_GENDER = "gender";

        //Column ID <P>Type: INTEGER</P>
        public static final String COLUMN_PET_WEIGHT = "weight";

        //constant value for gender values 0 = Unknown, 1 = male, 2 = female.
        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;

    }

}
