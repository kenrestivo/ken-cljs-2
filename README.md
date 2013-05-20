# ken-cljs-2

A Leiningen template for a CLJS web app with a Postgres back-end.

## Usage

Create a project, set your db parameters in .lein-env, and go for it.

As a hack to work around the fact that there is a mustache template in here, and we're using mustache inside this lein template which wipes out my variables, you'll have to manualy replace the \{\{\{ foo \}\}\} inside main.template with proper curly escape tage (i.e. without the backslashes).


"lein autorepl" to get cljs and the server and a repl.

"lein migrate" to migrate your db schema.

"lein prod" to get into production mode.

This is still a work in progress, particularly in terms of documentation, but I'm using this for projects at the moment. It will evolve though, as I move forward, this will too. I'm using the cljs, postgres, and aleph/ring/compojure stuff and it's pretty solid.

The bootstrap parts of it are particularly rough though; I haven't even started with that.

If you're using env via properties file or shell environment, you can remove the .lein-env file.

Enjoy.

## License

Copyright © 2012-2013 ken restivo <ken@restivo.org>

Distributed under the Eclipse Public License, the same as Clojure.
