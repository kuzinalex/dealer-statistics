package by.doubleK.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetToken implements Serializable {

    private String password;

    private String securityCode;
}
