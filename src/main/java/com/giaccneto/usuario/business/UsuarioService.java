package com.giaccneto.usuario.business;

import com.giaccneto.usuario.business.converter.UsuarioConverter;
import com.giaccneto.usuario.business.dto.EnderecoDTO;
import com.giaccneto.usuario.business.dto.TelefoneDTO;
import com.giaccneto.usuario.business.dto.UsuarioDTO;
import com.giaccneto.usuario.infrastructure.entity.Endereco;
import com.giaccneto.usuario.infrastructure.entity.Telefone;
import com.giaccneto.usuario.infrastructure.entity.Usuario;
import com.giaccneto.usuario.infrastructure.exceptions.ConflictException;
import com.giaccneto.usuario.infrastructure.exceptions.ResourseNotFoundException;
import com.giaccneto.usuario.infrastructure.repository.EnderecoRepository;
import com.giaccneto.usuario.infrastructure.repository.TelefoneRepository;
import com.giaccneto.usuario.infrastructure.repository.UsuarioRepository;
import com.giaccneto.usuario.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final EnderecoRepository enderecoRepository;
    public final TelefoneRepository telefoneRepository;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }
    public void emailExiste(String email){
        try{
            boolean existe = verificaEmailExistente(email);
            if (existe){
                throw  new ConflictException("Email ja cadastrado " + email);
            }

        }catch (ConflictException e){
            throw new ConflictException("Email ja cadastrado " + e.getCause());
        }
    }
    public boolean verificaEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public UsuarioDTO buscarUsuarioPorEmail(String email) {
        try {
            return usuarioConverter.paraUsuarioDTO(usuarioRepository.findByEmail(email).orElseThrow(
                    () -> new ResourseNotFoundException("Email não encontrado" + email)));
        } catch (ResourseNotFoundException e) {
            throw new ResourseNotFoundException("Email não encontrado " + email);
        }
    }

    public void deletaUsuarioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }

    public UsuarioDTO atualizaDadosUsuario(String token, UsuarioDTO dto){
        String email = jwtUtil.extrairEmailToken(token.substring(7));

        dto.setSenha(dto.getSenha()!= null ? passwordEncoder.encode(dto.getSenha()) : null);


        Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(() ->
                new ResourseNotFoundException("Email não encontrado"));

        Usuario usuario = usuarioConverter.updateUsuario(dto, usuarioEntity);


         return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }

    public EnderecoDTO atualizaEndereco(Long idEndereco, EnderecoDTO enderecoDTO){
        Endereco entity = enderecoRepository.findById(idEndereco).orElseThrow(()-> new ResourseNotFoundException("Id não encontrado! " + idEndereco));

        Endereco endereco = usuarioConverter.updateEndereco(enderecoDTO, entity);

        return usuarioConverter.paraEnderecoDTO(enderecoRepository.save(endereco));
    }

    public TelefoneDTO atualizaTelefone(Long idTelefone, TelefoneDTO dto){
        Telefone entity = telefoneRepository.findById(idTelefone).orElseThrow(()-> new ResourseNotFoundException("Id não encontrado " + idTelefone));

        Telefone telefone = usuarioConverter.updateTelefone(dto, entity);

        return usuarioConverter.paraTelefoneDTO(telefoneRepository.save(telefone));

    }

    public EnderecoDTO cadastraEndereco(String token, EnderecoDTO dto){
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(()-> new ResourseNotFoundException("Email não econtrado " + email));

        Endereco endereco = usuarioConverter.paraEnderecoEntity(dto, usuario.getId());
        Endereco  enderecoEntity = enderecoRepository.save(endereco);
        return usuarioConverter.paraEnderecoDTO(enderecoEntity);
    }

    public TelefoneDTO cadastraTelefone(String token, TelefoneDTO dto){
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(()-> new ResourseNotFoundException("Email não econtrado " + email));

        Telefone telefone = usuarioConverter.paraTelefoneEntity(dto, usuario.getId());
        return usuarioConverter.paraTelefoneDTO(telefoneRepository.save(telefone));

    }
}
