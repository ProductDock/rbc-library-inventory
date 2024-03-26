package com.productdock.library.inventory.adapter.out.mongo;

import com.productdock.library.inventory.adapter.out.mongo.entity.BookSubscriptionsEntity;
import com.productdock.library.inventory.adapter.out.mongo.mapper.BookSubscriptionsMapper;
import com.productdock.library.inventory.domain.BookSubscriptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookSubscriptionsPersistenceAdapterShould {

    private static final String BOOK_ID = "1";

    private static final Optional<BookSubscriptionsEntity> SUBSCRIPTIONS_ENTITY = Optional.of(mock(BookSubscriptionsEntity.class));
    private static final BookSubscriptions SUBSCRIPTIONS = mock(BookSubscriptions.class);

    @InjectMocks
    private BookSubscriptionsPersistenceAdapter bookSubscriptionsPersistenceAdapter;

    @Mock
    private BookSubscriptionsRepository subscriptionsRepository;

    @Mock
    private BookSubscriptionsMapper subscriptionsMapper;

    @Test
    void findSubscriptionsByBookId() {
        given(subscriptionsRepository.findByBookId(BOOK_ID)).willReturn(SUBSCRIPTIONS_ENTITY);
        given(subscriptionsMapper.toDomain(SUBSCRIPTIONS_ENTITY.get())).willReturn(SUBSCRIPTIONS);

        var subscriptions = bookSubscriptionsPersistenceAdapter.findByBookId(BOOK_ID);

        assertThat(subscriptions).isEqualTo(Optional.of(SUBSCRIPTIONS));
    }

    @Test
    void saveSubscriptions() {
        given(subscriptionsRepository.findByBookId(SUBSCRIPTIONS.getBookId())).willReturn(Optional.empty());
        given(subscriptionsMapper.toEntity(SUBSCRIPTIONS)).willReturn(SUBSCRIPTIONS_ENTITY.get());

        bookSubscriptionsPersistenceAdapter.save(SUBSCRIPTIONS);

        verify(subscriptionsRepository).save(SUBSCRIPTIONS_ENTITY.get());
    }

    @Test
    void deleteBookSubscriptions() {

        bookSubscriptionsPersistenceAdapter.deleteByBookId(BOOK_ID);

        verify(subscriptionsRepository).deleteByBookId(BOOK_ID);
    }

}
