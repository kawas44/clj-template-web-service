(ns web-service.web.routes
  (:require [io.pedestal.http.route :as route]
            [io.pedestal.interceptor.helpers :refer [handler]]
            [web-service.web.handlers.core :as core]
            [web-service.web.handlers.admin :as admin]))

(defn- routes-data
  []
  #{["/health" :get (handler :admin-health admin/get-health)]
    ["/hello" :get (handler :core-hello core/get-hello)]})

(defn initialize
  []
  (route/expand-routes (routes-data)))
