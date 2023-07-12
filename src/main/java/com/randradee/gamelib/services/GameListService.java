package com.randradee.gamelib.services;

import com.randradee.gamelib.dtos.GameListDTO;
import com.randradee.gamelib.entities.GameList;
import com.randradee.gamelib.repositories.GameListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {

    @Autowired
    GameListRepository gameListRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        List<GameList> gameLists = gameListRepository.findAll();
        return gameLists.stream().map(GameListDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public GameListDTO findById(Long id){
        var gameList = gameListRepository.findById(id).get();
        return new GameListDTO(gameList);
    }
}
