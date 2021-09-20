package com.ss.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.library.entity.Author;

public class AuthorDAO extends BaseDAO<Author>{
	
	public AuthorDAO(Connection conn) {
		super(conn);
	}
	
	public void addAuthor(Author author) throws ClassNotFoundException, SQLException {
		save("insert into tbl_author values (?)", new Object[] {
				author.getAuthorName()
		});
	}

	public void updateAuthor(Author author) throws ClassNotFoundException, SQLException {
		save("update tbl_author set authorName = ? where authorId = ?", new Object[] {
				author.getAuthorName(), author.getAuthorId()
		});
	}

	public void deleteAuthor(Author author) throws ClassNotFoundException, SQLException {
		save("delete from tbl_author where authorId = ?", new Object[] {
				author.getAuthorId()
		});
	}

	public List<Author> readAuthorsById(Integer authorId) throws SQLException, ClassNotFoundException {
		return read("select * from tbl_author where author_id = ?", new Object[] {
				authorId
		});
	}

	public List<Author> readAllAuthors() throws SQLException, ClassNotFoundException {
		return read("select * from tbl_author", null);
	}

	public List<Author> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Author> authors = new ArrayList<>();
		while (rs.next()) {
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));
			authors.add(a);
		}
		return authors;
	}
	
}