package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        int oldSize = authors.length;
        Author[] newAuthors = new Author[oldSize + 1];

        for(int i = 0; i < oldSize; i++) {
            final Author currentAuthor = authors[i];
            if (currentAuthor.equals(author)) {
                return false;
            }
            newAuthors[i] = currentAuthor;
        }

        newAuthors[oldSize] = author;
        authors = newAuthors;

        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (int i = 0; i < authors.length; i++) {
            final Author author = authors[i];
            final String currentName = author.getName();
            final String currentLastName = author.getLastName();
            if (currentName.equals(name) && currentLastName.equals(lastname)) {
                return author;
            }
        }

        return null;
    }

    @Override
    public boolean remove(Author author) {
        int findIndex = -1;
        int originSize = authors.length;
        for (int i = 0; i < originSize; i++) {
            if (authors[i].equals(author)) {
                findIndex = i;
                break;
            }
        }

        if (findIndex == -1) {
            return false;
        }

        Author[] newAuthors = new Author[originSize - 1];
        for (int i = 0, j = 0; i < originSize; i++) {
            if (i != findIndex) {
                newAuthors[j++] = authors[i];
            }
        }
        authors = newAuthors;

        return true;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
