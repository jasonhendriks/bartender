package ca.hendriks.bartender.web.functionaltest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class DomainSpecificLanguage {

    public final BddMockMvcService mockMvc;
    public final BddIngredientService ingredients;

    DomainSpecificLanguage(final BddMockMvcService mockMvc, final BddIngredientService bddIngredientService) {
        this.mockMvc = mockMvc;
        this.ingredients = bddIngredientService;
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
