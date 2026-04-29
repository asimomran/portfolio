
# 📦 Cell-Based Architecture using Kubernetes

## 🚀 Project Overview

This project demonstrates the implementation of a **Cell-Based Architecture** using **Kubernetes (kind)** deployed on an **AWS EC2 instance**.

The goal is to design a system where applications are divided into **independent cells (namespaces)**, each containing its own services and database, ensuring **isolation, scalability, and fault tolerance**.

In this project, a **Mumbai cell** is fully deployed and active, while another cell (`rest`) is created but intentionally kept inactive to demonstrate **resource management and cell isolation**.

---

## 🧠 Architecture Concept

Cell-Based Architecture is a design pattern where:

* The system is divided into **independent units (cells)**
* Each cell contains its own:

  * Application services
  * Database
* Failure in one cell does **not affect other cells**

In this project:

* Each **Kubernetes namespace = one cell**
* Each cell is isolated and can be scaled independently

---

## 🛠️ Technologies Used

* **AWS EC2** – Cloud server hosting the system
* **Docker** – Container runtime
* **Kubernetes (kind)** – Cluster setup inside EC2
* **NGINX Ingress Controller** – Traffic routing
* **Spring Boot** – Order Service backend
* **MySQL** – Database for each cell
* **kubectl** – Kubernetes CLI

---

## 🏗️ System Architecture

```
Client (Postman / Browser)
        ↓
EC2 Public IP : 8080
        ↓
Port Forwarding
        ↓
Ingress Controller (NGINX)
        ↓
Path: /mumbai
        ↓
Mumbai Namespace (Cell)
   ├── Order Service (Spring Boot)
   └── MySQL Database
```

---

## ⚙️ Kubernetes Implementation

The following components were created:

### 🔹 Cluster

* Kubernetes cluster using **kind**
* 1 control-plane + 1 worker node

### 🔹 Namespaces (Cells)

* `mumbai` → Active cell
* `rest` → Inactive cell (for demonstration)

### 🔹 Deployments

* MySQL Deployment (mumbai)
* Order Service Deployment (mumbai)

### 🔹 Services

* ClusterIP service for MySQL
* ClusterIP service for Order Service

### 🔹 Ingress

* Path-based routing using NGINX Ingress
* Route:

  ```
  /mumbai → order-service
  ```

---

## ✨ Features

* ✅ Cell-based isolation using namespaces
* ✅ Independent database per cell
* ✅ Path-based routing using ingress
* ✅ External access using port-forward
* ✅ Resource-aware architecture (inactive cell for optimization)

---

## 🔄 How It Works

1. Client sends request to EC2 Public IP
2. Request reaches **port-forwarded port (8080)**
3. Traffic is forwarded to **Ingress Controller**
4. Ingress checks path `/mumbai`
5. Routes request to **order-service (mumbai namespace)**
6. Order Service processes request
7. Data is stored/retrieved from **MySQL (same cell)**
8. Response is sent back to client

---

## 📡 API Endpoints

### 🔹 Get Orders

```
GET /mumbai/orders
```

### 🔹 Create Order

```
POST /mumbai/orders
```

Example JSON:

```json
{
  "city": "mumbai",
  "product": "laptop",
  "quantity": 2
}
```

---

## ▶️ How to Run the Project

### 1. Setup EC2

* Launch Ubuntu EC2 instance

### 2. Install Dependencies

* Docker
* kubectl
* kind

### 3. Create Cluster

```bash
kind create cluster --name cell-cluster
```

### 4. Create Namespaces

```bash
kubectl create namespace mumbai
kubectl create namespace rest
```

### 5. Deploy Mumbai Cell

```bash
kubectl apply -f mysql-mumbai.yml
kubectl apply -f order-mumbai.yml
```

### 6. Install Ingress

```bash
kubectl apply -f ingress-nginx
```

### 7. Apply Ingress Rule

```bash
kubectl apply -f mumbai-ingress.yml
```

### 8. Port Forward Ingress

```bash
kubectl port-forward -n ingress-nginx svc/ingress-nginx-controller 8080:80 --address 0.0.0.0
```

### 9. Test

```
http://EC2_PUBLIC_IP:8080/mumbai/orders
```

---

## 🎯 Demonstration

* Insert data using POST request
* Retrieve data using GET request
* Show MySQL data inside mumbai namespace
* Demonstrate that `rest` namespace is inactive
* Explain how ingress routes traffic

---

## ⚠️ Challenges Faced

* ❌ EC2 resource limitations (CPU exhaustion)
* ❌ NodePort not accessible externally
* ❌ Ingress path mismatch issues

### ✅ Solutions

* Used **port-forwarding for ingress**
* Scaled down unused cells
* Simplified ingress routing

---

## 📚 Learning Outcomes

* Practical understanding of Kubernetes architecture
* Namespace-based isolation (cell design)
* Ingress-based routing
* Debugging real-world cloud issues
* Resource management in distributed systems

---

## 🔮 Future Improvements

* Add multiple active cells (Delhi, etc.)
* Use **LoadBalancer** instead of port-forward
* Implement **API Gateway logic** for dynamic routing
* Add monitoring (Prometheus, Grafana)

---

## 🏁 Conclusion

This project successfully demonstrates how **Cell-Based Architecture** can be implemented using Kubernetes.

It highlights:

* Scalability
* Fault isolation
* Cloud-native design principles

The system reflects real-world distributed architectures used in modern cloud applications.

---

🔥 Done. This is a **strong, professional README**.

---

If you want next:

* I can **shorten this for viva explanation (2–3 mins)**
* Or help you create **architecture diagram for submission**

Just tell me 👍
