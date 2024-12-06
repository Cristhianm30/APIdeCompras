package com.cristhian.apicompras.Service;

import com.cristhian.apicompras.Model.CompraModel;
import com.cristhian.apicompras.Repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    CompraRepository compraRepository;

    public ArrayList<CompraModel> obtenerCompras(){
        return (ArrayList<CompraModel>) compraRepository.findAll();
    }

    public CompraModel crearCompra (CompraModel compra){
        return compraRepository.save(compra);
    }

    public Optional<CompraModel> obtenerPorId(Long id){
        return compraRepository.findById(id);
    }

    public boolean eliminarCompra(Long id){
        if(compraRepository.existsById(id)){
            compraRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

}
