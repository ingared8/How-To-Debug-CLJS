(ns example.main-test
  (:require-macros
    [cemerick.cljs.test :refer (is deftest testing done)])
  (:require
    [cemerick.cljs.test :as t]
    [example.main :as main]))

(def $ js/$)

;;------------------------------------------------------------
;; Test a pure function.
;;------------------------------------------------------------

(deftest greeting
  (is (= "Hello there, Bob" (main/greeting "Bob"))))

;;------------------------------------------------------------
;; Test a DOM side effect.
;;------------------------------------------------------------

(deftest greeting-drawn

  ; The default PhantomJS runner provided by clojurescript.test
  ; only loads javascript files listed in project.clj under the
  ; "phantomjs" command vector.  Since the DOM isn't created by our
  ; JS files, the test environment's DOM is blank.
  ; So we create temporary DOM elements here.

  ; Create the DOM element.
  (-> ($ "<div id='greeting'></div>")
      (.appendTo "body"))

  ; Test DOM side effect.
  (reset! main/username "Billy")
  (is (= "Hello there, Billy"
         (.html ($ "#greeting"))))

  ; Remove DOM element from test environment.
  (-> ($ "#greeting")
      (.remove)))

;;------------------------------------------------------------
;; Asynchronous DOM test.
;;------------------------------------------------------------

(deftest ^:async greeting-clear

  ; Create the DOM element.
  (-> ($ "<div id='greeting'></div>") (.appendTo "body"))

  ; Make sure DOM greeting is cleared after 2 seconds.
  (reset! main/username "Billy")
  (js/setTimeout
    (fn []
      (is (= "" (.html ($ "#greeting")))) ; check if clear
      (-> ($ "#greeting") (.remove))      ; remove DOM element
      (done))                             ; exit test
    2000))
