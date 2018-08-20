package com.skilldistillery.film.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.DatabaseAccessor;
import com.skilldistillery.film.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	String user = "student";
	String pass = "student";

	public DatabaseAccessorObject() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
	}

	@Override
	public Film getFilmById(int filmId) {
//		 Implement the getFilmById method that takes an int film ID, and returns a
//		 Film object (or null, if the film ID returns no data.)
		Film film = null;
		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
//			String sql = "SELECT id, title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features FROM film WHERE id = ?";
//			String sql = "SELECT film.title, film.description, film.release_year, language.name, film.rental_duration, film.rental_rate, film.length, film.replacement_cost, film.rating, film.special_features, film.language_id "
//					+ "FROM film JOIN language ON film.language_id = language.id WHERE film.id = ?";
			
			String sql = "SELECT film.title, film.description, film.release_year, language.name, film.rental_duration, film.rental_rate, film.length, film.replacement_cost, film.rating, film.special_features, film.language_id, category.name\n" + 
					"FROM film JOIN language ON (film.language_id = language.id)\n" + 
					"LEFT JOIN film_category ON (film.id = film_category.film_id)\n" + 
					"LEFT JOIN category ON (category.id = film_category.category_id)\n" + 
					"WHERE film.id = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet filmResult = stmt.executeQuery();
			if (filmResult.next()) {
				film = new Film();// Create the object
				// Here is our mapping of query columns to our object fields:
//				film.setTitle(filmResult.getString(1));
//				film.setDescription(filmResult.getString(2));
//				film.setReleaseYear(filmResult.getInt(3));
//				film.setRating(filmResult.getString(4));
//				film.setLanguage(filmResult.getString(5));
				film.setId(filmId);
				film.setTitle(filmResult.getString(1));
				film.setDescription(filmResult.getString(2));
				film.setReleaseYear(filmResult.getInt(3));
				film.setLanguage(filmResult.getString(4));
				film.setRentalDuration(filmResult.getInt(5));
				film.setRentalRate(filmResult.getDouble(6));
				film.setLength(filmResult.getInt(7));
				film.setReplacementCost(filmResult.getDouble(8));
				film.setRating(filmResult.getString(9));
				film.setSpecialFeatures(filmResult.getString(10));
				film.setLanguageID(filmResult.getShort(11));
				film.setCategories(filmResult.getString(12));

				film.setActors(getActorsByFilmId(filmId)); // A Film has actors
				
			}
			filmResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public Actor getActorById(int actorId) {
		Actor actor = null;
		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet actorResult = stmt.executeQuery();
			if (actorResult.next()) {
				actor = new Actor(); // Create the object
				// Here is our mapping of query columns to our object fields:
				actor.setId(actorResult.getInt(1));
				actor.setFirstName(actorResult.getString(2));
				actor.setLastName(actorResult.getString(3));
				actor.setFilms(getFilmsByActorId(actorId)); // An Actor has Films
			}
			actorResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actor;
	}

	@Override
	public List<Actor> getActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT actor.id, actor.first_name, actor.last_name FROM actor JOIN film_actor ON actor.id = film_actor.actor_id WHERE film_id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				Actor actor = new Actor(id, firstName, lastName);
				actors.add(actor);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

	public List<Film> getFilmsByActorId(int actorId) {
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT id, title, description, release_year, language_id, rental_duration, ";
			sql += " rental_rate, length, replacement_cost, rating, special_features "
					+ " FROM film JOIN film_actor ON film.id = film_actor.film_id " + " WHERE actor_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int filmId = rs.getInt(1);
				String title = rs.getString(2);
				String desc = rs.getString(3);
				short releaseYear = rs.getShort(4);
				int langId = rs.getInt(5);
				int rentDur = rs.getInt(6);
				double rate = rs.getDouble(7);
				int length = rs.getInt(8);
				double repCost = rs.getDouble(9);
				String rating = rs.getString(10);
				String features = rs.getString(11);
				Film film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features);
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	public List<Film> getFilmsBySearchKeyWord(String keyWord) {
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
//			String sql = "SELECT film.title, film.description, film.release_year, film.rental_duration, film.rental_rate, film.length, film.replacement_cost, film.special_features, film.rating, language.name, film.id "
//					+ "FROM film JOIN language ON film.language_id = language.id " + "WHERE film.title LIKE ?"
//					+ " OR film.description LIKE ?;";
			String sql = "SELECT film.title, film.description, film.release_year, film.rental_duration, film.rental_rate, film.length, film.replacement_cost, film.special_features, film.rating, language.name, film.id, category.name \n" + 
					"FROM film JOIN language ON (film.language_id = language.id)\n" + 
					"LEFT JOIN film_category ON (film.id = film_category.film_id)\n" + 
					"LEFT JOIN category ON (category.id = film_category.category_id)\n" + 
					"WHERE film.title LIKE ?\n" + 
					"OR film.description LIKE ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyWord + "%");
			stmt.setString(2, "%" + keyWord + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String title = rs.getString(1);
				String desc = rs.getString(2);
				int releaseYear = rs.getInt(3);
				int rentalDuration = rs.getInt(4);
				double rentalRate = rs.getDouble(5);
				int length = rs.getInt(6);
				double replacementCost = rs.getDouble(7);
				String specialFeatures = rs.getString(8);
				String rating = rs.getString(9);
				String language = rs.getString(10);
				int filmId = rs.getInt(11);
				String categories = rs.getString(12);
				List<Actor> actors = getActorsByFilmId(filmId); // use this when need to display actors
				Film film = new Film(filmId, title, desc, releaseYear, language, rentalDuration, rentalRate, length,
						replacementCost, rating, specialFeatures, categories, actors);
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;

	}

	public void addFilm(Film film) {

		Connection conn = null;
		String sql = "INSERT INTO film (title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features)"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			conn.setAutoCommit(false);
			st.setString(1, film.getTitle());
			st.setString(2, film.getDescription());
			st.setInt(3, film.getReleaseYear());
			st.setInt(4, film.getLanguageID());
			st.setInt(5, film.getRentalDuration());
			st.setDouble(6, film.getRentalRate());
			st.setInt(7, film.getLength());
			st.setDouble(8, film.getReplacementCost());
			st.setString(9, film.getRating());
			st.setString(10, film.getSpecialFeatures());

			int uc = st.executeUpdate();

			if (uc != 1) {

//				return null;
			}
			ResultSet keys = st.getGeneratedKeys();
			if (keys.next()) {
				int filmId = keys.getInt(1);
				film.setId(filmId);

			}
			conn.commit();

			st.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("Error during inserts.");
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.err.println("Error rolling back.");
					e1.printStackTrace();
				}
			}
		}

//		return film;
	}

	public boolean deleteFilmById(int filmId) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); // START TRlANSACTION
			String sql = "DELETE FROM film WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			int updateCount = stmt.executeUpdate();
			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}
	

	public boolean updateFilmById(int filmId) {
		Connection conn = null;
		Film film = getFilmById(filmId);

		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);
			String sql = "UPDATE film JOIN language ON (film.language_id = language.id) "
					+ "SET film.title=?, film.description=?, film.release_year=?, film.language_id=?, film.rental_duration=?, film.rental_rate=?, film.length=?, film.replacement_cost=?, film.rating=?, film.special_features=? "

					+ " WHERE film.id=?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setInt(3, film.getReleaseYear());
			stmt.setInt(4, film.getLanguageID());
			stmt.setInt(5, film.getRentalDuration());
			stmt.setDouble(6, film.getRentalRate());
			stmt.setInt(7, film.getLength());
			stmt.setDouble(8, film.getReplacementCost());
			stmt.setString(9, film.getRating());
			stmt.setString(10, film.getSpecial_features());
			stmt.setInt(11, film.getId());

			int updateCount = stmt.executeUpdate();

			conn.commit();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} // ROLLBACK TRANSACTION ON ERROR
				catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;


	}


}
