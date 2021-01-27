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

    private int countBooksByName(String name) {
        int numberOfBooksFound = 0;
        for (int i = 0; i < schoolBooks.length; i++) {
            final SchoolBook currentBook = schoolBooks[i];
            final String currentName = currentBook.getName();
            if (currentName.equals(name)) {
                numberOfBooksFound++;
            }
        }

        return numberOfBooksFound;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int numberOfBooksFound = countBooksByName(name);
        SchoolBook[] foundedBooks = new SchoolBook[numberOfBooksFound];
        for (int i = 0; i < schoolBooks.length; i++) {
            final SchoolBook currentBook = schoolBooks[i];
            final String currentName = currentBook.getName();
            if (currentName.equals(name)) {
                foundedBooks[i] = currentBook;
            }
        }

        return foundedBooks;
    }

    @Override
    public boolean removeByName(String name) {
        SchoolBook[] newSchoolBooks = new SchoolBook[0];
        boolean isRemoved = false;

        for (int i = 0; i < schoolBooks.length; i++) {
            final SchoolBook currentBook = schoolBooks[i];
            final String currentName = currentBook.getName();
            if (currentName.equals(name)) {
                isRemoved = true;
            } else {
                final int originSize = newSchoolBooks.length;
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
