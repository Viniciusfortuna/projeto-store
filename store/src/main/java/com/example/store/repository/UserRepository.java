package com.example.store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.store.entity.User;

import jakarta.transaction.Transactional;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByNome(String nome);
	
	@Transactional  /*Caso de algum erro ele não deleta o email*/
	void deleteByEmail(String email);
}
