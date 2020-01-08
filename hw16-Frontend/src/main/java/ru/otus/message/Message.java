package ru.otus.message;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

//ВАЖНО!
//Чтобы успешно обмениваться между компонентами объектами класса,
// то он (класс) должен удовлетворять следующим требованиям в реалзиации каждой из компонент:
//  1) быть сериализуемым, т.е. реализовывать интерфейс java.io.Serializable;
//  2) иметь тоже название (в нашем случае Message);
//  3) находиться в том же пакете в каждой из компонент (в нашем случае ru.otus.message)
//  4) иметь serialVersionUID с УНИКАЛНЫМ ЗНАЧЕНИЕМ (в нашем случае 555L).
//      Желательно, чтобы значение было отличным от 1L, т.к. это самое распространненное зхначение.

public class Message implements Serializable {

    private static final long serialVersionUID = 555L;
    public static final Message VOID_MESSAGE = new Message();

    private final UUID id = UUID.randomUUID();
    private final String from;
    private final String to;
    private final UUID sourceMessageId;
    private final String type;
    private final int payloadLength;
    private final byte[] payload;

    private Message() {
        this.from = null;
        this.to = null;
        this.sourceMessageId = null;
        this.type = "voidTechnicalMessage";
        this.payload = new byte[1];
        this.payloadLength = payload.length;
    }

    public Message(String from, String to, UUID sourceMessageId, String type, byte[] payload) {
        this.from = from;
        this.to = to;
        this.sourceMessageId = sourceMessageId;
        this.type = type;
        this.payloadLength = payload.length;
        this.payload = payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", sourceMessageId=" + sourceMessageId +
                ", type='" + type + '\'' +
                ", payloadLength=" + payloadLength +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getType() {
        return type;
    }

    public byte[] getPayload() {
        return payload;
    }

    public int getPayloadLength() {
        return payloadLength;
    }

    public UUID getSourceMessageId() {
        return sourceMessageId;
    }
}
