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
    private int order;
    @Override
    public String toString() {
        return "User{" +
                "  order :'" + order + '\''+
                ", name : '" + name + '\'' +
                ", lastname : '" + lastname + '\'' +
                ", email : '" + email + '\'' +
                ", password : '" + password + '\'' +
                ", role : '" + role +
                '}';
    }
}
