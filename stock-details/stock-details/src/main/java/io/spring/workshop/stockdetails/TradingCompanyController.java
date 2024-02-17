package io.spring.workshop.stockdetails;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.*;

@RestController
public class TradingCompanyController {
    @Autowired
    private TradingCompanyRepository tradingCompanyRepository;

    @GetMapping(value="/details",produces =  MediaType.APPLICATION_JSON_VALUE)
    public Flux<TradingCompany> findAll(){
        return tradingCompanyRepository.findAll();
    }

    @GetMapping(value="/detail/{ticker}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TradingCompany> findTradingCompany(@PathVariable("ticker") String ticker){
        return tradingCompanyRepository.findByTicker(ticker);
    }
}
