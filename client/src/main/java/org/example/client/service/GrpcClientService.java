package org.example.client.service;

import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.client.grpc.GreeterGrpc;
import org.example.client.grpc.HelloWorldProto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GrpcClientService {

  @GrpcClient("myGrpcService")
  private GreeterGrpc.GreeterBlockingStub greeterBlockingStub;

  public String sayHello(String name) {
    var request = HelloWorldProto.HelloRequest.newBuilder()
        .setName(name)
        .build();

    var reply = greeterBlockingStub.sayHello(request);
    return reply.getMessage();
  }

  @Scheduled(fixedRate = 5000)
  public void reportCurrentTime() {
    log.info("Result is: {}", sayHello("Username"));
  }
}
