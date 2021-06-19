rootProject.name = "blood-pressure-diary"

include("backend")
//include("app")

pluginManagement {
    repositories {
        gradlePluginPortal()
        jcenter()
        mavenCentral()
        google()
    }
}
include(":medpod")
