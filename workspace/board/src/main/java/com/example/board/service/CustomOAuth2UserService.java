package com.example.board.service;

import com.example.board.domain.dto.UsersDTO;
import com.example.board.domain.oauth.CustomOAuth2User;
import com.example.board.domain.vo.UsersVO;
import com.example.board.mapper.UsersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UsersMapper usersMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 로그인이 된 객체를 oAuth2User를 저장
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String name = null;
        // 프로필 사진
        String profileUrl = null;
        // 입력한 카카오이메일 아이디
        String providerId = null;

        // OAuth2로부터 받은 사용자 정보
        // resultType이 map이기 때문에 map 에 저장
        Map<String, Object> attribute = oAuth2User.getAttributes();

        // application.properties 파일 안에서 id를 사용한다고 했음
        providerId = attribute.get("id").toString();

        // 세부적으로 한 번 더 들어가야된다.
        // 강제형변환 후 저장하는 것이 편하다.
        Map<String, Object> account = (Map<String, Object>)attribute.get("kakao_account");

        // 한 번 더 들어간다. (카카오로 로그인할 사용자 정보)
        Map<String, Object> info = (Map<String, Object>)account.get("profile");

        // 카카오로 로그인한 닉네임
        name = (String)info.get("nickname");
        // 카카오 프로필 이미지
        profileUrl = (String)info.get("profile_image_url");

        // 디비에 넣는 과정만 우리가 로직을 짜면 된다.
        UsersDTO loginUser = new UsersDTO();
        loginUser.setName(name);
        loginUser.setProviderId(providerId);
        loginUser.setProfilePic(profileUrl);
        loginUser.setProvider(userRequest.getClientRegistration().getRegistrationId()); // OAuth2 공급자의 이름

        // DB 저장 및 업데이트 로직
        UsersDTO existingUser = usersMapper.findByProviderId(providerId);

        // 우리 애플리케이션에 가입한 적이 없는 OAuth 계정
        if(existingUser == null){
            // insert
            loginUser.setRole("new");
            UsersVO user = UsersVO.toEntity(loginUser);
            usersMapper.saveUser(user);
        }
        // 가입 이력이 있다면, 정보 업데이트
        else {
            UsersVO user = UsersVO.toEntity(loginUser);
            usersMapper.updateUser(user);
        }
        // 시큐리티에 등록되면서 넘어가는 값
//        return oAuth2User;
        // 우리가 정한 양식으로 필요한 정보를 가져오기 위함
        // 생성자를 호출하여 객체 정보로 넘기자
        // getName()과 같이 편하게 정보를 가져오기 위해 커스텀을 한다고 생각
        return new CustomOAuth2User(oAuth2User, name, profileUrl, providerId);
    }
}