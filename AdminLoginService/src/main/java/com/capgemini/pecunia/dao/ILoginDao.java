package com.capgemini.pecunia.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.pecunia.entity.User;

@Repository
public interface ILoginDao extends JpaRepository<User, String>{

	
}
