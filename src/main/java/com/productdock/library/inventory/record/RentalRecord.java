package com.productdock.library.inventory.record;

import com.productdock.library.inventory.book.BookInteraction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RentalRecord {
    @Id
    private String bookId;
    private List<BookInteraction> reservations;
    private List<BookInteraction> rents;

    public void removeLastReservation() {
        reservations.remove(reservations.size()-1);
    }

    public void removeLastRent() {
        rents.remove(rents.size()-1);
    }
}
