package ca.hendriks.bartender.web.functionaltest.ingredient;

import ca.hendriks.bartender.common.exception.UnexpectedBartenderException;
import ca.hendriks.bartender.drinks.ingredient.Ingredient;
import ca.hendriks.bartender.drinks.ingredient.IngredientRepository;
import ca.hendriks.bartender.drinks.ingredient.IngredientType;
import ca.hendriks.bartender.web.functionaltest.BddMockMvcService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BddIngredientService {

    private final ObjectMapper objectMapper;
    private final BddMockMvcService mockMvc;
    private final IngredientRepository ingredientRepository;

    private MvcResult mvcResult;

    public BddIngredientService(final ObjectMapper objectMapper, final BddMockMvcService mockMvc, final IngredientRepository ingredientRepository) {
        this.objectMapper = objectMapper;
        this.mockMvc = mockMvc;
        this.ingredientRepository = ingredientRepository;
    }

    public void addIngredient(final Ingredient ingredient) {
        mvcResult = mockMvc.post("/ingredients", ingredient);
    }

    public List<Ingredient> findIngredients() {
        final MvcResult mvcResult = mockMvc.get("/ingredients");
        return deserializeResult(mvcResult);
    }

    private List<Ingredient> deserializeResult(final MvcResult mvcResult) {
        try {
            final String contentAsString = mvcResult.getResponse().getContentAsString();
            return objectMapper.readValue(contentAsString, new TypeReference<>() {
            });
        } catch (final UnsupportedEncodingException | JsonProcessingException e) {
            throw new UnexpectedBartenderException(e);
        }
    }

    public void givenIngredients(final List<Ingredient> ingredients) {
        ingredientRepository.saveAll(ingredients);
    }

    public Ingredient findIngredient(final String ingredientName) {
        return ingredientRepository.findByName(ingredientName);
    }

    public void updateIngredient(final String originalIngredientName, final String originalIngredientType,
                                       final String updatedIngredientName, final String updatedIngredientType){
        List<String> ingredientUpdates = new ArrayList<>();
        ingredientUpdates.add(originalIngredientName);
        ingredientUpdates.add(originalIngredientType);
        ingredientUpdates.add(updatedIngredientName);
        ingredientUpdates.add(updatedIngredientType);
        mockMvc.put("/ingredients", ingredientUpdates);
    }

    public void cleanUpRepository(){
        ingredientRepository.deleteAll();
    }

    public void deleteIngredient(final String ingredientName) {
        mockMvc.delete("/ingredients", ingredientName);
    }
}
