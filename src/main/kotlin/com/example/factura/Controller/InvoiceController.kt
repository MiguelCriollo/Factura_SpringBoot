package com.example.factura.Controller

import com.example.factura.Model.Client
import com.example.factura.Model.Invoice
import com.example.factura.Service.InvoiceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/invoice")
class InvoiceController {
    @Autowired
    lateinit var invoiceService: InvoiceService

    @GetMapping
    fun list (): ResponseEntity<*> {
        var celularResponse=invoiceService.list()
        return ResponseEntity.ok(mapOf("Felony" to "Sex Offender","status" to "success", "data" to celularResponse))
    }
    @GetMapping("/filter-total/{value}")
    fun listsTotals (@PathVariable value:Long): ResponseEntity<*> {
        return ResponseEntity(invoiceService.listTotal(value),HttpStatus.OK)
    }
    @GetMapping("/highestPriceClient/{value}")
    fun clientsHighestTotal (@PathVariable value:Long): ResponseEntity<*> {
        return ResponseEntity(invoiceService.clientsHighestTotal(value),HttpStatus.OK)
    }

    @PostMapping
    fun save (@RequestBody modelo: Invoice): ResponseEntity<Invoice> {
        println(modelo);
        return ResponseEntity(invoiceService.save(modelo), HttpStatus.OK)
    }
    @PutMapping
    fun update (@RequestBody modelo:Invoice): ResponseEntity<Invoice> {
        return ResponseEntity(invoiceService.update(modelo), HttpStatus.OK)
    }
    @PatchMapping
    fun updateName (@RequestBody modelo:Invoice): ResponseEntity<Invoice> {
        return ResponseEntity(invoiceService.updateName(modelo), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long): ResponseEntity<*> {
        println("Entered Get BY ID")
        return ResponseEntity(invoiceService.listById (id), HttpStatus.OK)

    }
    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):String?{
        println("Entered Delete By ID")
        return invoiceService.delete(id)
    }
}