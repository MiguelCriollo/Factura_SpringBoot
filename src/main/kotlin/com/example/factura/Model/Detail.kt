package com.example.factura.Model

import jakarta.persistence.*

@Entity
@Table(name = "detail")
class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null;
    var quantity: Int? = null;
    var price: Double? = null;
    @JoinColumn(name = "invoice_id")
    var invoice_id: Long? = null;
    @JoinColumn(name = "product_id")
    var product_id: Long? = null;
}