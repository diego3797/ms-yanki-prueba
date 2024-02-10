package com.prueba.yanki.kafka.consumer;

import com.prueba.yanki.service.TransactionService;
import com.prueba.yanki.util.Constante;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;


/**
 * .
 * <b>Class</b>: KafkaConsumer <br/>
 *
 * <u>Service Provider</u>: PruebaTest <br/>
 * <u>Developed by</u>: Diego Condezo <br/>
 * <u>Changes:</u><br/>
 * <ul>
 *   <li>
 *     Febrero 08, 2024 Creación de Clase.
 *   </li>
 * </ul>
 */
@Configuration
public class KafkaConsumer {

  @Autowired
  TransactionService transacService;
  private Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

  /**
   * a.
   *
   */
  @KafkaListener(topics = {Constante.TOPIC_TRANSACTION_YANKI}, groupId = "my-group-id")
  public void consumerTransaction(String message) throws IOException {

    logger.info(String.format("Received message transaction: %s", message));

    transacService.registerPay(message);
  }

  /**
   * a.
   *
   */
  @KafkaListener(topics = {Constante.TOPIC_WALLET_YANKI}, groupId = "my-group-id")
  public void consumerWallet(String message) throws IOException {
    logger.info(String.format("Received message wallet:  %s", message));

    transacService.registerWallet(message);
  }
}
