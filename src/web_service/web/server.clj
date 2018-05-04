(ns web-service.web.server
  (:require [io.pedestal.http :as http]
            [web-service.web.routes :as routes]))


(defn- update-routes
  [service-map]
  (assoc service-map ::http/routes (routes/initialize)))

(defn- service-map
  [conf]
  (-> conf update-routes))


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
