# ClojureScript Debugging

I want to share some ClojureScript debugging practices that I find helpful.
This project provides a simple web app example intended for studying/practicing
the following debugging workflow:

- __Testing__ - quickly test your code without opening a web browser
- __Interacting__ - play with your code while it's running on your web page
- __Logging__ - easily and descriptively log your application state
- __Tracing__ - pause and step through code

## Setup

Run the server:

```
npm install express
node server
```

Run the ClojureScript auto-compiler:

```
lein cljsbuild auto
```

Open your browser to the url:

```
http://localhost:1234
```

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
