package io.spring.workshop.stockdetails;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;
@Component
public class TradingCompanyCommandLineRunner implements CommandLineRunner {
    @Autowired
    private TradingCompanyRepository tradingCompanyRepository;
    @Override
    public void run(String... args) throws Exception {
        List<TradingCompany> companies = Arrays.asList(
                new TradingCompany("Pivotal Software", "PVTL"),
                new TradingCompany("Dell Technologies", "DELL"),
                new TradingCompany("Google", "GOOG"),
                new TradingCompany("Microsoft", "MSFT"),
                new TradingCompany("Oracle", "ORCL"),
                new TradingCompany("Red Hat", "RHT"),
                new TradingCompany("Vmware", "VMW")
        );
        tradingCompanyRepository.insert(companies).blockLast(Duration.ofSeconds(30));
    }
}
