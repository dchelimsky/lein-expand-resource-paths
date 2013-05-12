(ns lein-expand-resource-paths.plugin-test
  (:require [clojure.test :refer :all]
            [lein-expand-resource-paths.plugin :refer :all]))

(deftest expand-paths
  (is (= {:resource-paths []} (middleware {:resource-paths ["i-do-not-exist/*"]})))
  (is (= {:resource-paths ["./resources/a/1.txt" "./resources/a/2.txt"]} (middleware {:resource-paths ["resources/a/*"]})))
  (is (= {:resource-paths ["./resources/b/3.txt" "./resources/b/4.txt"]} (middleware {:resource-paths ["resources/b/*"]})))
  (is (= {:resource-paths ["./resources/a/1.txt" "./resources/a/2.txt" "./resources/b/3.txt" "./resources/b/4.txt"]} (middleware {:resource-paths ["resources/**/*"]}))))
