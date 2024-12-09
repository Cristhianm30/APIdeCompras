package com.cristhian.apicompras.Service;

import com.cristhian.apicompras.DTO.ArticuloDTO;
import com.cristhian.apicompras.DTO.CompraRespuestaDTO;
import com.cristhian.apicompras.Model.CompraModel;
import com.cristhian.apicompras.Repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    CompraRepository compraRepository;

    @Autowired
    ArticuloClient articuloClient;

    public ArrayList<CompraModel> obtenerCompras(){
        return (ArrayList<CompraModel>) compraRepository.findAll();
    }

    public CompraRespuestaDTO crearCompra (CompraModel compra){

        // Consultar la API de Articulos
        ArticuloDTO articulo = articuloClient.obtenerArticuloPorId(compra.getArticuloId());

        // Verificar si hay suficiente stock
        if (articulo.getStock() < compra.getCantidad()) {
            throw new RuntimeException("Stock insuficiente para el articulo: " + articulo.getNombre());
        }

        // Registrar la compra en la base de datos de Compras
        CompraModel compraRegistrada = compraRepository.save(compra);

        //Reducir el stock del articulo en la API articulos
        int stockActualizado = articulo.getStock() - compra.getCantidad();
        articulo.setStock(stockActualizado);
        articuloClient.actualizarStock(articulo);

        // Calcular el valor total de la compra
        double valortotal = compra.getCantidad() * articulo.getPrecio();

        //Generar el DTO para la respuesta
        CompraRespuestaDTO respuesta = new CompraRespuestaDTO();
        respuesta.setCompraId(compraRegistrada.getId());
        respuesta.setArticuloId(articulo.getId());
        respuesta.setNombreArticulo(articulo.getNombre());
        respuesta.setPrecioArticulo(articulo.getPrecio());
        respuesta.setCantidadComprada(compra.getCantidad());
        respuesta.setStockRestante(stockActualizado);
        respuesta.setValorTotal(valortotal);
        respuesta.setFechaCompra(compraRegistrada.getFechaCompra());

        return respuesta;

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

    public List<CompraRespuestaDTO> listarComprasPorArticulo(Long articuloId) {

        List<CompraModel> compras = compraRepository.findByArticuloId(articuloId);


        List<CompraRespuestaDTO> detalles = new ArrayList<>();


        ArticuloDTO articulo = articuloClient.obtenerArticuloPorId(articuloId);


        if (articulo == null) {
            throw new RuntimeException("El artículo con ID " + articuloId + " no existe.");
        }


        for (CompraModel compra : compras) {

            double valorTotal = compra.getCantidad() * articulo.getPrecio();


            CompraRespuestaDTO detalle = new CompraRespuestaDTO();
            detalle.setCompraId(compra.getId());
            detalle.setArticuloId(articulo.getId());
            detalle.setNombreArticulo(articulo.getNombre());
            detalle.setPrecioArticulo(articulo.getPrecio());
            detalle.setCantidadComprada(compra.getCantidad());
            detalle.setStockRestante(articulo.getStock());
            detalle.setValorTotal(valorTotal);
            detalle.setFechaCompra(compra.getFechaCompra());


            detalles.add(detalle);
        }

        return detalles;
    }


}
