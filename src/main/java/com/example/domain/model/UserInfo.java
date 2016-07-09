package com.example.domain.model;

import com.example.model.User;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserInfo {
    private Long id;
    private String name;
    private User.Gender gender;

    public static UserInfo getUser(User user) {
        UserInfo info = new UserInfo();
        info.setId(user.getId());
        info.setName(user.getName());
        info.setGender(user.getGender());
        return info;
    }
}
