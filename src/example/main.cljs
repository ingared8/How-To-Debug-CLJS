(ns example.main
  (:require
    [clojure.browser.repl :as repl]
    [jayq.core :refer [$ ajax document-ready]]))

(defn connect-repl []
  (ajax {:url "js/repl-url"
         :cache false
         :dataType "text"
         :success #(repl/connect %)}))

(defn on-submit []
  (let [username (.val ($ "#username"))]
    (.html ($ "#reply") (str "Hello there, " username))))

(defn init []
  (js/console.log "HELLO WORLD")
  (.on ($ "#submit") "click" on-submit)
  (connect-repl))

(document-ready init)
