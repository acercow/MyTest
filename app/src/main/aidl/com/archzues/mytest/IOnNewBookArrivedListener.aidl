// IOnNewBookArrivedListener.aidl
package com.archzues.mytest;
import com.archzues.mytest.bean.Book;
// Declare any non-default types here with import statements

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book book);
}
