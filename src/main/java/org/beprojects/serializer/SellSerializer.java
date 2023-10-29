package org.beprojects.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.beprojects.model.Sell;

public class SellSerializer implements Serializer<Sell> {

    @Override
    public byte[] serialize(String s, Sell sell) {
        try {
            return new ObjectMapper().writeValueAsBytes(sell);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
