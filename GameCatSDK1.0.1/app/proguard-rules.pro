# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Applications/sdk/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-optimizationpasses 5                                                           # 指定代码的迭代优化次数
-dontusemixedcaseclassnames                                                     # 不使用大小写名称的类名
-dontskipnonpubliclibraryclasses                                                # 指定不去忽略非公共的库类
-dontpreverify                                                                  # 混淆时是否做预校验
-verbose                                                                        # 混淆时是否记录日志
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*        # 优化选项
-keep class com.alibaba.sdk.android.**{*;}
-keep class com.ut.**{*;}
-keep class com.ta.**{*;}
-keep class com.alipay.** { *; }
-keep class com.org.json.alipay.** { *; }
-keep class android.support.** { *; }
-keep class com.org.apache.commons.codec.** { *; }
-keep class com.tencent.mm.** { *; }
-keep class com.linkea.** { *; }
-keep class okhttp3.** { *; }
-keep class com.zhy.http.okhttp.** { *; }
-keep class okio.** { *; }
