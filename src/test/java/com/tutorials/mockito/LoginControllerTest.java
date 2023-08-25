package com.tutorials.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class LoginControllerTest {

    @Mock
    private LoginDao loginDao;

    @Spy
    @InjectMocks
    private LoginService spiedLoginService;

    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    public void setUp() {
        loginController = new LoginController();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void assertThatNoMethodHasBeenCalled() {
        loginController.login(null);
        Mockito.verifyNoInteractions(loginService);
    }

    @Test
    public void assertTwoMethodsHaveBeenCalled() {
        UserForm userForm = new UserForm();
        userForm.username = "foo";
        Mockito.when(loginService.login(userForm)).thenReturn(true);

        String login = loginController.login(userForm);

        Assertions.assertEquals("OK", login);
        Mockito.verify(loginService).login(userForm);
        Mockito.verify(loginService).setCurrentUser("foo");
    }

    @Test
    public void assertOnlyOneMethodHasBeenCalled() {
        UserForm userForm = new UserForm();
        userForm.username = "foo";
        Mockito.when(loginService.login(userForm)).thenReturn(false);

        String login = loginController.login(userForm);

        Assertions.assertEquals("KO", login);
        Mockito.verify(loginService).login(userForm);
        Mockito.verifyNoMoreInteractions(loginService);
    }

    @Test
    public void mockExceptionThrowing() {
        UserForm userForm = new UserForm();
        Mockito.when(loginService.login(userForm)).thenThrow(IllegalArgumentException.class);

        String login = loginController.login(userForm);

        Assertions.assertEquals("ERROR", login);
        Mockito.verify(loginService).login(userForm);
        //Mockito.verifyNoInteractions(loginService);
        Mockito.verifyNoMoreInteractions(loginService);
    }
}
