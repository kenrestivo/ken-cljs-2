(ns {{name}}.core-test
  (:require [clj-http.client :as client]
            [{{name}}.entry :as entry]
            aleph.formats
            [clojure.tools.logging :as log])
  (:use clojure.test
        {{name}}.core
        clojure.pprint))

