(ns lein-expand-resource-paths.plugin
  (:require [org.satta.glob :as glob]))

(defn- expand-paths [paths]
  (->> paths (mapcat glob/glob) (map str)))

(defn middleware [project & args]
  (update-in project [:resource-paths] expand-paths))
