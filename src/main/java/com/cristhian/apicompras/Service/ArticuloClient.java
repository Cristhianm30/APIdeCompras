package com.cristhian.apicompras.Service;

import com.cristhian.apicompras.DTO.ArticuloDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ArticuloClient {

    @Autowired
    RestTemplate restTemplate;

    /*
    Endpoint para la comunicacion entre APIs
     */

    private static final String API_ARTICULOS_URL = "http://localhost:8081/articulos";

    //Metodo para Traer el objeto articulo segun el ID

    public ArticuloDTO obtenerArticuloPorId(Long id){
        String url = API_ARTICULOS_URL+"/"+id;
        try {
            return restTemplate.getForObject(url, ArticuloDTO.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("Artículo con ID " + id + " no encontrado.");
        }
    }

    //Metodo para mandar el articulo con el stock actualizado en CompraService atraves del endpoint

    public void actualizarStock(ArticuloDTO articulo){
        String url = API_ARTICULOS_URL+"/"+articulo.getId();
        restTemplate.put(url, articulo);
    }
}
