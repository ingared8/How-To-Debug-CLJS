# ClojureScript Debugging

I want to share some ClojureScript debugging practices that I find helpful.
This project provides a simple web app example intended for studying/practicing
the following debugging workflow:

- __Testing__ - quickly test your code without opening a web browser
- __Interacting__ - play with your code while it's running on your web page
- __Logging__ - easily and descriptively log your application state
- __Tracing__ - pause and step through code

## Setup

1. Install [Leiningen](http://leiningen.org/), [NodeJS](http://nodejs.org/), and [PhantomJS](http://phantomjs.org/).
1. Run the following in the project directory:

    ```
    npm install express
    node server
    ```

1. In another terminal, run the auto-compiler from the project directory:

    ```
    lein cljsbuild auto
    ```

1. Open <http://localhost:1234> in your browser.

## Unit Tests

```
lein test
```

## Browser REPL

```
lein repl
(brepl)
```

## Source Maps

```
lein with-profile debug-extra cljsbuild auto
```
