package com.example.factura.Repository

import com.example.factura.Model.Client
import org.springframework.data.jpa.repository.JpaRepository

interface ClientRepository  : JpaRepository<Client, Long?> {
    fun findById (id: Long?): Client?
}