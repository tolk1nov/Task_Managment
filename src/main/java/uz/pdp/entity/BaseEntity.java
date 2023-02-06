package uz.pdp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.*;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;


import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
public abstract class BaseEntity {
    {
        id = UUID.randomUUID();
        createdDate = LocalDateTime.now();
        updateDate = LocalDateTime.now();
    }
    protected UUID id;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    protected LocalDateTime createdDate;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    protected LocalDateTime updateDate;
}
