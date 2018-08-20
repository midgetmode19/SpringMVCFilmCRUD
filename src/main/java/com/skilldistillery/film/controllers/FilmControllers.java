package com.skilldistillery.film.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public ModelAndView getByFilmId(int filmId) {
		ModelAndView mv = new ModelAndView();
		Film f = dao.getFilmById(filmId);
		mv.addObject("film", f);
		mv.setViewName("result");
		return mv;
	}

	@RequestMapping(path = "AddFilm.do", method = RequestMethod.POST)
	public ModelAndView addFilmToDatabase(Film film, RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		dao.addFilm(film);
		redir.addFlashAttribute("film", film);
		mv.setViewName("redirect:filmAdded.do"); // redirect to new mapping
		return mv;
	}

	@RequestMapping(path = "filmAdded.do", // mapping to handle Redirect
			method = RequestMethod.GET)
	public String filmView() {
		return "filmAddResult";
	}

	@RequestMapping(path = "removeFilm.do", params = "filmId", method = RequestMethod.POST)
	public ModelAndView removeFilmById(int filmId) {
		ModelAndView mv = new ModelAndView();
		boolean deletedFilm = dao.deleteFilmById(filmId);
		mv.addObject("succeeded", deletedFilm);
		mv.setViewName("filmDeleteResult");
		return mv;
	}

	@RequestMapping(path = "findFilmByKeyword.do", params = "keyWord", method = RequestMethod.GET)
	public ModelAndView getFilmsByKeyword(String keyWord) {
		ModelAndView mv = new ModelAndView();
		List<Film> films = dao.getFilmsBySearchKeyWord(keyWord);
		mv.addObject("films", films);
		mv.setViewName("keywordResults");
		return mv;
	}

	@RequestMapping(path = "editFilm.do", method = RequestMethod.POST)
	public ModelAndView updateFilmById(Film film, RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		boolean updatedFilm = dao.updateFilmById(film);
		mv.addObject("success", updatedFilm); // "succeeded" is variable to check in the view file
		mv.setViewName("redirect:filmUpdated.do");
		return mv;
	}

	@RequestMapping(path = "filmUpdated.do", method = RequestMethod.GET)
	public String filmUpdatedView() {
		return "filmUpdateResult"; // TODO: ***!!Change this if using a new jsp file to display updated film
								// result!!****
	}

}