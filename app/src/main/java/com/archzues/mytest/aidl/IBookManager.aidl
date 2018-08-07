// IBookManager.aidl
package com.archzues.mytest.aidl;

import com.archzues.mytest.Book;

interface IBookManager {

    List<Book> getBookList();
    void addBook(in Book book);
}