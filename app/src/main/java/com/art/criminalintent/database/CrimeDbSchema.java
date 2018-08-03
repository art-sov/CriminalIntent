package com.art.criminalintent.database;

public class CrimeDbSchema {
    public static final class CrimeTable {

        public static final String NAME = "crimes";

        public static final class Coal {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
        }
    }
}
