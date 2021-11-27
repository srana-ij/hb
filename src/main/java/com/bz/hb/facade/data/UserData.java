package com.bz.hb.facade.data;

import com.bz.hb.model.common.UserStatus;
import com.bz.hb.model.common.UserType;
import lombok.*;

/**
 * created by srana on 27/11/20 at 11.53 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserData {
    private Long id;
    private String fullName;
    private String userName;
    private UserType userType;
    private UserStatus userStatus;
    private int activeStatus;

    public UserData(Long id,String fullName,String userName,UserType userType,UserStatus userStatus){
        this.id=id;
        this.fullName=fullName;
        this.userName=userName;
        this.userType=userType;
        this.userStatus=userStatus;
    }
}
