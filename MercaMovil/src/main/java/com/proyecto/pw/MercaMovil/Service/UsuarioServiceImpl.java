package com.proyecto.pw.MercaMovil.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.pw.MercaMovil.DTO.UsuarioRegistroDTO;
import com.proyecto.pw.MercaMovil.Model.Carrito;
import com.proyecto.pw.MercaMovil.Model.Rol;
import com.proyecto.pw.MercaMovil.Model.Usuario;
import com.proyecto.pw.MercaMovil.Repository.RolRepository;
import com.proyecto.pw.MercaMovil.Repository.UsuarioRepository;

/**
 * Clase de implementación del servicio de usuario.
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    // INYECCIÓN DE DEPENDENCIAS DEL REPOSITORIO DE USUARIO
    @Autowired
    private UsuarioRepository usuarioRepositorio;

    // INYECCIÓN DE DEPENDENCIAS DEL REPOSITORIO DE ROL
    @Autowired
    private RolRepository rolRepositorio;

    // INYECCIÓN DE DEPENDENCIAS DEL CODIFICADOR DE CONTRASEÑA
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // CONSTRUCTOR DEL SERVICIO CON DEPENDENCIAS
    public UsuarioServiceImpl(UsuarioRepository usuarioRepositorio, RolRepository rolRepositorio) {
        super();
        this.usuarioRepositorio = usuarioRepositorio;
        this.rolRepositorio = rolRepositorio;
    }

    /**
     * Obtiene todos los usuarios.
     *
     * @return Lista de usuarios.
     */
    public List<Usuario> getAllUsuarios() {
        return usuarioRepositorio.findAll();
    }

    /**
     * Guarda un nuevo usuario.
     *
     * @param usuarioRegistroDTO DTO con los datos del usuario a registrar.
     * @return Usuario guardado.
     * @throws UsuarioExistenteException Si el nombre de usuario ya existe.
     * @throws Exception                 Si ocurre un error al guardar el usuario.
     */
    public Usuario guardar(UsuarioRegistroDTO usuarioRegistroDTO) throws UsuarioExistenteException, Exception {
        if (usuarioRepositorio.findByUsername(usuarioRegistroDTO.getUsername()) != null) {
            throw new UsuarioExistenteException("El nombre de usuario ya existe");
        }
        Rol userRole = rolRepositorio.findByName("ROLE_USER");
        if (userRole == null) {
            throw new RuntimeException("El rol ROLE_USER no se encuentra en la base de datos");
        }
        Usuario usuario = new Usuario(usuarioRegistroDTO.getUsername(), usuarioRegistroDTO.getEmail(),
                passwordEncoder.encode(usuarioRegistroDTO.getPassword()),
                Arrays.asList(userRole));
        
        // Crear un carrito vacío y asociarlo al usuario
        Carrito carrito = new Carrito(usuario);
        usuario.setCarrito(carrito);

        try {
            Usuario savedUser = usuarioRepositorio.save(usuario);
            System.out.println("Usuario guardado: " + savedUser);
            return savedUser;
        } catch (Exception e) {
            System.out.println("Error al guardar el usuario: " + e.getMessage());
            throw new Exception("Error al guardar el usuario", e);
        }
    }

    /**
     * Carga un usuario por nombre de usuario.
     *
     * @param username Nombre de usuario.
     * @return Detalles del usuario cargado.
     * @throws UsernameNotFoundException Si no se encuentra el usuario.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario o password inválidos");
        }

        if (!usuario.getUsu_estado().equalsIgnoreCase("ACTIVO")) {
            throw new UsernameNotFoundException("La cuenta está inactiva. Por favor, contacta al administrador.");
        }
        return new User(usuario.getUsername(), usuario.getUsu_password(), mapearAutoridadesRoles(usuario.getRoles()));
    }

    /**
     * Mapea las autoridades a partir de los roles.
     *
     * @param roles Roles asignados al usuario.
     * @return Colección de autoridades.
     */
    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRol_nombre()))
                .collect(Collectors.toList());
    }

    /**
     * Lista todos los usuarios.
     *
     * @return Lista de usuarios.
     */
    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepositorio.findAll();
    }

    /**
     * Encuentra un usuario por nombre de usuario.
     * 
     * @param username Nombre de usuario.
     * @return Usuario encontrado.
     */
    @Override
    public Usuario findByUsername(String username) {
        return usuarioRepositorio.findByUsername(username);
    }

    /**
     * Encuentra un usuario por su ID.
     * 
     * @param id ID del usuario a buscar.
     * @return Usuario encontrado.
     */
    public Usuario findById(Long id) {
        return usuarioRepositorio.findById(id).orElse(null);
    }

    /**
     * Encuentra todos los usuarios con un rol específico.
     * 
     * @param role Rol para buscar usuarios.
     * @return Lista de usuarios con el rol especificado.
     */
    @Override
    public List<Usuario> findAllByRole(String role) {
        return usuarioRepositorio.findAllByRole(role);
    }

    /**
     * Obtiene un usuario por su ID.
     * 
     * @param userId ID del usuario a obtener.
     * @return Usuario encontrado.
     * @throws NoSuchElementException Si no se encuentra ningún usuario con el ID
     *                                especificado.
     */
    @Override
    public Usuario getUserById(Long userId) {
        Optional<Usuario> optionalUsuario = usuarioRepositorio.findById(userId);
        if (optionalUsuario.isPresent()) {
            return optionalUsuario.get();
        } else {
            throw new NoSuchElementException("No se encontró ningún usuario con el ID " + userId);
        }
    }
}