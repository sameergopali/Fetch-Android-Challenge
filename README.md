
# Fetch Challenge - Android


## Table of Contents
1. [Task](#task)
2. [How to Run](#how-to-run)
3. [About Project ](#test-api)
5. [Next Steps](#next-steps)
6. [Demo](#demo)

## Task
Display this list of items to the user based on the following requirements:
- Display all the items grouped by "listId"
- Sort the results first by "listId" then by "name" when displaying.
- Filter out any items where "name" is blank or null.
The final result should be displayed to the user in an easy-to-read list.


## How to Run
- Install latest version of Android Studio (#https://developer.android.com/studio)
- Clone repository
    ```sh
    git clone https://github.com/sameergopali/Fetch-Android-Challenge.git
    ```
- Import the project: File->Open
- Sync Gradle (File-> Sync Project with Gradle File)
- Run application: (Configure emulator or connected device to use at least API 34)
You can also run Unit tests and Instrumentation test



## About Project
The project is built upon the Model-View-ViewModel (MVVM) architectural pattern. 
For networking, it uses Retrofit to handle api request and Jetpack Compose for the UI framework. 
The downloaded JSON is filtered for non null names and is sorted based on  first listId and then by name. While sorting the name field
it is done through lexicographical ordering. So, "Item 123" comes before "Item 2".
Data is presented in a user-friendly LazyColumn with sticky headers. Each header displays the current list group 
and includes buttons for  scrolling to the  start of previous and next groups.

## Next Steps:
Some enhancement that could be done include implementing persistent caching using Room to support offline mode, allowing users to access data without an internet connection.
Dependency injection framework could also be integrated to improve code maintainability and adding more tests for more coverage and various use cases.

## Demo:
![Project Demo](https://github.com/sameergopali/Fetch-Android-Challenge/blob/master/demo/recording.gif)
