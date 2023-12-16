package com.example.factura.Model

import jakarta.persistence.*

@Entity
@Table(name = "detail")
class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null;
    var quantity: Int = 0;
    var price: Double = 0.0;
    @JoinColumn(name = "invoice_id")
    var invoiceId: Long? = null;
    @JoinColumn(name = "product_id")
    var productId: Long? = null;
}