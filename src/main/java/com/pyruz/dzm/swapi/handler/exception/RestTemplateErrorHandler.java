package com.pyruz.dzm.swapi.handler.exception;

import lombok.NonNull;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
public class RestTemplateErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(@NonNull ClientHttpResponse  clientHttpResponse) {
        return true;
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        if (!clientHttpResponse.getStatusCode().is2xxSuccessful()) {
            String newLine = System.getProperty("line.separator");
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientHttpResponse.getBody(), StandardCharsets.UTF_8));
            StringBuilder result = new StringBuilder();
            String line;
            boolean flag = false;
            while ((line = reader.readLine()) != null) {
                result.append(flag ? newLine : "").append(line);
                flag = true;
            }
            throw new HttpClientErrorException(clientHttpResponse.getStatusCode(), result.toString());
        }
    }
}
