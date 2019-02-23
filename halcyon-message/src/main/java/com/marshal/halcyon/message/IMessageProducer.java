package com.marshal.halcyon.message;

public interface IMessageProducer {

    void produce(String queue, Object message);

}
