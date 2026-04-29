This is the project of private cloud.

# ☁️ Private Cloud Storage using Nextcloud + Docker + Cloudflare Tunnel

## 📌 Project Overview

This project demonstrates how to build a **self-hosted private cloud storage system** using containerized infrastructure.
The system allows users to upload, access, and manage files from anywhere in the world through a secure tunnel without exposing local ports.

The architecture uses:

* **Nextcloud** as the application layer
* **MariaDB** as the database layer
* **Docker Compose** for orchestration
* **Cloudflare Tunnel** for secure public access

---

## 🏗️ Architecture

```
User (Browser / Mobile)
        ↓
Cloudflare Edge (HTTPS)
        ↓
Cloudflare Tunnel
        ↓
Local Machine (Kali Linux)
        ↓
Nextcloud Container
        ↓
MariaDB Container
        ↓
Local Storage (Bind Mount)
```

---

## ⚙️ Technologies Used

* Docker
* Docker Compose
* Nextcloud
* MariaDB
* Cloudflare Tunnel (Quick Tunnel)
* Linux (Kali)

---

## 🚀 Features

* 🌐 Access files globally without public IP
* 🔒 Secure HTTPS via Cloudflare
* 📦 Containerized multi-service architecture
* 💾 Persistent storage using bind mounts
* 🔁 Easy restart and reproducibility
* 📁 Real-time file visibility on local system

---

## 📂 Project Structure

```
nextcloud/
 ├── docker-compose.yml
 ├── nextcloud_data/
 ├── db/
 ├── .gitignore
 └── README.md
```

---

## ⚠️ Important Notes

* Do NOT upload `nextcloud_data/` or `db/` folders to GitHub
* These contain user data and database files
* Add them to `.gitignore`

Example `.gitignore`:

```
nextcloud_data/
db/
```

---

## 🛠️ Setup Instructions

### 1️⃣ Clone Repository

```bash
git clone https://github.com/YOUR_USERNAME/private-cloud-nextcloud.git
cd private-cloud-nextcloud
```

---

### 2️⃣ Start Containers

```bash
docker-compose up -d
```

Check running containers:

```bash
docker ps
```

---

### 3️⃣ Access Nextcloud Locally

Open:

```
http://localhost:8080
```

Create admin account and complete setup.

---

### 4️⃣ Start Cloudflare Tunnel

```bash
docker run --rm -it --network host cloudflare/cloudflared:latest tunnel --no-autoupdate --url http://localhost:8080
```

You will get a public URL like:

```
https://random-name.trycloudflare.com
```

---

### 5️⃣ Add Trusted Domain

```bash
docker exec -u www-data nextcloud-app php occ config:system:set trusted_domains 1 --value=YOUR_DOMAIN
```

Example:

```bash
docker exec -u www-data nextcloud-app php occ config:system:set trusted_domains 1 --value=random-name.trycloudflare.com
```

---

### 6️⃣ Access Globally

Open the Cloudflare URL on another device (mobile data recommended).

Login and use your cloud storage.

---

## 🔄 Restart Procedure (After Reboot)

```bash
sudo systemctl start docker
cd ~/Downloads/privatecloud/nextcloud
docker-compose up -d
```

Start tunnel again:

```bash
docker run --rm -it --network host cloudflare/cloudflared:latest tunnel --no-autoupdate --url http://localhost:8080
```

Add new domain again using `occ`.

---

## 📁 File Storage Location (Bind Mount)

Uploaded files are stored locally at:

```
nextcloud_data/data/<username>/files/
```

Example:

```
nextcloud_data/data/asim/files/
```

---

## 🧠 Key Concepts Demonstrated

* Container orchestration using Docker Compose
* Service separation (App + Database)
* Persistent storage using bind mounts
* Reverse tunneling (Cloudflare Tunnel)
* Secure remote access without port forwarding
* Trusted domain validation in Nextcloud
* Stateless vs Stateful system design

---

## ⚠️ Limitations

* Quick Tunnel URL changes on restart
* Requires manual trusted domain update
* Not suitable for large file uploads (Cloudflare limits)
* Not production-ready (for learning/demo purposes only)

---

## 🎯 Conclusion

This project demonstrates how modern cloud systems can be built using:

* Containerized infrastructure
* Secure tunneling instead of exposing ports
* Persistent storage for real-world data handling

It provides hands-on experience with **cloud architecture, networking, and DevOps practices**.

---

## 🙌 Author

**asim**
Computer Science (Networks) Student
Cloud & Infrastructure Enthusiast
