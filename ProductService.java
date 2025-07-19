package com.tuempresa.proyecto.service;

import com.tuempresa.proyecto.model.Producto;
import com.tuempresa.proyecto.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductoRepository repo;

    public ProductService(ProductoRepository repo) {
        this.repo = repo;
    }

    public List<Producto> listar() {
        return repo.findAll();
    }

    public Producto guardar(Producto p) {
        return repo.save(p);
    }

    public Optional<Producto> buscar(String nombre) {
        return repo.findByNombreIgnoreCase(nombre);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    public Producto actualizar(Long id, Producto nuevo) {
        return repo.findById(id).map(p -> {
            p.setNombre(nuevo.getNombre());
            p.setPrecio(nuevo.getPrecio());
            p.setStock(nuevo.getStock());
            return repo.save(p);
        }).orElse(null);
    }
}
