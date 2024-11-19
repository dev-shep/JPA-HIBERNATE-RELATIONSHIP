/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.ksolis.springboot.jpa.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import com.ksolis.springboot.jpa.demo.entities.ClientDetails;

/**
 *
 * @author Limon
 */
public interface  ClientsDetailsRepository extends CrudRepository<ClientDetails, Long> {

}
