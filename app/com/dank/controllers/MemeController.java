package com.dank.controllers;

import com.dank.entities.Category;
import com.dank.entities.Meme;
import com.dank.entities.PaidMeme;
import com.dank.repositories.MemeRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import org.jasypt.util.text.BasicTextEncryptor;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;


@Singleton //controls your memes
public class MemeController extends Controller {

  private MemeRepository memeRepository;
  private Gson gson;
  private BasicTextEncryptor encryptor;

  @Inject
  public MemeController(MemeRepository memeRepository) {
    this.memeRepository = memeRepository;
    gson = new Gson();
    encryptor = new BasicTextEncryptor();
    encryptor.setPassword("SE42");
  }

  @Transactional
  public Result addNewMeme() {
    JsonNode body = request().body().asJson();
    PaidMeme eaMemeEverything = new PaidMeme();
    eaMemeEverything.setName(body.get("name").asText());
    eaMemeEverything.setUrl(body.get("url").asText());
    if (body.get("price") != null) {
      Float price = Float.valueOf(body.get("price").asText());
      eaMemeEverything.setPrice(price);
      memeRepository.saveMeme(eaMemeEverything);
    } else memeRepository.saveMeme((Meme)eaMemeEverything);

    return created(gson.toJson(eaMemeEverything));
  }

  @Transactional(readOnly = true)
  public Result getMeme(int id) {
    Meme meme = memeRepository.findMeme(id);
    if (meme.getClass() == PaidMeme.class) meme.setUrl(encryptor.encrypt(meme.getUrl()));
    return ok(gson.toJson(meme));
  }

  @Transactional(readOnly = true)
  public Result getMemes() {
    return ok(gson.toJson(memeRepository.findAllMemes()));
  }
}
