(defproject {{name}} "0.1.7"
  :description "A CLJS project based on ken's template"
  :url "fixme.com"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 ;; web related
                 [ring/ring-core "1.1.8" :exclusions [[commons-codec] commons-io]]
                 [ring-edn "0.1.0"]
                 [ring/ring-json "0.2.0" :exclusions [cheshire]]
                 [aleph "0.3.0-beta16" :exclusions [cheshire]]
                 [compojure "1.1.5" :exclusions [org.clojure/tools.macro]]
                 [hiccup "1.0.3"]
                 [stencil "0.3.1" :exclusions [slingshot]]
                 [firealarm "0.1.2"]
                 [clj-stacktrace "0.2.5"] ;; strangely, this isn't picked up by firealarm's transients!
                 ;; postgres-related
                 [postgresql "9.1-901-1.jdbc4"]
                 [org.clojure/java.jdbc "0.3.0-alpha1"]
                 [c3p0/c3p0 "0.9.1.2"]
                 [honeysql "0.3.0"]
                 [migratus "0.6.0"]
                 ;; cljs-related
                 [prismatic/dommy "0.1.1"]
                 ;; general utilities
                 [org.slf4j/slf4j-log4j12 "1.6.4"] ;; clown shoes
                 [environ "0.3.0"]
                 [clj-time "0.5.0"]
                 [robert/bruce "0.7.1"]
                 [utilza "0.1.12"]]
  :profiles {:dev  {:dependencies [[org.clojure/tools.nrepl "0.2.3"]
                                   [clj-http "0.7.2"]
                                   [com.cemerick/piggieback "0.0.4"
                                    :exclusions [[org.clojure/google-closure-library-third-party]
                                                 org.clojure/clojurescript]]
                                   [cljsbuild "0.3.2"]]
                    :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}
             :server { :injections [(do (require '{{name}}.server)
                                        ({{name}}.server/-main))]}}
  :min-lein-version "2.1.3"
  ;; XXX HACK. add  a file .lein-env, with these keys: :db-host :db-name :db-user :db-pass
  :migratus ~(let [{:keys [db-host db-name db-user db-pass]} (-> ".lein-env" slurp read-string)]
               {:store :database
                :migration-dir "migrations"
                :db {:classname "com.postgresql.Driver"
                     :subprotocol "postgresql"
                     :subname (str "//" db-host "/" db-name)
                     :user db-user
                     :password db-pass}})
  :plugins [[lein-cljsbuild "0.3.2" ]
            [migratus-lein "0.1.0" :exclusions [org.clojure/clojure]]
            ]
  :cljsbuild {:builds
              [{:id "dev",
                :source-paths ["src-cljs"],
                :compiler
                {:pretty-print true,
                 :output-to "resources/public/js/main-dev.js",
                 :optimizations :whitespace},
                :jar true}
               {:id "production",
                :source-paths ["src-cljs"],
                :compiler
                {:pretty-print false,
                 :output-to "resources/public/js/main.js",
                 :externs ["externs/jquery-1.8.js"
                           "externs/uikit.js"
                           "externs/flot.js"
                           "externs/twitter-bootstrap.js"],
                 :optimizations :advanced},
                :jar true}]}
  :aliases {
            ;; simple, for thrashing the server
            "tr" ["with-profile" "user,dev,server"
                  "trampoline" "repl" ":headless"]
            ;; use this for development purposes. runs cljsbuild in bg.
            "autorepl" ["with-profile" "user,dev,server"
                        "pdo"
                        "cljsbuild" "auto" "dev,"
                        "repl" ":headless"]
            ;; for production, don't need cljsbuild auto in this one,
            ;; and better trampoline for memory usage.
            "prod" ["with-profile" "user,server"
                    "do"
                    "trampoline" "repl" ":headless"]}
  )
