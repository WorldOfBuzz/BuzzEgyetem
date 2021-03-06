package controllers.library;

import dao.library.LibraryDao;
import models.Library;
import models.LibraryBook;
import play.mvc.Controller;

public class LibraryBookDeleteController extends Controller{

	public static void deleteLibraryBook(Long libraryBookId){
		String errorMessage = null;
		Library owningLibrary = null;
		if (libraryBookId != null){
			LibraryBook libraryBook = LibraryBook.findById(libraryBookId);
			if (libraryBook != null){
				owningLibrary = libraryBook.owningLibrary;
				libraryBook.delete();
			} else {
				errorMessage = "A törlendő könyv nem létezik!";
			}
		} else {
			errorMessage = "Üres könyvazonosító!";
		}
		
		if (errorMessage != null){
			flash.put("errorMessage", errorMessage);
		}
		
		if (owningLibrary == null){
			LibraryController.libraryBooks(null);
		} else {
			LibraryDetailsControllers.libraryDetails(owningLibrary.libraryId);
		}
	}
	
	
}
