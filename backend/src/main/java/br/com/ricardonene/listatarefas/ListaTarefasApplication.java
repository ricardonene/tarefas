package br.com.ricardonene.listatarefas;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.ricardonene.listatarefas.domain.Tarefa;
import br.com.ricardonene.listatarefas.domain.Usuario;
import br.com.ricardonene.listatarefas.domain.enums.Perfil;
import br.com.ricardonene.listatarefas.repositories.TarefaRepository;
import br.com.ricardonene.listatarefas.repositories.UsuarioRepository;

@SpringBootApplication
public class ListaTarefasApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ListaTarefasApplication.class, args);
	}
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private TarefaRepository tarefaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public void run(String... args) throws Exception {

		Tarefa t1 = new Tarefa(1, "Fazer chamada", false);
		Tarefa t2 = new Tarefa(2, "Cozinhar", false);
		Tarefa t3 = new Tarefa(3, "Comprar Café", false);
		Tarefa t4 = new Tarefa(4, "Comprar CD", false);
		tarefaRepository.saveAll(Arrays.asList(t1, t2, t3, t4));
		
		Usuario u1 = new Usuario(null, "José", "jose@gmail.com", passwordEncoder.encode("123"));
		u1.addPerfil(Perfil.ADMIN);

		Usuario u2 = new Usuario(null, "Maria", "maria@gmail.com", passwordEncoder.encode("123"));

		usuarioRepository.saveAll(Arrays.asList(u1,u2));
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
