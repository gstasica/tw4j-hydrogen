package com.tw4j.hydrogen.source;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.ImmutableList;
import com.tw4j.hydrogen.auth.OAuth;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.BasicClient;
import com.twitter.hbc.httpclient.auth.Authentication;

public class DataPump {

  public void pump() throws InterruptedException {
    Authentication auth = OAuth.authentication();
    
    BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10000);

    StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
    endpoint.trackTerms(ImmutableList.of("java"));

    BasicClient client =
        new ClientBuilder().name("tw4j-hydrogen").hosts(Constants.STREAM_HOST)
            .endpoint(endpoint).authentication(auth).processor(new StringDelimitedProcessor(queue))
            .build();

    // Establish a connection
    client.connect();
    
    for (int msgRead = 0; msgRead < 1000; msgRead++) {
      if (client.isDone()) {
        System.out.println("Client connection closed unexpectedly: "
            + client.getExitEvent().getMessage());
        break;
      }

      String msg = queue.poll(5, TimeUnit.SECONDS);
      if (msg == null) {
        System.out.println("Did not receive a message in 5 seconds");
      } else {
        System.out.println(msg);
      }
    }

    client.stop();
  }
}
