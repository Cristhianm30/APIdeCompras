package com.cristhian.apicompras.DTO;


/*
    DTO es una clase para controlar qué datos se envían o reciben del cliente.
    En este caso como prueba para comunicar APIs  trayendo el articulo desde compra
    con GET
 */
public class ArticuloDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
