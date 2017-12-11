package com.dank.controllers;

import com.dank.entities.Category;
import com.dank.entities.Meme;
import com.dank.entities.PaidMeme;
import com.dank.repositories.MemeRepository;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;


@Singleton //controls your memes
public class MemeController extends Controller {

  private MemeRepository memeRepository;

  @Inject
  public MemeController(MemeRepository memeRepository) {
    this.memeRepository = memeRepository;
  }

  @Transactional
  public Result addNewMeme() {

    PaidMeme eaMemeEverything = new PaidMeme();
    eaMemeEverything.setName(request().getQueryString("name"));
    eaMemeEverything.setUrl(request().getQueryString("url"));
    if (request().getQueryString("price") != null) {
      Float price = Float.valueOf(request().getQueryString("price"));
      eaMemeEverything.setPrice(price);
      memeRepository.saveMeme(eaMemeEverything);
    } else memeRepository.saveMeme((Meme)eaMemeEverything);

    StringBuilder memes = new StringBuilder();
    for(Meme m : memeRepository.findAllMemes()) {
      memes.append(m.getId() + ' ' + m.getName() + ' ' + m.getUrl() + '\n');
    }

    return ok(memes.toString());
  }
}
