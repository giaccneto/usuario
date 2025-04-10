package com.giaccneto.usuario.business;

import com.giaccneto.usuario.business.converter.UsuarioConverter;
import com.giaccneto.usuario.business.dto.UsuarioDTO;
import com.giaccneto.usuario.infrastructure.entity.Usuario;
import com.giaccneto.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }
}
