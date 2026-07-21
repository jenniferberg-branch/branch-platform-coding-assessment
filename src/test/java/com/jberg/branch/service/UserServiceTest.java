package com.jberg.branch.service;

import com.jberg.branch.model.UserData;
import com.jberg.branch.model.UserRepos;
import com.jberg.branch.utility.MockData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private RestClient restClient;

    @InjectMocks
    private UserService userService;

    private final MockData mockData = new MockData();

    @Test
    public void test_getUserData_success() throws Exception {
        UserData mockUserData = mockData.getMockUserData();
        List<UserRepos> mockUserRepos = mockData.getMockUserRepos();

        RestClient.RequestHeadersUriSpec mockUriSpec = mock(RestClient.RequestHeadersUriSpec.class);
        RestClient.ResponseSpec mockResponseSpec = mock(RestClient.ResponseSpec.class);

        when(restClient.get()).thenReturn(mockUriSpec);
        when(mockUriSpec.uri(eq("/{username}/repos"), eq(mockData.TEST_USERNAME))).thenReturn(mockUriSpec);
        when(mockUriSpec.uri(eq("/{username}"), eq(mockData.TEST_USERNAME))).thenReturn(mockUriSpec);
        when(mockUriSpec.retrieve()).thenReturn(mockResponseSpec);
        when(mockResponseSpec.body(any(ParameterizedTypeReference.class))).thenReturn(mockUserRepos);
        when(mockResponseSpec.body(UserData.class)).thenReturn(mockUserData);

        mockUserData.setRepos(mockUserRepos);

        UserData userData = userService.getUserData(mockData.TEST_USERNAME);

        assertEquals(mockUserData, userData);
    }

    @Test
    public void test_getUserData_throwsException() {
        String errorMessage = "rest client error";
        when(restClient.get()).thenThrow(new RuntimeException(errorMessage));

        Exception exception = assertThrows(Exception.class,
                () -> userService.getUserData(mockData.TEST_USERNAME));

        assertEquals(errorMessage, exception.getMessage());
    }
}
