(ns web-service.web.handlers.core)

(defn get-hello
  [request]
  {:status 200 :body "Hello tout le monde"})
