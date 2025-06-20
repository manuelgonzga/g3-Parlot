package es.Parlot.Language_Learning.seguridad;

import es.Parlot.Language_Learning.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfiguration {

    @Autowired
    private UsuarioService usuarioServicio;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(usuarioServicio);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/h2-console/**").permitAll() // H2 console first
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/js/**", "/css/**", "/img/**", "/videoUploads/**", "/imagenFotoPerfilUpload/**").permitAll()
                        .requestMatchers("/consulta","/profesor/{id}","/registrar-profesor","/find-teacher","/registrar-usuario","/", "/logout", "/sign-up-teacher","/sign-up","/Inicio", "error", "/EncontrarProfesores", "login", "/h2-console/**").permitAll() // Specific endpoints
                        .anyRequest().authenticated())
                .formLogin((form) -> form
                        .loginPage("/login"))
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/")
                );
        return http.build();
    }
}