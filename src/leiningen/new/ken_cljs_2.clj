(ns leiningen.new.ken-cljs-2
  (:use [leiningen.new.templates :only [renderer name-to-path ->files]]))

(def render (renderer "ken-cljs-2"))

(defn ken-cljs-2
  "Builds a skeleton of a cljs app with postres as a backing db."
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (->files data
             ["src/{{sanitized}}/core.clj" (render "core.clj" data)]
             ["src/{{sanitized}}/db.clj" (render "db.clj" data)]
             ["src/{{sanitized}}/handler.clj" (render "handler.clj" data)]
             ["src/{{sanitized}}/server.clj" (render "server.clj" data)]
             ["src/{{sanitized}}/templates/main.mustache" (render "main.mustache" data)]
             ["resources/log4j.properties" (render "log4j.properties" data)]
             ["resources/public/robots.txt" (render "robots.txt" data)]
             ["resources/public/js/bootstrap.min.js" (render "bootstrap.min.js" data)]
             ["resources/public/js/excanvas.min.js" (render "excanvas.min.js" data)]
             ["resources/public/css/bootstrap-responsive.min.css" (render "bootstrap-responsive.min.css" data)]
             ["resources/public/css/bootstrap.min.css" (render "bootstrap.min.css" data)]
             ["resources/public/img/glyphicons-halflings.png" (render "glyphicons-halflings.png" data)]
             ["resources/public/img/glyphicons-halflings-white.png"
              (render "glyphicons-halflings-white.png" data)]
             ["src-cljs/{{sanitized}}/main.cljs" (render "main.cljs" data)]
             ["project.clj" (render "project.clj" data)]
             ["README.md" (render "README.md" data)]
             [".lein-env" (render "lein-env" data)]
             )))
