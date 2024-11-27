package org.example.server.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.server.grpc.GreeterGrpc;
import org.example.server.grpc.HelloWorldProto;

@GrpcService
public class HelloWorldService extends GreeterGrpc.GreeterImplBase {

  @Override
  public void sayHello(
      HelloWorldProto.HelloRequest request,
      StreamObserver<HelloWorldProto.HelloReply> responseObserver
  ) {
    String responseMessage = "Hello, %s!".formatted(request.getName());
    HelloWorldProto.HelloReply reply = HelloWorldProto.HelloReply.newBuilder()
        .setMessage(responseMessage)
        .build();

    responseObserver.onNext(reply);
    responseObserver.onCompleted();
  }
}
