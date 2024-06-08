package DAWII_T1_MOSCOLGONZALESJULIOALEXANDER.MOSCOLGONZALESJULIOALEXANDER.controller;

import DAWII_T1_MOSCOLGONZALESJULIOALEXANDER.MOSCOLGONZALESJULIOALEXANDER.model.bd.Usuario;
import DAWII_T1_MOSCOLGONZALESJULIOALEXANDER.MOSCOLGONZALESJULIOALEXANDER.service.DetalleUsuarioService;
import DAWII_T1_MOSCOLGONZALESJULIOALEXANDER.MOSCOLGONZALESJULIOALEXANDER.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@AllArgsConstructor
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
    public String dashboard(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "auth/home";
    }

    @GetMapping("/login-success")
    public String loginsuccess(){
        return "redirect:/auth/dashboard";
    }

    @GetMapping("/cambiarcontrasena")
    public String cambiarcontrasena(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "auth/cambiarcontrasena";
    }

    @PostMapping("/contrasenacambiada")
    public String contrasenacambiada(@RequestParam String username, @RequestParam String password, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        username = authentication.getName();
        model.addAttribute("username", username);
        Usuario usuario = usuarioService.buscarUsuarioXNom(username);
        if (usuario != null) {
            usuario.setPassword(password);
            usuarioService.actualizarContrasena(usuario);
            return "redirect:/auth/dashboard";
        } else {
            return "redirect:/auth/cambiarcontrasena?error";
        }
    }

    @GetMapping("/cerrar-sesion")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }
}
