(defproject cljs-debugging "0.0.1"
  :description "a simple example of how to debug clojurescript"
  :source-paths ["src"]
  :test-paths ["test"]
  :clean-targets ["out" :target-path]
  :dependencies
  [[org.clojure/clojure "1.5.1"]
   [org.clojure/clojurescript "0.0-2202"]
   [jayq "2.5.0"]]
  :plugins
  [[lein-cljsbuild "1.0.3"]
   [com.cemerick/austin "0.1.4"]
   [com.cemerick/clojurescript.test "0.3.0"]]

  :hooks [leiningen.cljsbuild]

  :cljsbuild
  {:builds {:main {:source-paths ["src" "test"]
                   :compiler {:optimizations :whitespace
                              :output-to "public/js/main.js"
                              :output-dir "public/js/out"
                              :pretty-print true}}}
   :test-commands {"phantomjs" ["phantomjs" :runner
                                "public/js/jquery-1.9.1.min.js"
                                "public/js/main.js"]}}

  :injections [; Rig a (brepl) function to setup an Austin REPL and dump the url to a file.
               ; (This code is immediately executed after starting the repl.)
               ; (The url file is read by our clojurescript app so it can connect to it.)
               ; (We don't auto-execute (brepl) because we want the prompt to be colored)
               (require 'cemerick.austin.repls)
               (defn brepl []
                 (let [env (cemerick.austin/repl-env)]
                   (spit "public/repl-url" (:repl-url env))
                   (cemerick.austin.repls/cljs-repl (reset! cemerick.austin.repls/browser-repl-env env))))]

  :profiles {:debug-extra
             {:cljsbuild {:builds {:main {:compiler {:source-map "public/js/main.js.map"}}}}}})
