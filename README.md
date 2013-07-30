# lein-expand-resource-paths

Leiningen 2 plugin with
[project middleware](https://github.com/technomancy/leiningen/blob/master/doc/PLUGINS.md#project-middleware)
that uses [clj-glob](https://github.com/jkk/clj-glob) to expand glob
patterns in `:resource-paths` before the classpath is generated.

## Motivation

Leiningen 1 expanded glob patterns assigned to `:resource-path`, which
allowed us to add jars to a local directory e.g. `lib/jars`, set `:resource-path`
to `"lib/jars/*"`, and lein would add those jars to the classpath.

Leiningen 2 does not support this usage out of the box in order to
support the principle that builds should be repeatable (see
[https://github.com/technomancy/leiningen/wiki/Repeatability](https://github.com/technomancy/leiningen/wiki/Repeatability)).
While I agree with the principle, the choice to explicitly not support
glob expansion of `:resource-paths` meant that we could not upgrade to
lein 2 as/is.

And so lein-expand-resource-paths was born.

### Disclaimer

The intent of this plugin is to support use of alternative package
and/or dependency management systems alongside Leiningen. This also
happens to support sticking random jars into random directories and
getting them show up on your classpath. Don't do this. You've been
warned.

## Usage

Add `lein-expand-resource-paths` to `:plugins` in `project.clj`, e.g.

    :plugins [[lein-expand-resource-paths "0.0.1"]]

Add the directory that your other dependency manager drops its jars
into to `:resource-paths`, e.g.

    :resource-paths ["lib/jars/*"]

## How it works

lein-expand-resource-paths takes advantage of Leiningen 2 support for
[autoloading middleware](https://github.com/technomancy/leiningen/blob/master/doc/PLUGINS.md#project-middleware)
packaged in plugins. Leiningen passes the project map to a function that
returns the same map, leaving the plugin to implement side effects and/or
modify the map before it used by any Leiningen tasks.

## License

Copyright © 2013 David Chelimsky

Distributed under the Eclipse Public License, the same as Clojure.
