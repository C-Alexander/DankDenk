package com.dank.entities;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NamedQueries({
        @NamedQuery(name = "Meme.getAll", query = "select m from Meme as m")
})
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Meme {
  @Id @GeneratedValue(strategy = IDENTITY)
  private Integer id;
  private String name;
  private String url;
//  @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
//  @JoinColumn(name = "cat_id")
//  private Category category;

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

//  public Category getCategory() {
//    return category;
//  }

//  public void setCategory(Category category) {
//    this.category = category;
//  }
}
