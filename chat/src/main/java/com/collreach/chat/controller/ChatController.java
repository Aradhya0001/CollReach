package com.collreach.chat.controller;

import com.collreach.chat.model.request.UserLoginRequest;
import com.collreach.chat.model.response.TestResponse;
import com.collreach.chat.model.response.UserLoginResponse;
import com.collreach.chat.model.response.UserNameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class ChatController {

    @Autowired
    RestTemplate restTemplate;


    @GetMapping(path = "/test")
    public List<String> test(){
        return Arrays.asList("Ayush", "Ankit", "Aakash", "Arpit");
    }

    @RequestMapping(value = "/get-user-details", method = RequestMethod.GET)
    //public String getUserDetails(@RequestBody UserLoginRequest userLoginRequest) {
    public ResponseEntity<UserLoginResponse> getUserDetails() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODIyY3MxMDQ5IiwiZXhwIjoxNjE5NzA4NjcxLCJpYXQiOjE2MTg4NDQ2NzF9.GBcuS0vHRObN2y_kbUs0eQcJdOqkq_1tno4Z3FiMTOpNGniujorRGPYuT6wn1V3B9OMaAurRRB6MS2J0a8geEw");
        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setUserName("1822cs1049");
        userLoginRequest.setPassword("Ayush@123");
        HttpEntity<UserLoginRequest> entity = new HttpEntity<UserLoginRequest>(userLoginRequest,headers);

        try {
            return ResponseEntity.ok()
                                  .body(restTemplate.exchange("http://userprofile/user/login",
                                          HttpMethod.POST, entity, UserLoginResponse.class).getBody());
             /*
            TestResponse testResponse =  new TestResponse();
            testResponse.setName("Ayush Choudhary");
            testResponse.setUsername("1822cs1049");

            UserNameResponse userNameResponse1 = new UserNameResponse();
            userNameResponse1.setUserName("ccsdfg344");

            UserNameResponse userNameResponse2 = new UserNameResponse();
            userNameResponse2.setUserName("akfgsg379");

            testResponse.setList(Arrays.asList(userNameResponse1, userNameResponse2));
            return ResponseEntity.ok().body(testResponse);*/
        }catch(Exception e){
            System.out.println("Some Error occurred." + e);
            return ResponseEntity.ok().body(new UserLoginResponse());
        }
    }
}
