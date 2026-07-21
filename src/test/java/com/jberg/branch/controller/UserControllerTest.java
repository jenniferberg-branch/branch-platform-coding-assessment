package com.jberg.branch.controller;

import com.jberg.branch.model.UserData;
import com.jberg.branch.service.UserService;
import com.jberg.branch.utility.MockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    private final MockData mockData = new MockData();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .setControllerAdvice(new UserExceptionHandler())
                .build();
    }

    @Test
    public void test_getUserData_success() throws Exception {
        UserData mockUserData = mockData.getMockUserDataResponse();
        ResponseEntity<UserData> expectedResponse = ResponseEntity.status(HttpStatus.OK).body(mockUserData);

        doReturn(mockUserData).when(userService).getUserData(mockData.TEST_USERNAME);

        ResponseEntity<UserData> response = userController.getUserData(mockData.TEST_USERNAME);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void test_getUserData_httpClientErrorException() throws Exception {
        String errorMessage = "there was an http client error";
        HttpClientErrorException exception = new HttpClientErrorException(HttpStatus.NOT_FOUND, errorMessage);
        when(userService.getUserData(mockData.TEST_USERNAME)).thenThrow(exception);

        mockMvc.perform(get("/user").param("username", mockData.TEST_USERNAME))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(exception.getMessage()));
    }

    @Test
    public void test_getUserData_missingParameterException() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").value(containsString("username")));
    }

    @Test
    public void test_getUserData_generalException() throws Exception {
        String errorMessage = "there was an error";
        when(userService.getUserData(mockData.TEST_USERNAME)).thenThrow(new Exception(errorMessage));

        mockMvc.perform(get("/user").param("username", mockData.TEST_USERNAME))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.errorMessage").value(errorMessage));
    }
}
