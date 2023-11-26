package com.example.factura.Service

import com.example.factura.Model.Client
import com.example.factura.Repository.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ClientService {
    @Autowired
    lateinit var clientRepository: ClientRepository

    fun list ():List<Client>{
        return clientRepository.findAll()
    }
    fun save(modelo: Client): Client{
        try{
            return clientRepository.save(modelo)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun update(modelo: Client): Client{
        try {

            clientRepository.findById(modelo.id)
                ?: throw Exception("ID no existe")

            return clientRepository.save(modelo)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun updateName(modelo:Client): Client{
        try{
            val response = clientRepository.findById(modelo.id)
                ?: throw Exception("ID no existe")
            response.apply {
                fullname=modelo.fullname
            }
            return clientRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun listById (id:Long?):Client?{
        return clientRepository.findById(id)
    }

    fun delete (id: Long?):String?{
        try{
            val response = clientRepository.findById(id)
                ?: throw Exception("ID no existe")
            clientRepository.deleteById(id!!)
            return "ID eliminado Correctamente!!!"
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}
