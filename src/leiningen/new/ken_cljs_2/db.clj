(ns {{name}}.db
  (:require [utilza.postgres :as pg]
            [environ.core :as env])


(defonce db-pool (delay (pg/pool (pg/spec env/env))))

(defn db
  "This is intended to be the public interface to the database"
  []
  @db-pool)

