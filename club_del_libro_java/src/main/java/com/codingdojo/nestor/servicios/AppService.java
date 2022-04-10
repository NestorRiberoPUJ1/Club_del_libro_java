package com.codingdojo.nestor.servicios;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.nestor.modelos.Book;
import com.codingdojo.nestor.modelos.LoginUser;
import com.codingdojo.nestor.modelos.User;
import com.codingdojo.nestor.repositorios.BookRepository;
import com.codingdojo.nestor.repositorios.UserRepository;

@Service
public class AppService {

	@Autowired
	private UserRepository repositorio_user;

	@Autowired
	private BookRepository repositorio_book;
	
	
	public List<Book> get_all_books()
	{
		return repositorio_book.findAll();
	}

	public Book create_book(Book nuevoLibro, BindingResult result) {

		String nuevoTitulo = nuevoLibro.getName();

		/*if (repositorio_book.findByName(nuevoTitulo).isPresent()) {
			result.rejectValue("name", "Unique","El libro ya fue ingresado previamente.");
		}*/
		
		if(result.hasErrors()) {
			return null;
		}else {
			return repositorio_book.save(nuevoLibro);
		}
	}
	
	public Optional<Book> find_book_by_id(Long id){
		return repositorio_book.findById(id);
	}

	public User register(User nuevoUsuario, BindingResult result) {

		String nuevoEmail = nuevoUsuario.getEmail();

		// Revisamos si existe el correo electrónico en BD
		if (repositorio_user.findByEmail(nuevoEmail).isPresent()) {
			result.rejectValue("email", "Unique", "El correo fue ingresado previamente.");
		}

		if (!nuevoUsuario.getPassword().equals(nuevoUsuario.getConfirm())) {
			result.rejectValue("confirm", "Matches", "Las contraseñas no coiniciden");
		}

		if (result.hasErrors()) {
			return null;
		} else {
			// Encriptamos contraseña
			String contra_encr = BCrypt.hashpw(nuevoUsuario.getPassword(), BCrypt.gensalt());
			nuevoUsuario.setPassword(contra_encr);
			// Guardo usuario
			return repositorio_user.save(nuevoUsuario);
		}

	}

	public User login(LoginUser nuevoLogin, BindingResult result) {

		if (result.hasErrors()) {
			return null;
		}

		// Buscamos por correo
		Optional<User> posibleUsuario = repositorio_user.findByEmail(nuevoLogin.getEmail());
		if (!posibleUsuario.isPresent()) {
			result.rejectValue("email", "Unique", "Correo ingresado no existe");
			return null;
		}

		User user_login = posibleUsuario.get();

		// Comparamos contraseñas encriptadas
		if (!BCrypt.checkpw(nuevoLogin.getPassword(), user_login.getPassword())) {
			result.rejectValue("password", "Matches", "Contraseña inválida");
		}

		if (result.hasErrors()) {
			return null;
		} else {
			return user_login;
		}

	}

}
