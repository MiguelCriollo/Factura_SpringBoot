package com.example.factura.Service

import com.example.factura.Model.Client
import com.example.factura.Model.Invoice
import com.example.factura.Repository.ClientRepository
import com.example.factura.Repository.InvoiceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class InvoiceService {
    @Autowired
    lateinit var clientRepository: ClientRepository
    @Autowired
    lateinit var invoiceRepository: InvoiceRepository

    fun listTotal(value:Long):List<Invoice>{
        return invoiceRepository.filterTotal(value)
    }
    fun clientsHighestTotal(value:Long):List<*>{
        return invoiceRepository.clientsHighestTotal(value)
    }
    fun list ():List<Invoice>{
        return invoiceRepository.findAll()
    }
    fun save(modelo: Invoice): Invoice{
        clientRepository.findById(modelo.clientId)
            ?:throw Exception("Id del cliente no existe")
        modelo.createdAt?:throw Exception("La fecha de creacion no debe ser incluida, o en caso de necesitarlo debe ser de formato 'year-month-day'")
        try{
            return invoiceRepository.save(modelo)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun update(modelo: Invoice): Invoice{
        try {

            invoiceRepository.findById(modelo.id)
                ?: throw Exception("ID no existe")

            return invoiceRepository.save(modelo)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun updateName(modelo:Invoice): Invoice{
        try{
            val response = invoiceRepository.findById(modelo.id)
                ?: throw Exception("ID no existe")
            response.apply {
                code=modelo.code
            }
            return invoiceRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun listById (id:Long?):Invoice?{
        return invoiceRepository.findById(id)
    }

    fun delete (id: Long?):String?{
        try{
            val response = invoiceRepository.findById(id)
                ?: throw Exception("ID no existe")
            invoiceRepository.deleteById(id!!)
            return "ID eliminado Correctamente!!!"
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}
