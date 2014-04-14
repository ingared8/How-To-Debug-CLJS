(ns example.main
  (:require-macros
    [example.macros :refer [inspect breakpoint]])
  (:require
    [clojure.browser.repl :as repl]
    [jayq.core :refer [$ ajax]]))

;;------------------------------------------------------------
;; Connect to the Browser REPL
;;------------------------------------------------------------

(defn connect-repl []
  (ajax {:url "repl-url"
         :cache false
         :dataType "text"
         :success #(repl/connect %)}))

;;------------------------------------------------------------
;; Create a greeting string.
;;------------------------------------------------------------

(defn greeting [username]
  (str "Hello there, " username))

;;------------------------------------------------------------
;; Synchronize a username to a greeting label.
;;------------------------------------------------------------

(def username (atom nil))

(defn on-change-username [_ _ old-name new-name]

  ; Show the values of these variables in the console.
  (inspect old-name new-name)

  ; Show the result of the greeting function in the console.
  ; Then set the greeting label to it.
  ; (inspect function returns the last argument's value)
  (.html ($ "#greeting") (inspect (greeting new-name)))
  (inspect (.html ($ "#greeting")))

  ; Clear the greeting after 2 seconds.
  (js/setTimeout #(.html ($ "#greeting") "") 2000)

  ;(breakpoint)   ;<--- uncomment to start breakpoint
                  ; (only works if browser dev toolbar open)
                  ; (may require page refresh)
  )

(add-watch username :_ on-change-username)

;;------------------------------------------------------------
;; Submit button event.
;;------------------------------------------------------------

(defn on-submit []
  (reset! username (.val ($ "#username"))))

;;------------------------------------------------------------
;; Initialization
;;------------------------------------------------------------

(defn init []
  (js/console.log "HELLO WORLD")
  (.on ($ "#submit") "click" on-submit)
  (connect-repl))
