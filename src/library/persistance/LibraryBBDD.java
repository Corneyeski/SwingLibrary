/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static javax.management.Query.match;
import library.model.Author;
import library.model.Book;
import library.model.User;

/**
 *
 * @author alanv
 */
public class LibraryBBDD {

    private Connection conexion;

    private void conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/library";
        String usr = "root";
        String pass = "";
        conexion = DriverManager.getConnection(url, usr, pass);
    }

    private void desconectar() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }

    private User obtenerUser(ResultSet ps) throws SQLException {
        
        User user = new User();

        user.setName(ps.getString("username"));
        user.setPass(ps.getString("password"));
        
        return user;
    }
    
    public List<User> todosUsers() throws SQLException {
        
        conectar();
        List<User> list = new ArrayList<>();

        String obtener = "select * from user";
        Statement consulta = conexion.createStatement();

        ResultSet ps = consulta.executeQuery(obtener);

        while (ps.next()) {
            list.add(obtenerUser(ps));
        }
        desconectar();
        return list;
        
    }
    
    private Author obtenerAutor(ResultSet ps) throws SQLException {
        
        Author autor = new Author();
        
        System.out.println("idauthor" + ps.getInt("idauthor"));

        autor.setIdauthor(ps.getInt("idauthor"));
        autor.setName(ps.getString("name"));
        autor.setSurname(ps.getString("surname"));
        autor.setCountry(ps.getString("country"));

        return autor;
    }

    private Book obtenerLibro(ResultSet ps) throws SQLException {
        
        Book book = new Book();

        book.setIdb(ps.getInt("isbn"));
        book.setTitle(ps.getString("title"));
        book.setNump(ps.getInt("npages"));
        book.setGenre(ps.getString("genre"));
        
        System.out.println(ps.getInt("author"));
        
        String obtener = "select * from author where idauthor =" + ps.getInt("author");
        Statement consulta = conexion.createStatement();

        ResultSet ps2 = consulta.executeQuery(obtener);
        
        System.out.println(ps2.next());

        book.setAuthor(obtenerAutor(ps2));
        
        return book;
    }

    public boolean consulta(User user) throws SQLException {
        
        conectar();
        boolean exist = false;

        String obtener = "select * from user where username ='" + user.getName() + "' and password ='" + user.getPass() + "'";
        Statement consulta = conexion.createStatement();

        System.out.println(user.getName() + " " + user.getPass());
        ResultSet ps = consulta.executeQuery(obtener);
        

        if (ps.next()) {
            exist = true;
        } else {}
        desconectar();
        return exist;
    }

    public void insertarLibro(Book book) throws SQLException {

        conectar();
        String insert = "INSERT INTO book values (?,?,?,?,?)";
        PreparedStatement ps = conexion.prepareStatement(insert);

        ps.setInt(1, book.getIdb());
        ps.setString(2, book.getTitle());
        ps.setInt(3, book.getNump());
        ps.setString(4, book.getGenre());
        ps.setInt(5, book.getAuthor().getIdauthor());

        ps.executeUpdate();
        desconectar();
    }

    public void eliminarlibro(Book book) throws SQLException {

        conectar();
        String insert = "delete from book where isbn = ?";
        PreparedStatement ps = conexion.prepareStatement(insert);
        
        ps.setInt(1, book.getIdb());
        ps.executeUpdate();
        desconectar();
    }

    public void updatearLibro(Book book) throws SQLException {

        conectar();
        String insert = "update book set title=?, npages=?, genre=?, author=? where isbn=" + book.getIdb();
        PreparedStatement ps = conexion.prepareStatement(insert);

        ps.setString(1, book.getTitle());
        ps.setInt(2, book.getNump());
        ps.setString(3, book.getGenre());
        ps.setInt(4, book.getAuthor().getIdauthor());

        ps.executeUpdate();
        desconectar();
    }

    public void insertarAutor(Author author) throws SQLException {

        conectar();
        String insert = "insert into author values (?,?,?,?)";
        PreparedStatement ps = conexion.prepareStatement(insert);

        ps.setInt(1, author.getIdauthor());
        ps.setString(2, author.getName());
        ps.setString(3, author.getSurname());
        ps.setString(4, author.getCountry());

        ps.executeUpdate();
        desconectar();
    }

    public void eliminarAutor(Author author) throws SQLException {

        conectar();
        String insert = "delete from author where idauthor=" + author.getIdauthor();
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.executeUpdate();
        desconectar();
    }

    public void updatearAutor(Author author) throws SQLException {

        conectar();
        String insert = "update author set name=?, surname=?, country=? where idauthor=" + author.getIdauthor();
        PreparedStatement ps = conexion.prepareStatement(insert);

        ps.setString(1, author.getName());
        ps.setString(2, author.getSurname());
        ps.setString(3, author.getCountry());

        ps.executeUpdate();
        desconectar();
    }

    public void insertUser(User user) throws SQLException {

        conectar();
        String insert = "insert into user values (?,?)";
        PreparedStatement ps = conexion.prepareStatement(insert);

        ps.setString(1, user.getName());
        ps.setString(2, user.getPass());

        ps.executeUpdate();
        desconectar();
    }

    public void deleteUser(User user) throws SQLException {

        conectar();
        String insert = "delete from user where username='" + user.getName()+"'";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.executeUpdate();
        desconectar();
    }

    public void updateUser(User user, String name) throws SQLException {

        conectar();
        String insert = "update user set username=?, password=? where username='" + name+"'";
        PreparedStatement ps = conexion.prepareStatement(insert);

        ps.setString(1, user.getName());
        ps.setString(2, user.getPass());

        ps.executeUpdate();
        desconectar();
    }

    public List<Author> todosAutores() throws SQLException {

        conectar();
        List<Author> list = new ArrayList<>();

        String obtener = "select * from author";
        Statement consulta = conexion.createStatement();

        ResultSet ps = consulta.executeQuery(obtener);

        while (ps.next()) {
            list.add(obtenerAutor(ps));
        }
        desconectar();
        return list;
    }

    public Author unAutor(String nombre) throws SQLException {

        conectar();
        String obtener = "select * from author where idauthor =" + nombre;
        Statement consulta = conexion.createStatement();

        ResultSet ps = consulta.executeQuery(obtener);
        
        return obtenerAutor(ps);
    }

    public List<Book> todosLibros() throws SQLException {

        conectar();
        List<Book> list = new ArrayList<>();

        String obtener = "select * from book";
        Statement consulta = conexion.createStatement();

        ResultSet ps = consulta.executeQuery(obtener);

        while (ps.next()) {
            list.add(obtenerLibro(ps));
        }
        desconectar();
        return list;
    }

    public List<Book> librosAutor(int name) throws SQLException {

        conectar();
        List<Book> list = new ArrayList<>();

        String obtener = "select * from book where author=" + name;
        Statement consulta = conexion.createStatement();

        ResultSet ps = consulta.executeQuery(obtener);

        while (ps.next()) {
            list.add(obtenerLibro(ps));
        }
        desconectar();
        return list;
    }

    public List<Book> librosGenero(String name) throws SQLException {

        conectar();
        List<Book> list = new ArrayList<>();

        String obtener = "select * from book where genre=" + name;
        Statement consulta = conexion.createStatement();

        ResultSet ps = consulta.executeQuery(obtener);

        while (ps.next()) {
            list.add(obtenerLibro(ps));
        }
        desconectar();
        return list;
    }

    public List<Book> librosAutorGenero(String name, int author) throws SQLException {

        conectar();
        List<Book> list = new ArrayList<>();

        String obtener = "select * from book where genre='" + name + "' and author =" + author;
        Statement consulta = conexion.createStatement();

        ResultSet ps = consulta.executeQuery(obtener);

        while (ps.next()) {
            list.add(obtenerLibro(ps));
        }
        desconectar();
        return list;
    }

}
