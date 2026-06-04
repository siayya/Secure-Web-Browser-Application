# Secure Web Browser Application

## Overview

Secure Web Browser Application is an Android application built using Kotlin. The application allows users to sign in using Google Authentication, enter a website URL, and browse websites inside the application using WebView. It also provides browsing history and logout functionality.

---

# Project Setup Steps

### Requirements

* Android Studio
* Android SDK 24+
* Firebase Project
* Google Services JSON file

### Setup

1. Clone the repository.
2. Open the project in Android Studio.
3. Add the `google-services.json` file to the app folder.
4. Sync Gradle files.
5. Run the application on an emulator or Android device.

---

# Firebase Setup Explanation

Firebase Authentication is used for Google Sign-In.

### Steps Followed

1. Created a Firebase project.
2. Added Android application package name.
3. Downloaded and added `google-services.json`.
4. Enabled Google Authentication in Firebase Console.
5. Added Firebase and Google Sign-In dependencies.
6. Implemented login and logout functionality.

### Authentication Flow

* User clicks Sign In.
* Google account picker opens.
* User selects an account.
* Firebase authenticates the user.
* User is redirected to the Home Screen.
* Logout signs out the user and returns to Sign In Screen.

---

# Architecture Explanation

The application consists of four main screens:

### SignInScreen

Handles Google Authentication using Firebase.

### HomeScreen

Allows users to:

* Enter website URLs
* Open websites
* Access browsing history
* Logout

### WebViewScreen

Responsible for:

* Loading websites
* Showing loading progress
* Handling invalid URLs
* Back navigation

### HistoryScreen

Displays previously visited URLs and allows users to clear history.

---

# Database Schema Explanation

Due to time constraints, browsing history is currently stored using SharedPreferences.

### Stored Data

* Website URL
* Visit timestamp

The structure can later be migrated to Room Database with:

* id
* url
* title
* visitCount
* lastVisitedTime

---

# Notification Flow Explanation

Notification functionality was planned for implementation.

The intended flow was:

1. Check if notification was already shown for the day.
2. Create notification channel.
3. Display:

Title: Welcome Back

Message: Thanks for opening the app

4. Prevent duplicate notifications on the same day.

---

# WebView Lifecycle Handling Explanation

### Implemented Features

* JavaScript enabled
* DOM Storage enabled
* Loading progress indicator
* URL loading inside WebView
* Error handling for invalid URLs
* Back navigation support

### Navigation Flow

* If WebView can go back → open previous page.
* Otherwise → close WebView screen and return to Home Screen.

### Error Handling

If a URL is invalid or internet is unavailable, an error message is displayed to the user.

---

# Challenges Faced

### Firebase Configuration

Configuring Google Authentication and resolving sign-in issues.

### WebView Handling

Ensuring websites open inside the application instead of launching Chrome.

### History Management

Implementing browsing history storage and retrieval.

### UI Development

Managing layouts, ViewBinding, and screen navigation.

---

# Future Improvements

* Complete Room Database integration
* Website title tracking
* Visit count tracking
* Daily welcome notifications
* Dark mode support
* Better history management
* Bookmark functionality
* Screen rotation state restoration

---

# Conclusion

This project demonstrates Android application development using Kotlin, Firebase Authentication, WebView integration, local data storage, and user session management. The application provides a simple and secure browsing experience while serving as a foundation for future enhancements.
