package com.example.factura.Controller

import com.example.factura.Model.Client
import com.example.factura.Model.Product
import com.example.factura.Service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/product")
class ProductController {
    @Autowired
    lateinit var productService: ProductService
    @GetMapping()
    fun listAll(modelo: Product):ResponseEntity<*>{
        return ResponseEntity(productService.getAll(modelo),HttpStatus.OK)
    }
    @GetMapping("/pageable")
    fun list (model:Product, pageable: Pageable):ResponseEntity<*>{
        val response= productService.list(pageable,model)
        return ResponseEntity(response, HttpStatus.OK)
    }
    @GetMapping("/listDto")
    fun listDt ():ResponseEntity<*>{
        return ResponseEntity(productService.listDt(), HttpStatus.OK)
    }
    @PostMapping
    fun save (@RequestBody modelo: Product): ResponseEntity<Product> {
        return ResponseEntity(productService.save(modelo), HttpStatus.OK)
    }
    @PutMapping
    fun update (@RequestBody modelo:Product): ResponseEntity<Product> {
        return ResponseEntity(productService.update(modelo), HttpStatus.OK)
    }
    /*
    @PatchMapping
    fun updateName (@RequestBody modelo:Product): ResponseEntity<Product> {
        return ResponseEntity(productService.updateStock(modelo), HttpStatus.OK)
    }
    */


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