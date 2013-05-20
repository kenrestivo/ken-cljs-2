(ns {{name}}.handler
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [firealarm.core :as firealarm]
            [environ.core :as env]
            [{{name}}.core :as cora]
            [stencil.core :as stencil]
            [compojure.core :refer [GET POST defroutes]])
  (:use [ring.middleware.resource :only [wrap-resource]]
        [ring.middleware.file-info :only [wrap-file-info]]))


(def main-template "{{name}}/templates/main")

(def wrap-exceptions
  (firealarm/exception-wrapper
   (firealarm/file-reporter "/tmp/web.log")))

(defn dev-mode? []
  (= "dev"  (get env/env :type)))

(defn dev-tag []
  (when (dev-mode?)
    "<div class=\"hidden\" id=\"env\" >dev</div>"))

(defn cljs-name []
  (when (dev-mode?)
    "-dev"))




(defroutes app-routes
  (GET "/" []
       (stencil/render-file main-template
                            {:dev-tag (dev-tag)
                             :main-js-file (str  "main" (cljs-name) ".js")}))
  (route/not-found "Not Found"))


(def app
  (-> #'app-routes
      handler/site
      (wrap-resource  "public") ;; for css and js
      wrap-file-info ;; for correct mime types
      wrap-exceptions ;; last!
      ))

;; stupdi hack required by slamhound
(defn slamhound-hack [] "this is a stupid hack")



(comment
  ;; this PITA is needed in order to invalidate the cache.
  ;; maybe a lein plugin could do this?
  (require 'stencil.loader)
  (stencil.loader/invalidate-cache-entry main-template)
  )