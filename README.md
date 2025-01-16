# Books App

A simple Android application that displays a list of books—demonstrating robust MVVM architecture and effective use of RxJava, Retrofit, and Hilt. This project is built using modern Android development practices including Jetpack Compose for the UI and Room for offline data handling.

## Overview

This Books App fulfills the following requirements:
- **Fetch data from the API:** Uses the OpenLibrary API ([API Documentation](https://openlibrary.org/developers/api)) to search for books.
- **Display books in a scrollable list:** Each item shows the book cover, title, and author.
- **Loading & error states:** The UI responds to loading and error states appropriately.
- **Book details in a bottom sheet:** Tapping a book opens a bottom sheet with detailed information.
- **Architecture:** Implements the MVVM architecture with a clean separation of concerns.
- **Technologies:** 
  - RxJava for asynchronous data flows.
  - Retrofit for network calls.
  - Hilt for dependency injection.
  - Jetpack Compose for building a responsive UI.
  - Room for local (offline-first) storage.

## Architecture & Project Structure

The project follows a layered architecture:
- **Data Layer:**
  - **Remote:** Retrofit API interface (`BooksApi`), DTOs, and repository implementation for fetching data remotely.
  - **Local:** Room entities (`BookEntity`), DAO (`BooksDao`), and the database.
  - **Mappers:** Functions to convert between remote DTOs, domain models, and local entities.
- **Domain Layer:**
  - **Model:** Core `Book` model.
  - **Repository Interface:** Defines methods for data retrieval.
  - **Use Case:** Business logic encapsulated in `GetBooksUseCase`.
- **UI Layer:**
  - **ViewModel:** Manages state (loading, success, error) and exposes it to the UI.
  - **Composables:** 
    - A `BooksListScreen` that displays a list of books.
    - List items using `BookItemRow` (now styled with Material 3 `ElevatedCard` with a thumbnail).
    - A `BookDetailsSheet` that appears as a bottom sheet for detailed view.
  - **Navigation:** Uses Jetpack Navigation with a sealed class (`BooksDestinations`) for routing.
- **Dependency Injection:**
  - Hilt is used for DI with appropriate modules (NetworkModule, DatabaseModule, RepositoryModule) and test replacements via `TestRepositoryModule`.

## Key Features

- **MVVM Architecture:** Separation of UI, domain, and data layers.
- **RxJava:** Asynchronous data handling with proper schedulers.
- **Retrofit & Room:** Remote data fetching and local caching (offline-first approach).
- **Hilt:** Simplified dependency injection across modules and tests.
- **Jetpack Compose:** Modern, reactive UI with Material 3 components for a clean design.
- **Testing:**
  - Unit tests for DAO, repository, and ViewModel using Mockito and RxJavaPlugins overrides.
  - Instrumented UI tests using Compose testing APIs and Hilt for dependency replacement.
  
## Getting Started

### Setup

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/mohammed-mayouf/books.git
   cd books
