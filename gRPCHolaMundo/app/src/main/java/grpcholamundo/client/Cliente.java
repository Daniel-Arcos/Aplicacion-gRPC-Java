package grpcholamundo.client;

import javax.swing.JOptionPane;

import com.proto.saludo.CalculadoraServiceGrpc;
import com.proto.saludo.CalculadoraServiceGrpc.CalculadoraServiceBlockingStub;
import com.proto.saludo.Holamundo.OperacionRequest;

// import com.proto.saludo.SaludoServiceGrpc;
// import com.proto.saludo.Holamundo.SaludoRequest;
// import com.proto.saludo.Holamundo.SaludoResponse;
// import com.proto.saludo.SaludoServiceGrpc.SaludoServiceBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 8080;

        ManagedChannel ch = ManagedChannelBuilder
            .forAddress(host, puerto)
            .usePlaintext()
            .build();
        
        // SaludoServiceBlockingStub stub = SaludoServiceGrpc.newBlockingStub(ch);

        // SaludoRequest peticion = SaludoRequest.newBuilder().setNombre("Daniel").build();

        // SaludoResponse respuesta = stub.saludo(peticion);

        // System.out.println("Respuesta RPC: " + respuesta.getResultado());

        CalculadoraServiceBlockingStub stubCalculadora = CalculadoraServiceGrpc.newBlockingStub(ch);
        
        while (true) {
            String opt = JOptionPane.showInputDialog(
                "Calcular\n" +
                    "Suma ---------------- (1)\n" + 
                    "Resta ----------------(2))\n" + 
                    "Multiplicacion ------ (3)\n" + 
                    "Division ------------ (4)\n" +
                    "Cancelar para salir"   
            );
            if (opt == null) {
                break;
            }

            int a = Integer.parseInt(JOptionPane.showInputDialog("Ingresa a"));
            
            int b = Integer.parseInt(JOptionPane.showInputDialog("Ingresa b"));
            
            OperacionRequest operacionRequest = OperacionRequest.newBuilder().setA(a).setB(b).build();
            switch (opt) {
                case "1":
                    JOptionPane.showMessageDialog(null, a + "+" + b + " = " + stubCalculadora.suma(operacionRequest).getResult());
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, a + "-" + b + " = " + stubCalculadora.resta(operacionRequest).getResult());
                    break;
                case "3":
                JOptionPane.showMessageDialog(null, a + "*" + b + " = " + stubCalculadora.multiplicacion(operacionRequest).getResult());
                    break;
                case "4":
                JOptionPane.showMessageDialog(null, a + "/" + b + " = " + stubCalculadora.division(operacionRequest).getResult());
                default:
                    break;
            }
        }

        System.out.println("Apagando...");
        ch.shutdown();
    }
}
