# Location Tracker using Apache Kafka and Spring Boot

This project is a real-time location tracking system built using **Apache Kafka** and **Spring Boot**, consisting of two microservices:

- **Producer Microservice**: Simulates delivery location updates.
- **Consumer Microservice**: Listens for those updates and processes them.

---

## 🌐 Tech Stack
- Java 17+
- Spring Boot 3.x
- Apache Kafka 3.x
- Spring Kafka
- Maven

---

## ⚙️ Kafka Setup
Make sure Apache Kafka and ZooKeeper are running:

```bash
# Start ZooKeeper
bin/zookeeper-server-start.sh config/zookeeper.properties

# Start Kafka Server
bin/kafka-server-start.sh config/server.properties
```

If you're using Windows:
```cmd
bin\windows\zookeeper-server-start.bat config\zookeeper.properties
bin\windows\kafka-server-start.bat config\server.properties
```

---

## 🚚 Producer Microservice
**Path**: `/location/update`

### Features:
- Exposes a POST endpoint
- Generates random (x, y) coordinates
- Publishes the location to Kafka topic `location-update-topic`

### Sample Request:
```http
POST http://localhost:8080/location/update
```

### Kafka Producer Code Snippet:
```java
@PostMapping("/update")
public ResponseEntity<?> updateLocation() {
    kafkaService.updateLocation("(" + Math.round(Math.random() * 100) + " , " + Math.round(Math.random() * 100) + ")");
    return ResponseEntity.ok(Map.of("message", "LocationUpdated"));
}
```

---

## 👀 Consumer Microservice
**Listens to**: `location-update-topic`

### Features:
- Uses `@KafkaListener` to consume messages
- Logs each incoming location update

### Kafka Consumer Code Snippet:
```java
@KafkaListener(topics = AppConstatnt.LOCATION_UPDATE_TOPIC, groupId = AppConstatnt.GROUP_ID)
public void updatedLocation(String value) {
    System.out.println(value);
}
```

---

## 📚 Constants (Shared)
```java
public class AppConstatnt {
    public static final String LOCATION_UPDATE_TOPIC = "location-update-topic";
    public static final String GROUP_ID = "deliveryboy-group";
}
```

---

## 🔄 How It Works (Flow)
1. Start Kafka and ZooKeeper.
2. Start the Producer microservice.
3. Start the Consumer microservice.
4. Hit `/location/update` endpoint.
5. Watch location updates print in the consumer's console.

---

## 📄 Future Improvements
- Add database integration in the consumer.
- Add a front-end map to visualize locations.
- Containerize with Docker Compose.
- Use JSON payloads for better structure.
