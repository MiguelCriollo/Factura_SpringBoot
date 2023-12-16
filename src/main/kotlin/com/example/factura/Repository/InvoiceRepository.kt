package com.example.factura.Repository

import com.example.factura.Model.Detail
import com.example.factura.Model.Invoice
import jakarta.persistence.NamedNativeQuery
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface InvoiceRepository  : JpaRepository<Invoice, Long?> {
    fun findById (id: Long?): Invoice?
    @Query(nativeQuery = true)
        fun filterTotal(value:Long): List<Invoice>
    @Query(nativeQuery = true)
        fun clientsHighestTotal(value: Long): List<*>

}