package com.binar.Batch7.Config;

import lombok.Data;
import org.springframework.stereotype.Component;



@Data
@Component
public class Config {

    public  static String APP_NAME ="name";

    public static  String yourName(){
        return "riki";
    }
}

