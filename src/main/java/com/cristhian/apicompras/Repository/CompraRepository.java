package com.cristhian.apicompras.Repository;

import com.cristhian.apicompras.Model.CompraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<CompraModel, Long> {
    /*
    Método generado automáticamente por Spring Data JPA para buscar por articuloId
     */
    List<CompraModel> findByArticuloId(Long articuloId);
}
