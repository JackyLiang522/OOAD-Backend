package com.sustech.ooad.repository;

import com.sustech.ooad.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("select p from Client p where p.email=?1")
    List<Client> findByEmail(String address);
}
