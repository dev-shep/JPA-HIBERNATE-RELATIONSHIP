package com.ksolis.springboot.jpa.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ksolis.springboot.jpa.demo.entities.Client;

public interface ClientRepository extends  CrudRepository<Client, Long> {
    @Query("select c from Client c left join fetch c.addresses where c.id=?1")
    Optional<Client> findOne(Long id);

    @Query("select c from Client c left join fetch c.invoices where c.id=?1")
    Optional<Client> findOneWithInvoices(Long id);

    @Query("select c from Client c left join fetch c.invoices left join fetch c.addresses where c.id=?1")
    Optional<Client> findOneW(Long id);
}
