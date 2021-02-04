package com.jabaddon.tutorials.micronaut.loginwithmicrosoft;

import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.core.async.publisher.Publishers;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.UserDetails;
import io.micronaut.security.oauth2.endpoint.authorization.state.State;
import io.micronaut.security.oauth2.endpoint.token.response.OauthUserDetailsMapper;
import io.micronaut.security.oauth2.endpoint.token.response.TokenResponse;
import io.reactivex.Single;
import org.reactivestreams.Publisher;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Collections;
import java.util.Map;

@Named("microsoft")
@Singleton
public class UserDetailsMapper implements OauthUserDetailsMapper {
    private final MicrosoftGraphApiClient microsoftGraphApiClient;

    public UserDetailsMapper(MicrosoftGraphApiClient microsoftGraphApiClient) {
        this.microsoftGraphApiClient = microsoftGraphApiClient;
    }

    @Override
    public Publisher<AuthenticationResponse> createAuthenticationResponse(TokenResponse tokenResponse,
                                                                          @Nullable State state) {
        Single<MicrosoftUser> microsoftUserSingle = microsoftGraphApiClient.myProfile(tokenResponse.getAccessToken());
        MicrosoftUser microsoftUser = microsoftUserSingle.blockingGet();
        return Publishers.just(
                new UserDetails(
                        microsoftUser.getUserPrincipalName(),
                        Collections.singletonList("user"),
                        Map.of(
                                OauthUserDetailsMapper.ACCESS_TOKEN_KEY, tokenResponse.getAccessToken(),
                                "user", microsoftUser
                        )
                )
        );
    }

    @Override
    public Publisher<UserDetails> createUserDetails(TokenResponse tokenResponse) {
       return null;
    }
}
