# Google URL Shortener Service
This plugin provides a abstraction layer around the google URL shortener service, you can shorten, expand and retrieve analytics from URL following the grails conventions.

## Introduction
Like [TinyURL](https://tinyurl.com/) or [Bit.ly](https://bitly.com/), Google has also its [URL shortener service](https://goo.gl/).

Beside shorten URLs it also provides some extra services like providing analytics of shorten URLs.

Since 13.01.2011 Google provides an API for this service. This grails plugin wraps this API and provides some convenient methods to shorten your URLs.

## Usage
After installing the plugin you have 2 options to shorten or expand your URLs by dynamic methods on `String` class or by using service

### By dynamic methods
Use the `shorten()` / `expand()` methods which are dynamically added to the `String` object.
So you can do something like that:

```groovy
def shortUrl = myLongUrlString.shorten()
//or
def shortUrtl = params.tweetLink.shorten()
//expanding a link
def longLink = myShortLinkString.expand()
```

### Using the Shortener service.
Methods of this service, instead of returning a `String`, will retrieve an `UrlResource` instance that contains more information.
The service also provides an extra method `getAnalytics()` that will retrieves analytics of your shorten URL.
The structure of the analytics data follows the API specification (except some property naming convention that was not supported by Grails (`id` property) or Hibernate (`count` property) and is made available through Domain classes.

The analytics data structure of the API can be found [here](http://code.google.com/apis/urlshortener/v1/reference.html#collection_url).

```groovy
def shortenerService

def linkHandler = {
   def urlResource = shortenerService.shorten(params.myLongLink)
   def shortLink = urlResource.shortUrl
}

def analyseLink = {
   def analyticsUrlResource = shortenerService.getAnalytics(params.shortLink)
   int monthlyClicks = analyticsUrlResource.month.shortUrlClicks
  //etc check the API reference to see all the info you can retrieve
}
```

## API key
Having an API key is optional for this service but Google advice to get one, it's free and easy to get one, see [this link](https://code.google.com/apis/console-help/#UsingKeys)

When you get your key you can add to the `Config.groovy` like that:

```groovy
google.shortener.api.key='yourGoogleApiKey'
```

Each service call will then use your key in the request

## Exception handling
If you provide a wrong URL (typo or long url instead of `shortUrl`) the plugin will throw a `HttpResponseException`, the plugin do not catch this exception so your application can take care of it.