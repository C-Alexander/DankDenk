package com.dank.repositories;

import com.dank.contexts.MemeContext;
import com.dank.entities.Meme;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class MemeRepository {

  private MemeContext memeContext;

  @Inject
  public MemeRepository(MemeContext memeContext) {
    this.memeContext = memeContext;
  }


  public void saveMeme(Meme meme) {
    memeContext.save(meme);
  }

 public List<Meme> findAllMemes() {
    return memeContext.findAll();
  }

  public Meme findMeme(int id) { return memeContext.findOne(id); }
}
