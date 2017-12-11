package com.dank.contexts;

import com.dank.entities.Meme;

import java.util.List;

public interface MemeContext {
    void save(Meme m);

    List<Meme> findAll();
}
