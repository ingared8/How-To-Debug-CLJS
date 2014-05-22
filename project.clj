(defproject cljs-debugging "0.0.1"
  :description "a simple example of how to debug clojurescript"
  :source-paths ["src"]
  :test-paths ["test"]

  :dependencies
  [[org.clojure/clojure "1.5.1"]
   [org.clojure/clojurescript "0.0-2202"]]

  :plugins
  [[lein-cljsbuild "1.0.3"]
   [com.cemerick/austin "0.1.4"]
   [com.cemerick/clojurescript.test "0.3.0"]]

  :cljsbuild
  {:builds {:main {:source-paths ["src" "test"]
                   :compiler {:optimizations :whitespace
                              :pretty-print true
                              :output-to "example.js"
                              :output-dir "out"}}}

   :test-commands {"phantomjs" ["phantomjs" :runner
                                "jquery-1.9.1.min.js"
                                "example.js"]}}

  :injections [; Rig a (brepl) function to setup an Austin REPL and dump the url to a file.
               ; (This code is immediately executed after starting the repl.)
               ; (The url file is read by our clojurescript app so it can connect to it.)
               ; (We don't auto-execute (brepl) because we want the prompt to be colored)
               (require 'cemerick.austin.repls)
               (defn brepl []
                 (let [env (cemerick.austin/repl-env)]
                   (spit "repl-url" (:repl-url env))
                   (cemerick.austin.repls/cljs-repl (reset! cemerick.austin.repls/browser-repl-env env))))])
