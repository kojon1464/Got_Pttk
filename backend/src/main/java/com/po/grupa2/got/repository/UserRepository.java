package com.po.grupa2.got.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.po.grupa2.got.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmailIgnoreCase(String email);
}
