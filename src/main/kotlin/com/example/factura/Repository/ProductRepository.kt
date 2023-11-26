package com.example.factura.Repository

import com.example.factura.Model.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository  : JpaRepository<Product, Long?> {
    fun findById (id: Long?): Product?
}