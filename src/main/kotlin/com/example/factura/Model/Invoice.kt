package com.example.factura.Model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "invoice")
class Invoice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null;
    var code: String? = null;
    @Column(name="create_at")
    var createdAt: LocalDate? = null;
    var total: Int? = null;
    var client_id: Long? = null;
}