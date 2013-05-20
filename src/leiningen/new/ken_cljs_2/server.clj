(ns {{name}}.server
  (:require [aleph.http :as http]
            [environ.core :as env]
            [{{name}}.handler :as handler])
  (:gen-class))

;; needed for restarts, etc
(defonce srv (atom nil))

(defn -main [& args]
  (let [port (get env/env :{{name}}-port 8080)]
    (reset! srv
            (http/start-http-server
             ;; XXX note! #'ing this handler makes slamhound disappear it.
             (http/wrap-ring-handler #'{{name}}.handler/app) {:port port :host "localhost" }))
    (println "{{Name}} now listening on port " port)))



;; stupid hack required by slamhound
(handler/slamhound-hack)