/**
 * Configuration file
 * @param namespace Package name for its generated R and BuildConfig classes.
 * @param compileSdk An integer designating the maximum API Level on which the application is designed to run.
 * @param targetSdk An integer designating the API Level that the application targets.
 * @param minSdk An integer designating the minimum API Level required for the application to run.
 * @param versionCode A positive integer used as an internal version number.
 * @param versionName A string used as the version number shown to users.
 * @param applicationId The unique identifier of the application that identifies it on the device and on Google Play
 */
object AppConfig {
    const val namespace = "com.example.shopapplication"
    const val domainNamespace = "com.example.domain"
    const val dataNamespace = "com.example.data"
    const val applicationId = "com.example.shopapplication"

    const val compileSdk = 33
    const val targetSdk = 33
    const val minSdk = 21

    const val versionCode = 1
    const val versionName = "1.0"
}