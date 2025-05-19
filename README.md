#  Finance Tracker - Microservices Based Application

This project is a **Spring Boot Microservices-based Finance Tracker** application that allows users to manage transactions, generate reports, and monitor system metrics. The application includes end-to-end DevOps pipeline integration with Docker, Jenkins, Kubernetes, Prometheus, and Grafana.

---

##  Features

-  User registration & management
-  Transaction CRUD operations with category tagging
-  Generate downloadable Excel-based financial reports
-  Centralized routing via API Gateway
-  Swagger UI documentation for all services
-  CI/CD with Jenkins pipeline
-  Dockerized microservices
- ️ Kubernetes deployment with NodePort
-  Monitoring with Prometheus & Grafana

---

##  Tech Stack

| Category          | Tech Used                          |
|-------------------|------------------------------------|
| Backend           | Java 17, Spring Boot               |
| Microservices     | Spring Cloud, Spring Data JPA      |
| API Documentation | SpringDoc OpenAPI + Swagger UI     |
| Build Tool        | Maven                              |
| Containerization  | Docker, DockerHub                  |
| Orchestration     | Kubernetes (Minikube, NodePort)    |
| CI/CD             | Jenkins, Ansible                   |
| Monitoring        | Prometheus, Grafana                |

---

## ️ Microservices Overview

1. **User Service** – manages user profiles
2. **Transaction Service** – handles income/expense tracking
3. **Report Service** – generates downloadable Excel reports
4. **API Gateway** – central access point for routing requests

---

##  Running the Project


```bash
docker-compose up --build

minikube start

kubectl apply -f k8s/

minikube service api-gateway --url
minikube service prometheus --url
minikube service grafana --url
```
Prometheus and Grafana dashboards are used for real-time metrics and health monitoring.

Prometheus scrapes metrics from /actuator/prometheus of each microservice.

Grafana visualizes those metrics using Prometheus as a data source.

