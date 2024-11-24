package com.proyecto.pw.MercaMovil.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.pw.MercaMovil.DTO.UsuarioRegistroDTO;
import com.proyecto.pw.MercaMovil.Service.UsuarioExistenteException;
import com.proyecto.pw.MercaMovil.Service.UsuarioService;

/**
 * Controlador encargado de la gestión del registro de usuarios.
 */
@Controller
@RequestMapping("/signup")
public class SignupController {

	/** Servicio de usuario necesario para el registro. */
	private UsuarioService usuarioServicio;

	/**
	 * Constructor del controlador, inyecta el servicio de usuario.
	 *
	 * @param usuarioServicio el servicio de usuario
	 */
	public SignupController(UsuarioService usuarioServicio) {
		super();
		this.usuarioServicio = usuarioServicio;
	}

	/**
	 * Método para inicializar un nuevo objeto UsuarioRegistroDTO en el modelo.
	 *
	 * @return un nuevo UsuarioRegistroDTO
	 */
	@ModelAttribute("username")
	public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDTO() {
		return new UsuarioRegistroDTO();
	}

	/**
	 * Método para manejar las peticiones GET a /signup.
	 *
	 * @return la vista "signup"
	 */
	@GetMapping
	public String mostrarFormularioDeRegistro() {
		return "signup"; // Retorna la vista "signup"
	}

	/**
	 * Método para manejar las peticiones POST a /signup, registra un nuevo usuario.
	 *
	 * @param usuarioRegistroDTO el DTO con los datos del usuario a registrar
	 * @param result             el resultado de la validación del formulario
	 * @param model              el modelo de la vista
	 * @return la vista "signup"
	 */
	@PostMapping
	public String registrarNuevoUsuario(@ModelAttribute("username") UsuarioRegistroDTO usuarioRegistroDTO,
			BindingResult result, Model model) {
		try {
			usuarioServicio.guardar(usuarioRegistroDTO);
			model.addAttribute("successMessage", "El usuario se creó correctamente");
		} catch (UsuarioExistenteException e) {
			model.addAttribute("errorMessage", e.getMessage());
			model.addAttribute("usuario", usuarioRegistroDTO);
		} catch (Exception e) {
			System.out.println("Error al registrar el usuario: " + e.getMessage());
			model.addAttribute("errorMessage",
					"Error al registrar el usuario (Verifique los campos o intente con otro correo)");
		}
		return "signup";
	}

	
}