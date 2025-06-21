# 📱 Zalo Clone – Android App (Java + Firebase)

A **Zalo-inspired messaging app** built with **Java** and powered by **Firebase Realtime Database**, **Storage**, **FCM**, and **Vonage API**. The app supports real-time chat, video/voice calling, push notifications, and social features like friend requests via QR code.

> 🎬 **Video Demo**: [Watch on YouTube](https://www.youtube.com/watch?v=An8scMXZKJw)

---

## 🚀 Key Features

- 💬 **Real-time Chat**  
  Send and receive messages instantly using **Firebase Realtime Database**.

- 📞 **Video & Voice Calling**  
  Seamless calls powered by **Vonage API**.

- 🔔 **Push Notifications**  
  Receive message alerts via **FCM** integrated with **Retrofit2**.

- 🧠 **Google OAuth Login**  
  Secure sign-in with Google accounts.

- 🎙️ **Voice Messages**  
  Record and send audio messages in chat.

- 📷 **Friend Request via QR Code**  
  Add friends easily by scanning QR codes using **ZXing**.

- ✅ **Read Receipts & Online Status**  
  See when messages are read and who is currently online.

- 🌐 **RESTful API Integration**  
  For external data communication and future expansion.

---

## 🧪 Tech Stack

| Layer         | Technologies                             |
|---------------|------------------------------------------|
| **Language**  | Java                                     |
| **Database**  | Firebase Realtime Database               |
| **Storage**   | Firebase Storage                         |
| **Notifications** | Firebase Cloud Messaging (FCM) + Retrofit2 |
| **Video/Voice** | Vonage API (Video SDK)                  |
| **QR Scanner** | ZXing (QR code scanning)                |
| **Auth**      | Google Sign-In API (OAuth2)              |

---

## 📸 Screenshots

*(Optional: Add screenshots of your login, chat UI, call UI, QR scanning, etc.)*

---

## ⚙️ Getting Started

```bash
# 1. Clone the repository
git clone https://github.com/your-username/zalo-clone-android.git
cd zalo-clone-android

# 2. Open with Android Studio

# 3. Setup Firebase
- Create a Firebase project
- Add Android app package name
- Download `google-services.json` and place in `app/` folder

# 4. Enable Firebase Services
- Realtime Database
- Storage
- Firebase Authentication (Google)
- Cloud Messaging (FCM)

# 5. Vonage Setup
- Register on Vonage (https://vonage.com)
- Get your API key/secret
- Integrate into your call logic (see `CallActivity.java`)

# 6. Run App on Emulator or Real Device (API 23+)
```
