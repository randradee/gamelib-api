package com.randradee.gamelib.services;

import com.randradee.gamelib.dtos.GameDTO;
import com.randradee.gamelib.dtos.GameMinDTO;
import com.randradee.gamelib.entities.Game;
import com.randradee.gamelib.projections.GameMinProjection;
import com.randradee.gamelib.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll() {
        List<Game> games = gameRepository.findAll();
        return games.stream().map(GameMinDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public GameDTO findById(Long id){
        Game result = gameRepository.findById(id).get();
        return new GameDTO(result);
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findByListId(Long listId){
        List<GameMinProjection> list = gameRepository.searchByList(listId);
        return list.stream().map(GameMinDTO::new).toList();
    }
}
