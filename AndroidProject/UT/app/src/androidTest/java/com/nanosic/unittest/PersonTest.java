package com.nanosic.unittest;

import android.test.InstrumentationTestCase;
import android.util.Log;

/**
 * Created by Administrator on 2016/3/30.
 */
public class PersonTest extends InstrumentationTestCase {
    private static final String TAG = "PersonTest";
    Person person;


    @Override
    protected void tearDown() throws Exception {
        Log.d(TAG, "tearDown: ");
        person = null;
        super.tearDown();
    }

    @Override
    protected void setUp() throws Exception {
        Log.d(TAG, "setUp: ");
        person = new Person();
        super.setUp();
    }

    public void test_getName() {
        Log.d(TAG, "test_getName: ");
        person.setName("xiaoming");
        assertTrue(person.getName().equals("xiaoming"));
    }

    public void test_getAge() {
        Log.d(TAG, "test_getAge: ");
        person.setAge(88);
        assertTrue(88 == person.getAge());
    }

    public void test_getId() {
        Log.d(TAG, "test_getId: ");
        person.setId("123456789");
        assertTrue(person.getId().equals("123456789"));
    }
}
