package com.nanosic.contentprovider;

import android.net.Uri;

/**
 * Created by Administrator on 2016/3/24.
 */
public class Profile {
    /*table name */
    public static final String TABLE_NAME = "profile";

    /*_ID, increase auto*/
    public static final String COLUMN_ID = "_id";

    /*name*/
    public static final String COLUMN_NAME = "name";

    /*authority*/
    public static final String AUTHORITY = "com.nanosic.contentprovider";

    public static final int ITEM = 1;
    public static final int ITEM_ID = 2;

    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.nanosic.profile";
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.nanosic.profile";

    public static final Uri CONTEN_URI = Uri.parse("content://" + AUTHORITY + "/profile");

}
