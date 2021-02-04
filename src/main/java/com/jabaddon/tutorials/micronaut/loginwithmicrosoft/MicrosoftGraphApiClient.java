package com.jabaddon.tutorials.micronaut.loginwithmicrosoft;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.uri.UriBuilder;
import io.reactivex.Flowable;
import io.reactivex.Single;

import javax.inject.Singleton;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

@Singleton
public class MicrosoftGraphApiClient {
    private final RxHttpClient httpClient;
    private final URI uri;

    public MicrosoftGraphApiClient() throws MalformedURLException {
        httpClient = RxHttpClient.create(new URL("https://graph.microsoft.com"));
        uri = UriBuilder.of("/v1.0/me").build();
    }

    Single<MicrosoftUser> myProfile(String authorization) {
        MutableHttpRequest<?> req = HttpRequest.GET(uri);
        req.bearerAuth(authorization);
        Flowable<MicrosoftUser> flowable = httpClient.retrieve(req, Argument.of(MicrosoftUser.class));
        return flowable.singleOrError();
    }
}
