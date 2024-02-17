package io.spring.workshop.tradingservice;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.*;

@Component
public class TradingCompanyClient {
    @Autowired
    private WebClient.Builder builder;

    public Flux<TradingCompany> findAllCompanies() {
        return builder.baseUrl("http://localhost:8082").build()

                .get()
                .uri("/details")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(TradingCompany.class);
    }

    public Mono<TradingCompany> getTradingCompany(String ticker) {
        return builder.baseUrl("http://localhost:8082").build()
                .get()
                .uri("/detail/{ticker}", ticker)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(TradingCompany.class)
                .switchIfEmpty(Mono.error(TickerNotFoundException::new));
    }
}
