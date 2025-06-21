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
- Integrate into your call logic (see `VideoChatActivity.java`)

# 6. Run App on Emulator or Real Device (API 23+)
```

---
## 📁 Project Structure (Simplified)
```
📁 app/
├── 📁 manifests/
├── 📁 sampledata/
├── 📁 java/
│   └── 📁 com.example.chatapp/
│       ├── 📁 Adapter/
│       │   ├── FriendAdapter.java
│       │   ├── ListFriendRequestAdapter.java
│       │   ├── MessageAdapter.java
│       │   └── UserAdapter.java
│       ├── 📁 Fragments/
│       │   ├── ChatsFragment.java
│       │   ├── FriendsFragment.java
│       │   ├── ProfileFragment.java
│       │   ├── QrCodeFragment.java
│       │   └── UsersFragment.java
│       ├── 📁 Model/
│       │   ├── Chat.java
│       │   └── User.java
│       ├── 📁 Notification/
│       │   ├── APIService.java
│       │   ├── Client.java
│       │   ├── Data.java
│       │   ├── MyFirebaseMessagingService.java
│       │   ├── MyResponse.java
│       │   ├── Sender.java
│       │   └── Token.java
│       ├── CallingActivity.java
│       ├── CaptureAct.java
│       ├── ForgetPasswordView.java
│       ├── HelperClass.java
│       ├── ListFriendRequestActivity.java
│       ├── LoginByPhoneView.java
│       ├── LoginView.java
│       ├── MainActivity.java
│       ├── MessageActivity.java
│       ├── MicRecorderActivity.java
│       ├── ProfileUserViewActivity.java
│       ├── RegisterView.java
│       ├── SplashActivity.java
│       ├── StartActivity.java
│       ├── TEST.java
│       └── VideoChatActivity.java

📁 res/
├── 📁 drawable/
├── 📁 layout/
│   ├── activity_calling.xml
│   ├── activity_forget_password_view.xml
│   ├── activity_list_friend_request.xml
│   ├── activity_login_by_phone_view.xml
│   ├── activity_login_screen.xml
│   ├── activity_login_view.xml
│   ├── activity_main.xml
│   ├── activity_message.xml
│   ├── activity_mic_recorder.xml
│   ├── activity_profile_user_view.xml
│   ├── activity_register_view.xml
│   ├── activity_splash.xml
│   ├── activity_start.xml
│   ├── activity_test.xml
│   ├── activity_video_chat.xml
│   ├── chat_item_left.xml
│   ├── chat_item_right.xml
│   ├── fragment_chats.xml
│   ├── fragment_friends.xml
│   ├── fragment_profile.xml
│   ├── fragment_qr_code.xml
│   ├── fragment_users.xml
│   ├── friend_item.xml
│   ├── friend_request_item.xml
│   └── user_item.xml
├── 📁 menu/
│   ├── menu_main.xml
│   └── menu_message.xml

```
---
### 🔐 Permissions Required
```
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.RECORD_AUDIO" />
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
```
---

