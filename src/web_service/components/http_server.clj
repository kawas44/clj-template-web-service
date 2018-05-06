(ns web-service.components.http-server
  (:require [clojure.spec.alpha :as s]
            [integrant.core :as ig]
            [web-service.web.server :as server]))


(defmethod ig/pre-init-spec :web/server [_]
  (s/get-spec ::server/config))

(defmethod ig/init-key :web/server [_ conf]
  (let [{:io.pedestal.http/keys [host port] env :env} conf]
    (printf "Starting web server on %s:%s [%s] %n" host port (name env))
    (server/start (server/create conf))))

(defmethod ig/halt-key! :web/server [_ component]
  (server/dispose component))
