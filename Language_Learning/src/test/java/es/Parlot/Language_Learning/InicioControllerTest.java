package es.Parlot.Language_Learning;
import es.Parlot.Language_Learning.controladores.InicioController;
import es.Parlot.Language_Learning.modelo.Usuario;
import es.Parlot.Language_Learning.modelo.enums.rol;
import es.Parlot.Language_Learning.servicios.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InicioControllerTest {

    @InjectMocks
    InicioController inicioController;

    @Mock
    UsuarioService usuarioService;

    @Mock
    Model model;

    @Mock
    Principal principal;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void isAuthenticatedAdmin_withAdminRole_returnsTrue() {
        Usuario usuario = new Usuario();
        usuario.setRole(rol.ROLE_ADMIN);
        when(principal.getName()).thenReturn("admin");
        when(usuarioService.getByUsername("admin")).thenReturn(usuario);

        assertTrue(inicioController.isAuthenticatedAdmin(principal));
    }

    @Test
    public void isAuthenticatedAdmin_withNonAdminRole_returnsFalse() {
        Usuario usuario = new Usuario();
        usuario.setRole(rol.ROLE_STUDENT);
        when(principal.getName()).thenReturn("student");
        when(usuarioService.getByUsername("student")).thenReturn(usuario);

        assertFalse(inicioController.isAuthenticatedAdmin(principal));
    }

    @Test
    public void isAuthenticatedAdmin_withNoPrincipal_returnsFalse() {
        assertFalse(inicioController.isAuthenticatedAdmin(null));
    }

    @Test
    public void isAuthenticatedStudent_withStudentRole_returnsTrue() {
        Usuario usuario = new Usuario();
        usuario.setRole(rol.ROLE_STUDENT);
        when(principal.getName()).thenReturn("student");
        when(usuarioService.getByUsername("student")).thenReturn(usuario);

        assertTrue(inicioController.isAuthenticatedStudent(principal));
    }

    @Test
    public void isAuthenticatedStudent_withNonStudentRole_returnsFalse() {
        Usuario usuario = new Usuario();
        usuario.setRole(rol.ROLE_ADMIN);
        when(principal.getName()).thenReturn("admin");
        when(usuarioService.getByUsername("admin")).thenReturn(usuario);

        assertFalse(inicioController.isAuthenticatedStudent(principal));
    }

    @Test
    public void isAuthenticatedStudent_withNoPrincipal_returnsTrue() {
        assertTrue(inicioController.isAuthenticatedStudent(null));
    }

    @Test
    public void isAuthenticated_withPrincipal_returnsTrue() {
        assertTrue(inicioController.isAuthenticated(principal));
    }

    @Test
    public void isAuthenticated_withNoPrincipal_returnsFalse() {
        assertFalse(inicioController.isAuthenticated(null));
    }
}