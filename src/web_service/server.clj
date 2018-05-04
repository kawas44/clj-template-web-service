(ns web-service.server
  (:require [web-service.config :as config]
            [integrant.core :as ig])
  (:gen-class))


(defn -main
  [& args]
  (ig/init (:system (config/read!))))
