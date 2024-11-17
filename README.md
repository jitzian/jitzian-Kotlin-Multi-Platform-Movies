This is a Kotlin Multiplatform project targeting Android, iOS.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

## Technologies and Frameworks

- **Kotlin Multiplatform**
- **Jetpack Compose**
- **SwiftUI**
- **Koin** for dependency injection
- **Ktor** for networking
- **Room** for database
- **Coil** for image loading
- **Moko Permissions** for handling permissions
- **Gradle** for build automation

## Android Screenshots

<table>
  <tr>
    <th>Main</th>
    <th>Details</th>
  </tr>
  <tr>
    <td><img src="screenshots/android/android_main.png" alt="Main" /></td>
    <td><img src="screenshots/android/android_details.png" alt="Details" /></td>
  </tr>
</table>

## iOS Screenshots

<table>
  <tr>
    <th>Main</th>
    <th>Details</th>
  </tr>
  <tr>
    <td><img src="screenshots/ios/ios_main.png" alt="Main" /></td>
    <td><img src="screenshots/ios/ios_details.png" alt="Details" /></td>
  </tr>
</table>

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…