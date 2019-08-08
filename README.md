#Template DPR NOW

Membuat Template DPR NOW untuk Bidang Data dan Teknologi Informasi (BDTI) Sekretariat Jenderal DPR-RI
aplikasi ini berguna untuk media pembelajaran Mobile Application (Android)  di BDTI.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

Project ini dibuat menggunakan, 
* Android Studio 3.4.1, 
* JDK 1.8, 
* Gradle 3.4.0,

Library/tools pendukung
* Retrofit -> client HTTP type-safe untuk Android dan Java, untuk CRUD ke web server
* Volley -> HTTP library untuk client android (sama seperti Retrofit), untuk CrUD ke web server
* Firebase
* Google Services
### Prerequisites

What things you need to install the software and how to install them

```
Give examples
```

### Installing

A step by step series of examples that tell you how to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system

### Parameter

### `/pengaduan`

Returns all information about all episodes. This returns an array of Episode
objects as defined above.

### `/newest`

Returns the episode of My Little Pony: Friendship is Magic that will air next.

### `/last_aired`

Returns the episode of My Little Pony: Friendship is Magic that most recently
aired.

### `/season/<number>`

Returns all information about episodes in the given season number or a `404`
reply if no episodes could be found. To get all information about the movies
shown, set the season as `99`.

### `/season/<number>/episode/<number>`

Returns all information about the episode with the given season and episode
number. If the episode cannot be found, this will return a `404`.

### `/random`

Returns a random episode record from the list of episodes.

### `/search`

This must be given a query paramater `q` containing the text to search for. Not
including this will return a `406` reply. This will search the list of episode
records for any episodes whose names match the given search terms. This is
case-insensitive. If no episodes can be found, this will return a `404` reply.

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Gradle](https://gradle.org/) - Build tool
* [Retrofit](https://square.github.io/retrofit/) -  A type-safe HTTP client for Android and Java
* [Volley](https://github.com/google/volley) - HTTP library for Android
* [JDK](https://www.oracle.com/technetwork/java/javase/downloads/index.html) -  a development environment for building applications, applets, and components using the Java programming language.

## Versioning

We use [Github](https://github.com) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Contributors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)
* **Annisa Gita Asmara** [aannisagita](https://github.com/aannisagita)
* **Bahrul Faizi** [ApaKabahrul](https://github.com/ApaKabahrul)
* **Diki Hermawan** [Hermawan77](https://github.com/Hermawan77)
* **Yulianto Pambudi** [pevensey](https://github.com/pevensey)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
