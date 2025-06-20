package es.Parlot.Language_Learning;

import es.Parlot.Language_Learning.modelo.Usuario;
import es.Parlot.Language_Learning.repositorios.UsuarioRepository;
import es.Parlot.Language_Learning.servicios.ClaseService;
import es.Parlot.Language_Learning.servicios.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    @InjectMocks
    UsuarioService usuarioService;

    @Mock
    UsuarioRepository usuarioRepository;

    @Mock
    BCryptPasswordEncoder passwordEncoder;

    @Mock
    ClaseService claseService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void save_withValidUser_savesUser() {
        Usuario user = new Usuario();
        user.setPassword("password");

        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");

        usuarioService.save(user);

        verify(usuarioRepository, times(1)).saveAndFlush(user);
    }

    @Test
    public void delete_withValidId_deletesUser() {
        Integer id = 1;
        Usuario user = new Usuario();
        user.setId(id);

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(user));

        usuarioService.delete(id);

        verify(usuarioRepository, times(1)).deleteById(id);
    }

    @Test
    public void update_withValidUser_updatesUser() {
        Usuario user = new Usuario();
        user.setId(1);
        user.setUsername("username");
        user.setMail("mail");

        when(usuarioRepository.getOne(user.getId())).thenReturn(user);

        usuarioService.update(user);

        verify(usuarioRepository, times(1)).saveAndFlush(user);
    }
}