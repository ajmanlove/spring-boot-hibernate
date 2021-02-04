package com.ajmanlove.spring.controllers;

import com.ajmanlove.spring.services.UsersService;
import com.ajmanlove.spring.structs.RestErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

  private static final ObjectMapper mapper = new ObjectMapper();

  @Autowired
  MockMvc mvc;

  @MockBean
  UsersService usersService;

  @Test
  public void shouldGetUsers() throws Exception {

    Object user1 = "string";

    Mockito.doReturn(ImmutableList.of(user1))
      .when(usersService)
      .getUsers();

    mvc.perform(
      MockMvcRequestBuilders.get("/users")
        .accept(MediaType.APPLICATION_JSON_VALUE)
    )
      .andExpect(status().isOk())
      .andDo((result) -> {
        Object[] results = mapper.readValue(result.getResponse().getContentAsString(), Object[].class);
        assertEquals(1, results.length);
        assertArrayEquals(new Object[] { user1 }, results);

        Mockito.verify(usersService, Mockito.times(1))
          .getUsers();
      });
  }

  @Test
  public void shouldHandleGetUsersException() throws Exception {

    Mockito.doThrow(new IllegalArgumentException("WORDS"))
      .when(usersService)
      .getUsers();

    mvc.perform(
      MockMvcRequestBuilders.get("/users")
        .accept(MediaType.APPLICATION_JSON_VALUE)
    )
      .andExpect(status().is(400))
      .andDo((result) -> {

        RestErrorResponse response = mapper.readValue(result.getResponse().getContentAsString(), RestErrorResponse.class);
        assertEquals("WORDS", response.getMessage() );
        assertEquals(400, response.getStatus());

        Mockito.verify(usersService, Mockito.times(1))
          .getUsers();
      });
  }
}
