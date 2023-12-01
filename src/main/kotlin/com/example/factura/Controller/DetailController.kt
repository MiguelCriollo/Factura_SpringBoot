package com.example.factura.Controller

import com.example.factura.Model.Client
import com.example.factura.Model.Detail
import com.example.factura.Service.DetailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/detail")
class DetailController {
    @Autowired
    lateinit var detailService: DetailService
    //---------------Queries------------

    @GetMapping("/bestSeller/{value}")
    fun listBestSeller (@PathVariable value:Long): ResponseEntity<*> {
        return ResponseEntity(detailService.bestProductSeller(value),HttpStatus.OK)
    }

    //--------------------
    @GetMapping
    fun list (): ResponseEntity<*> {
        var celularResponse=detailService.list()
        return ResponseEntity.ok(mapOf("Felony" to "Sex Offender","status" to "success", "data" to celularResponse))
    }
    @PostMapping
    fun save (@RequestBody modelo: Detail): ResponseEntity<Detail> {
        return ResponseEntity(detailService.save(modelo), HttpStatus.OK)
    }
    @PutMapping
    fun update (@RequestBody modelo:Detail): ResponseEntity<Detail> {
        return ResponseEntity(detailService.update(modelo), HttpStatus.OK)
    }
    @PatchMapping
    fun updateName (@RequestBody modelo:Detail): ResponseEntity<Detail> {
        return ResponseEntity(detailService.updateName(modelo), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long): ResponseEntity<*> {
        println("Entered Get BY ID")
        return ResponseEntity(detailService.listById (id), HttpStatus.OK)

    }
    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):String?{
        println("Entered Delete By ID")
        return detailService.delete(id)
    }
}