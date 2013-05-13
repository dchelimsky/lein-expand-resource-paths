(ns lein-expand-resource-paths.plugin-test
  (:require [clojure.test :refer :all]
            [lein-expand-resource-paths.plugin :refer :all]))

(deftest expand-paths
  (is (= {:resource-paths []} (middleware {:resource-paths ["i-do-not-exist/*"]})))
  (is (= {:resource-paths ["./test/resources/a/1.txt"]} (middleware {:resource-paths ["test/resources/a/1.txt"]})))
  (is (= {:resource-paths ["./test/resources/a/1.txt" "./test/resources/a/2.txt"]} (middleware {:resource-paths ["test/resources/a/*"]})))
  (is (= {:resource-paths ["./test/resources/b/3.txt" "./test/resources/b/4.txt"]} (middleware {:resource-paths ["test/resources/b/*"]})))
  (is (= {:resource-paths ["./test/resources/a/1.txt" "./test/resources/a/2.txt" "./test/resources/b/3.txt"]} (middleware {:resource-paths ["test/resources/a/*" "test/resources/b/3.txt"]})))
  (is (= {:resource-paths ["./test/resources/a/1.txt" "./test/resources/a/2.txt" "./test/resources/b/3.txt" "./test/resources/b/4.txt"]} (middleware {:resource-paths ["test/resources/**/*"]}))))
