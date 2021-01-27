package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        final Author foundAuthor = findByFullName(author.getName(), author.getLastName());
        if (foundAuthor != null) {
            return false;
        }

        final int originSize = authors.length;
        authors = Arrays.copyOf(authors, originSize + 1);
        authors[originSize] = author;

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
        final Author foundAuthor = findByFullName(author.getName(), author.getLastName());
        if (foundAuthor == null) {
            return false;
        }

        int originSize = authors.length;
        Author[] newAuthors = new Author[originSize - 1];
        for (int i = 0, j = 0; i < originSize; i++) {
            final Author currentAuthor = authors[i];
            if (!currentAuthor.equals(author)) {
                newAuthors[j++] = currentAuthor;
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
