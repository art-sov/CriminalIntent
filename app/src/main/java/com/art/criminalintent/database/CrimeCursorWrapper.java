package com.art.criminalintent.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.art.criminalintent.Crime;
import com.art.criminalintent.database.CrimeDbSchema.CrimeTable;

import java.util.Date;
import java.util.UUID;

public class CrimeCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime() {
        String uuindString = getString(getColumnIndex(CrimeTable.Coal.UUID));
        String title = getString(getColumnIndex(CrimeTable.Coal.TITLE));
        long date = getLong(getColumnIndex(CrimeTable.Coal.DATE));
        int isSolved = getInt(getColumnIndex(CrimeTable.Coal.SOLVED));

        Crime crime = new Crime(UUID.fromString(uuindString));
        crime.setTitle(title);
        crime.setDate(new Date(date));
        crime.setSolved(isSolved != 0);

        return crime;
    }
}
