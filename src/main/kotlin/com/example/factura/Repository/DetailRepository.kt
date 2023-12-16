package com.example.factura.Repository

import com.example.factura.Model.Detail
import com.example.factura.Model.Invoice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface DetailRepository  : JpaRepository<Detail, Long?> {
    fun findById (id: Long?): Detail?
    @Query(nativeQuery = true)
    fun bestProductSeller(value:Long): List<*>
    @Query(nativeQuery = true)
    fun getDetailsFromInvoice(value:Long?): List<Detail>
}