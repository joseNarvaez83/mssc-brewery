package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.ConsumerDto;

import java.util.UUID;

public interface ConsumerService {
    ConsumerDto getConsumerById(UUID consumerId);

    ConsumerDto saveNewConsumer(ConsumerDto consumerDto);

    void updateConsumer(UUID consumerId, ConsumerDto consumerDto);

    void deleteById(UUID consumerId);
}
