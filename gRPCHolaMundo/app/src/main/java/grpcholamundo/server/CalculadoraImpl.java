package grpcholamundo.server;

import com.proto.saludo.CalculadoraServiceGrpc.CalculadoraServiceImplBase;
import com.proto.saludo.Holamundo.OperacionRequest;
import com.proto.saludo.Holamundo.OperacionResponse;

import io.grpc.stub.StreamObserver;

public class CalculadoraImpl extends CalculadoraServiceImplBase {

    @Override
    public void division(OperacionRequest request, StreamObserver<OperacionResponse> responseObserver) {
        double result = request.getA() / request.getB();
        OperacionResponse response = OperacionResponse.newBuilder().setResult(result).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void multiplicacion(OperacionRequest request, StreamObserver<OperacionResponse> responseObserver) {
        double result = request.getA() * request.getB();
        OperacionResponse response = OperacionResponse.newBuilder().setResult(result).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void resta(OperacionRequest request, StreamObserver<OperacionResponse> responseObserver) {
        double result = request.getA() - request.getB();
        OperacionResponse response = OperacionResponse.newBuilder().setResult(result).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void suma(OperacionRequest request, StreamObserver<OperacionResponse> responseObserver) {
        double result = request.getA() + request.getB();
        OperacionResponse response = OperacionResponse.newBuilder().setResult(result).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    
}
