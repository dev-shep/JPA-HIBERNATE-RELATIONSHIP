package com.ksolis.springboot.jpa.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ksolis.springboot.jpa.demo.entities.Invoice;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author Limon
 */
public interface InvoiceRepository extends  CrudRepository<Invoice, Long>{
    
}
