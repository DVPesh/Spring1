package ru.peshekhonov.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.peshekhonov.model.Cart;
import ru.peshekhonov.model.Role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VisitorDto {

    private Long id;

    @NotBlank(message = "поле не может быть пустым")
    private String username;

    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$", message = "не является номером телефона")
    private String phoneNumber;

    @Pattern(regexp = "^(?=.*?[0-9])(?=.*?[A-Z]).{8,}$", message = "слишком простой пароль")
    private String password;

    //    @JsonIgnore
    @NotBlank(message = "поле не может быть пустым")
    private String matchingPassword;

    private Set<Role> roles;

    private Set<Cart> carts;

    public VisitorDto(String username, String phoneNumber, String password, String matchingPassword) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.matchingPassword = matchingPassword;
    }

    public List<String> getRoleNames() {
        return roles.stream().map(r -> r.getName().substring(5)).toList();
    }

    public String getRoleNamesString() {
        String message = getRoleNames().toString();
        return message.substring(1, message.length() - 1);
    }
}
