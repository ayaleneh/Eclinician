package com.miu.se.Eclincian.entity.dto.response;

//import cs544.ea.OnlineRetailSystem.domain.Address;
//import cs544.ea.OnlineRetailSystem.domain.Roles;
import com.miu.se.Eclincian.entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String address;
    private Roles roles;
}