package DAWII_T1_MOSCOLGONZALESJULIOALEXANDER.MOSCOLGONZALESJULIOALEXANDER.controller;

import DAWII_T1_MOSCOLGONZALESJULIOALEXANDER.MOSCOLGONZALESJULIOALEXANDER.model.bd.Usuario;
import DAWII_T1_MOSCOLGONZALESJULIOALEXANDER.MOSCOLGONZALESJULIOALEXANDER.service.DetalleUsuarioService;
import DAWII_T1_MOSCOLGONZALESJULIOALEXANDER.MOSCOLGONZALESJULIOALEXANDER.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/auth")
public class LoginController {

    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }
    @GetMapping("/registrar")
    public String registro(){
        return "auth/registro";
    }
    @PostMapping("/guardarusuario")
    public String guardarUsuario(@ModelAttribute Usuario usuario){
        usuarioService.guardarUsuario(usuario);
        return "auth/login";
    }

    @GetMapping("/dashboard")
    public String dashboard(){
        return "auth/home";
    }

    @GetMapping("/login-success")
    public String loginsuccess(){
        return "redirect:/auth/dashboard";
    }

    @GetMapping("/cambiarcontrasena")
    public String cambiarcontrasena(){
        return "auth/cambiarcontrasena";
    }

    @PostMapping("/contrasenacambiada")
    public String contrasenacambiada(Usuario usuario){
        usuarioService.buscarUsuarioXNom(usuario.getNomusuario());
        usuarioService.actualizarContrasena(usuario);
        return "redirect:/auth/dashboard";
    }

    @GetMapping("/cerrar-sesion")
    public String cerrarSesion(HttpSession session) {
        // Invalidar la sesión
        session.invalidate();
        // Redirigir a la página de inicio de sesión
        return "redirect:/auth/login";
    }
}
