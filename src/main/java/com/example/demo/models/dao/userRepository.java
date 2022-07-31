package com.example.demo.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.entity.Usuario;

@Repository
public interface userRepository extends JpaRepository<Usuario, Long> {
	

}
