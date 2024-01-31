package ca.hendriks.bartender.web.functionaltest;

import ca.hendriks.bartender.common.exception.UnexpectedBartenderException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public MvcResult post(final String uri, final Object data) {
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

    public MvcResult put(final String uri, final Object data){
        try {
            return mockMvc.perform(MockMvcRequestBuilders
                    .put(uri)
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
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
