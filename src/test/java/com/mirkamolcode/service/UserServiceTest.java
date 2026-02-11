package com.mirkamolcode.service;

import com.mirkamolcode.dao.UserDAO;
import com.mirkamolcode.dao.UserFileDAO;
import com.mirkamolcode.model.User;
import com.mirkamolcode.model.enums.ResponseMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static com.mirkamolcode.model.enums.ResponseMessage.UNKNOWN_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserService underTest;

    @Test
    void shouldGetAllUsers() {
        // given
        List<User> expected = new ArrayList<>(Arrays.asList(
                new User(UUID.randomUUID(), "Akbar"),
                new User(UUID.randomUUID(), "Jamila")));
        given(userDAO.selectAllUsers()).willReturn(expected);

        // when
        List<User> actual = underTest.getAllUsers();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldThrowWhenUserListIsEmpty() {
        // given
        given(userDAO.selectAllUsers()).willReturn(new ArrayList<>());

        // then
        assertThatThrownBy(() -> underTest.getAllUsers())
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining(ResponseMessage.NO_USERS.getMessage());
    }

    @Test
    void getUserById() {
        // given
        UUID expectedUserId = UUID.randomUUID();
        Optional<User> expected = Optional.of(new User(expectedUserId, "Jamila"));
        given(userDAO.getUserById(expectedUserId)).willReturn(expected);
        // when
        User actual = underTest.getUserById(expectedUserId);
        // then
        assertThat(actual).isEqualTo(expected.get());
    }
    @Test
    void shouldThrowWhenUserNotFound() {
        // given

        given(userDAO.getUserById(any())).willReturn(Optional.empty());
        // then
        assertThatThrownBy(() -> underTest.getUserById(any()))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining(UNKNOWN_USER.getMessage());
    }
}