# Preserve the line number information for debugging stack traces.
-keepattributes SourceFile,LineNumberTable

# OKHttp v.3.x
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-keep class sun.security.ssl.** { *; }
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
-dontwarn okhttp3.**
-dontwarn javax.annotation.**
-dontwarn org.codehaus.mojo.animal_sniffer.*

# Retrofit 2.x
-keep class retrofit.** { *; }
-keepattributes Exceptions
-keepclasseswithmembers class * {
  @retrofit.http.* <methods>;
}
-keepclassmembers,allowshrinking,allowobfuscation interface * {
  @retrofit2.http.* <methods>;
}
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn com.squareup.okhttp.**
-dontwarn retrofit.**
