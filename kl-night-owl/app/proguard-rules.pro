# osmdroid ships no consumer rules and loads a few classes reflectively.
-keep class org.osmdroid.** { *; }
-dontwarn org.osmdroid.**
-dontwarn org.slf4j.**
-dontwarn javax.annotation.**
