package com.dank.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "category")
@NamedQueries({
        @NamedQuery(name = "Category.findByName", query = "select c from Category as c WHERE c.name = :name")
})
public class Category {
  @Id @GeneratedValue(strategy = IDENTITY)
  private Integer id;
  private String name;
  private String description;
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "category")
  @JsonBackReference
 // @JoinColumn(name = "id", referencedColumnName = "categoryId")
  private List<Meme> memes;

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

  public List<Meme> getMemes() {
    return memes;
  }

  public void setMemes(List<Meme> memes) {
    this.memes = memes;
  }
}
