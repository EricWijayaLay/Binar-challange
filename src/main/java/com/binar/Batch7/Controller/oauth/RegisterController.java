package com.binar.Batch7.Controller.oauth;

import com.binar.Batch7.Service.Email.EmailSender;
import com.binar.Batch7.Utils.EmailTemplate;
import com.binar.Batch7.Utils.SimpleStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

import com.binar.Batch7.Repository.oauth.UserRepository;
import com.binar.Batch7.Service.oauth.UserService;
import com.binar.Batch7.Utils.Response;
import com.binar.Batch7.dto.req.RegisterModel;
import com.binar.Batch7.Entity.oauth.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/v1/user-register")
public class RegisterController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final Response responseTemplate;

    @Autowired
    public RegisterController(UserRepository userRepository, UserService userService, Response responseTemplate) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.responseTemplate = responseTemplate;
    }
    @Autowired
    public UserService serviceReq;

    @Autowired
    public EmailTemplate emailTemplate;
    @Autowired
    public EmailSender emailSender;

    @Value("${expired.token.password.minute:}")//FILE_SHOW_RUL
    private int expiredToken;

    @PostMapping("/register-google")
    public ResponseEntity<Map> saveRegisterByGoogle(@Valid @RequestBody RegisterModel objModel) throws RuntimeException {
        Map map = new HashMap();

        User user = userRepository.checkExistingEmail(objModel.getUsername());
        if (null != user) {
            return new ResponseEntity<Map>(responseTemplate.error("Username sudah ada",""), HttpStatus.OK);

        }
        map = serviceReq.registerByGoogle(objModel);
        //gunanya send email
        Map mapRegister =  sendEmailegister(objModel);
        return new ResponseEntity<Map>(mapRegister, HttpStatus.OK);

    }
    @PostMapping("/send-otp")//send OTP
    public Map sendEmailegister(
            @RequestBody RegisterModel user) {
        String message = "Thanks, please check your email for activation.";

        if (user.getUsername() == null) return responseTemplate.sukses("No email provided");
        User found = userRepository.findOneByUsername(user.getUsername());
        if (found == null) return responseTemplate.error("Email not found",""); //throw new BadRequest("Email not found");

        String template = emailTemplate.getRegisterTemplate();
        if (StringUtils.isEmpty(found.getOtp())) {
            User search;
            String otp;
            do {
                otp = SimpleStringUtils.randomString(6, true);
                search = userRepository.findOneByOTP(otp);
            } while (search != null);
            Date dateNow = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateNow);
            calendar.add(Calendar.MINUTE, expiredToken);
            Date expirationDate = calendar.getTime();

            found.setOtp(otp);
            found.setOtpExpiredDate(expirationDate);
            template = template.replaceAll("\\{\\{USERNAME}}", (found.getFullname() == null ? found.getUsername() : found.getFullname()));
            template = template.replaceAll("\\{\\{VERIFY_TOKEN}}",  otp);
            userRepository.save(found);
        } else {
            template = template.replaceAll("\\{\\{USERNAME}}", (found.getFullname() == null ? found.getUsername() : found.getFullname()));
            template = template.replaceAll("\\{\\{VERIFY_TOKEN}}",  found.getOtp());
        }
        emailSender.sendAsync(found.getUsername(), "Register", template);
        return responseTemplate.sukses(message);
    }




    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> saveRegisterManual(@Valid @RequestBody RegisterModel objModel) {
        Map<String, Object> map;

        User user = userRepository.checkExistingEmail(objModel.getEmail().toLowerCase());
        if (user != null) {
            map = responseTemplate.error("Username sudah ada", HttpStatus.OK);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }

        map = userService.registerManual(objModel);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @Value("${BASEURL:}")//FILE_SHOW_RUL
    private String BASEURL;

    @PostMapping("/send-otp-tymeleaf")//send OTP
    public Map sendEmailegisterTymeleaf(@RequestBody RegisterModel user) {
        String message = "Thanks, please check your email for activation.";

        if (user.getUsername() == null) return responseTemplate.error("No email provided","");
        User found = userRepository.findOneByUsername(user.getUsername());
        if (found == null) return responseTemplate.error("Email not found",""); //throw new BadRequest("Email not found");

        String template = emailTemplate.getRegisterTemplate();
        if (StringUtils.isEmpty(found.getOtp())) {
            User search;
            String otp;
            do {
                otp = SimpleStringUtils.randomString(6, true);
                search = userRepository.findOneByOTP(otp);
            } while (search != null);
            Date dateNow = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateNow);
            calendar.add(Calendar.MINUTE, expiredToken);
            Date expirationDate = calendar.getTime();

            found.setOtp(otp);
            found.setOtpExpiredDate(expirationDate);
            template = template.replaceAll("\\{\\{USERNAME}}", (found.getFullname() == null ? found.getUsername() : found.getFullname()));
            template = template.replaceAll("\\{\\{VERIFY_TOKEN}}", BASEURL + "/v1/user-register/web/index/"+ otp);
            userRepository.save(found);
        } else {
            template = template.replaceAll("\\{\\{USERNAME}}", (found.getFullname() == null ? found.getUsername() : found.getFullname()));
            template = template.replaceAll("\\{\\{VERIFY_TOKEN}}", BASEURL + "/v1/user-register/web/index/"+  found.getOtp());
        }
        emailSender.sendAsync(found.getUsername(), "Register", template);
        return responseTemplate.sukses(message);
    }

    @PostMapping("/register-google-tymeleaf")
    public ResponseEntity<Map> saveRegisterByGoogleTyemeleaf(@Valid @RequestBody RegisterModel objModel) throws RuntimeException {
        Map map = new HashMap();

        User user = userRepository.checkExistingEmail(objModel.getUsername());
        if (null != user) {
            return new ResponseEntity<Map>(responseTemplate.error("Username sudah ada",""), HttpStatus.OK);

        }
        map = serviceReq.registerByGoogle(objModel);
        //gunanya send email
        Map mapRegister =  sendEmailegisterTymeleaf(objModel);
        return new ResponseEntity<Map>(mapRegister, HttpStatus.OK);
    }

}
