# Sports News

The Sports News is an Android Native Application that displays the latest sports news in chronological order by the most recent first.

This Android application uses the MVVM (Model-View-ViewModel) clean architecture with modules for data, domain and ui layers.

This App relies on several powerful libraries and technologies to provide an efficient and feature-rich user experience. Below are the key dependencies used in this project:

## Dependencies

[Android Jetpack Compose](https://developer.android.com/jetpack/compose) is a modern Android UI toolkit that simplifies and accelerates UI development on Android. In this application, Retrofit is used to connect to the GitHub API and retrieve user data based on search queries. It handles HTTP requests and responses seamlessly.

[Hilt](https://developer.android.com/training/dependency-injection/hilt-android) is a dependency injection library for Android that simplifies the management of dependencies in your Android app. It's built on top of Dagger and provides an easy and consistent way to inject dependencies into your Android components, such as Activities, Fragments, and ViewModels.

[Retrofit](https://square.github.io/retrofit/) is a popular open-source library in the Android development ecosystem. Its primary purpose is to simplify the process of making network requests in Android applications. Retrofit turns your HTTP API into a Java interface, making defining and handling web service calls easier.

[Moshi](https://github.com/square/moshi) is a JSON parsing library commonly used in Android app development where JSON processing is required. It is designed to simplify the process of converting JSON data into Java objects and vice versa.



The code written for this project was from my coding experience with importance given to the overall modular project structure which follows the MVVM Clean Architecture guide from Android.

This project was built using references from the below links

[Guide to app architecture](https://developer.android.com/topic/architecture)

[Now in Android App](https://github.com/android/nowinandroid)



### ToDo

- Overall UI/UX can be improved.
- More tests can be written to increase the overall test coverage along with UI testing for compose.
- Project modules can be made more refined.
- Hardcode string can be moved to string.xml
- Mode code comments can be added.
- Adding build flavors and configuring proguard for production build
- Need to handle various screen sizes for tablet and horizontal screen orientations

