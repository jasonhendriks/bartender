package ca.hendriks.bartender.web.functionaltest;

import ca.hendriks.bartender.common.exception.UnexpectedBartenderException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BddMockMvcService {

    private final MockMvc mockMvc;

    public BddMockMvcService(final WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    public MvcResult get(final String uri) {
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

}
