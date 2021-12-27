package com.spring.demo.repository.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.demo.model.Taco;
import com.spring.demo.repository.TacoRepository;

@Repository
public class JdbcTacoRepository implements TacoRepository {
	
	private SimpleJdbcInsert tacoInserter;
	private SimpleJdbcInsert tacoIngredientInserter;
	private ObjectMapper objectMapper;

	@Autowired
	public JdbcTacoRepository(JdbcTemplate jdbc) {
		this.tacoInserter = new SimpleJdbcInsert(jdbc).withTableName("taco").usingGeneratedKeyColumns("id");
		this.tacoIngredientInserter = new SimpleJdbcInsert(jdbc).withTableName("taco_ingredients");
		this.objectMapper = new ObjectMapper();
	}
//	private JdbcTemplate jdbc;
//
//	public JdbcTacoRepository(JdbcTemplate jdbc) {
//		this.jdbc = jdbc;
//	}

	@Override
	public Taco save(Taco taco) {
		
		long tacoId = saveTacoInfo(taco);
		taco.setId(tacoId);
		for (String ingredient : taco.getIngredients()) {
			saveIngredientToTaco(ingredient, tacoId);
		}
		return taco;
	}

	private long saveTacoInfo(Taco taco) {
		@SuppressWarnings("unchecked")
		Map<String, Object> values = objectMapper.convertValue(taco, Map.class);
		values.put("createdAt", new Date());
		long tacoId = tacoInserter.executeAndReturnKey(values).longValue();
		return tacoId;
//		taco.setCreatedAt(new Date());
//		PreparedStatementCreator psc = new PreparedStatementCreatorFactory(
//				"insert into Taco (name, createdAt) values (?, ?)", Types.VARCHAR, Types.TIMESTAMP)
//						.newPreparedStatementCreator(
//								Arrays.asList(taco.getName(), new Timestamp(taco.getCreatedAt().getTime())));
//		jdbc.update(psc);
//		return 1;
	}

	private void saveIngredientToTaco(String ingredient, long taco) {
		Map<String, Object> values = new HashMap<>();
		values.put("taco", taco);
		values.put("ingredient", ingredient);
		tacoIngredientInserter.execute(values);
//		jdbc.update("insert into Taco_Ingredients (taco, ingredient) " + "values (?, ?)", tacoId, ingredient);
	}
}
