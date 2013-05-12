# lein-expand-resource-paths

Leiningen middleware that uses
[clj-glob](https://github.com/jkk/clj-glob) to expand glob patterns in
`:resource-paths` so you can use leiningen alongside a different package
and/or dependency management tool.

### Motivation

Leiningen 1 supported glob patterns assigned to `:resource-path`, which
allowed us to add jars to a local directory e.g. `lib/jars`, set `:resource-path`
to `"lib/jars/*"`, and lein would add those jars to the classpath.

Leiningen 2 does not support this usage out of the box in order to
support the principle that builds should be repeatable (see
[https://github.com/technomancy/leiningen/wiki/Repeatability](https://github.com/technomancy/leiningen/wiki/Repeatability)).
While I agree with the principle, the choice to explicitly not support
glob expansion of `:resource-paths` meant that we could not upgrade to
Lein 2 as/is.

And so lein-expand-resource-paths was born.

### Disclaimer

The intent of this middleware is to support use of alternative package
and/or dependency management systems alongside Leiningen. This also
happens to support sticking random jars into random directories and
getting them show up on your classpath. Don't do this. You've been
warned.

## Usage

This will be released soon, but until it is you can use it locally as follows:

    git clone https://github.com/dchelimsky/lein-expand-resource-paths.git
    cd lein-expand-resource-paths
    lein install

Now add `lien-expand-resource-paths` to `:plugins` in `project.clj`, e.g.

    :plugins [[lein-expand-resource-paths "0.0.1-SNAPSHOT"]]

Add the directory that your other dependency manager drops its jars
into to `:resource-paths`, e.g.

    :resource-paths ["lib/jars/*"]

## License

Copyright © 2013 David Chelimsky

Distributed under the Eclipse Public License, the same as Clojure.
