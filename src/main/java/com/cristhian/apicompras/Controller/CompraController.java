package com.cristhian.apicompras.Controller;

import com.cristhian.apicompras.Model.CompraModel;
import com.cristhian.apicompras.Service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    CompraService compraService;

    @GetMapping
    public ArrayList<CompraModel> obtenerCompras(){
        return compraService.obtenerCompras();
    }

    @PostMapping
    public CompraModel crearCompra(@RequestBody CompraModel compra) {
        return compraService.crearCompra(compra);
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<?> obtenerCompraPorId(@PathVariable Long id) {
        Optional<CompraModel> compra = compraService.obtenerPorId(id);
        if (compra.isPresent()) {
            return ResponseEntity.ok(compra.get());
        } else {
            return ResponseEntity.status(404).body("Compra con ID " + id + " no encontrada.");
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> eliminarCompra(@PathVariable Long id) {
        boolean eliminado = compraService.eliminarCompra(id);
        if (eliminado) {
            return ResponseEntity.ok("Compra con ID " + id + " eliminada.");
        } else {
            return ResponseEntity.status(404).body("Compra con ID " + id + " no encontrada.");
        }
    }
}
