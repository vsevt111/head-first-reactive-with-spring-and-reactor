package io.spring.workshop.stockdetails;

import org.springframework.data.mongodb.repository.*;
import reactor.core.publisher.*;

public interface TradingCompanyRepository extends ReactiveMongoRepository<TradingCompany,String> {
    Mono<TradingCompany> findByTicker(String ticker);
}
