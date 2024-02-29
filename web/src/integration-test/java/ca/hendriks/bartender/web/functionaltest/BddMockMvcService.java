package ca.hendriks.bartender.web.functionaltest;

import ca.hendriks.bartender.common.exception.UnexpectedBartenderException;
import ca.hendriks.bartender.drinks.ingredient.Ingredient;
import ca.hendriks.bartender.drinks.recipe.Recipe;
import ca.hendriks.bartender.drinks.recipe.ingredient.IngredientWithQuantity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BddMockMvcService {

    private final MockMvc mockMvc;

    public BddMockMvcService(final WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    public MvcResult getViaApi(final String uri) {
        try {
            return mockMvc.perform(MockMvcRequestBuilders
                            .get(uri)
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (final Exception e) {
            throw new UnexpectedBartenderException(e);
        }
    }

    public MvcResult postViaApi(final String uri, final Object data) {
        try {
            return mockMvc.perform(MockMvcRequestBuilders
                            .post(uri)
                            .content(asJsonString(data))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andReturn();
        } catch (final Exception e) {
            throw new UnexpectedBartenderException(e);
        }
    }

    public MvcResult postViaHtmlForm(final String uri, final MultiValueMap<String, String> params) {
        try {
            return mockMvc.perform(MockMvcRequestBuilders
                            .post(uri)
                            .params(params)
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .accept(MediaType.TEXT_HTML))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andReturn();
        } catch (final Exception e) {
            throw new UnexpectedBartenderException(e);
        }
    }

    public MvcResult put(final String uri, final int id, final Ingredient data){
        try {
            return mockMvc.perform(MockMvcRequestBuilders
                            .put(uri+"/"+id)
                            .content(asJsonString(data))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            throw new UnexpectedBartenderException(e);
        }
    }

    public MvcResult put(final String uri, final int id, final Recipe data){
        try {
            return mockMvc.perform(MockMvcRequestBuilders
                    .put(uri+"/"+id)
                    .content(asJsonString(data))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            throw new UnexpectedBartenderException(e);
        }
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public MvcResult delete(final String uri, final Object data) {
        try {
            return mockMvc.perform(MockMvcRequestBuilders
                    .delete(uri)
                    .content(asJsonString(data))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isNoContent())
                    .andReturn();
        } catch (Exception e) {
            throw new UnexpectedBartenderException(e);
        }
    }
}
