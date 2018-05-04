(ns web-service.components.http-server
  (:require [integrant.core :as ig]
            [web-service.web.server :as server]))


(defmethod ig/init-key :web/server [_ conf]
  (let [{:io.pedestal.http/keys [host port] env :env} conf]
    (printf "Starting web server on %s:%s [%s] %n" host port (name env))
    (server/start (server/create conf))))

(defmethod ig/halt-key! :web/server [_ component]
  (server/dispose component))
