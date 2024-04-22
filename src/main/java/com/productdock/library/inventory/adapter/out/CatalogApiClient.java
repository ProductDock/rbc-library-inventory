package com.productdock.library.inventory.adapter.out;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.productdock.library.inventory.application.port.out.web.CatalogClient;
import com.productdock.library.inventory.domain.BookDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
@Component
public class CatalogApiClient implements CatalogClient {

    private String catalogServiceUrl;
    private HttpClient client = HttpClient.newHttpClient();
    private ObjectMapper objectMapper = new ObjectMapper();

    public CatalogApiClient(@Value("${catalog.service.url}/api/catalog/books/") String catalogServiceUrl) {
        this.catalogServiceUrl = catalogServiceUrl;
    }

    @Override
    public BookDetails getBookDetails(String bookId) throws IOException, InterruptedException {
        var uri = new DefaultUriBuilderFactory(catalogServiceUrl)
                .builder()
                .path(bookId)
                .build();
        var request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objectMapper.readValue(response.body(), BookDetails.class);
    }
}
