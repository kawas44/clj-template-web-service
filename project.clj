(defproject clj-template-web-service "0.1.0-SNAPSHOT"
  :description "Template for a Clojure Web service"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [integrant "0.6.3"]
                 [io.pedestal/pedestal.service "0.5.3"]
                 [io.pedestal/pedestal.jetty "0.5.3"]]
  :main ^:skip-aot web-service.server
  :target-path "target/%s"
  :profiles {:dev {:source-paths ["dev"]
                   :dependencies [[integrant/repl "0.3.1"]]}
             :uberjar {:aot :all}}
  ; :global-vars {*warn-on-reflection* true}
  :repl-options {:init-ns user
                 :init (set! *warn-on-reflection* true)})
