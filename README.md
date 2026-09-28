# 📝 Notes App

## Overview

The **Notes App** is a two-screen Android application built using **Kotlin**, **Jetpack Compose**, **Room**, and **MVVM architecture**.

The application allows users to:

- Create notes
- View saved notes
- Edit existing notes
- Delete notes

All notes are stored locally on the device using the **Room database**, providing persistent storage even after the application is closed.

This project fulfills the requirements of the **Mobile App module** and demonstrates modern Android development practices, including clean architecture, database persistence, reactive state management, and declarative UI design.

---

## 🛠️ Development Environment

The application was developed using:

- **Android Studio Hedgehog**
- **Kotlin 1.9+**
- **Jetpack Compose**
- **Room Database**
- **Material 3**
- **Navigation Compose**
- **StateFlow**
- **Pixel 6 / Pixel 7 Emulator**
- **Android API 34**

---

## ✨ Features

- Create new notes
- Edit existing notes
- Delete notes
- View notes in a scrollable list
- Persistent on-device storage using Room
- Material 3 UI styling
- MVVM architecture
- Repository pattern for data abstraction
- Reactive UI updates using StateFlow
- Navigation between the notes list and note editor screens

---

## 🏗️ Architecture Overview

The application follows an **MVVM (Model-View-ViewModel)** architecture with separate data, domain, presentation, and UI responsibilities.

### 1. Data Layer

The data layer manages local database storage using **Room**.

Main components:

- `NoteEntity` — Represents a note stored in the Room database.
- `NoteDao` — Defines database operations such as insert, update, delete, and retrieve.
- `NoteDatabase` — Provides the Room database instance.

---

### 2. Domain Layer

The domain layer keeps the application's business models separate from the database implementation.

Main components:

- `Note` — Domain model used by the UI and ViewModels.
- `toDomain()` — Converts a Room `NoteEntity` into a domain `Note`.
- `toEntity()` — Converts a domain `Note` into a Room `NoteEntity`.
- `NoteRepository` — Provides an abstraction between the ViewModels and the data layer.

The mapping functions help prevent the UI from depending directly on Room database entities.

---

### 3. Presentation Layer

The presentation layer manages application state and connects the UI to the repository.

Main components:

- `NotesListViewModel` — Manages the notes displayed on the list screen.
- `NoteEditorViewModel` — Manages note creation and editing.
- `StateFlow` — Provides reactive state updates to the Compose UI.

When application data changes, `StateFlow` allows the UI to automatically react and update.

---

### 4. UI Layer

The UI is built entirely using **Jetpack Compose** and **Material 3** components.

Main components:

- Notes List Screen
- Note Editor Screen
- `NoteCard` component
- Material 3 theme
- Navigation Compose

---

## 📱 Screens

### Notes List Screen

The **Notes List Screen** displays all saved notes in a scrollable list.

Users can:

- View saved notes
- Add a new note
- Tap a note to edit it
- Delete a note

---

### Note Editor Screen

The **Note Editor Screen** is used to create new notes and modify existing ones.

Users can:

- Enter a note title
- Enter note content
- Save the note
- Edit an existing note

---

## 💾 Data Persistence

The application uses **Room** for local data persistence.

Notes are stored directly on the device, meaning they remain available after:

- Navigating between screens
- Closing the application
- Restarting the application

Room provides a structured SQLite-based storage system while integrating naturally with Kotlin coroutines and Flow.

---

## 🔄 Application Data Flow

```text
User Action
    │
    ▼
Jetpack Compose UI
    │
    ▼
ViewModel
    │
    ▼
Repository
    │
    ▼
Room DAO
    │
    ▼
Room Database
```

Database changes flow back through the application:

```text
Room Database
    │
    ▼
Flow / StateFlow
    │
    ▼
ViewModel
    │
    ▼
Jetpack Compose UI
```

This allows the interface to automatically update whenever the stored notes change.

---

## 🎥 Video Demonstration

A video demonstration of the application can be found here:

**Demo Video:**  
`https://youtu.be/iU-OKSce9qQ`

---

## 📌 Summary

The Notes App demonstrates several important Android development concepts:

- Kotlin
- Jetpack Compose
- Room database persistence
- MVVM architecture
- Repository pattern
- StateFlow
- Compose Navigation
- Material 3
- Reactive UI design
- Separation of concerns

The result is a simple, maintainable notes application that demonstrates modern Android application development practices.