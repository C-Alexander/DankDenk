package com.dank.controllers;

import com.dank.entities.Meme;
import com.dank.entities.PaidMeme;
import com.dank.repositories.CategoryRepository;
import com.dank.repositories.MemeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.jasypt.util.text.BasicTextEncryptor;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton //controls your memes
public class MemeController extends Controller {

  private MemeRepository memeRepository;
  private CategoryRepository categoryRepository;
  private ObjectMapper mapper;
  private BasicTextEncryptor encryptor;

  @Inject
  public MemeController(MemeRepository memeRepository, CategoryRepository categoryRepository) {
    this.memeRepository = memeRepository;
    this.categoryRepository = categoryRepository;
    mapper = new ObjectMapper();
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

    try {
      return created(mapper.writeValueAsString(eaMemeEverything));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return internalServerError(e.getMessage());
    }
  }

  @Transactional(readOnly = true)
  public Result getMeme(int id) {
    Meme meme = memeRepository.findMeme(id);
   // System.out.println(meme.getCategory().getName());
    if (meme.getClass() == PaidMeme.class) meme.setUrl(encryptor.encrypt(meme.getUrl()));
    try {
      return ok(mapper.writeValueAsString(meme));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return internalServerError(e.getMessage());
    }
  }

  @Transactional(readOnly = true)
  public Result getMemes() {
    if (request().getQueryString("category") == null) try {
      return ok(mapper.writeValueAsString(memeRepository.findAllMemes()));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return internalServerError(e.getMessage());
    }
    try {
      return ok(mapper.writeValueAsString(categoryRepository.getCategoryByName(request().getQueryString("category")).getMemes()));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return internalServerError(e.getMessage());
    }
  }
}
