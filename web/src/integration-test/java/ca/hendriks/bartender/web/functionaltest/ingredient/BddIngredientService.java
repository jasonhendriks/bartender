package ca.hendriks.bartender.web.functionaltest.ingredient;

import ca.hendriks.bartender.common.exception.UnexpectedBartenderException;
import ca.hendriks.bartender.web.functionaltest.BddMockMvcService;
import ca.hendriks.bartender.web.inventory.Ingredient;
import ca.hendriks.bartender.web.inventory.IngredientRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MvcResult;

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

}
