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
public class Author {
    
    private int idauthor;
    private String name;
    private String surname;
    private String country;

    public Author() {}

    public Author(int idauthor, String name, String surname, String country) {
        this.idauthor = idauthor;
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    public int getIdauthor() {
        return idauthor;
    }

    public void setIdauthor(int idauthor) {
        this.idauthor = idauthor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Author{" + "ida=" + idauthor + ", name=" + name + ", surnamje=" + surname + ", country=" + country + '}';
    }
}
