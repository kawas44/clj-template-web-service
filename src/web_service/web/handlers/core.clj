(ns web-service.web.handlers.core
  (:require [clojure.tools.logging :as log]))

(defn get-hello
  [request]
  (log/debug "We say hello")
  {:status 200 :body "Hello tout le monde"})
