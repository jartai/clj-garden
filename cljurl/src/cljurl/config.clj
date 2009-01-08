(ns cljurl.config
  (:use clj-jdbc.data-sources)
  (:require cljurl.boot)
  (:import java.io.File))

(def env cljurl.boot/env)

(def app-host "localhost:8000")

(def public-dir (File. "public"))

(def dev?  (= env :dev))
(def test? (= env :test))
(def prod? (= env :prod))

(def handle-exceptions prod?)

(def data-source
  (pg-data-source
    (cond
      prod? {:database "cljurl_prod" :user "deploy" :password "somepass"}
      dev?  {:database "cljurl_dev"  :user "mmcgrana" :password ""}
      test? {:database "cljurl_test" :user "mmcgrana" :password ""})))
