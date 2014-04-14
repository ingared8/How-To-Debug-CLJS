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

## Testing

It's nice having the ability to test your code without opening a browser.  The
following command runs all the tests contained in the test/ directory:

```
lein test
```

### Pure Function Testing

It is simple to create a test case that verifies the output of a function:

```clojure
(deftest greeting
  (is (= "Hello there, Bob" (main/greeting "Bob"))))
```

### DOM Testing

You can test a function's side effects on the DOM as well.  Just keep in mind
that by default, the PhantomJS test runner is evaluating all your code in the
context of a blank DOM page.

```clojure
(deftest greeting-drawn

  ; Create the DOM element.
  (-> ($ "<div id='greeting'></div>")
      (.appendTo "body"))

  ; Test DOM side effect when modifying atom.
  (reset! main/username "Billy")
  (is (= "Hello there, Billy"
         (.html ($ "#greeting"))))

  ; Remove DOM element from test environment.
  (-> ($ "#greeting")
      (.remove)))
```

So if you're testing a simple function that only touches a few DOM elements, you
can create those DOM elements in the test.

For more complex DOM tests, you could reconfigure the PhantomJS runner to start
with an existing page rather than a blank one.  The default test command running
tests on a blank page can be seen in `project.clj`:

```clojure
:test-commands {"phantomjs" ["phantomjs" :runner
                             "public/js/jquery-1.9.1.min.js"
                             "public/js/main.js"]}
```

The `:runner` keyword is expanded to [the default runner
script](https://github.com/cemerick/clojurescript.test/blob/master/resources/cemerick/cljs/test/runner.js),
which can be replaced with a custom script for opening test pages with a
preconfigured DOM, or even for directly running the web app as is.

## Browser REPL

```
lein repl
(brepl)
```

## Source Maps

```
lein with-profile debug-extra cljsbuild auto
```
