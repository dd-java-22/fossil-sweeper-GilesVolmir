---
title: Technical requirements & dependencies
description: "Requirements and dependencies used in the project."
order: 60
---

{% include ddc-abbreviations.md %}

## Page contents
{:.no_toc:}

- ToC
  {:toc}

## Tested Hardware
* Tested on Samsung Galaxy A56 running Android 16
* Minimum SDK version: 30
* known layout issues in landscape mode

## 3rd Party Libraries
* [AndroidX](https://developer.android.com/jetpack/androidx)
* [GoogleId](https://developers.google.com/identity)
* [Retrofit](https://square.github.io/retrofit/)
* [OkHttp](https://square.github.io/okhttp/)
* [Material Components for Android](https://github.com/material-components/material-components-android)
* [Dagger](https://dagger.dev/)
* [Gson](https://github.com/google/gson)

Expect to use [Glide](https://bumptech.github.io/glide/) for image loading to achieve unfinished goals.


## Android Permissions Required
None in current state.
Will eventually require for GBIF network and image fetching at caching:
* Internet
* Access Network State

## External Services
* Google Sign-In
* GBIF API (Service implemented but not used in current state)