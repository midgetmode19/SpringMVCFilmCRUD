package com.skilldistillery.film.entities;

import java.util.InputMismatchException;
//import java.util.List;
import java.util.Scanner;

import com.skilldistillery.film.controllers.DatabaseAccessorObject;
import com.skilldistillery.film.entities.DatabaseAccessor;
import com.skilldistillery.film.entities.Film;
//import com.skilldistillery.filmquery.entities.Actor;
//import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();
		try {
			app.launch();
		} catch (InputMismatchException e) {
			System.out.println("That is not a valid selection.");
			app.launch();
		}
	}

//	private void test() {
//		Film film = db.getFilmById(1);
//		System.out.println(film);
//	
//		Actor actor = db.getActorById(1);
//		System.out.println(actor);
//		
//		List<Actor> actors = db.getActorsByFilmId(1);
//		System.out.println(actors);
//		
//		List<Film> films = db.getFilmsByActorId(1);
//		System.out.println(films);
//
//	}

	private void launch() {
		Scanner input = new Scanner(System.in);
		input.reset();

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		int menuSelect = -1;
		while (menuSelect != 0) {
			printMenu();
			menuSelect = input.nextInt();

			if (menuSelect == 1) {
				System.out.println("Enter Film ID #: ");
				int filmId = input.nextInt();
				if (db.getFilmById(filmId) != null) {
					System.out.println(db.getFilmById(filmId));
				} else {
					System.out.println("Movie not found.");
				}
				// need to display movie title, year, rating, and description
				// need to implement movie not found ("if null...")

			} else if (menuSelect == 2) {
				Scanner searchKeyWord = new Scanner(System.in);
				System.out.println("Enter your search keyword: ");
				String keyWord = searchKeyWord.nextLine();
				if (db.getFilmsBySearchKeyWord(keyWord).isEmpty()) {
					System.out.println("No matches found.");
				} else {
					System.out.println(db.getFilmsBySearchKeyWord(keyWord));
				}
				// searchKeyWord.close();
				// Closing this scanner throws an exception
			} else if (menuSelect == 3) {
				addFilmProcess();
				
				
				// user adds a film to the database
				
				
				
			} else if (menuSelect == 0) {
				System.out.println("Bye");
				System.exit(0);
			} else {
				System.out.println("That is not a valid selection.");
				input.reset();
				printMenu();
				menuSelect = input.nextInt();
			}
		}
	}

	private void printMenu() {
		System.out.println("~~~**Menu**~~~");
		System.out.println("1: Look up a Film by ID");
		System.out.println("2: Look up a Film by search keyword");
		System.out.println("3: Add a Film");
		System.out.println("0: Exit");
	}
	private void addFilmProcess() {
		Scanner filmAdder = new Scanner(System.in);
		Film film = new Film();
		
		System.out.print("\nFilm title: ");
		film.setTitle(filmAdder.nextLine());
		filmAdder.reset();
		
		System.out.print("\nDescription: ");
		film.setDescription(filmAdder.nextLine());
		filmAdder.reset();
		
		System.out.print("\nRelease Year: ");
		film.setReleaseYear(Integer.parseInt(filmAdder.nextLine()));
		filmAdder.reset();
		
		System.out.print("\nRating: ");
		film.setRating(filmAdder.nextLine());
		filmAdder.reset();
		
		db.addFilm(film);
	}

}
