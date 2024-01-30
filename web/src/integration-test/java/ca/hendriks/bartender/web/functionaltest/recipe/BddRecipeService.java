package ca.hendriks.bartender.web.functionaltest.recipe;

import ca.hendriks.bartender.common.exception.UnexpectedBartenderException;
import ca.hendriks.bartender.drinks.recipe.Recipe;
import ca.hendriks.bartender.web.functionaltest.BddMockMvcService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class BddRecipeService {

    private final ObjectMapper objectMapper;
    private final BddMockMvcService mockMvc;

    private MvcResult mvcResult;

    public BddRecipeService(final ObjectMapper objectMapper, final BddMockMvcService mockMvc) {
        this.objectMapper = objectMapper;
        this.mockMvc = mockMvc;
    }

    public void addRecipe(final Recipe recipe) {
        mvcResult = mockMvc.post("/recipes", recipe, MediaType.APPLICATION_JSON);
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

}
