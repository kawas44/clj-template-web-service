(ns web-service.config
  (:require [clojure.edn :as edn]))


(defn read!
  [] (edn/read-string (slurp "config.edn")))
