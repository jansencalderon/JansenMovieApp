# JansenMovieApp

A sample app using MVVM, Realm, Retrofit, Dagger2, RxJava/Kotlin/Android and Clean Architecture.

Used MVVP for separation of concerns on layers of the app.
UI (Presentation), Domain and Data.
It also helps on handling lifecycle and orientation changes on Android.

Retrofit for network requests
Realm for local persistence, while room is easier to integrate, realm provides a query builder instead of using raw SQL

The App is also using Material Design Components, and supports Day and Night Theme (for now it depends on the device setting)

There are many room for improvements in the app such as proper errorHandling on request, making the layers pure reactive, but given the development time, it's the best i can do. 
