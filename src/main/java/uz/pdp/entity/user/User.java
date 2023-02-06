package uz.pdp.entity.user;

import lombok.*;
import uz.pdp.entity.BaseEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    private String name;
    private String lastname;
    private String email;
    private String password;
    private UserRole role;
}
