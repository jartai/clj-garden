(ns cljurl.config
  (:use clj-jdbc.data-sources cljurl.utils)
  (:import java.io.File))

(def env (keyword (System/getProperty "cljurl.env")))

(def app-host "localhost:8000")

(def public-dir (File. "public"))

(def dev?  (= env :dev))
(def test? (= env :test))
(def prod? (= env :prod))

(def data-source
  (pg-data-source
    (cond
      prod? {:database "cljurl_prod" :user "deploy" :password "somepass"}
      dev?  {:database "cljurl_dev"  :user "mmcgrana" :password ""}
      test? {:database "cljurl_test" :user "mmcgrana" :password ""})))

(def logger
  (logger4j-err (cond prod? :info dev? :debug test? :warn)))

(def handle-exceptions? prod?)
(def log-exceptions?    (not test?))
