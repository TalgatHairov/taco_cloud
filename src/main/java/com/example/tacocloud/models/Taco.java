package com.example.tacocloud.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdAt;

    /*public Taco(String[] ingredietns){
        for(String current : ingredietns){
            Ingredient ingredient = new Ingredient(current, "", Ingredient.Type.CHEESE);
            this.ingredients.add(ingredient);
        }
    }*/

    @NotNull
    @Size(min=5, message = "Name must be at least 5 characters long")
    private String name;

    @ManyToMany(targetEntity = Ingredient.class)
    @Size(min=1, message = "You must choose at least 1 items")
    private List<Ingredient> ingredients;

    @PrePersist
    void createdAt(){
        this.createdAt = new Date();
    }
}
