package com.sustech.ooad.repository;

import com.sustech.ooad.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {


    List<Client> findByEmail(String address);
}
