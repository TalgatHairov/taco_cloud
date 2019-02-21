package com.example.tacocloud.controllers;

import com.example.tacocloud.models.Ingredient;
import com.example.tacocloud.models.Ingredient.Type;

import com.example.tacocloud.models.Order;
import com.example.tacocloud.models.Taco;
import com.example.tacocloud.repositories.IIngredientRepository;
import com.example.tacocloud.repositories.ITacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IIngredientRepository ingredientRepository;

    private final ITacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(IIngredientRepository ingredientRepository, ITacoRepository tacoRepository){
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(i -> ingredients.add(i));

        Type[] types = Ingredient.Type.values();
        for(Type type : types)
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));


        model.addAttribute("design", new Taco());
        return "design";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type){
        List<Ingredient> typeIngredients = new ArrayList<>();
        for(Ingredient ingredient : ingredients)
            if(ingredient.getType() == type) typeIngredients.add(ingredient);

        return typeIngredients;
    }

    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order){
        if(errors.hasErrors())
            return "design";

        log.info("Processing design: " + design);

        Taco savedTaco = tacoRepository.save(design);
        order.addDesign(savedTaco);

        return "redirect:/orders/current";
    }
}
