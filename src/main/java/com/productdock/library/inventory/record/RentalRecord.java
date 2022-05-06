package com.productdock.library.inventory.record;

import com.productdock.library.inventory.book.BookInteraction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RentalRecord implements Serializable {

    @Id
    private String bookId;
    private List<BookInteraction> rentalRecords;

    public int getRentsCount(){
        return (int) rentalRecords.stream().filter(book -> book.getStatus().equals(RentalStatus.RENTED)).count();
    }

    public int getReservationsCount(){
        return (int) rentalRecords.stream().filter(book -> book.getStatus().equals(RentalStatus.RESERVED)).count();
    }
}
