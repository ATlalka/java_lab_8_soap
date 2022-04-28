package pl.edu.pwr.classfinancemanager.soap.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.edu.pwr.classfinancemanager.data.entities.PaymentEntity;
import pl.edu.pwr.classfinancemanager.data.services.PaymentService;
import pl.edu.pwr.classfinancemanager.soap.Mapper;
import pl.edu.pwr.classfinancemanager.soap.generateddata.*;

@Endpoint
@RequiredArgsConstructor
public class PaymentsEndpoint {
    private static final String NAMESPACE_URI = "http://www.classfinancemanager.pwr.edu.pl/soap/generateddata";

    private final PaymentService paymentService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPaymentsRequest")
    @ResponsePayload
    public GetPaymentsResponse getPayments(@RequestPayload GetPaymentsRequest request) {
        GetPaymentsResponse response = new GetPaymentsResponse();
        for(PaymentEntity p : paymentService.getPayments()){
            response.getPaymentList().add(Mapper.mapPayment(p));
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createPaymentRequest")
    @ResponsePayload
    public GetPaymentsResponse createPayment(@RequestPayload CreatePaymentRequest request) {
        GetPaymentsResponse response = new GetPaymentsResponse();
        paymentService.createPayment(new String[]{
                request.getPayment().getPayDay(),
                Double.toString(request.getPayment().getAmount()),
                Long.toString(request.getPayment().getPersonId()),
                Long.toString(request.getPayment().getInstalmentId())
        });
        for(PaymentEntity p : paymentService.getPayments()){
            response.getPaymentList().add(Mapper.mapPayment(p));
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPaymentsByPersonRequest")
    @ResponsePayload
    public GetPaymentsResponse getPaymentsByPerson(@RequestPayload GetPaymentsByPersonRequest request) {
        GetPaymentsResponse response = new GetPaymentsResponse();
        for(PaymentEntity p : paymentService.getPaymentsByPerson(request.getPersonId())){
            response.getPaymentList().add(Mapper.mapPayment(p));
        }
        return response;
    }
}
