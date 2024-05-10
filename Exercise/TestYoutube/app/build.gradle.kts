plugins {
    id("com.android.application")
}

android {
    namespace = "com.ntp.testyoutube"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.ntp.testyoutube"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.12")
    implementation("com.gitee.chinasoft_ohos:youtubejextractor:1.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

//    implementation (fileTree("dir: 'libs', include: ['*.jar']))
    testImplementation ("junit:junit:4.12")
    androidTestImplementation ("com.android.support.test:runner:1.0.2")
    androidTestImplementation ("com.android.support.test.espresso:espresso-core:3.0.2")

    implementation ("com.hannesdorfmann.parcelableplease:annotation:1.0.2")
    annotationProcessor ("com.hannesdorfmann.parcelableplease:processor:1.0.2")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.7.2")
    implementation ("com.squareup.retrofit2:converter-gson:2.7.2")
    implementation ("com.squareup.okhttp3:logging-interceptor:3.12.2")

    // Gson
    implementation ("com.google.code.gson:gson:2.8.6")

    // Regex with named groups for pre Java 7
    implementation ("com.github.tony19:named-regexp:0.2.5")

    // JS interpreter (versions newer than 1.7.10 requires API 26)
    implementation ("org.mozilla:rhino:1.7.10")

    implementation ("javax.xml.bind:jaxb-api:2.3.1")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.22")
}