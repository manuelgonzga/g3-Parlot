package es.Parlot.Language_Learning.servicios;

import es.Parlot.Language_Learning.modelo.Alumno;
import es.Parlot.Language_Learning.modelo.Profesor;
import es.Parlot.Language_Learning.modelo.Usuario;
import es.Parlot.Language_Learning.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public void save(Usuario user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        usuarioRepository.saveAndFlush(user);
    }

    @Autowired
    ClaseService claseService;

    public void delete(Integer id) {
        Usuario user = usuarioRepository.findById(id).orElse(null);
        try{
            String fotoPath = user.getFotoPerfilPath().substring(26);
            Path rootPath = Paths.get("Language_Learning/src/main/resources/static/imagenFotoPerfilUpload").resolve(fotoPath).toAbsolutePath();
            Files.delete(rootPath);
        }catch(Exception e){
            e.printStackTrace();
        }
        if (user != null && user instanceof Profesor) {
            Profesor profesor = (Profesor) user;
            try{
                String videoPath = profesor.getVideoPath().substring(16);
                Path rootPath = Paths.get("Language_Learning/src/main/resources/static/videoUploads").resolve(videoPath).toAbsolutePath();
                Files.delete(rootPath);
            }catch(Exception e){
                e.printStackTrace();
            }
            profesor.getClases().forEach(clase -> claseService.delete(clase.getId()));
        } else if (user != null && user instanceof Alumno) {
            Alumno alumno = (Alumno) user;
            alumno.getClases().forEach(clase -> claseService.delete(clase.getId()));
        }
        usuarioRepository.deleteById(id);
    }

    public Usuario getById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public void update(Usuario user) {
        Usuario existingUser = usuarioRepository.getOne(user.getId());
        existingUser.setUsername(user.getUsername());
        existingUser.setName(user.getName());
        existingUser.setSurname(user.getSurname());
        existingUser.setMail(user.getMail());
        if(user.getDateOfBirth()!=null){
            existingUser.setDateOfBirth(user.getDateOfBirth());
        }
        if(user.getPais()!=null){
            existingUser.setPais(user.getPais());
        }
        if(user.getFotoPerfilPath()!=null){
            existingUser.setFotoPerfilPath(user.getFotoPerfilPath());
        }
        existingUser.setTelefono(user.getTelefono());
        if(user.getIdiomas()!=null){
            existingUser.setIdiomas(user.getIdiomas());
        }
        if(user.getSaldo()!=null){
            existingUser.setSaldo(user.getSaldo());
        }else{
            existingUser.setSaldo(0.0);
        }

        usuarioRepository.saveAndFlush(existingUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario o password inválidos");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(usuario.getRole().toString())); // Assign default role to user
        return new User(usuario.getUsername(), usuario.getPassword(), authorities);
    }

    public Usuario getByUsername(String username){
        return usuarioRepository.findByUsername(username);
    }

    public String getFotoPathByUsuarioId(Integer usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        return usuario != null ? usuario.getFotoPerfilPath() : null;
    }

    public void introducirSaldo(Usuario usuario, Double saldo){
        usuarioRepository.saveAndFlush(usuario);
    }


}