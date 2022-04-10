package com.codingdojo.nestor.controladores;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingdojo.nestor.modelos.Book;
import com.codingdojo.nestor.modelos.CreateBook;
import com.codingdojo.nestor.modelos.User;
import com.codingdojo.nestor.servicios.AppService;

@Controller
@RequestMapping("/books")
public class BookController {

	@Autowired
	private AppService servicio;

	@GetMapping("/new")
	public String newBook(HttpSession session, @ModelAttribute("newBook") Book newBook) {
		User currentUser = (User) session.getAttribute("user_session");

		if (currentUser == null) {
			return "redirect:/";
		}

		return "newBook.jsp";
	}

	@PostMapping("/create")
	public String register(@Valid @ModelAttribute("newBook") Book newBook, BindingResult result, Model model) {

		servicio.create_book(newBook, result);
		if (result.hasErrors()) {
			model.addAttribute("newBook", new CreateBook());
			return "newBook.jsp";
		}

		return "redirect:/dashboard";
	}

	@GetMapping("/{id}")
	public String viewBook(HttpSession session, @PathVariable(value = "id") Long id, Model model) {
		User currentUser = (User) session.getAttribute("user_session");

		if (currentUser == null) {
			return "redirect:/";
		}
		
		Optional<Book> book= servicio.find_book_by_id(id);

		model.addAttribute("book",book.get());
		return "viewBook.jsp";
	}
	
	
	@GetMapping("/edit/{id}")
	public String editBook(HttpSession session, @PathVariable(value = "id") Long id, Model model) {
		User currentUser = (User) session.getAttribute("user_session");

		if (currentUser == null) {
			return "redirect:/";
		}
		
		Optional<Book> book= servicio.find_book_by_id(id);

		model.addAttribute("book",book.get());
		return "editBook.jsp";
	}
	
    @PutMapping("/edit/{id}")
    public String updateBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
		
    	servicio.create_book(book, result);
		if (result.hasErrors()) {
			model.addAttribute("newBook", new CreateBook());
			return "editBook.jsp";
		}

		return "redirect:/dashboard";
    }

}
