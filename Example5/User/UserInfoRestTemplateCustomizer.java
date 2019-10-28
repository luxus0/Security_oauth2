package spring_boot.spring_boot.Security.Example5.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.header.Header;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class UserInfoRestTemplateCustomizer {

    @Autowired
    private OAuth2RestTemplate oAuth2RestTemplate;
    private OAuth2AccessToken accessToken;

    public UserInfoRestTemplateCustomizer(){}

    public UserInfoRestTemplateCustomizer(OAuth2RestTemplate restTemplate)
    {

        this.oAuth2RestTemplate = restTemplate;


        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(MediaType.APPLICATION_ATOM_XML);
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        mediaTypeList.add(MediaType.APPLICATION_JSON_UTF8);
        mediaTypeList.add(MediaType.APPLICATION_PDF);
        mediaTypeList.add(MediaType.APPLICATION_XHTML_XML);
        mediaTypeList.add(MediaType.APPLICATION_XML);


        Header header =new Header("Accept_charset","utf-16,");

        Map<Header,String> map = new HashMap<>();
        map.put(header,"HeaderOption");

        for(String values : header.getValues())
        {
            System.out.println(values);
        }

        ResponseEntity response = new ResponseEntity(HttpStatus.OK);
        response.getHeaders().setAccept(mediaTypeList);
        HttpEntity entity= new HttpEntity(response.getBody());


        System.out.println("Headers: " +entity.getHeaders());
        System.out.println("Body: " +entity.getBody());


        restTemplate.exchange("http://localhost:8090/rest",HttpMethod.GET,entity,UserInfoRestTemplateCustomizer.class);

    }


    }
