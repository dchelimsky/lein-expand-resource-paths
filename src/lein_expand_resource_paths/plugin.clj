(ns lein-expand-resource-paths.plugin
  (:require [org.satta.glob :as glob]))

(defn- expand-paths [paths]
  (->> paths (mapcat glob/glob) (map str)))

(defn middleware
  "Autoloaded by Leiningen. Returns a new project map based on project,
with any glob patterns in the :resource-paths vector expanded."
  [project & args]
  (update-in project [:resource-paths] expand-paths))
