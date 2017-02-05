/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.model;

/**
 *
 * @author alanv
 */
public class Book {
    
    private int idb;
    private String title;
    private int nump;
    private String genre;
    private Author author;

    public Book() {
    }

    public Book(int idb, String title, int nump, String genre, Author author) {
        this.idb = idb;
        this.title = title;
        this.nump = nump;
        this.genre = genre;
        this.author = author;
    }

    public int getIdb() {
        return idb;
    }

    public void setIdb(int idb) {
        this.idb = idb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNump() {
        return nump;
    }

    public void setNump(int nump) {
        this.nump = nump;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" + "idb=" + idb + ", title=" + title + ", nump=" + nump + ", genre=" + genre + ", author=" + author + '}';
    }
}
