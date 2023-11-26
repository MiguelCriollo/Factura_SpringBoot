package com.example.factura.Repository

import com.example.factura.Model.Invoice
import org.springframework.data.jpa.repository.JpaRepository

interface InvoiceRepository  : JpaRepository<Invoice, Long?> {
    fun findById (id: Long?): Invoice?
}