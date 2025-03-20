package com.zestindia.t2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zestindia.t2.entity.Order;
import com.zestindia.t2.enums.Roles;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String roles = "USER";

    private List<Order> orders;

}
