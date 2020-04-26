# JansenMovieApp

A sample app using MVVM, Realm, Retrofit, Dagger2, RxJava/Kotlin/Android and Clean Architecture.

### Screenshots
<p>
<img src="https://user-images.githubusercontent.com/8930681/80291381-980ff380-877f-11ea-9be4-58d6d2a7276f.jpeg" alt="alt text" width="200">
<img src="https://user-images.githubusercontent.com/8930681/80291391-aa8a2d00-877f-11ea-99cc-48dfe86ef817.jpeg" alt="alt text" width="200">
<img src="https://user-images.githubusercontent.com/8930681/80291388-a2ca8880-877f-11ea-9c11-ec9336dd566a.jpeg" alt="alt text" width="200">
<img src="https://user-images.githubusercontent.com/8930681/80291390-a6f6a600-877f-11ea-84a3-149d02a555b9.jpeg" alt="alt text" width="200">
</p>

Used MVVP for separation of concerns on layers of the app.
UI (Presentation), Domain and Data.
It also helps on handling lifecycle and orientation changes on Android.

Retrofit for network requests
Realm for local persistence, while room is easier to integrate, realm provides a query builder instead of using raw SQL

The App is also using Material Design Components, and supports Day and Night Theme (for now it depends on the device setting)

There are many room for improvements in the app such as proper errorHandling on request, making the layers pure reactive, but given the development time, it's the best i can do. 
