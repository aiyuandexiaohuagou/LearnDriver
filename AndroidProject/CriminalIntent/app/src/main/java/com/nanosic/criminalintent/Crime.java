package com.nanosic.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by hujihao on 3/22/16.
 */
public class Crime {
    private UUID mId;
    private String mTitle;
    private boolean mSolved;
    private Date mDate;


    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public void setSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }

    public Date getDate() {
        return mDate;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setId(UUID mId) {
        this.mId = mId;
    }

    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }


    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }
}
