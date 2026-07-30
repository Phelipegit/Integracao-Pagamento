package PhelipeProject.Integracao_Pagamento.service;

import PhelipeProject.Integracao_Pagamento.dto.ApiResponse.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class CreateOrderService {

    @Value("${URL_API_MERCADOPAGO}")
    private String URL_API;

    public RabbitComponent rabbitComponent;

    public CreateOrderService(RabbitComponent rabbitComponent) {
        this.rabbitComponent = rabbitComponent;
    }

    public void createOrder() throws URISyntaxException, IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpRequest httpRequest = HttpRequest.newBuilder().uri(new URI(URL_API + "orders")).POST(HttpRequest.BodyPublishers.ofString(null)).timeout(Duration.ofMinutes(5)).build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

    }
}
