package io.spring.workshop.tradingservice;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.*;

import java.time.*;

import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON;

@Component
public class QuotesClient {
    @Autowired
    WebClient.Builder webClientBuilder;

    public Flux<Quote> quotesFeed(){
        return  webClientBuilder
//                .baseUrl("http://localhost:8081")
                .build()
                .get()
                .uri("http://localhost:8081/quotes")
                .accept(APPLICATION_STREAM_JSON)
                //.header("Content-Type","text/event-stream")
                .retrieve()
                .bodyToFlux(Quote.class);
    }

    public Mono<Quote> getLatestQuotes(String ticker){
        return quotesFeed().filter(quote -> quote.getTicker().equalsIgnoreCase(ticker))
                .next()
                .timeout(Duration.ofSeconds(15), Mono.just(new Quote(ticker)));
    }
}
