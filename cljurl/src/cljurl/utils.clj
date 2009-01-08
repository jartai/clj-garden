(ns cljurl.utils
  (:use clojure.contrib.except)
  (:import (org.apache.log4j Level Logger ConsoleAppender SimpleLayout)))

(defn str-cat
  "Concat the given strings into a single string. Like (str-join \"\" strs)."
  [strs]
  (apply str strs))

(defn update
  "'Updates' a value in an associative structure, where k is a key and f is a 
  function that will take the old value and any supplied args and return the new 
  value, and returns the new associative structure."
  [m k f & args]
  (assoc m k (apply f (get m k) args)))

(defn choice
  "Returns a random element from the given vector."
  [#^clojure.lang.IPersistentVector vec]
  (get vec (rand-int (count vec))))

(def log-levels
  {:debug Level/DEBUG
   :info  Level/INFO
   :warn  Level/WARN
   :error Level/ERROR
   :fatal Level/FATAL})

(defn log-level
  "Returns a static field of Level corresponding to the lower case keyword
  level."
  [level]
  (or (log-levels level) (throwf "unrecognized log level: %s" level)))

(defn logger4j-err
  "Returns a stderr log4j logger with debug log level."
  [level]
  (let [apdr (doto (ConsoleAppender.)
               (.setTarget (ConsoleAppender/SYSTEM_OUT))
               (.setLayout (SimpleLayout.))
               (.activateOptions))]
    (doto (Logger/getLogger (str (gensym)))
      (.setLevel (log-level level))
      (.addAppender apdr))))
