package com.employee.management.repository;

import com.employee.management.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Integer> {

    public Optional<MyUser> findByUsername(String userName);
}
