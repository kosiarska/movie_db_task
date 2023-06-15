# Mapstruct
-keepnames @org.mapstruct.Mapper class *
-keep class * extends @org.mapstruct.Mapper **
-keepclassmembers public class * implements @org.mapstruct.Mapper ** { public <methods>; <fields>; }

# Preserve the line number information for debugging stack traces.
-keepattributes SourceFile,LineNumberTable

# Joda Time
-dontwarn org.joda.convert.FromString
-dontwarn org.joda.convert.ToString
-keep class org.joda.time.** { *; }
-keep interface org.joda.time.** { *; }

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
