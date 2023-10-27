package org.beprojects.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.beprojects.model.Sell;

import java.math.BigDecimal;
import java.util.Properties;
import java.util.Random;

public class SellProducerService {

    private static Random random = new Random();
    private static long operationId = 0;
    private static BigDecimal ticketPrice = BigDecimal.valueOf(500);

    public static void main(String[] args) {

        Properties properties = new Properties();
        // WIP properties definition
        properties.put();

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        Sell sell = createSell();

    }

    private static Sell createSell() {
        long clientId = random.nextLong();
        int ticketAmount = random.nextInt(10);

        return new Sell(operationId++, clientId, ticketAmount, ticketPrice.multiply(BigDecimal.valueOf(ticketAmount)));
    }

}