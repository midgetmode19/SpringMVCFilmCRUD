package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.entities.DatabaseAccessor;
import com.skilldistillery.film.entities.Film;


@Controller
public class FilmControllers {

	@Autowired
	private DatabaseAccessor dao;
	
//	public DatabaseAccessor getDAO() {
//		return dao;
//	}
//
	public void setDAO(DatabaseAccessor dao) {
		this.dao = dao;
	}

	@RequestMapping(path = "findFilm.do", params = "filmId", method = RequestMethod.GET)
	public ModelAndView getFilmById(int filmId) {
		ModelAndView mv = new ModelAndView();
		Film f = dao.getFilmById(filmId);
		mv.addObject("film", f);
		mv.setViewName("result");
		return mv;
	}

}
