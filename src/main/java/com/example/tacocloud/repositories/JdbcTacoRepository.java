package com.example.tacocloud.repositories;

import com.example.tacocloud.models.Ingredient;
import com.example.tacocloud.models.Taco;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

@Repository
public class JdbcTacoRepository implements ITacoRepository {

    private JdbcTemplate jdbcTemplate;

    public JdbcTacoRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Taco save(Taco designTaco){
        long tacoId = saveTaco(designTaco);
        designTaco.setId(tacoId);
        for(Ingredient ingredient : designTaco.getIngredients())
            saveIngredientToTaco(ingredient, tacoId);

        return designTaco;
    }

    private long saveTaco(Taco taco){
        taco.setCreatedAt(new Date());
        PreparedStatementCreator psc = new PreparedStatementCreatorFactory(
                "insert  into Taco(name, createdAt) values (?, ?)",
                Types.VARCHAR, Types.TIMESTAMP).newPreparedStatementCreator(
                Arrays.asList(
                        taco.getName(),
                        new Timestamp(taco.getCreatedAt().getTime())));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);
        //jdbcTemplate.update(, taco.getName(), taco.getCreatedAt());

        return keyHolder.getKey().longValue();
    }

    private void saveIngredientToTaco(Ingredient ingredient, long tacoId){
        jdbcTemplate.update("insert into Taco_Ingredients(taco, ingredient) values (?, ?)", tacoId, ingredient.getId());
    }

}
