package com.mirea.notifyservice;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class NotifyServiceApplication {

	private final ObservationRegistry observationRegistry;
	private final Tracer tracer;

	public static void main(String[] args) {
		SpringApplication.run(NotifyServiceApplication.class, args);
	}

	@KafkaListener(topics = "notificationTopic")
	public void handleNotification(OrderEvent orderEvent) {
		Observation.createNotStarted("on-message", this.observationRegistry).observe(() -> {
			log.info("Got message <{}>", orderEvent);
			log.info("TraceId- {}, Received Notification for Order - {}", this.tracer.currentSpan().context().traceId(),
					orderEvent.getOrderNumber());
		});
		log.info("Received Notification for Order - {}", orderEvent.getOrderNumber());
	}

}
