package ca.hendriks.bartender.web.functionaltest;

import ca.hendriks.bartender.common.Ingredient;
import ca.hendriks.bartender.common.exception.UnexpectedBartenderException;
import ca.hendriks.bartender.web.inventory.IngredientsRestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class BddIngredientService {

    private final ObjectMapper objectMapper;
    private final IngredientsRestController ingredientsRestController;
    private final BddMockMvcService mockMvc;

    public BddIngredientService(final ObjectMapper objectMapper, final IngredientsRestController ingredientsRestController, final BddMockMvcService mockMvc) {
        this.objectMapper = objectMapper;
        this.ingredientsRestController = ingredientsRestController;
        this.mockMvc = mockMvc;
    }

    public void addIngredient() {
        ingredientsRestController.addIngredient();
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

}
