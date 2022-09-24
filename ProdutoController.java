package br.senac.df.ProdutoRest;


import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
 
import org.springframework.web.bind.annotation.*;
 
@RestController
public class ProdutoController {
 
    @Autowired
    private ProdutoService service;
     
    // RESTful API methods for Retrieval operations
    @GetMapping("/produtos")
    public List<Produto> list() {
        return service.listAll();
    }
     
    //retornar por id
    @GetMapping("/produtos/{id}")
    public ResponseEntity<Produto> get(@PathVariable Integer id) {
        try {
            Produto Produto = service.get(id);
            return new ResponseEntity<Produto>(Produto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
        }      
    }
    
    // RESTful API method for Create operation
    @PostMapping("/produtos")
    public void add(@RequestBody Produto Produto) {
        service.save(Produto);
    }
     
    // RESTful API method for Update operation
    @PutMapping("/produtos/{id}")
    public ResponseEntity<?> update(@RequestBody Produto Produto, @PathVariable Integer id) {
        try {
            Produto existProduto = service.get(id);
            service.save(Produto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }      
    }
     
    // RESTful API method for Delete operation
    @DeleteMapping("/produtos/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}