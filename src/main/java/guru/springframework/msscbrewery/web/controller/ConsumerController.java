package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.ConsumerService;
import guru.springframework.msscbrewery.web.model.ConsumerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<ConsumerDto> handlePost(@RequestBody ConsumerDto consumerDto) {
        ConsumerDto savedDto = consumerService.saveNewConsumer(consumerDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/consumer/" + savedDto.getId().toString());

        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{consumerId}")
    public ResponseEntity<ConsumerDto> handlePut(@PathVariable("consumerId") UUID consumerId, @RequestBody ConsumerDto consumerDto) {
        consumerService.updateConsumer(consumerId, consumerDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{consumerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsumer(@PathVariable("consumerId") UUID consumerId) {

        consumerService.deleteById(consumerId);
    }
}
