/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.ksolis.springboot.jpa.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author Limon
 */
@Entity
@Table(name="clients_details")
public class ClientDetails {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    private boolean premium;
    private Integer point;
    
 

    public ClientDetails(Integer point, boolean premium) {
        this.point = point;
        this.premium = premium;
    }

    public ClientDetails() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

   


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ClientDetails{");
        sb.append("id=").append(id);
        sb.append(", premium=").append(premium);
        sb.append(", point=").append(point);
        sb.append('}');
        return sb.toString();
    }



}
