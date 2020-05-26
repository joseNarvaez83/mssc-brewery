package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.ConsumerService;
import guru.springframework.msscbrewery.web.model.ConsumerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/api/v1/consumer")
@RestController
public class ConsumerController {

    private ConsumerService consumerService;

    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @GetMapping
    @RequestMapping("/{consumerId}")
    public ResponseEntity<ConsumerDto> getConsumer(@PathVariable("consumerId") UUID consumerId) {
        return new ResponseEntity<>(consumerService.getConsumerById(consumerId), HttpStatus.OK);
    }
}
