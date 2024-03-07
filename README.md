# Tech Task for NoughtyFox

## Table of contents
- [Tech Task for NoughtyFox](#tech-task-for-noughtyfox)
  - [Table of contents](#table-of-contents)
  - [Tech Stack](#tech-stack)
  - [Architecture](#architecture)
    - [Data layer](#data-layer)
    - [Domain layer](#domain-layer)
    - [Presentation layer](#presentation-layer)
  - [Getting Started](#getting-started)
  - [Improvements](#improvements)

## Tech Stack
- Hilt
- Retrofit
- Jetpack Compose Navigation
- Jetpack Compose
  
## Architecture

The app is devided in 3 layers:

- Data layer
- Domain layer
- Presentation layer

### Data layer

In the data layer you'll find the **models** and the **api repository**.

### Domain layer

In the domain layer you'll find the **navigation controller** and the **dispatchers** and **coroutine scopes module**.

### Presentation layer

In the presentation layer you'll find the UI related components and screens.

## Getting Started

1. Clone the repository.
2. Set up your development environment with Android Studio or similar tools.
3. Ensure you have the necessary dependencies configured in your Gradle files.
4. In your **local.properties** file set the `api.key` with your api key, which you can get it from [HERE](https://www.thenewsapi.com/). In the end it should look like this: `api.key=your api key` 
5. Refer to the specific code within each layer to understand the implementation details.


## Improvements

The app can obviously be improved, but the required code for those improvements started growing and I just got tired :clown_face: Anyway, here is what could be improved:

1. The UI obviously, I would play around with different colors, and components, and considering the fact that the api provides some images, in the details screen I could display the news image with **coil** for example.
2. Adding a callAdapter for retrofit where `handleApiResponse` could be called, so that we wouldn't have to write it for every network call function.
3. Right now the app sends the error messages received from the api response directly into the UI, here I would create custom exceptions for the different errors which then would have a standardised error message.
4. The api endpoint for getting all the news has pagination, and so for the main screen where the list of the news is, **paging** should be implemented, and after doing that `AppLoadingWrapper` should be changed for a better UI experience when a new page is being loaded.
5. Currently the data flows directly from the repository to the UI i.e. the data from the response isn't really filtered or tailored to the specific need of the screen, so that part could and should be improved.
6. Right now i don't really like the way of creating the routes with arguments in the `AppNavHost` so that could be improved.
7. Some tests wouldn't hurt :clown_face:.

These are the areas which i see right now that can be improved, there might be more in the future. Peace :smiley: