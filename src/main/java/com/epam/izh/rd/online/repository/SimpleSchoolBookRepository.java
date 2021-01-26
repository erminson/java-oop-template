package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        if (book == null) {
            return false;
        }

        int originSize = schoolBooks.length;
        schoolBooks = Arrays.copyOf(schoolBooks, originSize + 1);
        schoolBooks[originSize] = book;

        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] findedBooks = new SchoolBook[0];
        for (int i = 0; i < schoolBooks.length; i++) {
            final SchoolBook currentBook = schoolBooks[i];
            if (currentBook.getName().equals(name)) {
                int originSize = findedBooks.length;
                findedBooks = Arrays.copyOf(findedBooks, originSize + 1);
                findedBooks[originSize] = currentBook;
            }
        }

        return findedBooks;
    }

    @Override
    public boolean removeByName(String name) {
        SchoolBook[] newSchoolBooks = new SchoolBook[0];
        boolean isRemoved = false;

        for (int i = 0; i < schoolBooks.length; i++) {
            final SchoolBook currentBook = schoolBooks[i];
            final String currentName = currentBook.getName();
            if (!currentName.equals(name)) {
                isRemoved = true;
                int originSize = newSchoolBooks.length;
                newSchoolBooks = Arrays.copyOf(newSchoolBooks, originSize + 1);
                newSchoolBooks[originSize] = currentBook;
            }
        }

        if (isRemoved) {
            schoolBooks = newSchoolBooks;
        }

        return isRemoved;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
