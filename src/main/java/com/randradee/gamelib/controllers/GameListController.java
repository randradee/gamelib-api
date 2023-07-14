package com.randradee.gamelib.controllers;

import com.randradee.gamelib.dtos.GameListDTO;
import com.randradee.gamelib.dtos.GameMinDTO;
import com.randradee.gamelib.dtos.ReplacementDTO;
import com.randradee.gamelib.services.GameListService;
import com.randradee.gamelib.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lists")
public class GameListController {

    @Autowired
    GameListService gameListService;

    @Autowired
    GameService gameService;

    @GetMapping
    public ResponseEntity<List<GameListDTO>> findAll() {
        return ResponseEntity.ok().body(gameListService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameListDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(gameListService.findById(id));
    }

    @GetMapping(value = "/{id}/games")
    public ResponseEntity<List<GameMinDTO>> findByListId(@PathVariable("id") Long listId) {
        return ResponseEntity.ok().body(gameService.findByListId(listId));
    }

    @PostMapping(value = "/{listId}/replacement")
    public ResponseEntity<String> updateGamePositionOnList(@PathVariable Long listId, @RequestBody ReplacementDTO body) {
        gameListService.updateGamePositionOnList(listId, body.getSourceIndex(), body.getDestinationIndex());
        return ResponseEntity.ok().body("Lista atualizada com sucesso");
    }
}
