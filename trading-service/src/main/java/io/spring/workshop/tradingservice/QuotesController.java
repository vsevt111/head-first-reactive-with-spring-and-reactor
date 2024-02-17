package io.spring.workshop.tradingservice;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.*;

import static org.springframework.http.MediaType.*;

@RestController
public class QuotesController {
    @Autowired
    private QuotesClient quotesClient;

    @Autowired
    private TradingCompanyClient tradingCompanyClient;

    @GetMapping(value="/quotes/feed", produces = "text/event-stream")
    public Flux<Quote> getFeed(){
        return quotesClient.quotesFeed();
    }

    @GetMapping(value="/quotes/summary/{ticker}", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public Mono<TradingCompanySummary> tradingSummary(@PathVariable("ticker") String ticker){
        return tradingCompanyClient.getTradingCompany(ticker).zipWith(quotesClient.getLatestQuotes(ticker)
                ,TradingCompanySummary::new)
                //.map(objects -> new TradingCompanySummary(objects.getT1(),objects.getT2()))
                ;

    }

    @ExceptionHandler(TickerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleError(){

    }


}
