(ns web-service.web.handlers.admin)

(defn get-health
  [request]
  {:status 200 :body "Health OK"})
