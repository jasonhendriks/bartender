package ca.hendriks.bartender.web.functionaltest;

import org.springframework.stereotype.Service;

@Service
public class DomainSpecificLanguage {

    public final BddMockMvcService mockMvc;
    public final BddIngredientService ingredients;

    DomainSpecificLanguage(final BddMockMvcService mockMvc, final BddIngredientService bddIngredientService) {
        this.mockMvc = mockMvc;
        this.ingredients = bddIngredientService;
    }

}
