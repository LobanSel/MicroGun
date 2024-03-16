package com.mirea.orderservice.service;

import brave.Tracer;
import com.mirea.orderservice.dto.ArsenalResponse;
import com.mirea.orderservice.dto.OrderLineItemsDto;
import com.mirea.orderservice.dto.OrderRequest;
import com.mirea.orderservice.model.Order;
import com.mirea.orderservice.model.OrderLineItems;
import com.mirea.orderservice.repo.OrderRepository;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    private final ObservationRegistry observationRegistry;
    private final ApplicationEventPublisher applicationEventPublisher;

    public String createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> gunCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getGunCode)
                .toList();

        Observation arsenalServiceObservation = Observation.createNotStarted("arsenal-service-lookup",
                this.observationRegistry);
        arsenalServiceObservation.lowCardinalityKeyValue("call", "arsenal-service");

        return arsenalServiceObservation.observe(() -> {
            ArsenalResponse[] arsenalResponsesArray = webClientBuilder.build().get()
                    .uri("http://localhost:8082/api/v1/arsenal",
                            uriBuilder -> uriBuilder.queryParam("gunCode", gunCodes).build())
                    .retrieve()
                    .bodyToMono(ArsenalResponse[].class)
                    .block();
            boolean allGunsAlreadyGet = Arrays.stream(arsenalResponsesArray)
                    .allMatch(ArsenalResponse::isAlreadyGet);
            if (allGunsAlreadyGet) {
                orderRepository.save(order);
                return "Заказ одобрен";
            } else {
                throw new IllegalArgumentException("Ствола нет в арсенале");
            }
        });
    }

    private OrderLineItems mapToDto (OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setGunCode(orderLineItemsDto.getGunCode());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        return orderLineItems;
    }
}
