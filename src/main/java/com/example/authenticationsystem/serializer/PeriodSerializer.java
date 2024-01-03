package com.example.authenticationsystem.serializer;


import com.example.authenticationsystem.entity.Period;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class PeriodSerializer extends StdSerializer<Period> {
    protected PeriodSerializer(Class<Period> t) {
        super(t);
    }

    @Override
    public void serialize(Period period, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", period.getId());
        jgen.writeEndObject();
    }
}
