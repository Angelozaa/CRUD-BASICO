package com.crud.meuprimeirocrud.service;

import com.crud.meuprimeirocrud.models.LivroModel;
import com.crud.meuprimeirocrud.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    public List<LivroModel> findAll(){
        return livroRepository.findAll();
    }

    public LivroModel criarLivro(LivroModel livroModel) {
        return livroRepository.save(livroModel);

    }

    public LivroModel getLivroById(Long id) {
        return livroRepository.findById(id).get(); //pode ser descontinuado, por isso fazemos o find depois o get
    }

    public void deletarLivro(Long id){
        livroRepository.deleteById(id);

    }

    public LivroModel atualizarLivro(Long id,LivroModel livroModel){
        LivroModel model = livroRepository.findById(id).get();
        model.setNome(livroModel.getNome());
        model.setAutor((livroModel.getAutor()));
        model = livroRepository.save(model);
        return model;
    }

}
