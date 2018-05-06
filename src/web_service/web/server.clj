(ns web-service.web.server
  (:require [clojure.spec.alpha :as s]
            [io.pedestal.http :as http]
            [web-service.web.routes :as routes]))


(defn- update-routes
  [service-map]
  (assoc service-map ::http/routes (routes/initialize)))

(defn- service-map
  [conf]
  (-> conf update-routes))

;; CONFIGURATION SPEC

(s/def ::env #{:prod :dev})
(s/def :io.pedestal.http/host string?)
(s/def :io.pedestal.http/port number?)
(s/def :io.pedestal.http/join? boolean?)
(s/def :io.pedestal.http/type #{:jetty :tomcat})

(s/def ::config (s/keys :req-un [::env]
                        :req [:io.pedestal.http/host
                              :io.pedestal.http/port
                              :io.pedestal.http/join?
                              :io.pedestal.http/type]))

(defn create
  [conf]
  (http/create-server (service-map conf)))

(defn dispose
  [server]
  (when server
    (http/stop server)))

(defn start
  [server] (http/start server))

(defn stop
  [server] (http/stop server))
