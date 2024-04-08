package com.productdock.library.inventory.adapter.out.mongo;

import com.productdock.library.inventory.adapter.out.mongo.entity.BookSubscriptionEntity;
import com.productdock.library.inventory.adapter.out.mongo.mapper.BookSubscriptionEntityMapper;
import com.productdock.library.inventory.domain.BookSubscription;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.productdock.library.inventory.data.provider.domain.BookSubscriptionMother.bookSubscription;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookSubscriptionPersistenceAdapterShould {

    private static final String BOOK_ID = "1";

    private static final String USER_ID = "userEmail";

    private static final Optional<BookSubscriptionEntity> SUBSCRIPTION_ENTITY = Optional.of(mock(BookSubscriptionEntity.class));
    private static final BookSubscription SUBSCRIPTION = bookSubscription();

    @InjectMocks
    private BookSubscriptionPersistenceAdapter bookSubscriptionPersistenceAdapter;

    @Mock
    private BookSubscriptionRepository subscriptionRepository;

    @Mock
    private BookSubscriptionEntityMapper subscriptionMapper;

    @Test
    void findSubscriptionByBookIdAndUserId() {
        given(subscriptionRepository.findByBookIdAndUserId(BOOK_ID, USER_ID)).willReturn(SUBSCRIPTION_ENTITY);
        given(subscriptionMapper.toDomain(SUBSCRIPTION_ENTITY.get())).willReturn(SUBSCRIPTION);

        var subscriptions = bookSubscriptionPersistenceAdapter.findByBookIdAndUserId(BOOK_ID, USER_ID);

        assertThat(subscriptions).isEqualTo(Optional.of(SUBSCRIPTION));
    }

    @Test
    void saveSubscription() {
        given(subscriptionMapper.toEntity(SUBSCRIPTION)).willReturn(SUBSCRIPTION_ENTITY.get());

        bookSubscriptionPersistenceAdapter.save(SUBSCRIPTION);

        verify(subscriptionRepository).save(SUBSCRIPTION_ENTITY.get());
    }

    @Test
    void deleteBookSubscriptions() {

        bookSubscriptionPersistenceAdapter.delete(SUBSCRIPTION);

        verify(subscriptionRepository).deleteByBookIdAndUserId(BOOK_ID, USER_ID);
    }

}
