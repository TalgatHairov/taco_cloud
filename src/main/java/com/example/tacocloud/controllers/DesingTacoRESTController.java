package com.example.tacocloud.controllers;

import com.example.tacocloud.models.Taco;
import com.example.tacocloud.repositories.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesingTacoRESTController {

    private TacoRepository tacoRepository;

    @Autowired
    EntityLinks entityLinks;

    public DesingTacoRESTController(TacoRepository repository){
        this.tacoRepository = repository;
    }

    @GetMapping("/recent")
    public Iterable<Taco> recentTacos(){
        PageRequest pageRequest = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return tacoRepository.findAll(pageRequest).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> getTacoById(@PathVariable("id") Long id){
        Optional<Taco> optionalTaco = tacoRepository.findById(id);
        if(optionalTaco.isPresent())
            return new ResponseEntity<>(optionalTaco.get(), HttpStatus.OK);
        return new ResponseEntity<>(new Taco(), HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco){
        return tacoRepository.save(taco);
    }
}
