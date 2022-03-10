package com.wdw.maj.controller;

import com.wdw.maj.dto.AccessTokenDto;
import com.wdw.maj.dto.GithubUser;
import com.wdw.maj.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    /**
     *Autowired:自动把spring容器内的写好的实例化的类，加载到我当前使用的上下文
     *Value:读取配置文件application.properties中对应的内容，注入到当前变量中
     */
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.url}")
    private String redirectUrl;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                            @RequestParam(name = "state")String state){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_url(redirectUrl);
        accessTokenDto.setState(state);
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getLogin());
        return "index";
    }
}
