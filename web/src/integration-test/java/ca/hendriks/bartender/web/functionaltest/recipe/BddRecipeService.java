package ca.hendriks.bartender.web.functionaltest.recipe;

import ca.hendriks.bartender.common.exception.UnexpectedBartenderException;
import ca.hendriks.bartender.drinks.recipe.Recipe;
import ca.hendriks.bartender.drinks.recipe.RecipeRepository;
import ca.hendriks.bartender.web.functionaltest.BddMockMvcService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class BddRecipeService {

    private final ObjectMapper objectMapper;
    private final BddMockMvcService mockMvc;
    private final RecipeRepository recipeRepository;

    private MvcResult mvcResult;

    public BddRecipeService(final ObjectMapper objectMapper, final BddMockMvcService mockMvc, final RecipeRepository recipeRepository) {
        this.objectMapper = objectMapper;
        this.mockMvc = mockMvc;
        this.recipeRepository = recipeRepository;
    }

    public void addRecipe(final Recipe recipe) {
        mvcResult = mockMvc.postViaApi("/recipes", recipe);
    }

    private List<Recipe> deserializeResult(final MvcResult mvcResult) {
        try {
            final String contentAsString = mvcResult.getResponse().getContentAsString();
            return objectMapper.readValue(contentAsString, new TypeReference<>() {
            });
        } catch (final UnsupportedEncodingException | JsonProcessingException e) {
            throw new UnexpectedBartenderException(e);
        }
    }

    public List<Recipe> getRecipes(){
        mvcResult = mockMvc.getViaApi("/recipes");
        return deserializeResult(mvcResult);
    }

    public void updateRecipe(final Recipe originalRecipe, final Recipe updatedRecipe) {
        int idOfRecipeToBeUpdated = recipeRepository.findAll()
                .stream()
                .filter(x->
                        x.getName().equals(originalRecipe.getName()))
                .toList()
                .get(0).getId();
        mockMvc.put("/recipes", idOfRecipeToBeUpdated, updatedRecipe);
    }

    public void cleanup(){
        recipeRepository.deleteAll();
    }
}
