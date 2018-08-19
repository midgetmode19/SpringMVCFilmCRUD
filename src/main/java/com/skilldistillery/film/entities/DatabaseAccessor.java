package com.skilldistillery.film.entities;

import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public interface DatabaseAccessor {

	public Film getFilmById(int filmId);

	public Actor getActorById(int actorId);

	public List<Actor> getActorsByFilmId(int filmId);

	public List<Film> getFilmsByActorId(int actorId);

	public List<Film> getFilmsBySearchKeyWord(String keyWord);

	public void addFilm(Film film);

	public boolean deleteFilmById(int filmId);

	public boolean updateFilmById(int filmId);
}
