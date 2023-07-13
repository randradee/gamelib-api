package com.randradee.gamelib.controllers;

import com.randradee.gamelib.dtos.GameDTO;
import com.randradee.gamelib.dtos.GameMinDTO;
import com.randradee.gamelib.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity<List<GameMinDTO>> findAll() {
        return ResponseEntity.ok().body(gameService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GameDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(gameService.findById(id));
    }
}
