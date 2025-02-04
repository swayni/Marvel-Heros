# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class org.bouncycastle.** { *; }
-keep interface org.bouncycastle.**

-keep class org.conscrypt.** { *; }
-keep class org.openjsse.** { *; }

-keep class com.android.** { *; }
-keep class javax.naming.** { *; }
-keep class org.apache.** { *; }

-dontnote libcore.io.Libcore
-dontnote org.apache.harmony.xnet.provider.jsse.OpenSSLRSAPrivateKey
-dontnote org.apache.harmony.security.utils.AlgNameMapper
-dontnote sun.security.x509.AlgorithmId

-dontwarn android.util.StatsEvent
-dontwarn dalvik.system.BlockGuard
-dontwarn dalvik.system.CloseGuard
-dontwarn com.android.org.conscrypt.AbstractConscryptSocket
-dontwarn com.android.org.conscrypt.ConscryptFileDescriptorSocket
-dontwarn com.android.org.conscrypt.OpenSSLSocketImpl
-dontwarn com.android.org.conscrypt.SSLParametersImpl
-dontwarn org.apache.harmony.xnet.provider.jsse.OpenSSLSocketImpl
-dontwarn org.apache.harmony.xnet.provider.jsse.SSLParametersImpl
-dontwarn javax.naming.**
-dontwarn org.bouncycastle.**
-dontwarn org.openjsse.**

-keep class * implements java.io.Serializable

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keepclassmembers enum * {
    @com.google.gson.annotations.SerializedName <fields>;
}

-keepattributes *Annotation*

-keepclassmembers,allowobfuscation class * {
    @dagger.** *;
}

-keep class **$$ModuleAdapter
-keep class **$$InjectAdapter
-keep class **$$StaticInjection

-if   class **$$ModuleAdapter
-keep class <1>

-if   class **$$InjectAdapter
-keep class <1>

-if   class **$$StaticInjection
-keep class <1>

-keepnames class dagger.Lazy

-adaptresourcefilenames    **.properties,**.gif,**.jpg
-adaptresourcefilecontents **.properties,META-INF/MANIFEST.MF

-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepnames class kotlinx.coroutines.android.AndroidExceptionPreHandler {}
-keepnames class kotlinx.coroutines.android.AndroidDispatcherFactory {}

-keep class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keep class kotlinx.coroutines.CoroutineExceptionHandler {}
-keep class kotlinx.coroutines.android.AndroidExceptionPreHandler {}
-keep class kotlinx.coroutines.android.AndroidDispatcherFactory {}

-keepclassmembernames,allowobfuscation,allowshrinking class androidx.core.view.ViewCompat$Api* {
  <methods>;
}
-keepclassmembernames,allowobfuscation,allowshrinking class androidx.core.view.WindowInsetsCompat$*Impl* {
  <methods>;
}
-keepclassmembernames,allowobfuscation,allowshrinking class androidx.core.app.NotificationCompat$*$Api*Impl {
  <methods>;
}
-keepclassmembernames,allowobfuscation,allowshrinking class androidx.core.os.UserHandleCompat$Api*Impl {
  <methods>;
}
-keepclassmembernames,allowobfuscation,allowshrinking class androidx.core.widget.EdgeEffectCompat$Api*Impl {
  <methods>;
}

-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}

-keep class sw.swayni.marvelheros.* { *; }
-keep class sw.swayni.marvelheros.utils.* { *; }
-keep class sw.swayni.marvelheros.data.* { *; }
-keep class sw.swayni.marvelheros.data.database.* { *; }
-keep class sw.swayni.marvelheros.data.local.* { *; }
-keep class sw.swayni.marvelheros.data.model.* { *; }
-keep class sw.swayni.marvelheros.data.remote.* { *; }
-keep class sw.swayni.marvelheros.data.repository.* { *; }
-keep class sw.swayni.marvelheros.di.module.* { *; }
-keep class sw.swayni.marvelheros.ui.detail.adapter.* { *; }
-keep class sw.swayni.marvelheros.ui.detail.presentation.* { *; }
-keep class sw.swayni.marvelheros.ui.detail.viewmodel.* { *; }
-keep class sw.swayni.marvelheros.ui.favorite.adapter.* { *; }
-keep class sw.swayni.marvelheros.ui.favorite.viewmodel.* { *; }
-keep class sw.swayni.marvelheros.ui.favorite.presentation.* { *; }
-keep class sw.swayni.marvelheros.ui.list.adapter.* { *; }
-keep class sw.swayni.marvelheros.ui.list.presentation.* { *; }
-keep class sw.swayni.marvelheros.ui.list.viewmodel.* { *; }
-keep class sw.swayni.marvelheros.ui.main_activity.presentation.* { *; }
-keep class sw.swayni.marvelheros.ui.main_activity.viewmodel.* { *; }

