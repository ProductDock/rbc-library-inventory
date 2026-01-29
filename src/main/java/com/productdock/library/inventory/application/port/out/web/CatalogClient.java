package com.productdock.library.inventory.application.port.out.web;

import com.productdock.library.inventory.domain.BookDetails;

import java.io.IOException;

public interface CatalogClient {

    BookDetails getBookDetails(String bookId) throws IOException, InterruptedException;

}
