This is the statful ai infrastruture using aws.

# 🧠 Stateful Personal AI Agent (Serverless, Cloud-Based)

## 🚀 Overview

This project is a **Stateful Personal AI Assistant** built using a serverless cloud architecture on AWS.
Unlike traditional chatbots, this system **remembers user information across sessions and devices**, enabling **personalized, evolving interactions**.

---

## 🎯 Key Features

* 🌍 **Accessible from anywhere** (phone, laptop, any device)
* 🧠 **Persistent Memory (S3)** – remembers user preferences and identity
* ⚡ **Serverless Architecture** – powered by AWS Lambda
* 🔗 **API-based Access** – via API Gateway
* 🎯 **Personalized Responses** – adapts based on stored memory
* 💡 **Lightweight & Scalable** – no server management required

---

## 🏗️ Architecture

```
User (Phone/Laptop)
        ↓
API Gateway (Public Endpoint)
        ↓
AWS Lambda (AI Orchestrator)
        ↓
Amazon S3 (Memory Storage)
        ↓
OpenRouter API (LLM)
```

---

## 🧠 How It Works

### 1. User sends a message

* Through Postman / frontend / any HTTP client

### 2. API Gateway receives request

* Routes request to Lambda function

### 3. Lambda processes request

* Loads memory from S3
* Extracts important user information
* Builds a personalized prompt

### 4. AI (LLM) generates response

* Uses OpenRouter API

### 5. Memory is updated

* Stored back into S3

### 6. Response returned to user

---

## 🧩 Tech Stack

* **AWS Lambda** – Backend compute
* **Amazon API Gateway** – Public API access
* **Amazon S3** – Persistent memory storage
* **OpenRouter API** – LLM access
* **Python (requests, boto3)** – Backend logic

---

## 📂 Project Structure

```
project/
│
├── lambda_function.py   # Main backend logic
├── memory.json          # Chat history (stored in S3)
├── long_memory.json     # User memory (stored in S3)
└── README.md
```

---

## ⚙️ Setup Guide (Step-by-Step)

---

### 🔹 Step 1: Create OpenRouter API Key

1. Go to: https://openrouter.ai
2. Generate API key
3. Save it securely

---

### 🔹 Step 2: Create AWS S3 Bucket

1. Go to AWS Console → S3
2. Click **Create Bucket**
3. Use a unique name (e.g., `ai-agent-storage-bucket`)
4. Keep default settings

---

### 🔹 Step 3: Create AWS Lambda Function

1. Go to AWS → Lambda
2. Click **Create Function**
3. Runtime: Python 3.x
4. Upload your `lambda_function.py`

---

### 🔹 Step 4: Set Environment Variable

In Lambda:

```
OPENROUTER_API_KEY = your_api_key_here
```

---

### 🔹 Step 5: Add S3 Permissions

1. Go to Lambda → Configuration → Permissions
2. Open execution role
3. Attach policy:

```
AmazonS3FullAccess
```

---

### 🔹 Step 6: Deploy API Gateway

1. Go to API Gateway
2. Create **HTTP API**
3. Add integration → Lambda
4. Create route:

```
POST /chat
```

5. Deploy → stage: `dev`

---

### 🔹 Step 7: Get API URL

```
https://xxxx.execute-api.region.amazonaws.com/chat
```

---

## 🧪 Testing the System

Use Postman:

### Request:

```
POST /chat
```

```json
{
  "message": "I am a networking student"
}
```

---

## 🎯 Demo Scenarios

---

### ✅ Test 1: Identity Memory

```
User: I am a networking student
User: Explain TCP
```

👉 Output:

```
"As a networking student..."
```

---

### ✅ Test 2: Preference Memory

```
User: I like simple explanations
User: Explain DNS
```

👉 Output:

```
"Here is a simple explanation..."
```

---

### ✅ Test 3: Persistence

* Send message
* Close Postman
* Send new request

👉 Memory is still retained

---

### ✅ Test 4: Cross-device

* Send from laptop
* Send from phone

👉 Same memory used

---

## 🧠 Memory System

### Short-Term Memory

* Last 10–20 messages
* Maintains conversation context

### Long-Term Memory

* Stored in S3
* Includes:

  * Preferences
  * Identity
  * Interests

---

## ⚠️ Challenges Faced

* API rate limits (OpenRouter)
* Lambda timeouts
* Read-only filesystem issue
* Handling API failures gracefully

---

## ✅ Solutions Implemented

* Timeout handling
* Fallback responses
* S3-based persistent storage
* Memory filtering (last 5 relevant entries)

---

## 🚀 Future Improvements

* 🔍 Vector Search (RAG)
* 🧠 Semantic memory (embeddings)
* 🗃️ OpenSearch integration
* 📱 Frontend UI (chat interface)
* 🔐 Authentication system
* 🧩 Multi-user support

---

## 🏆 Key Learnings

* Serverless architecture design
* Cloud-based memory systems
* API orchestration
* Handling real-world failures
* Building scalable AI systems

---

## 💡 Conclusion

This project demonstrates how to build a **personalized AI assistant with persistent memory**, using modern cloud technologies.

It moves beyond traditional chatbots by enabling:

```
Stateless AI ❌ → Stateful Personal AI ✅
```

---

## 📌 Author

Built as part of an advanced cloud + AI system project.

---

## ⭐ If you found this useful

Give it a star ⭐ and explore further improvements!
