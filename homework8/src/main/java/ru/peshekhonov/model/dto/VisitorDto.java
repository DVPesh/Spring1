package ru.peshekhonov.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.peshekhonov.model.Cart;
import ru.peshekhonov.model.Role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VisitorDto {

    private Long id;

    @NotBlank(message = "поле не может быть пустым")
    private String username;

    private String phoneNumber;

    @NotNull(message = "поле не может быть пустым")
    @Pattern(regexp = "^(?=.*?[0-9])(?=.*?[A-Z]).{8,}$", message = "слишком простой пароль")
    private String password;

    @JsonIgnore
    private String matchingPassword;

    private Set<Role> roles;

    private Set<Cart> carts;

    public VisitorDto(String username, String phoneNumber, String password, String matchingPassword) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.matchingPassword = matchingPassword;
    }
}
