package DAWII_T1_MOSCOLGONZALESJULIOALEXANDER.MOSCOLGONZALESJULIOALEXANDER.service;

import DAWII_T1_MOSCOLGONZALESJULIOALEXANDER.MOSCOLGONZALESJULIOALEXANDER.model.bd.Usuario;
import DAWII_T1_MOSCOLGONZALESJULIOALEXANDER.MOSCOLGONZALESJULIOALEXANDER.model.dto.ResultadoDto;

public interface IUsuarioService {

    Usuario buscarUsuarioXNom(String nomusuario);
    Usuario guardarUsuario(Usuario usuario);
    void actualizarContrasena(Usuario usuario);
    Usuario buscarUsuarioXId(Integer idusuario);
}