-keepclassmembers class sw.swayni.marvelheros.* { <fields>; }
-keepclassmembers class sw.swayni.marvelheros.utils.* { <fields>; }
-keepclassmembers class sw.swayni.marvelheros.data.* { <fields>; }
-keepclassmembers class sw.swayni.marvelheros.data.database.* { <fields>; }
-keepclassmembers class sw.swayni.marvelheros.data.local.* { <fields>; }
-keepclassmembers class sw.swayni.marvelheros.data.model.* { <fields>; }
-keepclassmembers class sw.swayni.marvelheros.data.remote.* { <fields>; }
-keepclassmembers class sw.swayni.marvelheros.data.repository.* { <fields>; }
-keepclassmembers class sw.swayni.marvelheros.di.module.* { <fields>; }
-keepclassmembers class sw.swayni.marvelheros.ui.detail.adapter.* { <fields>; }
-keepclassmembers class sw.swayni.marvelheros.ui.detail.presentation.* { <fields>; }
-keepclassmembers class sw.swayni.marvelheros.ui.detail.viewmodel.* { <fields>; }
-keepclassmembers class sw.swayni.marvelheros.ui.favorite.adapter.* { <fields>; }
-keepclassmembers class sw.swayni.marvelheros.ui.favorite.viewmodel.* { <fields>; }
-keepclassmembers class sw.swayni.marvelheros.ui.favorite.presentation.* { <fields>; }
-keepclassmembers class sw.swayni.marvelheros.ui.list.adapter.* { <fields>; }
-keepclassmembers class sw.swayni.marvelheros.ui.list.presentation.* { <fields>; }
-keepclassmembers class sw.swayni.marvelheros.ui.list.viewmodel.* { <fields>; }
-keepclassmembers class sw.swayni.marvelheros.ui.main_activity.presentation.* { <fields>; }
-keepclassmembers class sw.swayni.marvelheros.ui.main_activity.viewmodel.* { <fields>; }

-keepclasseswithmembernames class * { @javax.inject.Inject <init>(...); }
-keepclasseswithmembernames class * { @javax.inject.Inject <fields>; }
-keepclasseswithmembernames class * { @javax.inject.Inject <methods>; }

-keep class **__Factory { *; }
-keep class **__MemberInjector { *; }

-dontwarn retrofit.**
-keep class retrofit.** { *; }
-dontwarn sun.misc.Unsafe
-dontwarn com.octo.android.robospice.retrofit.RetrofitJackson**
-dontwarn retrofit.appengine.UrlFetchClient
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}
-keep class com.google.gson.** { *; }
-keep class com.google.inject.** { *; }
-keep class org.apache.http.** { *; }
-keep class org.apache.james.mime4j.** { *; }
-keep class javax.inject.** { *; }
-keep class retrofit.** { *; }
-dontwarn org.apache.http.**
-dontwarn android.net.http.AndroidHttpClient
-dontwarn retrofit.**

-dontwarn sun.misc.**

-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}

-keepattributes Signature, InnerClasses, EnclosingMethod
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
-keepattributes AnnotationDefault
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn javax.annotation.**
-dontwarn kotlin.Unit
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions.*

-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>
-keep,allowobfuscation,allowshrinking interface retrofit2.Call
-keep,allowobfuscation,allowshrinking class retrofit2.Response
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

-keep public class * extends androidx.recyclerview.widget.RecyclerView$LayoutManager {
    public <init>(android.content.Context, android.util.AttributeSet, int, int);
    public <init>();
}
-keepclassmembers class androidx.recyclerview.widget.RecyclerView {
    public void suppressLayout(boolean);
    public boolean isLayoutSuppressed();
}

-keep class * extends androidx.room.RoomDatabase
-dontwarn androidx.room.paging.**

-keepattributes RuntimeVisibleAnnotations
-keep,allowobfuscation,allowshrinking class * extends androidx.navigation.Navigator