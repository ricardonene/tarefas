package br.com.ricardonene.listatarefas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ricardonene.listatarefas.domain.Usuario;
import br.com.ricardonene.listatarefas.repositories.UsuarioRepository;
import br.com.ricardonene.listatarefas.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Usuario usuario = repo.findByEmail(email);
		
		if (usuario == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return new UserSS(usuario);
	}

}
