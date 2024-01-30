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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.UnsupportedEncodingException;
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

    public void addIngredientViaApi(final Ingredient ingredient) {
        mvcResult = mockMvc.postViaApi("/api/ingredients", ingredient);
    }

    public void addIngredientViaBrowser(final String name, String type) {
        final MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("name", name);
        formData.add("type", type);
        mvcResult = mockMvc.postViaHtmlForm("/ingredients", formData);
    }

    public List<Ingredient> findIngredientsViaApi() {
        final MvcResult mvcResult = mockMvc.getViaApi("/api/ingredients");
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

    public Ingredient updateIngredient(final String ingredientName, final IngredientType ingredientType) {
        final int ingredientFromRepoId = findIngredient(ingredientName).getId();
        final Ingredient ingredientToUpdate = new Ingredient(ingredientFromRepoId, ingredientName, ingredientType);
        ingredientRepository.save(ingredientToUpdate);
        return ingredientToUpdate;
    }

    public void cleanUpRepository() {
        ingredientRepository.deleteAll();
    }

    public List<Ingredient> retrieveIngredientsFromMockMvcResponse() {
        return (List<Ingredient>) mvcResult.getModelAndView().getModel().get("ingredients");
    }

}
