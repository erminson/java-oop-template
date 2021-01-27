package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;

public class SimpleSchoolBookService implements BookService<SchoolBook> {
    private BookRepository<SchoolBook> schoolBookBookRepository;
    private AuthorService authorService;

    public SimpleSchoolBookService() {
    }

    public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookBookRepository, AuthorService authorService) {
        this.schoolBookBookRepository = schoolBookBookRepository;
        this.authorService = authorService;
    }

    @Override
    public boolean save(SchoolBook book) {
        final String authorName = book.getAuthorName();
        final String authorLastName = book.getAuthorLastName();
        final Author author = authorService.findByFullName(authorName, authorLastName);

        if (author == null) {
            return false;
        }

        return schoolBookBookRepository.save(book);
    }

    @Override
    public SchoolBook[] findByName(String name) {
        return schoolBookBookRepository.findByName(name);
    }

    @Override
    public int getNumberOfBooksByName(String name) {
        return findByName(name).length;
    }

    @Override
    public boolean removeByName(String name) {
        return schoolBookBookRepository.removeByName(name);
    }

    @Override
    public int count() {
        return schoolBookBookRepository.count();
    }

    @Override
    public Author findAuthorByBookName(String name) {
        final SchoolBook[] books = findByName(name);
        if (books.length == 0) {
            return null;
        }

        final SchoolBook firstBook = books[0];
        final String authorName = firstBook.getAuthorName();
        final String authorLastName = firstBook.getAuthorLastName();

        return authorService.findByFullName(authorName, authorLastName);
    }
}
