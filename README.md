# ClojureScript Debugging

Try your hand at ClojureScript debugging using this simple web app example.
Includes the following facilities:

- Unit Tests (for automating pure function tests and DOM tests)
- Browser REPL (for inspecting state and injecting cljs code on a running page)
- Source Maps (for using the browser debugger to trace cljs code)

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
