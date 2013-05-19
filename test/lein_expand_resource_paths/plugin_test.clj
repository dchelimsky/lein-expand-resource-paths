(ns lein-expand-resource-paths.plugin-test
  (:require [clojure.test :refer :all]
            [lein-expand-resource-paths.plugin :refer :all]))

(deftest includes-explicit-paths
  (is (= ["./test/resources/a/1.txt"] (:resource-paths (middleware {:resource-paths ["test/resources/a/1.txt"]}))))
  (is (= ["./test/resources/a/1.txt" "./test/resources/b/3.txt"] (:resource-paths (middleware {:resource-paths ["test/resources/a/1.txt" "test/resources/b/3.txt"]})))))

(deftest expands-glob-patterns
  (is (= ["./test/resources/a/1.txt" "./test/resources/a/2.txt"] (:resource-paths (middleware {:resource-paths ["test/resources/a/*"]}))))
  (is (= ["./test/resources/b/3.txt" "./test/resources/b/4.txt"] (:resource-paths (middleware {:resource-paths ["test/resources/b/*"]}))))
  (is (= ["./test/resources/a/1.txt" "./test/resources/a/2.txt" "./test/resources/b/3.txt" "./test/resources/b/4.txt"] (:resource-paths (middleware {:resource-paths ["test/resources/a/*" "test/resources/b/*"]}))))
  (is (= ["./test/resources/a/1.txt" "./test/resources/a/2.txt" "./test/resources/b/3.txt" "./test/resources/b/4.txt"] (:resource-paths (middleware {:resource-paths ["test/resources/**/*"]})))))

(deftest supports-mixed-explicit-and-globs
  (is (= ["./test/resources/a/1.txt" "./test/resources/a/2.txt" "./test/resources/b/3.txt"] (:resource-paths (middleware {:resource-paths ["test/resources/a/*" "test/resources/b/3.txt"]})))))

(deftest ignores-paths-that-do-not-exist
  (is (= [] (:resource-paths (middleware {:resource-paths ["i-do-not-exist/*"]})))))
