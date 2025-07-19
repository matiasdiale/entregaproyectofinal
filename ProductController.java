package com.matias.proyecto.controller;

import com.matias.proyecto.model.Producto;
import com.matias.proyecto.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductController {

    private final ProductService servicio;

    public ProductController(ProductService servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public List<Producto> getAll() {
        return servicio.listar();
    }

    @PostMapping
    public Producto crear(@RequestBody Producto producto) {
        return servicio.guardar(producto);
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<Producto> getByNombre(@PathVariable String nombre) {
        return servicio.buscar(nombre)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto nuevo) {
        Producto actualizado = servicio.actualizar(id, nuevo);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            servicio.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
