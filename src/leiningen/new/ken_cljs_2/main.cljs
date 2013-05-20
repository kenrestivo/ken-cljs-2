(ns {{name}}.main
  (:require
   [clojure.browser.repl :as repl]
   [dommy.core :as dommy]
   [dommy.utils :as dutils]
   [utilza.utils :as utils]
   [goog.net.XhrIo :as xhr])
  (:use-macros
   [dommy.macros :only [sel sel1 node]])
  )


(when (= "dev" (->  :#env sel1 dommy/text))
  (utils/ready #(repl/connect "http://localhost:9000/repl")))

