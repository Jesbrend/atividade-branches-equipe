package app.core.repositories;

import java.util.HashMap;
import java.util.Map;
import app.core.entities.Usuario;

public class UsuarioRepository {
    private static UsuarioRepository instancia;
    private Map<String, Usuario> usuarios;
    
    private UsuarioRepository() {
        this.usuarios = new HashMap<>();
    }
    
    public static UsuarioRepository getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioRepository();
        }
        return instancia;
    }
    
    public Usuario adicionarUsuario(Usuario usuario) {
        this.usuarios.put(usuario.getEmail(), usuario);
        return this.usuarios.get(usuario.getEmail());
    }

    public boolean contains(String email) {
        return this.usuarios.containsKey(email);
    }

    public Usuario getUsuario(String email){
        return this.usuarios.get(email);
    }
}