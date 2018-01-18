package com.dank.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

import java.util.Locale;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NamedQueries({
        @NamedQuery(name = "Meme.getAll", query = "select m from Meme as m"),
        @NamedQuery(name = "Meme.findOne", query = "select m from Meme as m WHERE m.id = :id")
})
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Meme {
  @Id @GeneratedValue(strategy = IDENTITY)
  private Integer id;
  private String name;
  private String url;
  @ManyToOne
  @JoinColumn(name = "categoryId")
  @JsonManagedReference
  private Category category;

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

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }
}
