package com.marshal.halcyon.message;

public interface IMessageConsumer {

    void consume(String queue);

}
