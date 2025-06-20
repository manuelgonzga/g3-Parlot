package es.Parlot.Language_Learning.controladores;

import es.Parlot.Language_Learning.modelo.MetodoDePago;
import es.Parlot.Language_Learning.modelo.Profesor;
import es.Parlot.Language_Learning.modelo.Usuario;
import es.Parlot.Language_Learning.servicios.MetodoPagoService;
import es.Parlot.Language_Learning.servicios.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.HttpServletBean;

import java.security.Principal;
import java.util.Set;

@Controller
public class MetodoPagoController {

    @Autowired
    MetodoPagoService metodoPagoService;

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/SaveMetodoPago/{userId}")
    public String saveMetodoPago(MetodoDePago metodoDePago, HttpServletRequest request, Model model, @PathVariable Integer userId, Principal principal){
        metodoDePago.setUser(usuarioService.getByUsername(principal.getName()));
        metodoPagoService.save(metodoDePago);
        String referer = request.getHeader("Referer");
        if(referer.contains("/addPayMethodFromClase")){
            return "redirect:/confirmar-clase/profesor/"+userId;
        }else{
            return "Monedero";
        }
    }

    @ModelAttribute("MetodosPago")
    public Set<MetodoDePago> MetodosPago(Principal principal){
        Usuario user = usuarioService.getByUsername(principal.getName());
        return user.getMetodoDePagos();
    }

    @ModelAttribute("usuario")
    public Usuario usuario(Principal principal){
        return usuarioService.getByUsername(principal.getName());
    }





}
