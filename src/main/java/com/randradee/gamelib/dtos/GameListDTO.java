package com.randradee.gamelib.dtos;

import com.randradee.gamelib.entities.GameList;
import org.springframework.beans.BeanUtils;

public class GameListDTO {
    private Long id;
    private String name;

    public GameListDTO() {
    }

    public GameListDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public GameListDTO(GameList gameList) {
        BeanUtils.copyProperties(gameList, this);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
