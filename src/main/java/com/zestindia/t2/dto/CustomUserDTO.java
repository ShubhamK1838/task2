package com.zestindia.t2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zestindia.t2.enums.Roles;
import lombok.*;

@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
public class CustomUserDTO {

    private String id, username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Roles roles[] = new Roles[]{Roles.USER};

}
