// IBookManager.aidl
package com.archzues.mytest;

import com.archzues.mytest.bean.Book;

interface IBookManager {

    List<Book> getBookList();
    void addBook(in Book book);
}
