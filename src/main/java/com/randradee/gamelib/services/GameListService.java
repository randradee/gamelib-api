package com.randradee.gamelib.services;

import com.randradee.gamelib.dtos.GameListDTO;
import com.randradee.gamelib.entities.GameList;
import com.randradee.gamelib.projections.GameMinProjection;
import com.randradee.gamelib.repositories.GameListRepository;
import com.randradee.gamelib.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {

    @Autowired
    GameListRepository gameListRepository;

    @Autowired
    GameRepository gameRepository;

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

    @Transactional
    public void updateGamePositionOnList(Long listId, int sourceIndex, int destinationIndex){
        List<GameMinProjection> list = gameRepository.searchByList(listId);

        GameMinProjection obj = list.remove(sourceIndex);
        list.add(destinationIndex, obj);

        int min = Math.min(sourceIndex, destinationIndex);
        int max = Math.max(sourceIndex, destinationIndex);

        for (int i = min; i <= max; i++) {
            gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
        }

    }

}
