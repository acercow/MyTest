// IBookManager.aidl
package com.archzues.mytest;

import com.archzues.mytest.bean.Book;
import com.archzues.mytest.IOnNewBookArrivedListener;
interface IBookManager {

    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(IOnNewBookArrivedListener listener);
    void unRegisterListener(IOnNewBookArrivedListener listener);
}
