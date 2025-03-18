package com.crud.meuprimeirocrud.controller;

import com.crud.meuprimeirocrud.models.LivroModel;
import com.crud.meuprimeirocrud.repository.LivroRepository;
import com.crud.meuprimeirocrud.service.LivroService;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.awt.print.Pageable;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/livros")
public class LivroController {
    @Autowired
    private LivroService livroService;

    /*@PostMapping
    private LivroModel criarLivro(@RequestBody LivroModel livroModel) {
        return livroService.criarLivro(livroModel);

    }*/
    @GetMapping
    private ResponseEntity<List<LivroModel>> listarLivros(){
        List<LivroModel> list = livroService.findAll();
        return ResponseEntity.ok().body(list);
    }

    private ResponseEntity<LivroModel> criarLivro(@RequestBody LivroModel livroModel){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id")
                .buildAndExpand(livroModel.getId()).toUri();

        LivroModel response = livroService.criarLivro(livroModel);
        return ResponseEntity.created(uri).body(response);

    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Object> deletarLivro(@PathVariable Long id){
        livroService.deletarLivro(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    private ResponseEntity<LivroModel> update(@PathVariable Long id, @RequestBody LivroModel livroModel){
        LivroModel response = livroService.atualizarLivro(id, livroModel);
        return ResponseEntity.ok().body(response);
    }



    @GetMapping("/{id}")
    public LivroModel getLivroById(@PathVariable Long id) {
        return livroService.getLivroById(id);

    }


}
