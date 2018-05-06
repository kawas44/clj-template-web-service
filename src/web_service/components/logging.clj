(ns web-service.components.logging
  (:require [integrant.core :as ig]
            [unilog.config :as unilog]))


(defmethod ig/init-key :logging [_ conf]
  (printf "Starting logging %n")
  (unilog/start-logging! conf))
