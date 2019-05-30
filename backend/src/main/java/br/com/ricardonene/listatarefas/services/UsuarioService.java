package br.com.ricardonene.listatarefas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ricardonene.listatarefas.domain.Usuario;
import br.com.ricardonene.listatarefas.domain.enums.Perfil;
import br.com.ricardonene.listatarefas.repositories.UsuarioRepository;
import br.com.ricardonene.listatarefas.security.UserSS;
import br.com.ricardonene.listatarefas.services.exceptions.AuthorizationException;

@Service
public class UsuarioService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioRepository repo;

	public Usuario findById(Integer id) {
		
		UserSS user = UserService.authenticated();
		
		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso Negado");
		}
		
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElse(null);
	}

	public List<Usuario> findAll() {
		return repo.findAll();
	}

	public Usuario insert(Usuario obj) {
		obj.setId(null);
		obj.setSenha(passwordEncoder.encode(obj.getSenha()));
		return repo.save(obj);
	}

	public Usuario update(Usuario obj) {
		return repo.save(obj);
	}

	public void delete(Integer id) {
		findById(id);
		repo.deleteById(id);
	}
}
