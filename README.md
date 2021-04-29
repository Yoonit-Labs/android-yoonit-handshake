# android-yoonit-handshake

An Android Jitpack project written in Kotlin implementing dynamic SSL pinning.

* [About](#about)
* [Installation](#installation)
* [Usage](#usage)
* [API](#api)
  * [Method](#method)
  * [Event](#event)
  * [Handshake Result](#handshake-result)
* [To contribute and make it better](#to-contribute-and-make-it-better)

## About

The SSL pinning is a technique mitigating man-in-the-middle attacks against the secure HTTP communication, but has a drawback, the certificate's expiration date. This resolve this problem, implementing dynamic SSL pinning, providing easy to use fingerprint validation on the TLS handshake. The remote server must be the responsible to update the certificate(s).

## Installation

Add the JitPack repository to your root `build.gradle` at the end of repositories

```groovy
allprojects {
	repositories {
	..
	maven { url 'https://jitpack.io' }
	}
}
```

Add the dependency

```groovy
dependencies {
	implementation 'com.github.Yoonit-Labs:android-yoonit-handshake:master-SNAPSHOT'
}
```

> ### Special thanks and credits...
> The current version of the library depends on `[Wultra SSL Pinning]`(https://github.com/wultra/ssl-pinning-android)

## Usage

First, in the `local.properties`, insert the `public.key` and `service.url` values. 

```kotlin
class MainActivity : AppCompatActivity() {
    private lateinit var handshake: Handshake

    private val handshakeListener = object : HandshakeListener {
        override fun continueExecution() {
            Toast.makeText(applicationContext, "OK", Toast.LENGTH_LONG).show()
        }
        override fun handleFailedUpdate(type: String, result: String) {
            Toast.makeText(applicationContext, result, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.handshake = Handshake(applicationContext, handshakeListener)

        update_fingerprint.setOnClickListener {
            handshake.updateFingerprint(BuildConfig.PUBLIC_KEY, BuildConfig.URL)
        }
    }
}
```

## API

### Method

| Function          | Parameters                               | Description
| -                 | -                                        | -
| updateFingerprint | `publicKey: String, serviceUrl: String`  | Update the list of fingerprints from the remote server. The method is asynchronous. Response can get in the `HandshakeListener` interface.

### Event

| Event               | Parameters                     | Description
| -                   | -                              | -
| continueExecution   |                                | The update fingerprint request success result.
| handleFailedUpdate  | `type: String, result: String` | The update fail fingerprint request result.

### Handshake Result

| UpdateResult      | Description
| -                    | -
| OK                   | <ul><li>Update succeeded;</li><li>No Action is required;</li></ul>
| STORE_IS_EMPTY       | <ul><li>[CertStore] is empty;</li><li>There's no valid certificate fingerprint to validate server cert against;</li><li>Might happen when all the certificate fingerprints are already expired;</li><li>All loaded certificates are already expired;</li>
| NETWORK_ERROR        | There was an error in network communication with the server. 
| INVALID_DATA         | The update request returned invalid data from the server.
| INVALID_SIGNATURE    | The update request returned data which did not pass the signature validation.

## To contribute and make it better

Clone the repo, change what you want and send PR.

For commit messages we use <a href="https://www.conventionalcommits.org/">Conventional Commits</a>.

Contributions are always welcome!

<a href="https://github.com/Yoonit-Labs/android-yoonit-handshake/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=Yoonit-Labs/android-yoonit-handshake" />
</a>

---

Code with ❤ by the [**Cyberlabs AI**](https://cyberlabs.ai/) Front-End Team