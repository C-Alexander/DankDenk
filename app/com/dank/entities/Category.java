package com.dank.entities;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "category")
public class Category {
  @Id @GeneratedValue(strategy = IDENTITY)
  private Integer id;
  private String name;
  private String description;
//  @OneToMany(fetch = LAZY, mappedBy = "category")
//  private Set<Meme> memes;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

//  public Set<Meme> getMemes() {
//    return memes;
//  }
//
//  public void setMemes(Set<Meme> memes) {
//    this.memes = memes;
//  }
}
