(ns leiningen.new.ken-cljs-2
  (:use [leiningen.new.templates :only [renderer name-to-path ->files]]))

(def render (renderer "ken-cljs-2"))

(defn ken-cljs-2
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (->files data
             ["src/{{sanitized}}/foo.clj" (render "foo.clj" data)])))
