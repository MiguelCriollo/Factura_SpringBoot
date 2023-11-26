package com.example.factura.Model

import jakarta.persistence.*

@Entity
@Table(name = "client")
class Client {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null;
    var nui: String? = null;
    var fullname: String? = null;
    var address: String? = null;
}