(ns user
  (:require [web-service.config :as config]
            [integrant.repl :as repl :refer [go halt reset]]))

(def dev-conf {:system
               {:web/server {:env :dev
                              :io.pedestal.http/host "localhost"
                              :io.pedestal.http/port 8081
                              :io.pedestal.http/join? false
                              :io.pedestal.http/type :jetty}
                #_#_:logging {:path "log/"
                          :level :debug}}})

(defn dev-system-conf
  [] (-> (config/read!) (merge dev-conf) :system))

(repl/set-prep! dev-system-conf)
