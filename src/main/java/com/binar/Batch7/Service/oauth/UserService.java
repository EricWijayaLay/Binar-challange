package com.binar.Batch7.Service.oauth;

import com.binar.Batch7.dto.req.RegisterModel;

import java.security.Principal;
import java.util.Map;

public interface UserService {
    Map registerManual(RegisterModel objModel) ;

    Map registerByGoogle(RegisterModel objModel) ;

    Map getDetailProfile(Principal principal);
}
