package org.beprojects.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.beprojects.model.Sell;
import org.beprojects.serializer.SellSerializer;

import java.math.BigDecimal;
import java.util.Properties;
import java.util.Random;

public class SellProducerService {

    private static Random random = new Random();
    private static long operationId = 0;
    private static BigDecimal ticketPrice = BigDecimal.valueOf(500);

    private static final String TOPIC_NAME = "ticket-sell";

    public static void main(String[] args) throws InterruptedException {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, SellSerializer.class.getName());

        try (KafkaProducer<String, Sell> producer = new KafkaProducer<String, Sell>(properties);){

            while (true){
                Sell sell = createSell();

                ProducerRecord<String, Sell> record = new ProducerRecord<String, Sell>(TOPIC_NAME, createSell());
                producer.send(record);
                Thread.sleep(500);
            }

        }
    }

    private static Sell createSell() {
        long clientId = random.nextLong();
        int ticketAmount = random.nextInt(10);

        return new Sell(operationId++, clientId, ticketAmount, ticketPrice.multiply(BigDecimal.valueOf(ticketAmount)));
    }

}