# Summary for retrofit

### What
**Retrofit** is a type-safe HTTP client for Android and Java.

### Why
Using Retrofit made networking easier in Android apps. As it has many features like easy to add custom headers and request types, file uploads, mocking responses, etc through which we can reduce boilerplate code in our apps and consume the web service easily.

### How
To work with Retrofit we basically need the following three classes:
1. A model class which is used as a JSON model
2. An interface that defines the HTTP operations (GET, POST, etc.) needs to be performed
3. Retrofit.Builder class: Instance which uses the interface defined above and the Builder API to allow defining the URL endpoint for the HTTP operations. It also takes the converters we provide to format the Response.

### Reference

- https://medium.com/mindorks/understand-how-does-retrofit-work-c9e264131f4a
- https://medium.com/@prakash_pun/retrofit-a-simple-android-tutorial-48437e4e5a23
