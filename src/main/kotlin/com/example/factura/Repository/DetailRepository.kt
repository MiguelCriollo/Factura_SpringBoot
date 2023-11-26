package com.example.factura.Repository

import com.example.factura.Model.Detail
import org.springframework.data.jpa.repository.JpaRepository

interface DetailRepository  : JpaRepository<Detail, Long?> {
    fun findById (id: Long?): Detail?
}