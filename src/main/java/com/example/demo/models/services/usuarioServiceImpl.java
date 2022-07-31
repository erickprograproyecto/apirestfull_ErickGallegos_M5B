package com.example.demo.models.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.dao.userRepository;
import com.example.demo.models.entity.Usuario;

@Service
public class usuarioServiceImpl implements usuarioService  {
	
	@Autowired
	private userRepository userrepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Usuario> findAll() {
		
		return userrepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> findAll(Pageable pageable) {
		return userrepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> findById(Long id) {
		return userrepository.findById(id);
	}

	@Override
	@Transactional
	public Usuario save(Usuario user) {
		return userrepository.save(user);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		userrepository.deleteById(id);
	}

}
