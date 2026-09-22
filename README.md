# TokenGate

TokenGate is a high-performance Rate Limiting service built with Spring Boot and Redis. It implements the **Token Bucket algorithm** to manage API request limits across distributed systems.

## Features
- **Distributed Rate Limiting**: Uses Redis as a centralized state store.
- **Token Bucket Algorithm**: Supports bursty traffic while maintaining a steady refill rate.
- **Fail-Open Resilience**: Gracefully handles Redis downtime by allowing requests if the connection is lost.
- **API Documentation**: Integrated with Swagger/OpenAPI.
- **Containerized**: Ready for Docker and Kubernetes deployment.

## Tech Stack
- **Java 25** (Builds on JDK 21+ compatible)
- **Spring Boot 4.0.7**
- **Spring Data Redis**
- **Lombok**
- **Docker & Kubernetes**

## Prerequisites
- JDK 25
- Maven 3.9+
- Docker & Kubernetes (minikube/kind for local testing)

## Getting Started

### Local Development
1. **Start Redis**:
   ```sh
   docker run --name redis -p 6379:6379 -d redis
   ```
2. **Build and Run**:
   ```sh
   ./mvnw clean spring-boot:run
   ```

### Container Deployment
1. **Build Docker Image**:
   ```sh
   docker build -t tokengate:latest .
   ```
2. **Run with Docker Compose**:
   ```sh
   docker compose up --build
   ```
   This will build the `tokengate` image (if not already built) and start both the `redis` and `tokengate` services.

### Kubernetes Deployment
1. **Deploy Redis**:
   ```sh
   kubectl apply -f k8s/redis.yaml
   ```
2. **Deploy TokenGate**:
   ```sh
   kubectl apply -f k8s/app.yaml
   ```
3. **Access the Service**:
   ```sh
   kubectl get svc tokengate-service
   ```

## Configuration
Settings can be overridden via environment variables:
- `SPRING_DATA_REDIS_HOST`: Redis server hostname (default: `localhost`).
- `RATELIMIT_CAPACITY`: Max tokens in bucket (default: `10`).
- `RATELIMIT_REFILL_RATE_PER_SEC`: Tokens added per second (default: `0.5`).

## API Usage

### Check Rate Limit
Checks if a specific client is allowed to proceed.

**Endpoint:** `POST /api/v1/ratelimit/check`

**Example Request:**
```sh
curl -X POST "http://localhost:8080/api/v1/ratelimit/check?clientId=user123"
```

**Example Response:**
```json
{
  "allowed": true,
  "remainingTokens": 9
}
```

## Documentation
- **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **OpenAPI JSON**: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

## Resilience Strategy
TokenGate implements a **fail-open** strategy. If Redis is unreachable, it logs the error and returns `allowed: true` with `remainingTokens: -1` to ensure service availability under degraded conditions.

## Contributing
Contributions are always welcome! Please feel free to fork the repository and submit a Pull Request.
