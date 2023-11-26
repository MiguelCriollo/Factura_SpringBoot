package com.example.factura.Controller

import com.example.factura.Model.Client
import com.example.factura.Model.Product
import com.example.factura.Service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/product")
class ProductController {
    @Autowired
    lateinit var productService: ProductService

    @GetMapping
    fun list (): ResponseEntity<*> {
        var celularResponse=productService.list()
        return ResponseEntity.ok(mapOf("Felony" to "Sex Offender","status" to "success", "data" to celularResponse))
    }
    @PostMapping
    fun save (@RequestBody modelo: Product): ResponseEntity<Product> {
        return ResponseEntity(productService.save(modelo), HttpStatus.OK)
    }
    @PutMapping
    fun update (@RequestBody modelo:Product): ResponseEntity<Product> {
        return ResponseEntity(productService.update(modelo), HttpStatus.OK)
    }
    @PatchMapping
    fun updateName (@RequestBody modelo:Product): ResponseEntity<Product> {
        return ResponseEntity(productService.updateName(modelo), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long): ResponseEntity<*> {
        println("Entered Get BY ID")
        return ResponseEntity(productService.listById (id), HttpStatus.OK)

    }
    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):String?{
        println("Entered Delete By ID")
        return productService.delete(id)
    }
}